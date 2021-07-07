package com.selimhorri.pack.pattern.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.admin.dept.AdminDepartmentEditActivity;
import com.selimhorri.pack.activity.admin.dept.AdminDepartmentListActivity;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.impl.DepartmentServiceImpl;

import java.util.List;

public class AdminDepartmentListCustomAdapter extends RecyclerView.Adapter<AdminDepartmentListCustomAdapter.ViewHolder> {

    private List<Department> departments;
    private Context context;
    private final DepartmentService departmentService;

    public AdminDepartmentListCustomAdapter(List<Department> departments, Context context) {
        this.departments = departments;
        this.context = context;
        this.departmentService = new DepartmentServiceImpl(context);
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
        holder.btnEdit.setOnClickListener(v -> {
            this.context.startActivity(
                    new Intent(this.context, AdminDepartmentEditActivity.class)
                        .putExtra("departmentId", department.getDepartmentId())
            );
        });
        holder.btnDelete.setOnClickListener(v -> {
            this.departmentService.deleteById(
                    department.getDepartmentId(),
                    response -> {
                        if (!response)
                            Toast.makeText(this.context, "department not deleted, problem happened!", Toast.LENGTH_SHORT).show();
                        else
                            this.context.startActivity(new Intent(this.context, AdminDepartmentListActivity.class));
                    },
                    error -> Toast.makeText(this.context, error.toString(), Toast.LENGTH_SHORT).show()
            );
        });
    }

    @Override
    public int getItemCount() {
        return this.departments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDepartmentName;
        private TextView textViewLocation;
        private Button btnEdit;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewDepartmentName = itemView.findViewById(R.id.textViewAdminDepartmentListDepartmentName);
            this.textViewLocation = itemView.findViewById(R.id.textViewAdminDepartmentListLocation);
            this.btnEdit = itemView.findViewById(R.id.buttonAdminDepartmentListEdit);
            this.btnDelete = itemView.findViewById(R.id.buttonAdminDepartmentListDelete);
        }


    }



}
