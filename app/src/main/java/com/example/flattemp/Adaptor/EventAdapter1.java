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

import com.example.flattemp.Model.Event;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manoranjan on 4/23/2018.
 */

public class EventAdapter1 extends RecyclerView.Adapter<EventAdapter1.ImageViewHolder> {
    private Context mContext;
    private List<Event> mUploads;
    private OnItemClickListener mListener;
    String like="no";
    public EventAdapter1(Context mContext, List<Event> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.wall_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        Event uploadCurrent = mUploads.get(position);
String imgurl= UrlsList.pdf_storage +uploadCurrent.getUpdate_upcoming_events_img();
       Picasso.with(mContext)
                .load(imgurl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.imageView);
        holder.textViewName.setText(uploadCurrent.getUpdate_upcoming_events());
       holder.desc.setText(uploadCurrent.getUpdate_upcoming_events_date());
       holder.imglike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (like.equals("no"))
               {
                   holder.imglike.setImageResource(R.drawable.ic_unlike);
                   holder.likecount.setText("4 Likes");
                   like="yes";
               }else
               if (like.equals("yes")){
                   holder.likecount.setText("3 Likes");
                   holder.imglike.setImageResource(R.drawable.ic_like);
                   like="no";
               }

           }
       });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView textViewName,desc,likecount;
        public ImageView imageView,imglike;
         public ImageViewHolder(View itemView) {

             super(itemView);
             textViewName = itemView.findViewById(R.id.text_view_name);
             desc=itemView.findViewById(R.id.text_view_desc);
             imageView = itemView.findViewById(R.id.image_view_upload);
             imglike=itemView.findViewById(R.id.imglike);
             likecount=itemView.findViewById(R.id.likecount);
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