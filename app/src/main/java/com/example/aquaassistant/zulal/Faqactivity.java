package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class Faqactivity extends AppCompatActivity {
ArrayList<String> faqArrayList;
ArrayList<String> questions;
RecyclerAdapter myAdapter;
RecyclerView recyclerView;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Slidr.attach(this);
    setContentView(R.layout.activity_faqactivity);
    faqArrayList = new ArrayList<>();
    questions = new ArrayList<>();

    //listOfItems
    faqArrayList.add("Yes it has been proven that fish do sleep during the night. It is important to let them get there rest by giving the aquarium sufficient darkness during the night. If prolonged viewing hours are an issue, it is best to use lighting " +
            "during day hours and then at night add some moonlights for added viewing time. " +
            "Have a read of DIY Moonlights");
    faqArrayList.add("There could be many reasons for cloudy water. If the water turns into green, it shows that there is an algae reproduction which is hard to get rid of. You can use UV filter and keep the aquarium away from sunlight.\n" +
            "It could also be dust from a new ornament you have put in, you may need to do constant water changes to get rid of this. Substrates like Fluorite can make the tank cloudy for the first few weeks but will clear up later on.");
    faqArrayList.add("This is a misconception that is not true. Larger fish will soon need a larger aquarium to be healthy and happy. Restricting them to a smaller aquarium may stunt the fish growth and result in physical deformities. Be sure to give the right environment each species of fish in the " +
            "aquarium.");
    faqArrayList.add("A good guide for keeping fish in your tank is about 1” of fish per 1 gallon of water. For larger fish it is recommended only 1” of fish for every 3 gallons of water. Larger fish often have a higher strain on the tanks biological " +
            "bacteria and filter media.");
    faqArrayList.add("Algae problems are infuriating and generally exist due to a number of reasons. There may be an unbalance of nutrients in the water, you might have too much lighting and not enough co2 and fertilizers, the plants can " +
            "only consume these three in an equal ratio so having an off balance on one will lead to an algae outbreak.");
    questions.add("Do fish sleep?");
    questions.add("Why is my aquarium water cloudy?");
    questions.add("Is there any relation between tank size and fish's grow rate?");
    questions.add("How many fish can I keep in my tank?");
    questions.add("Why there are algea in my tank?");

    //RecyclerView
     recyclerView = findViewById(R.id.recycler_view1);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    myAdapter = new RecyclerAdapter(questions, faqArrayList);
    recyclerView.setAdapter(myAdapter);
    myAdapter.notifyDataSetChanged();

}
}

