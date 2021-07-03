package com.selimhorri.pack.pattern.adapter.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.Employee;

import java.util.List;

public class ManagerAddProjectCustomAdapter extends RecyclerView.Adapter<ManagerAddProjectCustomAdapter.ViewHolder> {

    private List<Employee> employees;
    private Context context;
    /*
    private CheckboxSelectionListener<Integer> selectionListener;
    private final List<Integer> list = new ArrayList<>();
     */

    public ManagerAddProjectCustomAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public ManagerAddProjectCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_assign_to_item, parent, false);

        return new ManagerAddProjectCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerAddProjectCustomAdapter.ViewHolder holder, int position) {
        Employee employee = this.employees.get(position);
        holder.checkBoxAssignTo.setText(String.format("%s %s", employee.getFirstName(), employee.getLastName()));

        /*
        if (holder.checkBoxAssignTo.isChecked())
            this.list.add(employee.getEmployeeId());
        else
            this.list.remove(employee.getEmployeeId());
        this.selectionListener.onSelect(list);
         */

    }

    @Override
    public int getItemCount() {
        return this.employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBoxAssignTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBoxAssignTo = itemView.findViewById(R.id.checkboxManagerAddProjectAssignTo);
        }


    }



}
