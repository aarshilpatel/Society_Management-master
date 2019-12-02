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

import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.Model.View_Complain_Model;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class View_Complain_Adapter extends RecyclerView.Adapter<View_Complain_Adapter.ComplainViewHolder> {
    private Context mContext;
    private List<View_Complain_Model> mUploads;
    private OnItemClickListener mListener;
    public View_Complain_Adapter(Context mContext, List<View_Complain_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ComplainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.meetings_list_item, parent, false);
        return new ComplainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComplainViewHolder holder, int position) {
        final View_Complain_Model model = mUploads.get(position);

        holder.date.setText("Complain Date:"+model.getMem_complaint_date());
        holder.complain.setText("Complain is "+model.getMem_complaint());
        holder.admin_reply.setText("Admin Reply:"+model.getAdmin_reply());



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

    public class ComplainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView date,complain,admin_reply;
        public ImageView vendor_img;
        public ComplainViewHolder(View itemView) {

            super(itemView);
            date = itemView.findViewById(R.id.m_date);
            complain = itemView.findViewById(R.id.m_title);
            admin_reply = itemView.findViewById(R.id.m_desc);



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