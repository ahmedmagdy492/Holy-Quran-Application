package com.migo.quran.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.migo.quran.R;

import java.util.ArrayList;

public class TafsirAdapter extends RecyclerView.Adapter<TafsirAdapter.TafsirViewHolder>
{
    private Context context;
    private ArrayList<Tafsir> tafsirs;

    public TafsirAdapter(Context context) {
        this.context = context;
        tafsirs = new ArrayList<>();
    }

    @NonNull
    @Override
    public TafsirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tafsir_element, parent, false);
        return new TafsirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TafsirViewHolder holder, int position) {
        holder.txtResource.setText(tafsirs.get(position).getResource_name());
        holder.txtTafsir.setText(tafsirs.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return tafsirs.size();
    }

    public void setTafsirs(ArrayList<Tafsir> tafsirs) {
        this.tafsirs = tafsirs;
    }

    public class TafsirViewHolder extends RecyclerView.ViewHolder{

        private TextView txtResource;
        private TextView txtTafsir;

        public TafsirViewHolder(@NonNull View itemView) {
            super(itemView);
            txtResource = itemView.findViewById(R.id.resourceName);
            txtTafsir = itemView.findViewById(R.id.txtTafsir);
        }
    }
}
