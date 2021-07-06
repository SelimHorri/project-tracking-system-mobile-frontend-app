package com.selimhorri.pack.pattern.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.Employee;

import java.util.List;

public class AdminEmployeeListCustomAdapter extends RecyclerView.Adapter<AdminEmployeeListCustomAdapter.ViewHolder> {

    private List<Employee> employees;
    private Context context;

    public AdminEmployeeListCustomAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminEmployeeListCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_employee_list_item, parent, false);

        return new AdminEmployeeListCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminEmployeeListCustomAdapter.ViewHolder holder, int position) {

        Employee employee = this.employees.get(position);
        // holder.textViewAdr.setText(employee.getAdr());
    }

    @Override
    public int getItemCount() {
        return this.employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // private TextView textViewAdr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // this.textViewAdr = itemView.findViewById(R.id.textViewAdminEmployeeListAdr);
        }


    }



}
