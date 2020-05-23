package com.example.aquaassistant.sena;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.kerem.ProfilePage;
import com.example.aquaassistant.kerem.Ranks;
import com.example.aquaassistant.zeynep.TanksPageActivity;
import com.example.aquaassistant.zulal.AquariumContainer;

import java.util.ArrayList;

/**
 * ToDoListPage Class - the activity that displays To-Do List
 * @author Fatma Sena Genç
 * @version 1.0 (May 10, 2020) - partially complete
 * @version 2.0 (May 11, 2020)
 * @version 3.0 (May 19, 2020) - completed
 */

public class ToDoListPage extends AppCompatActivity {

    public static SQLiteDatabase notifDatabase; //the SQLite database for the to-do list
    ListView listViewNotif;
    ArrayAdapter notifAdapter;
    ArrayList<String> notifArray = new ArrayList<String>();

    @Override
    /**
     * onCreate - called when the activity is started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        try {
            //Array adapter is set
            listViewNotif = findViewById(R.id.listViewNotif);
            notifAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notifArray);
            listViewNotif.setAdapter(notifAdapter);
            notifAdapter.notifyDataSetChanged();
            notifArray.add("Here is your To-Do list!");

            //database created
            notifDatabase = this.openOrCreateDatabase("Notifs", MODE_PRIVATE, null);
            notifDatabase.execSQL("CREATE TABLE IF NOT EXISTS notifs (id INTEGER PRIMARY KEY, notifText VARCHAR, tankName VARCHAR)");

            //cursor gets the data from the database
            Cursor cursor = notifDatabase.rawQuery("SELECT * FROM notifs", null);
            int notifTextIndex = cursor.getColumnIndex("notifText");
            int tankNameIndex = cursor.getColumnIndex("tankName");
            while (cursor.moveToNext()) {
                notifArray.add(cursor.getString(notifTextIndex) + cursor.getString(tankNameIndex));
            }
            cursor.close();

            //Following section allows the user to remove a task if they have completed it
            listViewNotif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    if (position != 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(ToDoListPage.this);
                        alert.setTitle("Remove from list");
                        alert.setMessage("If you completed this task, you can remove it from the list.");
                        alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String SQLString = "DELETE FROM notifs WHERE id = ?";
                                SQLiteStatement SQLStatement = notifDatabase.compileStatement(SQLString);
                                SQLStatement.bindString(1, String.valueOf(position));
                                SQLStatement.execute();

                                String currentExperience = "";
                                String newExperience;
                                SQLiteDatabase experienceDatabase = ToDoListPage.this.openOrCreateDatabase("Experience", MODE_PRIVATE,null);
                                Cursor cursor = experienceDatabase.rawQuery("SELECT * FROM experience WHERE id = 1", null);
                                while(cursor.moveToNext()){
                                    currentExperience = cursor.getString(cursor.getColumnIndex("experience"));
                                }
                                cursor.close();

                                newExperience = String.valueOf(Integer.parseInt(currentExperience)+50);
                                Ranks.Experience = newExperience;
                                String sqlSta = "UPDATE experience SET experience = ? WHERE id = 1";
                                SQLiteStatement update = experienceDatabase.compileStatement(sqlSta);
                                update.bindString(1,newExperience);
                                update.execute();
                                System.out.println(newExperience);
                                notifArray.remove(position);
                                notifAdapter.notifyDataSetChanged();

                                //rank & experience updating
                                if(newExperience.matches("50")){
                                    System.out.println(newExperience);
                                    Ranks.Experience = newExperience;
                                    Ranks.RANK = "Intermediate";
                                }
                                else if(newExperience.matches("100")){
                                    Ranks.Experience = newExperience;
                                    System.out.println(newExperience);
                                    Ranks.RANK = "Advanced";
                                }
                                else if(newExperience.matches("150")){
                                    Ranks.Experience = newExperience;
                                    System.out.println(newExperience);
                                    Ranks.RANK = "Expert";
                                }
                                else{
                                    Ranks.Experience = newExperience;
                                    System.out.println(newExperience+ " YEEHA");
                                    Ranks.RANK = "Fish";
                                }
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
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}