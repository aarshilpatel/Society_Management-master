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

import com.example.flattemp.Model.Staff_Model;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Staff_Adapter extends RecyclerView.Adapter<Staff_Adapter.StaffViewHolder> {
    private Context mContext;
    private List<Staff_Model> mUploads;
    private OnItemClickListener mListener;
    public Staff_Adapter(Context mContext, List<Staff_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.vendor_details, parent, false);
        return new StaffViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StaffViewHolder holder, int position) {
        final Staff_Model model = mUploads.get(position);

        holder.staff_name.setText(model.getStaff_name());
        holder.staff_post.setText(model.getStaff_post());
        holder.staff_about.setText(model.getStaff_about());

        final String imgurl= UrlsList.pdf_storage +model.getStaff_img();
        Picasso.with(mContext)
                .load(imgurl)
                .fit()
                .into(holder.staff_img);

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img=uploadCurrent.getGallery_img();
                String  name=uploadCurrent.getGallery_img_name();
                Intent i= new Intent(mContext, Shoesingleimage.class);
                i.putExtra("img",imgurl);
                i.putExtra("name",name);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView staff_name,staff_post,staff_about;
        public ImageView staff_img;
        public StaffViewHolder(View itemView) {

            super(itemView);
            staff_name = itemView.findViewById(R.id.vandor_contact);
            staff_post = itemView.findViewById(R.id.vandor_name);
            staff_about = itemView.findViewById(R.id.vandor_details);
            staff_img = itemView.findViewById(R.id.vandor_profileImage);


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