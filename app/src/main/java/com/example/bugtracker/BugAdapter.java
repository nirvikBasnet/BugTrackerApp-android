package com.example.bugtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BugAdapter extends RecyclerView.Adapter<BugAdapter.BugHolder> {
    private List<Bug> bugs = new ArrayList<>();

    @NonNull
    @Override
    public BugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bug_item,parent,false);

        return new BugHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BugHolder holder, int position) {

        Bug currentBug = bugs.get(position);
        holder.textViewTitle.setText(currentBug.getTitle());
        holder.textViewDescription.setText(currentBug.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentBug.getPriority()));

    }

    @Override
    public int getItemCount() {
        return bugs.size();
    }

    public void setBugs(List<Bug> bugs){
        this.bugs = bugs;
        notifyDataSetChanged();
    }

    class BugHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public BugHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
