package com.pw.helloworld.destination1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;

import java.util.ArrayList;
import java.util.List;

class Destination1Adapter extends RecyclerView.Adapter<Destination1ViewHolder> {

    private final List<Destination1Item> items;

    public Destination1Adapter() {
        items = new ArrayList<>();
        items.add(new Destination1Item("Title 1", String.valueOf(Math.random())));
        items.add(new Destination1Item("Title 2", String.valueOf(Math.random())));
        items.add(new Destination1Item("Title 3", String.valueOf(Math.random())));
    }

    @Override
    public Destination1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_destination_1, parent, false);
        return new Destination1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Destination1ViewHolder holder, int position) {
        final Destination1Item item = items.get(position);
        holder.getTitle().setText(item.getTitle());
        holder.getMessage().setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
