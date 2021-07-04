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
import com.selimhorri.pack.activity.admin.loc.AdminLocationEditActivity;
import com.selimhorri.pack.activity.admin.loc.AdminLocationListActivity;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.service.LocationService;
import com.selimhorri.pack.service.impl.LocationServiceImpl;

import java.util.List;

public class AdminLocationListCustomAdapter extends RecyclerView.Adapter<AdminLocationListCustomAdapter.ViewHolder> {

    private List<Location> locations;
    private Context context;
    private final LocationService locationService;

    public AdminLocationListCustomAdapter(List<Location> locations, Context context) {
        this.locations = locations;
        this.context = context;
        this.locationService = new LocationServiceImpl(context);
    }

    @NonNull
    @Override
    public AdminLocationListCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_location_list_item, parent, false);

        return new AdminLocationListCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminLocationListCustomAdapter.ViewHolder holder, int position) {

        Location location = this.locations.get(position);
        holder.textViewAdr.setText(location.getAdr());
        holder.textViewPostalCode.setText(location.getPostalCode());
        holder.textViewCity.setText(location.getCity());
        holder.btnEdit.setOnClickListener(v -> {
            this.context.startActivity(
                    new Intent(this.context, AdminLocationEditActivity.class)
                        .putExtra("locationId", location.getLocationId())
            );
        });
        holder.btnDelete.setOnClickListener(v -> {
            this.locationService.deleteById(
                    location.getLocationId(),
                    response -> {
                        if (!response)
                            Toast.makeText(this.context, "Location not deleted, problem happened!", Toast.LENGTH_SHORT).show();
                        else
                            this.context.startActivity(new Intent(this.context, AdminLocationListActivity.class));
                    },
                    error -> Toast.makeText(this.context, error.toString(), Toast.LENGTH_SHORT).show()
            );
        });
    }

    @Override
    public int getItemCount() {
        return this.locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAdr;
        private TextView textViewPostalCode;
        private TextView textViewCity;
        private Button btnEdit;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewAdr = itemView.findViewById(R.id.textViewAdminLocationListAdr);
            this.textViewPostalCode = itemView.findViewById(R.id.textViewAdminLocationListPostalCode);
            this.textViewCity = itemView.findViewById(R.id.textViewAdminLocationListCity);
            this.btnEdit = itemView.findViewById(R.id.buttonAdminLocationListEdit);
            this.btnDelete = itemView.findViewById(R.id.buttonAdminLocationListDelete);
        }


    }



}
