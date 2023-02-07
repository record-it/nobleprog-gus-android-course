package pl.gus.app.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import pl.gus.app.R;
import pl.gus.app.form.*;

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserViewModel> users;

    public RecyclerViewAdapter(List<UserViewModel> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mFirstNameView.setText(users.get(position).getFirstName());
        holder.mSwitch.setChecked(users.get(position).getLastName() != null);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mFirstNameView;
        public final Switch mSwitch;
        public ViewHolder(@NonNull View view) {
            super(view);
            mFirstNameView = view.findViewById(R.id.recyler_firstName);
            mSwitch = view.findViewById(R.id.recycler_switch);
        }
    }


}
