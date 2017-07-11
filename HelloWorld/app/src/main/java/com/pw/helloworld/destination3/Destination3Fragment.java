package com.pw.helloworld.destination3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;

public class Destination3Fragment extends Fragment {

    public static Destination3Fragment newInstance() {
        return new Destination3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination3, container, false);
    }
}
