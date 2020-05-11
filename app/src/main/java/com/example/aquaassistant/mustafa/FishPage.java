package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class FishPage extends AppCompatActivity {
    private List<Fish> listFish = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_page);
        Slidr.attach(this);

        recyclerView = findViewById(R.id.recyclerwiew2id);
        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(getApplicationContext(),listFish);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recyclerViewAdapter2);

        listFish.add(new Fish("Betta","Another extremely popular freshwater fish is the Betta. It’s not surprising why; Bettas are vibrantly colored, and easy to care for.\n" +
                "\n" +
                "Male Bettas are notoriously aggressive towards other males. Therefore only one male Betta should be kept in each aquarium. They can be housed with other peaceful fish.\n" +
                "\n" +
                "Bettas require an omnivorous diet, of both plant and animal foods.\n" +
                "\n" +
                "They grow to a maximum size of 3 inches. Although you often see Bettas in small ornamental tanks, they should be housed in larger tanks.\n" +
                "\n" +
                "Did you know: Bettas are able to breathe air outside of the water due to their labyrinth.","Difficulty:1.00",R.drawable.fish1));
        listFish.add(new Fish("Angelfish","Angelfish are a member of the Cichlid family, which also includes Discus, Oscars and Parrot fish, all common fish amongst aquarium keepers.\n" +
                "\n" +
                "They can grow up to 6 inches in length, 8 inches tall, and come in a variety of colors and patterns.\n" +
                "\n" +
                "They are omnivorous, so will need a balanced diet of meat and plant food.\n" +
                "\n" +
                "Their tank should be at least 20 gallons, and the water should be slightly soft and acidic.\n" +
                "\n" +
                "As they mature, they can become aggressive, especially if your tank in overcrowded. In general though, they are a good community fish, just don’t keep them with very small fish or fin-nipping species.","Difficulty:3.00",R.drawable.fish2));
        listFish.add(new Fish("Plecostomus","Plecs are a breed of catfish, they have heavy armored plates on their bodies, and sucker-shaped mouths to feed on the algae in your tank.\n" +
                "\n" +
                "Whilst some species are happy eating algae, wafers and flakes, others will need meaty food such as frozen brine shrimp.\n" +
                "\n" +
                "Breeding Plecs is extremely difficult and only a small number of aquarists have managed to breed them.\n" +
                "\n" +
                "Plecos can live for 20 years, sometimes longer if cared for correctly. It’s also worth knowing that they can jump too – so keep a lid on your tank.\n" +
                "\n" +
                "They can be housed with many different species, but avoid keeping them with fat/flat bodied fish such as goldfish as they may suck on them.","Difficulty:2.00",R.drawable.fish3));
        listFish.add(new Fish("Discus","These beautiful and graceful fish can grow to be quite large, and therefore require a larger tank, a minimum size of 25 gallons.\n" +
                "\n" +
                "Discus are not recommended for beginners, and instead should only be kept by experienced aquarists.\n" +
                "\n" +
                "They can be housed with other fish that require the same water conditions, as long as they are not aggressive.\n" +
                "\n" +
                "Discus will take a variety of foods but are carnivorous in nature. The best diet for them consists of beef heart and blood worms supplemented with flakes to provide vitamins and minerals.","Difficulty:5.00",R.drawable.fish4));
        listFish.add(new Fish("Pearl Gourami","Pearl Gourami is a relatively large, but peaceful fish and one of the most easy to keep Gouramis.\n" +
                "\n" +
                "The minimum tank size for this species is a 30 gallon tank with plenty of hiding places, dark substrate and low lighting.\n" +
                "\n" +
                "They can be housed with other fish of a similar size and temperament; however you should not house them with aggressive fish.\n" +
                "\n" +
                "Pearl Gourami’s are omnivorous and should be fed algae-based foods and meaty foods.\n" +
                "\n" +
                "They are well known for eating Hydra, a tiny pest that has tentacles with venom, so make a great solution if you have a hydra problem.","Difficulty:2.00",R.drawable.fish5));
        listFish.add(new Fish("Zebra Danios","Zebra Danios make the perfect beginner fish, they are very easy to care for and can grow up to 5-7cm.\n" +
                "\n" +
                "They should be kept in at least a 10 gallon tank, in groups of at least 5. Danios are a schooling fish and will become stressed if their numbers are too lows.\n" +
                "\n" +
                "They are not fussy eaters and will eat most foods; the healthiest option for them would be lots of worms, insets and crustaceans to mimic their natural diet, however a good quality flake will also work with a supplement of frozen or live food.\n" +
                "\n" +
                "Danios are also known to jump so you may want to keep your tank covered!","Difficulty:2.00",R.drawable.fish6));
        listFish.add(new Fish("Mollies","Small, peaceful species grow to around 3-4 inches, and adapt well to a variety of water conditions.\n" +
                "\n" +
                "The ideal tank conditions are: a minimum tank size of 20 gallons, and warm water with a pH between 7.0-7.8.\n" +
                "\n" +
                "Mollies are omnivorous, and will require a diet of both plant and animal food.\n" +
                "\n" +
                "Interestingly, they are livebearers, meaning they give birth to their young live, rather than lay eggs. Mollies are very easy to care for, but they also breed very easily, so if you’re a beginner you might want to keep just a single sex.","Difficulty:2.00",R.drawable.fish7));
        listFish.add(new Fish("Oscar","Oscars are thought to be one of the most intelligent aquarium fish available, and are one of the few species that can be trained to do tricks. \n" +
                "\n" +
                "Oscars however are not a community fish, they should be kept in a species only tank, and they can grow very large, very quickly.\n" +
                "\n" +
                "They require a lot more maintenance than other fish, due to their carnivorous nature and the amount of waste they create.\n" +
                "\n" +
                "On the positive side, they are one of the few species you can hand feed; they will often eat food from between your fingers. Oscars thrive when kept in pairs, or group of 5+, and should be housed together from a young age.","Difficulty:4.00",R.drawable.fish8));
        listFish.add(new Fish("Goldfish","When most people think of Goldfish, they think of small fish bowls with a fish that was won at the fair. This is not the correct way to house goldfish.\n" +
                "\n" +
                "Few people know that they can actually grow up to 14 inches in the wild.\n" +
                "\n" +
                "The minimum tank size for a goldfish is 20 gallons, you’ll also need a filter and to perform 10-15% weekly water changes.\n" +
                "\n" +
                "There are many different varieties of goldfish, and its fine to mix them as long as they aren’t breeds that would compete with each other for food. For example, keep single tailed varieties together and normal eyed goldfish together.","Difficulty:1.00",R.drawable.fish9));
    }
}
