package com.pw.helloworld.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pw.helloworld.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class YourActivity extends AppCompatActivity {

    @BindView(R.id.your_activity_message)
    TextView message;

    @Inject
    MessageGenerator messageGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your);

        ButterKnife.bind(this);

        message.setText(messageGenerator.generateMessage(MessageGenerator.Source.ACTIVITY));
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, YourFragment.newInstance()).commit();
    }
}
