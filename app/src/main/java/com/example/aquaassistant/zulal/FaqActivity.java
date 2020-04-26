package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {
    //variables
ListView listView;
TextView header;
ArrayList<Faq> faqArrayList;

//methods
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_faq);
    listView = findViewById(R.id.listView);
    header = findViewById(R.id.header);
    faqArrayList = new ArrayList<>();
    //listOfItems
    Faq q1 = new Faq("Yes it has been proven that fish do sleep during the night. It is important to let them get there rest by giving the aquarium sufficient darkness during the night. If prolonged viewing hours are an issue, it is best to use lighting " +
            "during day hours and then at night add some moonlights for added viewing time. " +
            "Have a read of DIY Moonlights" ,"Do fish sleep?");
    Faq q2 = new Faq("There could be many reasons for cloudy water. If the water turns into green, it shows that there is an algae reproduction which is hard to get rid of. You can use UV filter and keep the aquarium away from sunlight.\n" +
            "It could also be dust from a new ornament you have put in, you may need to do constant water changes to get rid of this. Substrates like Fluorite can make the tank cloudy for the first few weeks but will clear up later on.","Why is my aquarium water cloudy?");
    Faq q3 = new Faq("This is a misconception that is not true. Larger fish will soon need a larger aquarium to be healthy and happy. Restricting them to a smaller aquarium may stunt the fish growth and result in physical deformities. Be sure to give the right environment each species of fish in the " +
            "aquarium.","Is there any relation between tank size and fish's grow rate?");
    Faq q4 = new Faq("A good guide for keeping fish in your tank is about 1” of fish per 1 gallon of water. For larger fish it is recommended only 1” of fish for every 3 gallons of water. Larger fish often have a higher strain on the tanks biological " +
            "bacteria and filter media.","How many fish can I keep in my tank?");
    Faq q5 = new Faq("Algae problems are infuriating and generally exist due to a number of reasons. There may be an unbalance of nutrients in the water, you might have too much lighting and not enough co2 and fertilizers, the plants can " +
            "only consume these three in an equal ratio so having an off balance on one will lead to an algae outbreak.","Why there are algea in my tank?");

    faqArrayList.add(q1);
    faqArrayList.add(q2);
    faqArrayList.add(q3);
    faqArrayList.add(q4);
    faqArrayList.add(q5);
     //listView
    MyAdapter listAdapter = new MyAdapter(faqArrayList,FaqActivity.this);
    listView.setAdapter(listAdapter);


     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent = new Intent(getApplicationContext(), FaqActivity2.class);
             intent.putExtra("selectedContent",faqArrayList.get(position));
             startActivity(intent);
         }
     });

}
}
