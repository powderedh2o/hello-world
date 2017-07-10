package com.pw.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Destination1Fragment extends Fragment {

    public Destination1Fragment() {
    }

    public static Destination1Fragment newInstance() {
        return new Destination1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination1, container, false);
    }
}
