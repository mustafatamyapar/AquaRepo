package com.example.aquaassistant.mustafa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.aquaassistant.R;
/**
 * A class for difficulty dialog. Which will show how we differed 5 difficulty segments.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 18.05.2020 Created activity
 */
public class DifficultyExp extends AppCompatDialogFragment {

    TextView info;
    /**
     * We are using this method to create a dialog.
     * @param savedInstanceState
     * @return is a Dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.difficulty_dialog, null);
        builder.setView(view)
                .setTitle("Difficulty")
                .setPositiveButton("Okay,Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        info = view.findViewById(R.id.info);
        info.setText("Hey, dear aquarium enthusiast" + "\n" + "We created difficulty levels for our creatures to guide you." + "Here is the list of difficulty levels: " + "\n"
                + "5.0 it needs extra care" + "\n" + "4.0 reasonable care but still hard for new starters" + "\n" + "3.0 normal care  ok for anyone" + "\n" + "2.0 simple care, recommended for new starters." + "\n" + "1.0 easy, good for kids");
        return builder.create();
    }
}
