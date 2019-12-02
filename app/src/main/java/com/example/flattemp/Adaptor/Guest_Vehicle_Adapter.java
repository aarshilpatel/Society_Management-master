package com.example.flattemp.Adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flattemp.Model.Guest_Vehicle_Model;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Guest_Vehicle_Adapter extends RecyclerView.Adapter<Guest_Vehicle_Adapter.ImageViewHolder> {
    private Context mContext;
    private List<Guest_Vehicle_Model> mUploads;
    private OnItemClickListener mListener;
    public Guest_Vehicle_Adapter(Context mContext, List<Guest_Vehicle_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.guest_vehicle_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Guest_Vehicle_Model uploadCurrent = mUploads.get(position);

        holder.visitor_name.setText(uploadCurrent.getVisitor_name());
        holder.contact_no.setText(uploadCurrent.getVisitor_phone_num());
        holder.vehicle_type_no.setText(uploadCurrent.getVehicle_type()+" Vehicle "+uploadCurrent.getVehicle_num());
        holder.block_flat_no.setText(uploadCurrent.getMem_block_num()+uploadCurrent.getMem_flat_num());


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView visitor_name,contact_no,vehicle_type_no,block_flat_no;

        public ImageViewHolder(View itemView) {

            super(itemView);
            visitor_name = itemView.findViewById(R.id.visitor_name);
            contact_no = itemView.findViewById(R.id.contact_no);
            vehicle_type_no = itemView.findViewById(R.id.vehicle_type_no);
            block_flat_no = itemView.findViewById(R.id.block_flat_no);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (menuItem.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select Action");
            MenuItem doWhatever = contextMenu.add(Menu.NONE, 1, 1, "Update");
            MenuItem delete = contextMenu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}