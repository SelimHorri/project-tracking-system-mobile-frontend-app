package com.selimhorri.pack.activity.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.EmployeeShowCommitsActivity;
import com.selimhorri.pack.model.dto.custom.EmployeeProjectData;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<EmployeeProjectData> employeeProjectDataList;
    private int layout = R.layout.activity_employee_project_data_item;
    private Context context;

    public CustomAdapter(List<EmployeeProjectData> employeeProjectDataList, final int layout, Context context) {
        this.employeeProjectDataList = employeeProjectDataList;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                        .inflate(layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        EmployeeProjectData epd = this.employeeProjectDataList.get(position);
        holder.textViewTitle.setText(epd.getTitle());
        holder.textViewStartDate.setText("Start date: " + epd.getStartDate());
        holder.textViewEndDate.setText("End date: " + epd.getEndDate());
        holder.textViewStatus.setText("Status: "+ epd.getStatus());
        holder.btnMyCommits.setOnClickListener(v -> {
            this.context.startActivity(new Intent(this.context, EmployeeShowCommitsActivity.class).putExtra("projectId", epd.getProjectId()));
        });
        holder.btnAllCommits.setOnClickListener(v -> {

        });
        holder.btnNewCommit.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return this.employeeProjectDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewStartDate;
        private TextView textViewEndDate;
        private TextView textViewStatus;
        private Button btnMyCommits;
        private Button btnAllCommits;
        private Button btnNewCommit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
            this.textViewStartDate = itemView.findViewById(R.id.textViewStartDate);
            this.textViewEndDate = itemView.findViewById(R.id.textViewEndDate);
            this.textViewStatus = itemView.findViewById(R.id.textViewStatus);
            this.btnMyCommits = itemView.findViewById(R.id.buttonMyCommits);
            this.btnAllCommits = itemView.findViewById(R.id.buttonAllCommits);
            this.btnNewCommit = itemView.findViewById(R.id.buttonNewCommit);
        }



    }



}
