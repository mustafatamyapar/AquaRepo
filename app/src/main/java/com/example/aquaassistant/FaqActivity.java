package com.example.aquaassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {
    //variables
ListView listView;
TextView header;
ArrayList<String> questions;
ArrayList<String> content;
ArrayAdapter arrayAdapter ;
//methods
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_faq);
    listView = findViewById(R.id.listView);
    header = findViewById(R.id.header);
    //listOfItems
     questions = new ArrayList<>();
     questions.add("Why is my aquarium water cloudy?");
     questions.add("Do fish sleep?");
     questions.add("Is there any relation between tank size and fish's grow rate?");
     questions.add("How many fish can I keep in my tank?");
     questions.add("Why there are algea in my tank?");
     content = new ArrayList<>();
     content.add("There could be many reasons for cloudy water. If the water turns into green, it shows that there is an algae reproduction which is hard to get rid of. You can use UV filter and keep the aquarium away from sunlight.\n" +
             "It could also be dust from a new ornament you have put in, you may need to do constant water changes to get rid of this. Substrates like Fluorite can make the tank cloudy for the first few weeks but will clear up later on.\n");
     content.add("Yes it has been proven that fish do sleep during the night. It is important to let them get there rest by giving the aquarium sufficient darkness during the night. If prolonged viewing hours are an issue, it is best to use lighting during day hours and then at night add some moonlights for added viewing time. Have a read of DIY Moonlights");
     content.add("This is a misconception that is not true. Larger fish will soon need a larger aquarium to be healthy and happy. Restricting them to a smaller aquarium may stunt the fish growth and result in physical deformities. Be sure to give the right environment each species of fish in the aquarium.");
     content.add("A good guide for keeping fish in your tank is about 1” of fish per 1 gallon of water. For larger fish it is recommended only 1” of fish for every 3 gallons of water. Larger fish often have a higher strain on the tanks biological bacteria and filter media.");
    content.add("Algae problems are infuriating and generally exist due to a number of reasons. There may be an unbalance of nutrients in the water, you might have too much lighting and not enough co2 and fertilizers, the plants can only consume these three in an equal ratio so having an off balance on one will lead to an algae outbreak.");
     //listView
     arrayAdapter = new ArrayAdapter(FaqActivity.this, android.R.layout.simple_list_item_1,questions);
     listView.setAdapter(arrayAdapter);

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent =new Intent(getApplicationContext(),FaqActivity2.class);
             intent.putExtra("content",content.get(position));
             intent.putExtra("question",questions.get(position));
         }
     });

}
}
