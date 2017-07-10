package com.pw.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Destination2Fragment extends Fragment {

    public Destination2Fragment() {
    }

    public static Destination2Fragment newInstance() {
        return new Destination2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination2, container, false);
    }
}
