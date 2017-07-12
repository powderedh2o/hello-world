package com.pw.helloworld.destination2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;

public class Destination2Fragment extends Fragment {

    public static Destination2Fragment newInstance() {
        return new Destination2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination2, container, false);
    }
}
