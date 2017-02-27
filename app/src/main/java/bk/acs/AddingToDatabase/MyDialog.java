package bk.acs.AddingToDatabase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bk.acs.R;

/**
 * Created by koteswarao on 27-02-2017.
 */

public class MyDialog extends DialogFragment {
    View v;
    EditText et1,et2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater inflater=getActivity().getLayoutInflater();
        v=inflater.inflate(R.layout.add_dialog,null);
        et1=(EditText)v.findViewById(R.id.subjectName);
        et2=(EditText)v.findViewById(R.id.fname);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String subname=et1.getText().toString(),fname=et2.getText().toString();
                Toast.makeText(getActivity(), subname+"\n"+fname, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
}
