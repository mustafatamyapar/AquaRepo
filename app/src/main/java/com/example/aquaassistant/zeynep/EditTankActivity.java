package com.example.aquaassistant.zeynep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.AquariumContainer;
import com.example.aquaassistant.zulal.Fish;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class EditTankActivity extends AppCompatActivity {
    TextView editTank;
    Button changeName ;
    Button addCreature;
    Button removeCreature;
    AquariumContainer aquariumContainer;
    String tankName;
    SQLiteDatabase tanksDatabase;
    String tName;
    String tankId;
    Bitmap selectedImage;
    AlertDialog.Builder chooseCreature;
    Boolean fishAdded;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tank);
        Intent intent = getIntent();
        tankId = intent.getStringExtra("tankId");
        aquariumContainer = (AquariumContainer) intent.getSerializableExtra("tank");
        editTank = findViewById(R.id.editTankText);
        changeName = findViewById(R.id.changeName);
        addCreature = findViewById(R.id.addCreature);
        removeCreature = findViewById(R.id.removeCreature);

        editTank.setText("Edit Tank");
        changeName.setText("Change The Tank Name");
        addCreature.setText("Add a New Creature to Tank");
        removeCreature.setText("Remove a Creature From the Tank");
        fishAdded = false;

    }

    public void changeTankName(View view){
        AlertDialog.Builder changeName = new AlertDialog.Builder(EditTankActivity.this);
        changeName.setTitle("Change The Tank Name");
        changeName.setMessage("Please enter the new name.");
        //get the new tankname from user
        final EditText newName = new EditText(EditTankActivity.this);
        newName.setInputType(InputType.TYPE_CLASS_TEXT);
        changeName.setView(newName);
        changeName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tName = newName.getText().toString();
                try {
                    //open the tanks database
                    tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
                    //update the tank name
                    String changeName = "UPDATE tanks SET tankname= ? WHERE id = " + tankId ;
                    SQLiteStatement changeStatement = tanksDatabase.compileStatement(changeName);
                    changeStatement.bindString(1, tName);
                    changeStatement.execute();
                    //return the tanks page
                    Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e){
                    System.out.println( "Error = " + e);
                }
            }
        });
        changeName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        changeName.show();
    }
    public void removeCreature(View view){


    }
    @SuppressLint("SetTextI18n")
    public void addCreature(View view){
        chooseCreature = new AlertDialog.Builder(EditTankActivity.this);
        chooseCreature.setTitle("Choose Creature");
        chooseCreature.setView(R.layout.activity_choosecreature);
        chooseCreature.show();

    }
    public void addFishBut(View view){

        final SQLiteDatabase fishDatabase = EditTankActivity.this.openOrCreateDatabase("Fish", MODE_PRIVATE,null);
        fishDatabase.execSQL("CREATE TABLE IF NOT EXISTS fish (id INTEGER PRIMARY KEY , fishname VARCHAR, tankname VARCHAR , image BLOB)");
        AlertDialog.Builder setName = new AlertDialog.Builder(EditTankActivity.this);
        setName.setTitle("Fish Name");
        setName.setMessage("Please enter the fish name");



        final EditText fName = new EditText( EditTankActivity.this);
        fName.setInputType(InputType.TYPE_CLASS_TEXT);
        setName.setView(fName);
        setName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String fishName = fName.getText().toString();
                AlertDialog.Builder setImage = new AlertDialog.Builder(EditTankActivity.this);
                setImage.setTitle("Fish Image");
                setImage.setMessage("Do you want to add picture of fish?");
                setImage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // get the gallery permission from the user
                            if (ContextCompat.checkSelfPermission(EditTankActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(EditTankActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            } else {
                                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intentToGallery, 2);
                            }

                            //get the tankname that fish belongs to
                            tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                            Cursor cursor = tanksDatabase.rawQuery("SELECT tankname FROM tanks WHERE id = ?", new String[]{tankId});
                            while (cursor.moveToNext()) {
                                tankName = cursor.getString(cursor.getColumnIndex("tankname"));
                            }
                            cursor.close();

                            //put the image into the byteArray to save in the database
                            Bitmap smallImage = makeSmallerImage(selectedImage,300);
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                            byte[] byteArray = outputStream.toByteArray();

                            //put the info into the fish database
                            String sqlString = "INSERT INTO fish ( fishname , tankname , image) VALUES ( ?,?,?) ";
                            SQLiteStatement addStatement = fishDatabase.compileStatement(sqlString);
                            addStatement.bindString(1, fishName);
                            addStatement.bindString(2, tankName);
                            addStatement.bindBlob(3, byteArray);
                            addStatement.execute();

                            //update the number of fish in the tank
                            String addFish = "UPDATE tanks SET numoffish= ? WHERE id = "  + tankId ;
                            SQLiteStatement addFishStatement = tanksDatabase.compileStatement(addFish);
                            addFishStatement.bindString(1 , "1");
                            addFishStatement.execute();


                            //return the tanks page
                            Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                setImage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sqlString = "INSERT INTO fish ( fishname , tankname , image) VALUES ( ?,?,?) ";
                        SQLiteStatement addStatement = fishDatabase.compileStatement(sqlString);
                        addStatement.bindString(1, fishName);
                        addStatement.bindString(2, tankName);
                        addStatement.bindBlob(3, null);
                        addStatement.execute();
                        dialog.cancel();
                    }
                });
                setImage.show();
            }
        });
        setName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        setName.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // go to the gallery if the permission granted
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //get the selected image when the galery open
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            try {
                if ( imageData == null ){System.out.println("NULLL");}
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                }
                else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }
}
