package com.selimhorri.pack.pattern.adapter;

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

public class EmployeeTeamCustomAdapter extends RecyclerView.Adapter<EmployeeTeamCustomAdapter.ViewHolder> {

    private List<Employee> employees;
    private Context context;

    public EmployeeTeamCustomAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_employee_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeTeamCustomAdapter.ViewHolder holder, int position) {

        Employee employee = this.employees.get(position);
        // holder.textViewTitle.setText(epd.getTitle());
        holder.textViewFirstName.setText(employee.getFirstName());
        holder.textViewLastName.setText(employee.getLastName());
        holder.textViewEmail.setText(employee.getEmail());
        holder.textViewPhone.setText(employee.getPhone());
        holder.textViewHiredate.setText(employee.getHiredate().toString());
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
            this.textViewFirstName = itemView.findViewById(R.id.textViewEmployeeTeamFirstName);
            this.textViewLastName = itemView.findViewById(R.id.textViewEmployeeTeamLastName);
            this.textViewEmail = itemView.findViewById(R.id.textViewEmployeeTeamEmail);
            this.textViewPhone = itemView.findViewById(R.id.textViewEmployeeTeamPhone);
            this.textViewHiredate = itemView.findViewById(R.id.textViewEmployeeTeamHiredate);
            this.textViewJob = itemView.findViewById(R.id.textViewEmployeeTeamJob);
            this.textViewDepartment = itemView.findViewById(R.id.textViewEmployeeTeamDepartment);
            this.textViewLocation = itemView.findViewById(R.id.textViewEmployeeTeamLocation);
        }


    }



}
