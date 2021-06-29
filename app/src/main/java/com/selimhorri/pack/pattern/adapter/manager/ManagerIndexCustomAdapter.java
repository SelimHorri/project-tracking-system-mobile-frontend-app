package com.selimhorri.pack.pattern.adapter.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.custom.ManagerProjectData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ManagerIndexCustomAdapter extends RecyclerView.Adapter<ManagerIndexCustomAdapter.ViewHolder> {

    private List<ManagerProjectData> managerProjectDataList;
    private Context context;

    public ManagerIndexCustomAdapter(List<ManagerProjectData> managerProjectDataList, Context context) {
        this.managerProjectDataList = managerProjectDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ManagerIndexCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_manager_project_data_item, parent, false);

        return new ManagerIndexCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerIndexCustomAdapter.ViewHolder holder, int position) {

        ManagerProjectData epd = this.managerProjectDataList.get(position);
        holder.textViewTitle.setText(epd.getTitle());
        holder.textViewStartDate.setText("Start date: " + LocalDate.parse(epd.getStartDate()).format(DateTimeFormatter.ofPattern("dd-M-yyyy")));
        holder.textViewEndDate.setText("End date: " + LocalDate.parse(epd.getEndDate()).format(DateTimeFormatter.ofPattern("dd-M-yyyy")));
        holder.textViewStatus.setText("Status: "+ epd.getStatus());
        holder.btnCommits.setOnClickListener(v -> {

        });
        holder.btnAssign.setOnClickListener(v -> {

        });
        holder.btnEdit.setOnClickListener(v -> {

        });
        holder.btnDelete.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return this.managerProjectDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewStartDate;
        private TextView textViewEndDate;
        private TextView textViewStatus;
        private Button btnCommits;
        private Button btnAssign;
        private Button btnEdit;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewManagerIndexTitle);
            this.textViewStartDate = itemView.findViewById(R.id.textViewManagerIndexStartDate);
            this.textViewEndDate = itemView.findViewById(R.id.textViewManagerIndexEndDate);
            this.textViewStatus = itemView.findViewById(R.id.textViewManagerIndexStatus);
            this.btnCommits = itemView.findViewById(R.id.buttonManagerIndexCommits);
            this.btnAssign = itemView.findViewById(R.id.buttonManagerIndexAssign);
            this.btnEdit = itemView.findViewById(R.id.buttonManagerIndexEdit);
            this.btnDelete = itemView.findViewById(R.id.buttonManagerIndexDelete);
        }



    }



}
