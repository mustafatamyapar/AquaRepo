package com.example.aquaassistant.zulal;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.FishPage;
import com.example.aquaassistant.mustafa.PlantsPage;
import com.example.aquaassistant.mustafa.SnailPage;
import com.example.aquaassistant.zeynep.EditTankActivity;
import com.example.aquaassistant.zeynep.RemoveCreatureActivity;
import com.example.aquaassistant.zeynep.TanksPageActivity;
import com.r0adkll.slidr.Slidr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Creaturesample extends AppCompatActivity {
    //variables
    ImageView creatureImage;
    TextView nameOfCreature;
    TextView typeOFCreature;
    TextView nameOfTank;
    Button removeCreature;
    Button goEncyclopedia;
    Button changeName;
    String creatureName;
    String creatureId;
    int imageNo;
    String tname;
    String type;
    String type2;
    String tankId;
    byte[] emptyByteArray;
    Bitmap selectedImage;
    AlertDialog.Builder inform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creaturesample);
        Slidr.attach(this);
        creatureImage = findViewById(R.id.creature_image);
        nameOfCreature = findViewById(R.id.creature_name);
        removeCreature = findViewById(R.id.remove_creature);
        goEncyclopedia = findViewById(R.id.go_encyclopedia);
        changeName = findViewById(R.id.change_name);
        inform = new AlertDialog.Builder(Creaturesample.this);
        //get the info from gridVewAdapter
        Intent intent = getIntent();
        creatureId = intent.getStringExtra("creatureId");
        //create database
        SQLiteDatabase sqLiteDatabase = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM creatures WHERE id = ?", new String[]{creatureId});
        while (cursor.moveToNext()) {
            creatureName = cursor.getString(cursor.getColumnIndex("creaturename"));
            imageNo = cursor.getColumnIndex("image");
            tankId = cursor.getString(cursor.getColumnIndex("tankId"));
            if (cursor.getBlob(imageNo) != null) {
                byte[] bytes = cursor.getBlob(imageNo);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                creatureImage.setImageBitmap(bitmap);
            }
        }

        cursor.close();
        nameOfCreature.setText(creatureName);
        Bitmap empty = BitmapFactory.decodeResource(Creaturesample.this.getResources(), R.drawable.emptypicture);
        Bitmap smallImage = makeSmallerImage(empty, 500);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        emptyByteArray = outputStream.toByteArray();
        showInfo();

    }

    public void changeName(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Creaturesample.this);
        alert.setTitle("Change name");
        alert.setMessage("Please write the creature name:");
        final EditText nameOfCreature = new EditText(Creaturesample.this);
        nameOfCreature.setInputType(InputType.TYPE_CLASS_TEXT);
        alert.setView(nameOfCreature);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tname = nameOfCreature.getText().toString();
                try {
                    SQLiteDatabase changeNameDb = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
                    String switchName = "UPDATE creatures SET creaturename = ? WHERE id = " + creatureId;
                    SQLiteStatement changeNameStatement = changeNameDb.compileStatement(switchName);
                    changeNameStatement.bindString(1, tname);
                    changeNameStatement.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent7 = new Intent(Creaturesample.this, Creature.class);
                intent7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent7);

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    public void goEncyclopedia(View view) {
        try {
            SQLiteDatabase goEncycDb = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
            Cursor findType = goEncycDb.rawQuery("SELECT * FROM creatures WHERE id = ?", new String[]{creatureId});
            while (findType.moveToNext()) {
                type = findType.getString(findType.getColumnIndex("type"));
            }
            findType.close();
            if (type.matches("fish")) {
                Intent intent1 = new Intent(Creaturesample.this, FishPage.class);
                startActivity(intent1);
            } else if (type.matches("plant")) {
                Intent intent2 = new Intent(Creaturesample.this, PlantsPage.class);
                startActivity(intent2);
            } else if (type.matches("other")) {
                Intent intent3 = new Intent(Creaturesample.this, SnailPage.class);
                startActivity(intent3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCreature(View view) {
        final SQLiteDatabase removeCreature = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
        final SQLiteDatabase removeTank = Creaturesample.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
        AlertDialog.Builder remove = new AlertDialog.Builder(Creaturesample.this);
        remove.setTitle("Remove Creature");
        remove.setMessage("Do you want to remove this creature from your tank?");
        remove.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Cursor tankCursor = removeTank.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                    String numOfFish = "";
                    String numOFPlant = "";
                    String numOfOther = "";
                    while (tankCursor.moveToNext()) {
                        numOfFish = tankCursor.getString(tankCursor.getColumnIndex("numoffish"));
                        numOfOther = tankCursor.getString(tankCursor.getColumnIndex("numofother"));
                        numOFPlant = tankCursor.getString(tankCursor.getColumnIndex("numofplant"));
                    }
                    tankCursor.close();

                    Cursor typeFind = removeCreature.rawQuery("SELECT * FROM creatures WHERE id = ?", new String[]{creatureId});
                    while (typeFind.moveToNext()) {
                        type2 = typeFind.getString(typeFind.getColumnIndex("type"));
                    }
                    typeFind.close();
                    if (type2.matches("fish")) {
                        //decrease the number of fish in the tank
                        String decrease = "UPDATE tanks SET numoffish= ? WHERE id  = " + Integer.parseInt(tankId);
                        SQLiteStatement decreaseFishNum = removeTank.compileStatement(decrease);
                        final String newFishNum = String.valueOf(Integer.parseInt(numOfFish) - 1);
                        decreaseFishNum.bindString(1, newFishNum);
                        decreaseFishNum.execute();
                    }
                    //if creature is a plant
                    else if (type2.matches("plant")) {
                        //decrease the number of plant in the tank
                        String decrease = "UPDATE tanks SET numofplant= ? WHERE id  = " + Integer.parseInt(tankId);
                        SQLiteStatement decreasePlantNum = removeTank.compileStatement(decrease);
                        final String newPlantNum = String.valueOf(Integer.parseInt(numOFPlant) - 1);
                        decreasePlantNum.bindString(1, newPlantNum);
                        decreasePlantNum.execute();
                    }
                    //if creature is one of the others
                    else if (type2.matches("other")) {
                        //decrease the number of fish in the tank
                        String decrease = "UPDATE tanks SET numofother= ? WHERE id  = " + Integer.parseInt(tankId);
                        SQLiteStatement decreaseOtherNum = removeTank.compileStatement(decrease);
                        final String newOtherNum = String.valueOf(Integer.parseInt(numOfOther) - 1);
                        decreaseOtherNum.bindString(1, newOtherNum);
                        decreaseOtherNum.execute();
                    }
                    String delete = "DELETE FROM creatures WHERE id = " + creatureId;
                    SQLiteStatement deleteStatement = removeCreature.compileStatement(delete);
                    deleteStatement.execute();
                    Intent intent6 = new Intent(Creaturesample.this, Creature.class);
                    intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent6);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        remove.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        remove.show();

    }

    public void imageClicked(View view) {
        AlertDialog.Builder imageChange = new AlertDialog.Builder(Creaturesample.this);
        imageChange.setTitle("Change photo");
        imageChange.setMessage("Change the profile picture of the creature:");
        imageChange.setPositiveButton("Change Photo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int imageIndex;
                try {
                    if (ContextCompat.checkSelfPermission(Creaturesample.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Creaturesample.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentToGallery, 2);
                        //set another alert builder to show the informing message and insert the other creature into database
                        inform.setTitle("Done!");
                        inform.setMessage("Picture is added.");
                        inform.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //put the image into the byteArray to save in the database
                                Bitmap smallImage = makeSmallerImage(selectedImage, 500);
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                                byte[] byteArray = outputStream.toByteArray();
                                try {
                                    SQLiteDatabase changeImage = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
                                    String update = "UPDATE creatures SET image = ? WHERE id = " + creatureId;
                                    SQLiteStatement updateStatement = changeImage.compileStatement(update);
                                    updateStatement.bindBlob(1, byteArray);
                                    updateStatement.execute();
                                    Intent intent8 = new Intent(Creaturesample.this, Creature.class);
                                    intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent8);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        inform.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        imageChange.setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder sure = new AlertDialog.Builder(Creaturesample.this);
                sure.setTitle("Remove creature");
                sure.setMessage("Are you sure?");
                sure.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            SQLiteDatabase remove = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
                            String removeImage = "UPDATE creatures SET image = ? WHERE id = " + creatureId;
                            SQLiteStatement removeStatement = remove.compileStatement(removeImage);
                            removeStatement.bindBlob(1, emptyByteArray);
                            removeStatement.execute();
                            Intent intent9 = new Intent(Creaturesample.this, Creature.class);
                            intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent9);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }
                });
                sure.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                sure.show();
                dialog.cancel();
            }
        });
        imageChange.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        imageChange.show();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // go to the gallery if the permission granted
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery, 2);
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
                if (Build.VERSION.SDK_INT >= 28) {
                    assert imageData != null;
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        inform.show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {
        //get the size of image
        int width = image.getWidth();
        int height = image.getHeight();
        //determine the ratio to keep it same
        float bitmapRatio = (float) width / (float) height;
        //if the image is horizontal
        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
            //if the image is vertical
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public void goTank(View view){
        Intent intentGo = new Intent (Creaturesample.this, TanksPageActivity.class);
        startActivity(intentGo);
    }
    String type3;
    String nameTank;
    public void showInfo() {
        nameOfTank = findViewById(R.id.nameOfTank);
        typeOFCreature = findViewById(R.id.nameOfType);
        SQLiteDatabase getType = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
        Cursor cursortype = getType.rawQuery("SELECT * FROM creatures WHERE id = ?", new String[] {creatureId});
        while(cursortype.moveToNext()){
            type3 = cursortype.getString(cursortype.getColumnIndex("type"));
        }
        getType.close();

        SQLiteDatabase tanksDat = Creaturesample.this.openOrCreateDatabase("Tanks",MODE_PRIVATE,null);
        Cursor cursortname = tanksDat.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
        while (cursortname.moveToNext()){
            nameTank = cursortname.getString(cursortname.getColumnIndex("tankname"));
        }
        nameOfTank.setText("NAME OF TANK: " + nameTank);
        typeOFCreature.setText("TYPE OF CREATURE: " + type3);
    }

}

