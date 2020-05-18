package com.example.aquaassistant.zulal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.FishPage;
import com.example.aquaassistant.mustafa.PlantsPage;
import com.example.aquaassistant.mustafa.SnailPage;
import com.example.aquaassistant.zeynep.RemoveCreatureActivity;
import com.r0adkll.slidr.Slidr;

public class Creaturesample extends AppCompatActivity {
    //variables
    ImageView creatureImage;
    TextView nameOfCreature;
    Button removeCreature;
    Button goEncyclopedia;
    Button changeName;
    String creatureName;
    String creatureId;
    int imageNo;
    String tname;
    String type;
    String type2;
    String tankName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creaturesample);
        Slidr.attach(this);
        creatureImage =findViewById(R.id.creature_image);
        nameOfCreature = findViewById(R.id.creature_name);
        removeCreature =findViewById(R.id.remove_creature);
        goEncyclopedia = findViewById(R.id.go_encyclopedia);
        changeName = findViewById(R.id.change_name);
        //get the info from gridVewAdapter
        Intent intent = getIntent();
        creatureId = intent.getStringExtra("creatureId");
        //create database
        SQLiteDatabase sqLiteDatabase = Creaturesample.this.openOrCreateDatabase("Creatures" ,MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM creatures WHERE id = ?", new String[] {creatureId});
        while(cursor.moveToNext()){
            creatureName = cursor.getString(cursor.getColumnIndex("creaturename"));
            imageNo = cursor.getColumnIndex("image");
            tankName = cursor.getString(cursor.getColumnIndex("tankname"));
                    if (cursor.getBlob(imageNo) != null) {
                        byte[] bytes = cursor.getBlob(imageNo);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        creatureImage.setImageBitmap(bitmap);
                    }
                }

        cursor.close();
        nameOfCreature.setText(creatureName);

    }
    public void changeName(View view){
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
                try{
                    SQLiteDatabase changeNameDb = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
                    String switchName = "UPDATE creatures SET creaturename = ? WHERE id = "+ creatureId;
                    SQLiteStatement changeNameStatement = changeNameDb.compileStatement(switchName);
                    changeNameStatement.bindString(1,tname);
                    changeNameStatement.execute();
                }catch(Exception e){
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
    public void goEncyclopedia(View view){
        try{
            SQLiteDatabase goEncycDb = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
            Cursor findType = goEncycDb.rawQuery("SELECT * FROM creatures WHERE id = ?", new String [] {creatureId});
            while(findType.moveToNext()){
                type = findType.getString(findType.getColumnIndex("type"));
            }
            findType.close();
            if(type.matches("fish")){
                Intent intent1 = new Intent( Creaturesample.this, FishPage.class);
                startActivity(intent1);
            }
            else if( type.matches("plant")){
                Intent intent2 = new Intent ( Creaturesample.this, PlantsPage.class);
                startActivity(intent2);
            }
            else if (type.matches("other")){
                Intent intent3 = new Intent (Creaturesample.this, SnailPage.class);
                startActivity(intent3);
            }
        }catch( Exception e){
            e.printStackTrace();
        }
    }
    public void removeCreature( View view){
        final SQLiteDatabase removeCreature = Creaturesample.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
        final SQLiteDatabase removeTank = Creaturesample.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
        AlertDialog.Builder remove = new AlertDialog.Builder(Creaturesample.this);
        remove.setTitle("Remove Creature");
        remove.setMessage("Do you want to remove this creature from your tank?");
        remove.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    Cursor tankCursor = removeTank.rawQuery("SELECT * FROM tanks WHERE tankname = ?", new String[]{tankName});
                    String numOfFish = "";
                    String numOFPlant ="";
                    String numOfOther = "";
                    String tankId = "";
                    while (tankCursor.moveToNext()){
                        numOfFish = tankCursor.getString(tankCursor.getColumnIndex("numoffish"));
                        numOfOther = tankCursor.getString(tankCursor.getColumnIndex("numofother"));
                        numOFPlant = tankCursor.getString(tankCursor.getColumnIndex("numofplant"));
                        tankId = tankCursor.getString(tankCursor.getColumnIndex("id"));
                    }
                    tankCursor.close();

                    Cursor typeFind = removeCreature.rawQuery("SELECT * FROM creatures WHERE id = ?", new String [] {creatureId});
                    while(typeFind.moveToNext()){
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
                    Intent intent6 = new Intent(Creaturesample.this , Creature.class);
                    intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent6);

                }catch(Exception e ){
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



    public void imageClicked(View view){

    }
}
