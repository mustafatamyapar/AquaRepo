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
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.aquaassistant.R;
import com.example.aquaassistant.sena.ToDoListPage;
import com.r0adkll.slidr.Slidr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * EditTankActivity Class - the activity that provides buttons to edit tank
 * by changing name, adding creature and removing creature
 * @author Zeynep Berber
 * @version 1.0 (May 18, 2020) - completed
 */
public class EditTankActivity extends AppCompatActivity {
    TextView editTank;
    Button changeName, addCreature ,removeCreature ;
    String tName ,tankId ,currentFishNum ,currentPlantNum , currentOtherCreNum;
    SQLiteDatabase tanksDatabase;
    Bitmap selectedImage;
    AlertDialog choose;
    SQLiteDatabase creaturesDatabase;
    AlertDialog.Builder inform;
    byte[] emptyByteArray;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_edit_tank);
        Intent intent = getIntent();
        tankId = intent.getStringExtra("tankId");
        editTank = findViewById(R.id.editTankText);
        changeName = findViewById(R.id.changeName);
        addCreature = findViewById(R.id.addCreature);
        removeCreature = findViewById(R.id.removeCreature);

        editTank.setText("Edit Tank");
        changeName.setText("Change The Tank Name");
        addCreature.setText("Add New Creature");
        removeCreature.setText("Remove Creature");

        creaturesDatabase = EditTankActivity.this.openOrCreateDatabase("Creatures" ,MODE_PRIVATE, null);
        creaturesDatabase.execSQL("CREATE TABLE IF NOT EXISTS creatures (id INTEGER PRIMARY KEY , type VARCHAR , creaturename VARCHAR, tankId VARCHAR , image BLOB)");
        inform = new AlertDialog.Builder(EditTankActivity.this);

        Bitmap empty = BitmapFactory.decodeResource(EditTankActivity.this.getResources() , R.drawable.emptypicture);
        Bitmap smallImage = makeSmallerImage(empty,300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        emptyByteArray = outputStream.toByteArray();
    }
    /**
     * This method changes the tank name by updating the tanks database and notifs database
     * @param view ensures that this method used by a view
     */
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
                    String updateNotif = "UPDATE notifs SET tankname = ? WHERE id = " + tankId ;
                    //existing to-do list elements are also updated
                    SQLiteStatement updateNotifStatement = ToDoListPage.notifDatabase.compileStatement(updateNotif);
                    updateNotifStatement.bindString(1, tName);
                    updateNotifStatement.execute();
                    //return the tanks page
                    Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    /**
     * This method is being called from the "Remove Creature" button and
     * directs the user to the removeCreatureActivity page
     * @param view ensures that this method used by a view
     */
    public void removeCreature(View view){
        Intent intent = new Intent(EditTankActivity.this , RemoveCreatureActivity.class);
        intent.putExtra("tankId", tankId);
        startActivity(intent);
    }
    @SuppressLint("SetTextI18n")
    /**
     * This method is being called from the "Add Creature" button and
     * opens an alertDialog that shows the options - fish,plant and other-
     * to choose one of them and add it to tank
     * @param view ensures that this method used by a view
     */
    public void addCreature(View view){
        //show the window to choose a creature to be added
        AlertDialog.Builder chooseCreature = new AlertDialog.Builder(EditTankActivity.this);
        chooseCreature.setTitle("Choose Creature");
        chooseCreature.setView(R.layout.activity_choosecreature);
        choose = chooseCreature.create();
        if (!EditTankActivity.this.isFinishing()) {
            choose.show();
        }
        else{
            choose.dismiss();
        }
    }
    /**
     * This method is being called from the "Fish" button which is in the alertDialog
     * that addCrature method created and adds a "fish" type creature in the creature database by
     * getting the name and picture of the fish and
     * increases the current fish count in the tank by updating tanks database
     * If user does not want to add a picture, the image of the fish is arranged as a default picture
     * @param view ensures that this method used by a view
     */
    public void addFishBut(View view){
        AlertDialog.Builder setName = new AlertDialog.Builder(EditTankActivity.this);
        setName.setTitle("Fish Name");
        setName.setMessage("Please enter the fish name");

        final EditText fName = new EditText( EditTankActivity.this);
        fName.setInputType(InputType.TYPE_CLASS_TEXT);
        setName.setView(fName);
        setName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get the fish name
                final String fishName = fName.getText().toString();
                //set a builder to get image
                AlertDialog.Builder setImage = new AlertDialog.Builder(EditTankActivity.this);
                setImage.setTitle("Fish Image");
                setImage.setMessage("Do you want to add picture of fish?");
                setImage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            // get the gallery permission from the user
                            if (ContextCompat.checkSelfPermission(EditTankActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(EditTankActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            } else {
                                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intentToGallery, 2);
                            }

                                //set another alert builder to show the informing message and insert the fish into database
                                inform.setTitle("Done!");
                                inform.setMessage("Picture is added.");
                                inform.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //put the image into the byteArray to save in the database
                                        Bitmap smallImage = makeSmallerImage(selectedImage,300);
                                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                                        byte[] byteArray = outputStream.toByteArray();
                                        try {
                                            //get the tankname and fish number that fish belongs to
                                            tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                                            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                                            while (cursor.moveToNext()) {
                                                currentFishNum = cursor.getString(cursor.getColumnIndex("numoffish"));
                                            }
                                            cursor.close();

                                            //put the info into the fish database
                                            String sqlString = "INSERT INTO creatures ( type , creaturename , tankId , image) VALUES (?, ?,?, ?) ";
                                            SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                                            addStatement.bindString(1,"fish");
                                            addStatement.bindString(2, fishName);
                                            addStatement.bindString(3, tankId);
                                            addStatement.bindBlob(4, byteArray);
                                            addStatement.execute();

                                            //update the number of fish in the tank
                                            String addFish = "UPDATE tanks SET numoffish= ? WHERE id = "  + tankId ;
                                            SQLiteStatement addFishStatement = tanksDatabase.compileStatement(addFish);
                                            final String newFishNum = String.valueOf(Integer.parseInt(currentFishNum)+1);
                                            addFishStatement.bindString(1 , newFishNum);
                                            addFishStatement.execute();

                                            //return the tanks page
                                            choose.dismiss();
                                            EditTankActivity.this.finish();
                                            Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);

                                        } catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                }
                });
                setImage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            //get the tankname that fish belongs to
                            tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                            while (cursor.moveToNext()) {
                                currentFishNum = cursor.getString(cursor.getColumnIndex("numoffish"));
                            }
                            cursor.close();


                            //put the info into the fish database
                            String sqlString = "INSERT INTO creatures ( type, creaturename , tankId ,image) VALUES (?, ?,? ,? ) ";
                            SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                            addStatement.bindString(1,"fish");
                            addStatement.bindString(2, fishName);
                            addStatement.bindString(3, tankId);
                            addStatement.bindBlob(4,emptyByteArray);
                            addStatement.execute();

                            //update the number of fish in the tank
                            String addFish = "UPDATE tanks SET numoffish= ? WHERE id = "  + tankId ;
                            SQLiteStatement addFishStatement = tanksDatabase.compileStatement(addFish);
                            final String newFishNum = String.valueOf( Integer.parseInt(currentFishNum) + 1 );
                            addFishStatement.bindString(1 , newFishNum);
                            addFishStatement.execute();

                            //return the tanks page
                            Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } catch(Exception e){
                            e.printStackTrace();
                        }
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
    /**
     * This method is being called from the "Plant" button which is in the alertDialog
     * that addCrature method created and adds a "plant" type creature in the creature database by
     * getting the name and picture of the plant and
     * increases the current plant count in the tank by updating tanks database
     * If user does not want to add a picture, the image of the plant is arranged as a default picture
     * @param view ensures that this method used by a view
     */
    public void addPlantBut(View view){
        AlertDialog.Builder setName = new AlertDialog.Builder(EditTankActivity.this);
        setName.setTitle("Plant Name");
        setName.setMessage("Please enter the plant name");

        final EditText pName = new EditText( EditTankActivity.this);
        pName.setInputType(InputType.TYPE_CLASS_TEXT);
        setName.setView(pName);
        setName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get the plant name
                final String plantName = pName.getText().toString();
                //set a builder to get image
                AlertDialog.Builder setImage = new AlertDialog.Builder(EditTankActivity.this);
                setImage.setTitle("Plant Image");
                setImage.setMessage("Do you want to add picture of plant?");
                setImage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get the gallery permission from the user
                        if (ContextCompat.checkSelfPermission(EditTankActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(EditTankActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intentToGallery, 2);
                            //set another alert builder to show the informing message and insert the plant into database
                            inform = new AlertDialog.Builder(EditTankActivity.this);
                            inform.setTitle("Done!");
                            inform.setMessage("Picture is added.");
                            inform.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //put the image into the byteArray to save in the database
                                    Bitmap smallImage = makeSmallerImage(selectedImage,300);
                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                                    byte[] byteArray = outputStream.toByteArray();
                                    try {
                                        //get the tankname and plant number that plant belongs to
                                        tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                                        Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                                        while (cursor.moveToNext()) {
                                            currentPlantNum = cursor.getString(cursor.getColumnIndex("numofplant"));
                                        }
                                        cursor.close();

                                        //put the info into the plant database
                                        String sqlString = "INSERT INTO creatures ( type, creaturename , tankId , image) VALUES (?, ?,?,?) ";
                                        SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                                        addStatement.bindString(1,"plant");
                                        addStatement.bindString(2, plantName);
                                        addStatement.bindString(3, tankId);
                                        addStatement.bindBlob(4, byteArray);
                                        addStatement.execute();

                                        //update the number of plant in the tank
                                        String addPlant = "UPDATE tanks SET numofplant= ? WHERE id = "  + tankId ;
                                        SQLiteStatement addPlantStatement = tanksDatabase.compileStatement(addPlant);
                                        final String newPlantNum = String.valueOf(Integer.parseInt(currentPlantNum) + 1);
                                        addPlantStatement.bindString(1 , newPlantNum);
                                        addPlantStatement.execute();

                                        //return the tanks page
                                        choose.dismiss();
                                        EditTankActivity.this.finish();
                                        Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);

                                    } catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                            inform.show();
                        }
                    }
                });
                setImage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            //get the tankname that plant belongs to
                            tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                            while (cursor.moveToNext()) {
                                currentPlantNum = cursor.getString(cursor.getColumnIndex("numofplant"));
                            }
                            cursor.close();

                            //put the info into the plant database
                            String sqlString = "INSERT INTO creatures ( type,  creaturename , tankId , image) VALUES ( ?,?,?,? ) ";
                            SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                            addStatement.bindString(1,"plant");
                            addStatement.bindString(2, plantName);
                            addStatement.bindString(3, tankId);
                            addStatement.bindBlob(4, emptyByteArray);
                            addStatement.execute();

                            //update the number of plant in the tank
                            String addPlant = "UPDATE tanks SET numofplant= ? WHERE id = "  + tankId ;
                            SQLiteStatement addPlantStatement = tanksDatabase.compileStatement(addPlant);
                            final String newPlantNum = String.valueOf( Integer.parseInt(currentPlantNum) + 1 );
                            addPlantStatement.bindString(1 , newPlantNum);
                            addPlantStatement.execute();

                            //return the tanks page
                            Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } catch(Exception e){
                            e.printStackTrace();
                        }
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
    /**
     * This method is being called from the "Other" button which is in the alertDialog
     * that addCrature method created and adds a "other" type creature in the creature database by
     * getting the name and picture of the other creature and
     * increases the current other creatures count in the tank by updating tanks database
     * If user does not want to add a picture, the image of the creature is arranged as a default picture
     * @param view ensures that this method used by a view
     */
    public void addOtherBut(View view){
        AlertDialog.Builder setName = new AlertDialog.Builder(EditTankActivity.this);
        setName.setTitle("Creature Name");
        setName.setMessage("Please enter the creature name");

        final EditText oName = new EditText( EditTankActivity.this);
        oName.setInputType(InputType.TYPE_CLASS_TEXT);
        setName.setView(oName);
        setName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get the creature name
                final String otherName = oName.getText().toString();
                //set a builder to get image
                AlertDialog.Builder setImage = new AlertDialog.Builder(EditTankActivity.this);
                setImage.setTitle("Creature Image");
                setImage.setMessage("Do you want to add picture of creature?");
                setImage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get the gallery permission from the user
                        if (ContextCompat.checkSelfPermission(EditTankActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(EditTankActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intentToGallery, 2);
                            //set another alert builder to show the informing message and insert the other creature into database
                            AlertDialog.Builder inform = new AlertDialog.Builder(EditTankActivity.this);
                            inform.setTitle("Done!");
                            inform.setMessage("Picture is added.");
                            inform.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //put the image into the byteArray to save in the database
                                    Bitmap smallImage = makeSmallerImage(selectedImage,300);
                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                                    byte[] byteArray = outputStream.toByteArray();
                                    try {
                                        //get the tankname and other creatures number that creature belongs to
                                        tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                                        Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                                        while (cursor.moveToNext()) {
                                            currentOtherCreNum = cursor.getString(cursor.getColumnIndex("numofother"));
                                        }
                                        cursor.close();

                                        //put the info into the others database
                                        String sqlString = "INSERT INTO creatures (type, creaturename , tankId , image) VALUES (?, ?,?,?) ";
                                        SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                                        addStatement.bindString(1,"other");
                                        addStatement.bindString(2, otherName);
                                        addStatement.bindString(3, tankId);
                                        addStatement.bindBlob(4, byteArray);
                                        addStatement.execute();

                                        //update the number of other creatures in the tank
                                        String addOther = "UPDATE tanks SET numofother= ? WHERE id = "  + tankId ;
                                        SQLiteStatement addOtherStatement = tanksDatabase.compileStatement(addOther);
                                        final String newOtherNum = String.valueOf(Integer.parseInt(currentOtherCreNum) + 1);
                                        addOtherStatement.bindString(1 , newOtherNum);
                                        addOtherStatement.execute();

                                        //return the tanks page
                                        choose.dismiss();
                                        EditTankActivity.this.finish();
                                        Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);

                                    } catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                            inform.show();
                        }
                    }
                });
                setImage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            //get the tankname that creature belongs to
                            tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks" , MODE_PRIVATE ,null);
                            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});
                            while (cursor.moveToNext()) {
                                currentOtherCreNum = cursor.getString(cursor.getColumnIndex("numofother"));
                            }
                            cursor.close();

                            //put the info into the others database
                            String sqlString = "INSERT INTO creatures ( type, creaturename , tankId ,image) VALUES ( ?,?,?,?) ";
                            SQLiteStatement addStatement = creaturesDatabase.compileStatement(sqlString);
                            addStatement.bindString(1, "other");
                            addStatement.bindString(2, otherName);
                            addStatement.bindString(3, tankId);
                            addStatement.bindBlob(4, emptyByteArray);
                            addStatement.execute();

                            //update the number of other creature in the tank
                            String addOther = "UPDATE tanks SET numofother= ? WHERE id = "  + tankId ;
                            SQLiteStatement addOtherStatement = tanksDatabase.compileStatement(addOther);
                            final String newOtherNum = String.valueOf( Integer.parseInt(currentOtherCreNum) + 1 );
                            addOtherStatement.bindString(1 , newOtherNum);
                            addOtherStatement.execute();

                            //return the tanks page
                            Intent intent = new Intent(EditTankActivity.this, TanksPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } catch(Exception e){
                            e.printStackTrace();
                        }
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
    /**
     * This method opens the gallery is the permission is granted from the user
     * @param requestCode is the code that we determine for the permission
     * @param permissions is the permissions that application wants
     * @param grantResults is the result of the granting
     */
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
    /**
     * This method gets the image that user have chosen from the gallery
     * @param requestCode is the code that we determine for the permission
     * @param resultCode is whether the user chose an image or not
     * @param grantResults data the image that user have chosen
     */
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //get the selected image when the galery open
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    assert imageData != null;
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
        inform.show();
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * This method makes the images smaller to ensure that it does not lead to a problem in database
     * @param image is the image that will be made smaller
     * @param maximumSize is the maximum size of image
     * */
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
        return Bitmap.createScaledBitmap(image,width,height,true);
    }

}
