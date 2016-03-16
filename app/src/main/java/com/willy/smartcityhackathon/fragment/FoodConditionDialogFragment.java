package com.willy.smartcityhackathon.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.willy.smartcityhackathon.App;
import com.willy.smartcityhackathon.R;

/**
 * Created by Willy on 2016/3/12.
 */
public class FoodConditionDialogFragment extends DialogFragment {

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = inflater.inflate(R.layout.fragment_condition_food, container);
//        return view;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_condition_food, null);

        final EditText staple = (EditText)view.findViewById(R.id.edt_staple);
        final EditText snack = (EditText)view.findViewById(R.id.edt_snack);
        final EditText drink = (EditText)view.findViewById(R.id.edt_drink);


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                App.getInstance().sFoodCondition.setStapleBudget(Integer.valueOf(staple.getText().toString()));
                                App.getInstance().sFoodCondition.setSnackBudget(Integer.valueOf(snack.getText().toString()));
                                App.getInstance().sFoodCondition.setDrinkBudget(Integer.valueOf(drink.getText().toString()));
                            }
                        });
        return builder.create();
    }

}
