package com.example.flattemp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flattemp.GalleryActivity;
import com.example.flattemp.Model.Event;
import com.example.flattemp.Model.Gallery;
import com.example.flattemp.Model.GalleryCatagory;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;
import com.example.flattemp.Shoesingleimage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manoranjan on 4/23/2018.
 */

public class GallerycatagoryAdaptor extends RecyclerView.Adapter<GallerycatagoryAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<GalleryCatagory> mUploads;
    private OnItemClickListener mListener;
    public GallerycatagoryAdaptor(Context mContext, List<GalleryCatagory> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.gallerycat_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final GalleryCatagory uploadCurrent = mUploads.get(position);
final String imgurl= UrlsList.pdf_storage +uploadCurrent.getIcon();
       Picasso.with(mContext)
                .load(imgurl)
               // .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.iconimg);
       holder.gallary_date.setText(uploadCurrent.getDate());
        holder.tcat.setText(uploadCurrent.getCatagory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(mContext, GalleryActivity.class);
                i.putExtra("icon",imgurl);
                i.putExtra("cat",uploadCurrent.getCatagory());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView tcat,gallary_date;
        public ImageView iconimg;
         public ImageViewHolder(View itemView) {

             super(itemView);

            tcat=itemView.findViewById(R.id.tcatagory);
             gallary_date=itemView.findViewById(R.id.gallary_date);
             iconimg = itemView.findViewById(R.id.iconimg);
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