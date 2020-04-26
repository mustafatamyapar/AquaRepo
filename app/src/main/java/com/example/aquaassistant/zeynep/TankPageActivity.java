package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.AquariumContainer;

import java.io.Serializable;
import java.util.ArrayList;

public class TankPageActivity extends AppCompatActivity {
    AquariumContainer selectedTank;
    ArrayList<AquariumContainer> tanks;
    Bitmap bitmap;
    TextView tankName;
    TextView condCheck;
    TextView waterCheck;
    Button deleteButton;
    Button editButton;
    ImageView tankImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tankpage);
        //find the layout components
        tankName = findViewById(R.id.tankName);
        condCheck = findViewById(R.id.timeCond);
        waterCheck = findViewById(R.id.waterCheck);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        tankImage = findViewById(R.id.tankImage);

        editButton.setText("Edit Tank");
        deleteButton.setText("Delete Tank");
        //get the intent from the tankspageactivity
        Intent intent = getIntent();
        //get the tank
        selectedTank = (AquariumContainer) intent.getSerializableExtra("selectedTank");
        tanks = (ArrayList<AquariumContainer>) intent.getSerializableExtra("tanks");
        //find the image of the tank
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),selectedTank.getPictureInteger());

        tankImage.setImageBitmap(bitmap);
        tankName.setText(selectedTank.getTankName());
        condCheck.setText("Time until feeding: " + selectedTank.getTimeToFeed());
        waterCheck.setText("Time until water check: " + selectedTank.getWaterCheck());


    }
    //delete the tank when the user click o the delete button
    public void deleteTankBut(View view){
        //show an alert message
        AlertDialog.Builder sureDialog = new AlertDialog.Builder(TankPageActivity.this);
        sureDialog.setTitle("This tank will be deleted!");
        sureDialog.setMessage("Are you sure?");
        sureDialog.setPositiveButton( "YES" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tanks.remove( selectedTank );
                finish();
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

    }


}
