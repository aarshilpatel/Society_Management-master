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
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticeAdapter  extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private Context mContext;
    private List<Notice_Model> mUploads;
    private OnItemClickListener mListener;
    public NoticeAdapter(Context mContext, List<Notice_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.notice_list_item, parent, false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        final Notice_Model model = mUploads.get(position);

        holder.txt_id.setText(model.getId());
        holder.txt_date.setText(model.getDate());
        holder.txt_notice.setText(model.getNotice());

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
        public TextView txt_id,txt_date,txt_notice;
        public ImageView imageView;
        public NoticeViewHolder(View itemView) {

            super(itemView);
            txt_id = itemView.findViewById(R.id.notice_id_item);
            txt_date = itemView.findViewById(R.id.notice_date_item);
            txt_notice = itemView.findViewById(R.id.notice_details_item);
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