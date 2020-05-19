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
import com.example.aquaassistant.zeynep.TanksPageActivity;
import com.example.aquaassistant.zulal.AquariumContainer;

import java.util.ArrayList;

/**
 * ToDoListPage Class - the activity that displays To-Do List
 * @author Fatma Sena Genç
 * @version 1.0 (May 10, 2020) - partially complete
 * @version 2.0 (May 11, 2020) - completed
 */

public class ToDoListPage extends AppCompatActivity {

    public static SQLiteDatabase notifDatabase;
    ListView listViewNotif;
    ArrayAdapter notifAdapter;
    ArrayList<String> notifArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        try {
            listViewNotif = findViewById(R.id.listViewNotif);
            notifAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notifArray);
            listViewNotif.setAdapter(notifAdapter);
            notifAdapter.notifyDataSetChanged();
            notifArray.add("Here is your To-Do list!");

            notifDatabase = this.openOrCreateDatabase("Notifs", MODE_PRIVATE, null);
            notifDatabase.execSQL("CREATE TABLE IF NOT EXISTS notifs (id INTEGER PRIMARY KEY, notifText VARCHAR, tankName VARCHAR)");

            Cursor cursor = notifDatabase.rawQuery("SELECT * FROM notifs", null);
            int notifTextIndex = cursor.getColumnIndex("notifText");
            int tankNameIndex = cursor.getColumnIndex("tankName");
            while (cursor.moveToNext()) {
                notifArray.add(cursor.getString(notifTextIndex) + cursor.getString(tankNameIndex));
            }
            cursor.close();
            //Following section allows the user to remove a task if they have done it
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
                                notifArray.remove(position);
                                notifAdapter.notifyDataSetChanged();
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

