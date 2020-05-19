package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;
/**
 * Disease page which has various diseases that shown in row style
 * @author Mustafa Efe Tamyapar
 * @version 1.0 27.04.2020 Created activity.
 * @version 2.0 02.05.2020 trying JSOUP
 * @version 3.0 03.05.2020 trying JSON and Glide, created a gits website to get data
 * @version 4.0 04.05.2020 Created a local database and using it.
 * @version 5.0 12.05.2020 Added all the information about diseases.
 */
public class DiseasesPage extends AppCompatActivity {

    private List<Disease> listDisease = new ArrayList<>();
    private RecyclerView recyclerView;
    /**
     * This method crates items on the diseases page
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_page);

        recyclerView = findViewById(R.id.recyclerwiewid);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), listDisease);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        Slidr.attach(this);

        listDisease.add(new Disease("Cotton Mouth", "Potential Causes\n" +
                "Columnaris is common where high bioloads or stressful conditions exist. These can be due to overcrowding, injury, inadequate diet, poor water quality, and unstable pH.\n" +
                "\n" +
                "The bacteria can stay active in your tank for up to 32 days if the hardness is 50 ppm or more.\n" +
                "\n" +
                "Possible Treatments\n" +
                "The disease does not survive in salt solutions of 1% or higher. So salt can be a great initial treatment if the case is less severe.\n" +
                "\n" +
                "The use of a separate treatment bath can be great for infected fish, but, in severe cases, treatment of your entire tank is a must.\n" +
                "\n" +
                "Another option would be to buy an antibiotic that treats gram-negative bacterial infections. Oxytetracycline is regarded as a very effective option.\n" +
                "\n" +
                "To prevent the spread, remove any dead fish ASAP. This will stop surviving fish from picking at the infected fish, which can result in internal infection.", "Cottony growth near jaws and loss of appetite", R.drawable.disease1));
        listDisease.add(new Disease("Dropsy", "Potential Causes\n" +
                "In most cases, Dropsy is the caused by the bacteria Aeromonas. Now, this bacteria is commonly found in most fish tanks, however, it’s likely to infect fish that are stressed due to overcrowding or poor water conditions.\n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                "It can also affect fish with poor kidney function, which results in the absorption of water into the body cavity – causing the stomach to swell.\n" +
                "\n" +
                "Possible Treatments\n" +
                "Unfortunately, Dropsy is usually untreatable and your fish will not survive. However, if you catch it early enough you can attempt to treat the condition.\n" +
                "\n" +
                " \n" +
                "\n" +
                "It’s best to isolate the fish in its own treatment tank. You can then add a small amount of Epsom salt.\n" +
                "\n" +
                " \n" +
                "\n" +
                "No more than 1/8 teaspoons per 5 gallons of water. Epsom salts consist of magnesium sulfate, unlike sodium chloride, which is found in regular aquarium salt.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Epsom salt will help to draw out some of the excess water from the fish’s body cavity and tissues.\n" +
                "\n" +
                " \n" +
                "\n" +
                "You should also feed the affected fish with antibacterial food if it’s still eating. Adding Maracyn Two to the tank can also help, as it treats gram negative bacterial infections like Aeromonas.\n" +
                "\n", "Bloat, scales stick out", R.drawable.disease2));
        listDisease.add(new Disease("Fin/Tail Rot", "Potential Causes\n" +
                "Fish TB is caused by one of the several species of Mycobacteria:\n" +
                "\n" +
                "M. fortuitum, M. flavescens, M. chelonae, M. gordonae, M. terrae, M. triviale, M. diernhoferi, M. celatum, M. kansasii, M. intracellulare, and M. marinum.\n" +
                "\n" +
                "And the main cause for this disease is a poorly maintained aquarium. Mycobacterium thrives in low pH values, low oxygen, high soluble zinc, fulvic and humic acids.\n" +
                "\n" +
                "All the problems you’ll encounter if you’ve overstocked or can’t be arsed to maintain your tank.\n" +
                "\n" +
                "Possible Treatments\n" +
                "There is no current vaccine or satisfactory treatment available.\n" +
                "\n" +
                "Once a population of fish is infected, the most likely scenario is euthanasia, followed by disinfecting your tank. It makes little sense to attempt to maintain a population with the illness.\n" +
                "\n" +
                "They’ll have chronic health problems, poor growth, and feed conversion rates. Infected fish will be a constant source of infection.", "Erosion at edges of fins", R.drawable.disease3));
        listDisease.add(new Disease("Hole in the Head", "Potential Causes\n" +
                "Causes of this disease have been long debated, and nobody can say with certainty what the main trigger is.\n" +
                "\n" +
                " \n" +
                "\n" +
                "However, the most accepted cause is the use of activated carbon in closed aquarium ecosystems.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Other factors can include nutritional deficiencies, especially key vitamins and dietary iodine.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Water quality can also play a role. Aquariums with high nitrate levels can cause fish to develop the illness.\n" +
                "\n" +
                "Possible Treatments\n" +
                "If the infected fish has developed pale, eroding holes over its head, it’s in real trouble.\n" +
                "\n" +
                " \n" +
                "\n" +
                "The open lesions provide an entry point for other pathogens which can cause further illnesses.\n" +
                "\n" +
                " \n" +
                "\n" +
                "However, It’s possible for infected fish to improve when all activated carbon is removed and large percentage water changes are performed.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Improvements in nutrition have also been shown to benefit the fish.", "Pale ulcerated area around head", R.drawable.disease4));
        listDisease.add(new Disease("Fish Fungus", "Potential Causes\n" +
                "Poor water quality, decomposing material, and not maintaining your tank properly can lead to Cotton Fin.\n" +
                "\n" +
                " \n" +
                "\n" +
                "However, even if you’re very good at maintaining your tank, old fish or ones suffering from an injury could contract the disease. Especially if your fish is bullied or fighting a lot.\n" +
                "\n" +
                "Possible Treatments\n" +
                "You’ll be pleased to know that treatment is relatively easy, and can be done with fungal treatments.\n" +
                "\n" +
                " \n" +
                "\n" +
                "The basic four used are salt, Methylene blue, Malachite green, and Acriflavine (although this is now getting withdrawn because it can be toxic to humans).\n" +
                "\n" +
                " \n" +
                "\n" +
                "Malachite green is readily available and is known to be effective. You can also add aquarium salt to your tank at a low level of 1-3 grams per litre.", "Whitish, fur-like growths", R.drawable.disease5));
        listDisease.add(new Disease("Camallanus Worms", "Potential Causes\n" +
                "It’s caused by adding fish into your aquarium which is already carrying the parasite or plants that have larvae on them.\n" +
                "\n" +
                "Possible Treatments\n" +
                "Potassium permanganate is considered the best treatment, and it can be used as a tank treatment or dip.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Other options include formalin dip, salt dip, and modern antiparasitics.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Removing the parasite with tweezers is one of the surest ways to get rid of it. You’ll need to take care when doing this.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Make sure you don’t break the tail off; leaving the head inside. Dip the fish back into the water every few seconds so it can breathe.\n" +
                "\n" +
                " \n" +
                "\n" +
                "If the parasite has burrowed deep into your fish this may not be a good idea. Pulling it out could cause more trauma than leaving it in and treating it.", "Red or pink worm protruding from the anus, fish may become listless and bloated, fish refuse to eat", R.drawable.disease6));
        listDisease.add(new Disease("Ammonia Poisoning", "Causes of Ammonia Poisoning\n" +
                "Ammonia can enter the tank through a number of different ways. The first way is through chemically treated tap water. Some water treatment companies use a chemical called chloramine—chlorine bonded to ammonia—as a more stable disinfectant for city water systems. Using tap water that's been treated with this chemical is a recipe to aquarium disaster. The decomposition of organic matter—aquarium plants, fish excrement, and uneaten fish food—is another way ammonia levels rise in tanks.Overfeeding and lack of cleaning add to a buildup of the bacteria that feed on this superfluous matter, resulting in an ammonia byproduct. (So, clean your tank regularly!) Fish, themselves, also contribute to rising ammonia levels in tanks. When a fish eats food, the protein-building process that ensues (in order for them to grow larger) can produce a byproduct that enters their blood. This results in the seepage of ammonia through their gills and into the tank.\n" +
                "\n" +
                "Treatment\n" +
                "If the ammonia level in your tank rises above 1 ppm (part per million) on a standard test kit, begin treatment immediately. Lowering the pH of the water will provide immediate relief, as will a 50 percent water change (be sure the water added is the same temperature as the aquarium). Several water changes within a short period of time may be required to drop the ammonia below 1 ppm.\n" +
                "\n" +
                "If the fish appear to be severely distressed, use a chemical pH control product to neutralize the ammonia. At this point, restrict feedings so that additional waste is reduced. In cases of very high ammonia levels, feedings need to be discontinued for several days. And obviously, no new fish should be added to the tank until both the ammonia and nitrite levels have fallen to zero.", "Red or inflamed gills, fish are gasping for air at the surface", R.drawable.disease7));
        listDisease.add(new Disease("Vorticella", "Vorticella is actually a protozoa of 16 known species, not a fungus at all. Vorticella are aquatic organisms, most commonly found in freshwater habitats. They attach themselves to plant detritus, rocks, algae, or animals (particularly crustaceans).\n" +
                "\n" +
                "Vorticella are heterotrophic organisms. They prey on bacteria. Vorticella use their cilia to create a current of water (vortex) to direct food towards its mouth.\n" +
                "\n" +
                "Typically, Vorticella reproduce via binary fission. The new organism splits from the parent and swims until it can find something on which to anchor itself.\n" +
                "\n" +
                "Known cures:\n" +
                "\n" +
                "Salt bath with aquarium salts. Be careful not to use table salt with Iodine.\n" +
                "Dosage:   1 teaspoon to 1 cup of clean tank water (not tap water).\n" +
                "Duration: 30sec to 1 minute. You might need to repeat this a couple of times until the vorticella disappears, so keep the infected shrimp in a breeder or hospital tank (could be another cup of tank water).\n" +
                "Possible causes: Poor water conditions. Increase water change frequency.\n" +
                "\n" +
                "Low doses of the salt bath have been know to be ineffective.\n" +
                "Ick and fungus cure medicines don't work on Vorticella.\n" +
                "Seachem Paraguard could work as well at the full recommended dosage, since this is a parasitic med. But Seachem have admitted Paraguard isn't invertebrate-safe. So only try paraguard as a last resort and drip it into the tank premixed from a bucket of tank water slowly .\n" +
                "\n" +
                "This treatment is in no way a replacement for good tank husbandry.", "Cilia like white organisms on mouth of the shrimp", R.drawable.disease8));


    }

}
