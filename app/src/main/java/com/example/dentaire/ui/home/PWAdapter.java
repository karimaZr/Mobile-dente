package com.example.dentaire.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentaire.R;
import com.example.dentaire.ui.PW;

import java.util.ArrayList;
import java.util.List;

public class PWAdapter extends RecyclerView.Adapter<PWAdapter.PWViewHolder> {

    private List<PW> pwList = new ArrayList<>();
    private View.OnClickListener imageClickListener;

    // Setter for the click listener
    public void setImageClickListener(View.OnClickListener clickListener) {
        this.imageClickListener = clickListener;
    }
    @NonNull
    @Override
    public PWViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pw, parent, false);
        return new PWViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PWViewHolder holder, int position) {
        PW pw = pwList.get(position);

        holder.titleTextView.setText(pw.getTitle());
        holder.objectifTextView.setText(pw.getObjectif());
        holder.profile_image_view.setOnClickListener(imageClickListener);


    }

    @Override
    public int getItemCount() {
        return pwList.size();
    }

    public void setPWList(List<PW> pwList) {
        this.pwList.clear();
        this.pwList.addAll(pwList);
        notifyDataSetChanged();
    }


    static class PWViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView objectifTextView;
        ImageView profile_image_view;

        public PWViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            objectifTextView = itemView.findViewById(R.id.objectifTextView);
            profile_image_view = itemView.findViewById(R.id.profile_image_view);  // Add this line
        }
    }

}
