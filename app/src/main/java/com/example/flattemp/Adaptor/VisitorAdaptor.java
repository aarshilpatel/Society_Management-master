package com.example.flattemp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.Model.Visitormodel;
import com.example.flattemp.R;
import com.example.flattemp.ShowSingleVisitor;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VisitorAdaptor extends RecyclerView.Adapter<VisitorAdaptor.ImageViewHolder> {

        private Context mContext;
        private List<Visitormodel> mUploads;
        private OnItemClickListener mListener;
        public VisitorAdaptor(Context mContext, List<Visitormodel> mUploads) {
            this.mContext = mContext;
            this.mUploads = mUploads;
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.visitor_bydate_item, parent, false);
            return new ImageViewHolder(v);
        }



    @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
          final Visitormodel uploadCurrent = mUploads.get(position);
         // holder.date.setText(uploadCurrent.getDate());
             String url= UrlsList.pdf_storage +uploadCurrent.getVisitor_img();

        Picasso.with(mContext)
                .load(url)
                .fit()
                .into(holder.profilepic);
        holder.name.setText("Visitor Name : "+uploadCurrent.getVisitor_name());
        holder.contact.setText("Visitor Mob No: "+uploadCurrent.getVisitor_phone_num());
        holder.flatno.setText("Flat No: "+uploadCurrent.getMem_flat_num());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String visitor_id,visitor_time,guard_name,visitor_name,visitor_phone_num,mem_block_num,
                // mem_flat_num,visitor_purpose,visitor_img,visitor_vechile_img;
                Intent i=new Intent(mContext, ShowSingleVisitor.class);
                i.putExtra("visitor_id",uploadCurrent.getVisitor_id());
                i.putExtra("visitor_time",uploadCurrent.getVisitor_time());
                i.putExtra("guard_name",uploadCurrent.getGuard_name());
                i.putExtra("visitor_name",uploadCurrent.getVisitor_name());
                i.putExtra("visitor_phone_num",uploadCurrent.getVisitor_phone_num());
                i.putExtra("mem_block_num",uploadCurrent.getMem_block_num());
                i.putExtra("mem_flat_num",uploadCurrent.getMem_flat_num());
                i.putExtra("visitor_purpose",uploadCurrent.getVisitor_purpose());
                i.putExtra("visitor_img",uploadCurrent.getVisitor_img());
                i.putExtra("visitor_vechile_img",uploadCurrent.getVisitor_vechile_img());
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


            TextView flatno,name,contact;
            CircleImageView profilepic;
            public ImageViewHolder(View itemView) {

                super(itemView);
                name=itemView.findViewById(R.id.name);
                contact=itemView.findViewById(R.id.contact);
                flatno=itemView.findViewById(R.id.flatno);
                profilepic=itemView.findViewById(R.id.profileImage);

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