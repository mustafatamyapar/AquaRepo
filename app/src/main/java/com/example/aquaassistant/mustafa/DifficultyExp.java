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

public class DifficultyExp extends AppCompatDialogFragment {

    TextView info;
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
        info.setText("It is a good text");
        return builder.create();
    }
}
