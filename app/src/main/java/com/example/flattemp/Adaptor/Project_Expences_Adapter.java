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

import com.example.flattemp.Model.Project_Expences_Model;
import com.example.flattemp.R;

import java.util.List;

public class Project_Expences_Adapter extends RecyclerView.Adapter<Project_Expences_Adapter.NoticeViewHolder> {
    private Context mContext;
    private List<Project_Expences_Model> mUploads;
    private OnItemClickListener mListener;
    public Project_Expences_Adapter(Context mContext, List<Project_Expences_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.prject_expences_item, parent, false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        final Project_Expences_Model model = mUploads.get(position);


        holder.exp_name.setText("Projected Expences Name is "+model.getExp_name());
        holder.exp_date.setText("Projected Expences On Date "+model.getExp_date());
        holder.exp_amount.setText("Projected Expences Amount is "+model.getExp_amount());


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

    public class NoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView exp_name,exp_date,exp_amount;
        public ImageView imageView;
        public NoticeViewHolder(View itemView) {

            super(itemView);
            exp_name = itemView.findViewById(R.id.exp_name);
            exp_date = itemView.findViewById(R.id.exp_date);
            exp_amount = itemView.findViewById(R.id.exp_amount);
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