package com.pw.helloworld.destination1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pw.helloworld.R;
import com.pw.helloworld.rest.AugmentedUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class Destination1Adapter extends RecyclerView.Adapter<Destination1ViewHolder> {

    private final List<AugmentedUser> users;

    @Inject
    public Destination1Adapter() {
        users = new ArrayList<>();
    }

    @Override
    public Destination1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_destination_1, parent, false);
        return new Destination1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Destination1ViewHolder holder, int position) {
        final AugmentedUser user = users.get(position);
        holder.username.setText(user.getUsername());
        holder.name.setText(user.getName());
        holder.posts.setText(String.valueOf(user.getPosts()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<AugmentedUser> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }
}
