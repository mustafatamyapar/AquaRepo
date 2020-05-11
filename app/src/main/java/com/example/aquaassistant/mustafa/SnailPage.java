package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;

import java.util.ArrayList;
import java.util.List;

public class SnailPage extends AppCompatActivity {
    private List<Fish> listCreature = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snail_page);

        recyclerView = findViewById(R.id.recycler_snail);
        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(getApplicationContext(),listCreature);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recyclerViewAdapter2);

        listCreature.add(new Fish("Freshwater Crayfish","Crayfish aren't meant for community aquariums, but when kept in tanks on their own, these crustaceans make wonderful and interesting aquarium pets. There are more than 100 species of freshwater crayfish, and the different varieties requires different habitats and care, so do your research before you set up an aquarium and bring your crayfish home. Generally, any crayfish will do well in a relatively small five-gallon or 10-gallon tank. Larger tank will be necessary if you plan to house multiple crayfish, and frequent water changes will be required in any case because crayfish are messy eaters who also tend to hoard and hide food.","Difficulty:4.00",R.drawable.creature1));
        listCreature.add(new Fish("Ghost Shrimp","Ghost shrimp are among the most popular freshwater shrimp kept as pets. Their name comes from their unique, near-transparent bodies, which makes them cool and fun to watch. But the truth is, ghost shrimp as usually purchased as food for fish and other aquarium inhabitants. But there are plenty of people who enjoy keeping ghost shrimp as pets for their own sake. They are small and easy to care for. All you need in your tank to keep your shrimp happy are plenty of appropriate plants. Be prepared to watch your ghost shrimp family grow, because these suckers spawn easily in an aquarium environment.","Difficulty:3.00",R.drawable.creature2));
        listCreature.add(new Fish("Axolotl","Axolotls are unusual-looking but actually quite adorable aquatic salamanders from Mexico. Perhaps the most unique trait of these wonderful creatures is their ability to heal and regenerate. They've even been observed to regenerate parts of their brains when damaged. And in some cases, a damaged limb may heal, yet generate an additional appendage as well, resulting in a rare extra limb. If you're going to keep a pet axolotl, though, be sure to provide an aquarium that's around 20 gallons — about twice as big as a typical aquarium. Axolotls are messy, and soil their tank water quickly. Be prepared for frequent cleaning and maintenance. ","Difficulty:5.00",R.drawable.creature3));
        listCreature.add(new Fish("Fire-Bellied Newt","So you want a fire-bellied aquarium pet, but a toad isn't for you? A fire-bellied newt may be just the ticket. About 70 percent of your newts aquarium should be water, while the remainder should include a sloping land area for the newt to bask. It is possible, if desired, to provide a full aquarium with floating cork bark on which your newt can rest when necessary. A newt's aquarium should also have plenty of aquatic plants, stones, logs and the like for climbing and hiding purposes. Also note that, like their fire-bellied toad cousins, fire-bellied newts secrete a toxin that can irritate your skin, so you may wish to wear gloves if you have to handle your newt. ","Difficulty:4.00",R.drawable.creature4));
        listCreature.add(new Fish("Octopus","Though not exactly common as pets, octopuses are becoming increasingly popular with experienced aquarium enthusiasts. There are different species of octopus available as pets, and the species that you choose will dictate much of its care, so do a lot of thorough research before you buy. An octopus should not be kept in an aquarium with other fish, or even with other octopuses. One octopus per tank. Tank preparation and maintenance should be discussed with an experienced pet-store owner. Again, these are pets for experienced aquarium owners, but if you can handle the work involved, these highly intelligent creatures can prove to be very interesting and unique pets.","Difficulty:4.00",R.drawable.creature5));
        listCreature.add(new Fish("Sea Monkey","The name \"sea monkeys\" is a marketing term that was used to sell brine shrimp to kids from ads in comic books. The gap between the colorful cartoon drawings of sea monkeys in the ads and the reality of the brine shrimps appearance infamously caused a lot of kids to feel ripped off, but over the years, the retro kitsch aspect of sea monkeys has given them a continued cult following. And what makes brine shrimp legitimately cool is the very trait that allowed them to be sold through the mail. Brine shrimp lay eggs that, when dried, can remain viable for years. All you need to do is \"just add water,\" and the baby brine shrimp take just a week to grow into maturity.","Difficulty:2.00",R.drawable.creature6));
        listCreature.add(new Fish("Fire-Bellied Toad","It's obvious where these unique-looking amphibians got their name: They're typical toady green on top, but have super-cool orange-red tummies underneath. As amphibians, they require aquariums that offer both water and land. A 50-50 ratio works well, and the tank should definitely not contain less than 25 percent water. As you might expect, the fire-bellied toad's bright coloring indicates that it secretes toxins from its skin. While this should pose no severe danger to you, the toxins will contaminate the aquarium's water, which can harm the toad, so you will need to change the water frequently. ","Difficulty:5.00",R.drawable.creature7));
        listCreature.add(new Fish("Red Cherry Shrimp","The Red Cherry Shrimp (Neocaridina heteropoda) is also known as Cherry Shrimp or RCS, is a dwarf freshwater shrimp native to Taiwan. It is a freshwater Shrimp that is incredibly peaceful and renowned for its algae eating capabilities. Suitable for both beginners and experienced aquarists, it is one of the hardiest and easy to keep Shrimp available. They will add color into any tank which they are placed in and are very undemanding requiring very little upkeep.","Difficulty:2.00",R.drawable.creature8));
        listCreature.add(new Fish("Golden Mystery Snails","Mystery snail is one of the most popular additions to freshwater tanks. These slow-moving, peaceful herbivores let you sit back whilst they do some of the cleanings for you. There are lots of other names for this snail including; golden mystery snail, mystery apple snail, spike topped apple snail and Pomacea australis. The correct scientific name, however, is Pomacea bridgesii. Because they are so common among aquarists, use the following general rule when purchasing snails for your tank. Take a few minutes to observe the group of snails at the store and pick the ones who are moving or attached to a surface; never buy a snail that has a cracked or damaged shell.","Difficulty:1.00",R.drawable.creature9));
    }
}
