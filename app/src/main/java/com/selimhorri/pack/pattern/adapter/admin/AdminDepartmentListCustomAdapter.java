package com.selimhorri.pack.pattern.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.Department;

import java.util.List;

public class AdminDepartmentListCustomAdapter extends RecyclerView.Adapter<AdminDepartmentListCustomAdapter.ViewHolder> {

    private List<Department> departments;
    private Context context;

    public AdminDepartmentListCustomAdapter(List<Department> departments, Context context) {
        this.departments = departments;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminDepartmentListCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_department_list_item, parent, false);

        return new AdminDepartmentListCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDepartmentListCustomAdapter.ViewHolder holder, int position) {

        Department department = this.departments.get(position);
        holder.textViewDepartmentName.setText(department.getDepartmentName());
        holder.textViewLocation.setText(String.format("%s, %s, %s", department.getLocation().getAdr(), department.getLocation().getPostalCode(), department.getLocation().getCity()));
    }

    @Override
    public int getItemCount() {
        return this.departments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDepartmentName;
        private TextView textViewLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewDepartmentName = itemView.findViewById(R.id.textViewAdminDepartmentListDepartmentName);
            this.textViewLocation = itemView.findViewById(R.id.textViewAdminDepartmentListLocation);
        }


    }



}
