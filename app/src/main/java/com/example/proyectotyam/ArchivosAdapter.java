package com.example.proyectotyam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArchivosAdapter extends RecyclerView.Adapter<ArchivosAdapter.ViewHolder> {

    private List<Archivos> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ArchivosAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Archivos item);
    }

    public ArchivosAdapter(List<Archivos> itemList, Context context, ArchivosAdapter.OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ArchivosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_archivos_cv, null);
        return new ArchivosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArchivosAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Archivos> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImagenView);
            name = itemView.findViewById(R.id.nameTextView);
        }

        void bindData(final Archivos item) {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getNomb_arch());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}