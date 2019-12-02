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

import com.example.flattemp.Model.Notice_Model;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.Model.Vendor_Model;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Vendor_Adapter extends RecyclerView.Adapter<Vendor_Adapter.VendorViewHolder> {
    private Context mContext;
    private List<Vendor_Model> mUploads;
    private OnItemClickListener mListener;
    public Vendor_Adapter(Context mContext, List<Vendor_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.vendor_details, parent, false);
        return new VendorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VendorViewHolder holder, int position) {
        final Vendor_Model model = mUploads.get(position);

        holder.shop_name.setText(model.getShop_name());
        holder.owner_name.setText(model.getOwner_name());
        holder.about_vendor.setText(model.getAbout_vendor());

          final String imgurl= UrlsList.pdf_storage +model.getVendor_img();
        Picasso.with(mContext)
                .load(imgurl)
                .fit()
                .into(holder.vendor_img);

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

    public class VendorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView shop_name,owner_name,about_vendor;
        public ImageView vendor_img;
        public VendorViewHolder(View itemView) {

            super(itemView);
            shop_name = itemView.findViewById(R.id.vandor_contact);
            owner_name = itemView.findViewById(R.id.vandor_name);
            about_vendor = itemView.findViewById(R.id.vandor_details);
            vendor_img = itemView.findViewById(R.id.vandor_profileImage);


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