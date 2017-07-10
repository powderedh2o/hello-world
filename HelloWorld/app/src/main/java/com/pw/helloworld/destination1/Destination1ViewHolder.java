package com.pw.helloworld.destination1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pw.helloworld.R;

public class Destination1ViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView message;

    public Destination1ViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.destination_1_item_title);
        message = itemView.findViewById(R.id.destination_1_item_message);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getMessage() {
        return message;
    }
}
