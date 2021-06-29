package com.selimhorri.pack.pattern.adapter.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.Employee;

import java.util.List;

public class ManagerTeamCustomAdapter extends RecyclerView.Adapter<ManagerTeamCustomAdapter.ViewHolder> {

    private List<Employee> employees;
    private Context context;

    public ManagerTeamCustomAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public ManagerTeamCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_manager_item, parent, false);

        return new ManagerTeamCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerTeamCustomAdapter.ViewHolder holder, int position) {

        Employee employee = this.employees.get(position);
        holder.textViewFirstName.setText(employee.getFirstName());
        holder.textViewLastName.setText(employee.getLastName());
        holder.textViewEmail.setText(employee.getEmail());
        holder.textViewPhone.setText(employee.getPhone());
        holder.textViewHiredate.setText( (employee.getHiredate() != null)? employee.getHiredate() : "--" );
        holder.textViewJob.setText(employee.getJob());
        holder.textViewDepartment.setText(employee.getDepartment().getDepartmentName());
        holder.textViewLocation.setText(employee.getDepartment().getLocation().getAdr() + "-" + employee.getDepartment().getLocation().getPostalCode() + "-" + employee.getDepartment().getLocation().getCity());
    }

    @Override
    public int getItemCount() {
        return this.employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFirstName;
        private TextView textViewLastName;
        private TextView textViewEmail;
        private TextView textViewPhone;
        private TextView textViewHiredate;
        private TextView textViewJob;
        private TextView textViewDepartment;
        private TextView textViewLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewFirstName = itemView.findViewById(R.id.textViewManagerTeamFirstName);
            this.textViewLastName = itemView.findViewById(R.id.textViewManagerTeamLastName);
            this.textViewEmail = itemView.findViewById(R.id.textViewManagerTeamEmail);
            this.textViewPhone = itemView.findViewById(R.id.textViewManagerTeamPhone);
            this.textViewHiredate = itemView.findViewById(R.id.textViewManagerTeamHiredate);
            this.textViewJob = itemView.findViewById(R.id.textViewManagerTeamJob);
            this.textViewDepartment = itemView.findViewById(R.id.textViewManagerTeamDepartment);
            this.textViewLocation = itemView.findViewById(R.id.textViewManagerTeamLocation);
        }


    }



}
