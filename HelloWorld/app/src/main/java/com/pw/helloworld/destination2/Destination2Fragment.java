package com.pw.helloworld.destination2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;
import com.pw.helloworld.dagger.YourActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Destination2Fragment extends Fragment {

    private Unbinder unbinder;

    public static Destination2Fragment newInstance() {
        return new Destination2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.start_your_activity)
    void onStartYourActivityClicked() {
        startActivity(new Intent(getActivity(), YourActivity.class));
    }
}
