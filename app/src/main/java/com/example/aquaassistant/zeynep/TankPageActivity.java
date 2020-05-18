package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aquaassistant.R;
import com.example.aquaassistant.sena.ToDoListPage;
import com.example.aquaassistant.zulal.Creature;
import com.r0adkll.slidr.Slidr;

import java.util.Calendar;

public class TankPageActivity extends AppCompatActivity {

    Bitmap bitmap;
    TextView tankName , condCheck, waterCheck;
    Button deleteButton, editButton;
    ImageView tankImage;
    String tankId;
    SQLiteDatabase tanksDatabase , creatureDatabase;
    Calendar calendar = Calendar.getInstance();
    private int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    String currentWaterCheck;
    String currentTimeToFeed;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_tankpage);
        //find the layout components
        tankName = findViewById(R.id.tankName);
        condCheck = findViewById(R.id.timeCond);
        waterCheck = findViewById(R.id.waterCheck);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        tankImage = findViewById(R.id.tankImage);

        editButton.setText("Edit");
        deleteButton.setText("Delete");

        creatureDatabase = TankPageActivity.this.openOrCreateDatabase("Creatures", MODE_PRIVATE,null);

        //get the tank id
        Intent intent =getIntent();
        tankId = intent.getStringExtra("tankId");
        try {
            tanksDatabase = TankPageActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
            //get the info of tank with the tankId
            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{tankId});

            //get the column indexes of the info
            int tankNameIndex = cursor.getColumnIndex("tankname");
            int condCheckIndex = cursor.getColumnIndex("timetofeed");
            int waterCheckIndex = cursor.getColumnIndex("watercheck");
            int tankImageIndex = cursor.getColumnIndex("pictureint");

            while (cursor.moveToNext()) {
                //find the image of the tank
                bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), Integer.parseInt(cursor.getString(tankImageIndex)));

                //get the info and put them into text views
                tankImage.setImageBitmap(bitmap);
                tankName.setText(cursor.getString(tankNameIndex));
                condCheck.setText("Time until feeding: " + cursor.getString(condCheckIndex) + " day");
                waterCheck.setText("Time until water check: " + cursor.getString(waterCheckIndex) + " days");
            }
            //update the database day by day
            if (hourOfDay == 23 && minute == 59) {
                currentTimeToFeed = cursor.getString(waterCheckIndex);
                currentWaterCheck = cursor.getString(condCheckIndex);
                String updateWater = "UPDATE tanks SET watercheck = ? WHERE id = " + tankId;
                SQLiteStatement updateWaterT = tanksDatabase.compileStatement(updateWater);
                String newWaterTime = String.valueOf(Integer.parseInt(currentWaterCheck) - 1);
                updateWaterT.bindString(1, newWaterTime);
                updateWaterT.execute();
            }
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    //delete the tank when the user click o the delete button
    public void deleteTankBut(View view){
        //show an alert message
        AlertDialog.Builder sureDialog = new AlertDialog.Builder(TankPageActivity.this);
        sureDialog.setMessage("This tank will be deleted!");
        sureDialog.setTitle("Are you sure?");
        sureDialog.setPositiveButton( "YES" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    //delete the tank from database
                    String deleteString = "DELETE FROM tanks WHERE id = " + tankId;
                    SQLiteStatement deleteStatement = tanksDatabase.compileStatement(deleteString);
                    deleteStatement.execute();

                    String deleteFromCreatures = " DELETE FROM creatures WHERE tankId= ? ";
                    SQLiteStatement deleteCreature = creatureDatabase.compileStatement(deleteFromCreatures);
                    deleteCreature.bindString(1, tankId);
                    deleteCreature.execute();

                    //This section deletes the related notifications when a tank is deleted
                    String deleteNotifSQLString = "DELETE FROM notifs WHERE tankName = ?";
                    SQLiteStatement deleteNotifSQLStatement = ToDoListPage.notifDatabase.compileStatement(deleteNotifSQLString);
                    deleteNotifSQLStatement.bindString(1, tankName.getText().toString());
                    deleteNotifSQLStatement.execute();

                    //go back from the tanks page
                    Intent intent = new Intent(TankPageActivity.this, TanksPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        sureDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        sureDialog.show();
    }

    //edit the tank when the user click on the edit button
    public void editTankBut(View view){
        Intent intent = getIntent();
        String tankId = intent.getStringExtra("tankId");
        Intent intent2 = new Intent(TankPageActivity.this , EditTankActivity.class);
        //put the tank id as extra
        intent2.putExtra("tankId", tankId);
        Intent intent3 = new Intent (TankPageActivity.this, Creature.class);
        intent3.putExtra("tankId", tankId);
        startActivity(intent2);
    }


}
