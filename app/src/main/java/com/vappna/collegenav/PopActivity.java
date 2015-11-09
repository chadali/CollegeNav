package com.vappna.collegenav;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PopActivity extends DialogFragment {

    LayoutInflater layoutInflater;
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        layoutInflater = getActivity().getLayoutInflater();





        return super.onCreateDialog(savedInstanceState);
    }
}
