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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.AquariumContainer;
import com.example.aquaassistant.zulal.Fish;

import java.util.Arrays;

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

        tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
    }

    public void changeTankName(View view){
        AlertDialog.Builder changeName = new AlertDialog.Builder(EditTankActivity.this);
        changeName.setTitle("Change The Tank Name");
        changeName.setMessage("Please enter the new name.");

        final EditText newName = new EditText(EditTankActivity.this);
        newName.setInputType(InputType.TYPE_CLASS_TEXT);
        changeName.setView(newName);
        changeName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tName = newName.getText().toString();
                try {
                    tanksDatabase = EditTankActivity.this.openOrCreateDatabase("Tanks", MODE_PRIVATE, null);
                    String changeName = "UPDATE tanks SET tankname= ? WHERE id = " + tankId ;
                    SQLiteStatement changeStatement = tanksDatabase.compileStatement(changeName);
                    changeStatement.bindString(1, tName);
                    changeStatement.execute();
                   // Intent intent = new Intent(EditTankActivity.this, TankPageActivity.class);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                   // intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    //startActivity(intent);
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
    public void addCreature(View view){

    }

}
