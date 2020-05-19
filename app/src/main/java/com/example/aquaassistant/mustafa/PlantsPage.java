package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;

import java.util.ArrayList;
import java.util.List;

import com.r0adkll.slidr.Slidr;
/**
 *
 * @author
 * @version
 */
public class PlantsPage extends AppCompatActivity {
    private List<Disease> listPlants = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_page);

        recyclerView = findViewById(R.id.recycler_plants);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), listPlants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        Slidr.attach(this);
        listPlants.add(new Disease("Java Moss", "These plants are very popular plants for a freshwater aquarium and are likewise a solid favorite of lovers of shrimp, as they provide extraordinary hiding places for them.\n" +
                "\n" +
                "The Java Moss is best appended to ornaments present in your aquarium tanks, such as rocks or driftwood.\n" +
                "\n" +
                "To do this, you will have to utilize elastic bands or a similar item to support the live aquarium plant while they gradually get rooted to the ornament.\n" +
                "\n" +
                "If you append it to a rock, it will grow outward across the tank’s surface. This plant is well-known to float, so it is best to attach it to something stable that can prevent it from floating away.\n" +
                "\n" +
                "The Java Moss plants do enjoy marginally dimmed light. They will thrive in lower light, but be careful if the light is excessively strong or the inverse effect will occur. Brilliant light will stunt Java Moss’s growth and could subject it to the plague of green algae.\n" +
                "\n" +
                "It will look fuzzy and green on the ground and endures a temperature of 70°F to 90°F but grows best in about 70°F to 75°F.", "Shrimp lovers best choice", R.drawable.plant1));
        listPlants.add(new Disease("Anubias and Anubias Nana", "Anubias and Anubias Nana are two easy-to-keep aquatic plants for your freshwater aquarium. Anubias is usually sold on rocks or driftwood in the aquarium store.\n" +
                "\n" +
                "Unlike the Java Fern, this freshwater plant grows slowly. It’s therefore essential to purchase a specimen that already properly fits the size of your aquarium.\n" +
                "\n" +
                "Once acquired, make sure you keep the plant above the substrate and don’t cover the bottom of its root in the substrate.\n" +
                "\n" +
                "This freshwater plant needs to be in the shady part of the planted tank with an ideal flow in order to prevent the growth of algae on the leaves. The Anubias plant needs only a low level of nutrients and therefore doesn’t require any fluid fertilizer to be added to the water.\n" +
                "\n" +
                "Anubias Nana is a variation of anubias with much smaller leaves. It is more appropriate for smaller or Nano aquariums. This plant is difficult to get and usually comes at a higher cost. The greatest advantage of this aquatic plant is the measure of coverage it gives.", "Best for aquariums which has plenty rock", R.drawable.plant2));
        listPlants.add(new Disease("Crypt Wendtii", "Crypts are great plants that are normally displayed in pots in neighborhood fish stores.\n" +
                "\n" +
                "Crypts in some cases have a terrible reputation since they “liquefy” quickly upon addition to the planted aquarium.\n" +
                "\n" +
                "This is due to the fact that crypts are less resistant to change compared to other aquatic plants in this rundown.\n" +
                "\n" +
                "They likewise require a slightly higher amount of light compared to other plants. Hence, they should conceivably be left as an aquarium plant for the marginally advanced aquarist.\n" +
                "\n" +
                "In any case, they are a good-looking foreground plant that can be recognized by long slim leaves stretching out from a focal point below the substrate.\n" +
                "\n" +
                "However, upon addition to the planted aquarium, it might appear as if this plant has totally dissolved, but the Crypt is only responding to change. As you keep tending it, the crypt will have the capacity to return to full health as it develops its new root framework in the freshwater aquarium.\n" +
                "\n" +
                "Another benefit of this plant is that it can be effectively split at the rhizome area and propagated all through the aquarium.", "Are you looking for a authentic vibes in your aquarium? ", R.drawable.plant3));
        listPlants.add(new Disease("Cryptocorynes", "These live plants for freshwater aquariums can vary widely in size, color, and shape.\n" +
                "\n" +
                "Cryptocorynes comes in a wide range of varieties, yet regardless of what they look like, they are extremely popular with shrimp.\n" +
                "\n" +
                "Advanced aquarists consider these plants to be the “next step up” from Java Fern and Anubias. They still enjoy dimmed light, however they require somewhat more care because of their complex roots.\n" +
                "\n" +
                "They should be covered at a depth of 2 inches on the rock, however, you should take care to guarantee that the crown (the location of the leaves), is kept well over the rock. It needs 72 to 82°F water temperature and moderate lighting.\n" +
                "\n" +
                "You should be watchful with Cryptocoryne as they are very vulnerable. They can encounter what is regarded as the “Crypt Melt” situation, which usually happens when you first bring them into your freshwater aquarium.\n" +
                "\n" +
                "What actually happens is the sudden change in the water around them usually stuns the Crypts, and the outcome is that they frequently lose most of their leaves. Do not worry, however; this is very common and never lethal for the plant.\n", "Advanced aquarists should give them a chance", R.drawable.plant4));
        listPlants.add(new Disease("Hornwort", "Otherwise called Coontail, Hornwort is one of the less-difficult plants to keep in the aquarium. It is commonly sold in pots and floats freely on the surface of the aquarium.\n" +
                "\n" +
                "It is exceptionally versatile to changing conditions and has the ability to survive a wide range of temperatures. It is also appropriate for cool outdoor ponds and water tanks.\n" +
                "\n" +
                "Hornwort can achieve a length of up to 24 inches. This aquatic plant can be propagated by simply cutting down the stems and giving them a chance to float around. Another way is to replant them in the substrate.\n" +
                "\n" +
                "It is a brilliant background plant because it develops to the aquarium’s height. The Hornwort needs a minimal lighting and negligible fertilizer supplement.\n" +
                "\n" +
                "In the wild, Hornwort is commonly found in sloppy conditions joined to the substrate or floating freely in water bodies. In the aquarium, either is suitable. Nevertheless, it is highly recommended to plant Hornwort in the substrate.\n" +
                "\n" +
                "This allows your lighting to reach the underlying plants. If the plant floats freely on the surface, it can hinder a portion of the light from reaching the underlying plants.", "Easyt to take care and rewarding", R.drawable.plant5));
        listPlants.add(new Disease("Dwarf Lilies", "These are extremely delicate plants that grow slowly. In spite of the fact that shrimp seem to like them, they are easily damaged, so it may not be a smart idea to plant dwarf lilies if you have numerous shrimp circling around.\n" +
                "\n" +
                "Dwarf lilies look like little-pointed heads, and their tiny stems can break easily. If you wish to have dwarf lilies in your tank, it would be ideal if you could purchase older ones that have been allowed to grow fully.\n" +
                "\n" +
                "Then there will be a lower likelihood of breakage and you will additionally abstain from purchasing freshwater aquarium plants that do not have the ability to reproduce.", "Extreme hard to take care but artistic", R.drawable.plant6));
    }
}
