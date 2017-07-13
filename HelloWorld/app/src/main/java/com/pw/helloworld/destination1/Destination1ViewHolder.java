package com.pw.helloworld.destination1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pw.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Destination1ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.destination_1_item_username)
    TextView username;

    @BindView(R.id.destination_1_item_name)
    TextView name;

    @BindView(R.id.destination_1_item_posts)
    TextView posts;

    public Destination1ViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
