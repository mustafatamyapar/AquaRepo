package com.example.aquaassistant.zeynep;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.AllContainers;
import com.example.aquaassistant.zulal.AquariumContainer;

public class TanksPageActivity extends AppCompatActivity {
    Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tankspage);

        TextView myTanks = findViewById(R.id.myTanks);
        final ListView listView = findViewById(R.id.tankList);
        final AllContainers allContainers = new AllContainers();
        AquariumContainer firstTank = new AquariumContainer();
        firstTank.setName("Hellooo");
        firstTank.setSize(400);
        allContainers.addTank(firstTank);


        TanksAdapter tanksAdapter;
        tanksAdapter = new TanksAdapter( allContainers.getAllTanks(), TanksPageActivity.this);
        listView.setAdapter(tanksAdapter);
        //tanksAdapter.notifyDataSetChanged();
        // listView.updateViewLayout( listView, listView.getLayoutParams());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TanksPageActivity.this, TankPageActivity.class);
                intent.putExtra("selectedTank", allContainers.getAllTanks().get(i));
               // intent.putExtra("tanks", allContainers.getAllTanks());
                startActivity(intent);
            }
        });

        //button
        Button addTank = findViewById(R.id.addTank);
        addTank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
                        final String tName = name.getText().toString();
                        AlertDialog.Builder secondAlert = new AlertDialog.Builder(TanksPageActivity.this);
                        secondAlert.setTitle("Size?");
                        secondAlert.setMessage(" What is the size of the tank?");
                        final EditText size = new EditText((TanksPageActivity.this));
                        size.setInputType(InputType.TYPE_CLASS_NUMBER);
                        secondAlert.setView(size);
                        secondAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int tSize = Integer.parseInt(String.valueOf(size.getText()));
                                AquariumContainer newTank = new AquariumContainer();
                                newTank.setSize(tSize);
                                newTank.setName(tName);
                                allContainers.addTank(newTank);
                            }
                        });
                        secondAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        secondAlert.show();
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
        });
    }
}

