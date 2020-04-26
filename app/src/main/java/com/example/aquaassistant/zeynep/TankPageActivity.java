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

import java.util.ArrayList;

public class TankPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tankpage);

        TextView tankName = findViewById(R.id.tankName);
        TextView progressText = findViewById(R.id.progressText);
        TextView condCheck = findViewById(R.id.timeCond);
        TextView waterCheck = findViewById(R.id.waterCheck);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button editButton = findViewById(R.id.editButton);
        ImageView tankImage = findViewById(R.id.tankImage);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        final Intent intent = getIntent();
        final Tank selectedTank = (Tank) intent.getSerializableExtra("selectedTank");
        final ArrayList<Tank> tanks = (ArrayList<Tank>) intent.getSerializableExtra("tanks");
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),selectedTank.getPictureInteger());
        tankImage.setImageBitmap(bitmap);

        tankName.setText(selectedTank.getName());

        condCheck.setText("Time until condition check: " + selectedTank.getCondCheck());
        waterCheck.setText("Time until water check: " + selectedTank.getWaterCheck());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder sureDialog = new AlertDialog.Builder(TankPageActivity.this);
                sureDialog.setTitle("This tank will be deleted!");
                sureDialog.setMessage("Are you sure?");
                sureDialog.setPositiveButton( "YES" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tanks.remove(selectedTank);
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


        });
    }
}