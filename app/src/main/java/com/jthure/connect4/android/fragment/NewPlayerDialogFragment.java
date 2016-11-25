package com.jthure.connect4.android.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.jthure.connect4.R;

/**
 * Created by Jonas on 2016-11-25.
 */

public class NewPlayerDialogFragment extends DialogFragment {
    private EditText nameEditText;
    private Callback callback;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View v = View.inflate(getContext(),R.layout.fragment_dialog_new_player,null);
        nameEditText =(EditText)v.findViewById(R.id.et_new_player_name);
        builder.setView(v);
        builder.setTitle("Title");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = nameEditText.getText().toString();
                if(!name.equals(""))
                    callback.onNewPlayer(name);
                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    public void setCallback(Callback callback){
        this.callback=callback;
    }


    public interface Callback{
        void onNewPlayer(String name);
    }
}
