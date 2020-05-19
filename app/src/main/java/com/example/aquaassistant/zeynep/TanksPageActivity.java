package com.example.aquaassistant.zeynep;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.sena.ToDoListPage;
import com.example.aquaassistant.zulal.AllContainers;
import com.example.aquaassistant.zulal.AquariumContainer;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
/**
 * TanksPageActivity Class - the activity that shows the list view and provide
 * user to add a new tank
 * @author Zeynep Berber
 * @contributor Fatma Sena Genç
 * @version 1.0 (May 18, 2020) - completed
 */
public class TanksPageActivity extends AppCompatActivity {
    ListView listView;
    AllContainers allContainers;
    TanksAdapter tanksAdapter;
    Button addTank;
    SQLiteDatabase tanksDatabase;
    ArrayList<Integer> idArray;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_tankspage);
        TextView myTanks = findViewById(R.id.myTanks);
        myTanks.setText("My Tanks");

        //notifArray = new ArrayList<>();
        addTank = findViewById(R.id.addTank);
        allContainers = new AllContainers();
        idArray = new ArrayList<>();
        addTank.setText("Add New Tank");

        // a database to store the tanks that have been created by user
        tanksDatabase = TanksPageActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
        tanksDatabase.execSQL("CREATE TABLE IF NOT EXISTS tanks (id INTEGER PRIMARY KEY, tankname VARCHAR, tanksize VARCHAR, numoffish VARCHAR," +
                "numofplant VARCHAR , numofother VARCHAR , watercheck VARCHAR ,timetofeed VARCHAR ,pictureint VARCHAR)");
        //create a list view
        listView = findViewById(R.id.tankList);
        //put the tank list into the list view
        tanksAdapter = new TanksAdapter(idArray, TanksPageActivity.this);
        listView.setAdapter(tanksAdapter);

        //if the user click on the tank
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TanksPageActivity.this, TankPageActivity.class);
                //put the id arraylist as extra
                intent.putExtra("tankId", String.valueOf(idArray.get(i)));
                startActivity(intent);
            }
        });

        //get the id numbers and put them into arraylist
        getData();
    }
    /**
     * This method is called when the user click on the add tank button
     * and ask for the name and size of tank.
     * Tank is added to the database with the default creature count values and the
     * remaining times to the water check and feeding
     * Also a notif is added to the notifs database
     * @param view ensures that this method used by a view
     */
    public void addTankBut(View view) {
        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(TanksPageActivity.this);
            alert.setTitle("New Tank");
            alert.setMessage("Please enter a tank name.");
            final EditText name = new EditText(TanksPageActivity.this);
            // Specify the type of input expected;
            name.setInputType(InputType.TYPE_CLASS_TEXT);
            alert.setView(name);

            // Set up the buttons
            alert.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //the name of the tank
                    final String tName = name.getText().toString();
                    //second alert to determine the size of the tank
                    AlertDialog.Builder secondAlert = new AlertDialog.Builder(TanksPageActivity.this);
                    secondAlert.setTitle("New Tank");
                    secondAlert.setMessage("What is the size of the tank?");
                    // get the liter of the tank from the user
                    final EditText size = new EditText((TanksPageActivity.this));
                    size.setInputType(InputType.TYPE_CLASS_NUMBER);
                    //put a hint in the edit text
                    size.setHint("Please enter the maximum liter!");
                    secondAlert.setView(size);
                    //set up the buttons
                    secondAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //the size of the tank
                            String tSize = size.getText().toString();
                            //insert the name and size and other info into the database
                            String sqlString = "INSERT INTO tanks (tankname, tanksize, numoffish , " +
                                    "numofplant , numofother ,watercheck , timetofeed , pictureint) VALUES (?,?,?,?,?,?,?,?)";
                            SQLiteStatement sqLiteStatement = tanksDatabase.compileStatement(sqlString);
                            sqLiteStatement.bindString(1, tName);
                            sqLiteStatement.bindString(2, tSize);
                            sqLiteStatement.bindString(3, "0");
                            sqLiteStatement.bindString(4, "0");
                            sqLiteStatement.bindString(5, "0");
                            // a temporary tank to get the water check according to the tank size
                            AquariumContainer newTank = new AquariumContainer(tName, Integer.parseInt(tSize), R.drawable.aquarium);
                            sqLiteStatement.bindString(6, String.valueOf(newTank.getWaterCheck()));
                            sqLiteStatement.bindString(7, "1");
                            sqLiteStatement.bindString(8, String.valueOf(R.drawable.aquarium));
                            sqLiteStatement.execute();
                            tanksAdapter.notifyDataSetChanged();

                            //This section creates the notification to feed the creatures as soon as a tank is created
                            ToDoListPage.notifDatabase = TanksPageActivity.this.openOrCreateDatabase("Notifs",MODE_PRIVATE,null);
                            ToDoListPage.notifDatabase.execSQL("CREATE TABLE IF NOT EXISTS notifs (id INTEGER PRIMARY KEY, notifText VARCHAR, tankName VARCHAR)");
                            String notifSQLString = "INSERT INTO notifs (notifText, tankName) VALUES ('# It is time to feed your creatures in ', ?)";
                            SQLiteStatement notifStatement = ToDoListPage.notifDatabase.compileStatement(notifSQLString);
                            notifStatement.bindString(1, tName);
                            notifStatement.execute();

                            Intent intent = new Intent(TanksPageActivity.this , TanksPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                    secondAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    //show the alert
                    secondAlert.show();
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            //show the first alert
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method gets the id of the tanks and put them into an arraylist to be able to
     * show them on a listview
     */
    public void getData() {
        try {
            SQLiteDatabase tanksDatabase = TanksPageActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
            Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks", null);
            //get the column index of id numbers
            int idIndex = cursor.getColumnIndex("id");
            if ( cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    //insert the id numbers in the arraylist by getting the id numbers from id column
                   idArray.add(cursor.getInt(idIndex));
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println("The error is " + e);
        }
    }
}