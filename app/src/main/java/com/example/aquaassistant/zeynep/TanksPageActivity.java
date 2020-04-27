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
    ListView listView;
    AllContainers allContainers;
    AquariumContainer firstTank;
    TanksAdapter tanksAdapter;
    Button addTank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tankspage);
        TextView myTanks = findViewById(R.id.myTanks);
        myTanks.setText("My Tanks");

        addTank = findViewById(R.id.addTank);
        allContainers = new AllContainers();

        firstTank = new AquariumContainer( "Helloo", 150 , R.drawable.aquarium );
        allContainers.addTank(firstTank);

        //create a list view
        listView = findViewById(R.id.tankList);
        //put the tank list into the list view
        tanksAdapter = new TanksAdapter(allContainers.getAllTanks(), TanksPageActivity.this);
        listView.setAdapter(tanksAdapter);
        //adjust the changes
        tanksAdapter.notifyDataSetChanged();

        //if the user click on the tank
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TanksPageActivity.this, TankPageActivity.class);
                //put the tank as extra
                intent.putExtra("selectedTank", allContainers.getAllTanks().get(i));
                //put the tank list as extra
                intent.putExtra("tanks", allContainers.getAllTanks());
                startActivity(intent);
            }
        });
    }

        public void addTankBut (View view){
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
                    secondAlert.setTitle("Size?");
                    secondAlert.setMessage(" What is the size of the tank?");
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
                            int tSize = Integer.parseInt(String.valueOf(size.getText()));
                            //add a tank with specified name and size
                            allContainers.addTank(new AquariumContainer(tName , tSize, R.drawable.aquarium));
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
        }
}



