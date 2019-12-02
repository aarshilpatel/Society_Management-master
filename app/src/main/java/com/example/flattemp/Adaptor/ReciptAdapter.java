package com.example.flattemp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flattemp.Model.Recipt;
import com.example.flattemp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manoranjan on 4/23/2018.
 */

public class ReciptAdapter extends RecyclerView.Adapter<ReciptAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Recipt> mUploads;
    private OnItemClickListener mListener;
    public ReciptAdapter(Context mContext, List<Recipt> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recipt_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Recipt uploadCurrent = mUploads.get(position);

        holder.date.setText(uploadCurrent.getPay_date());
        holder.amount.setText(uploadCurrent.getPay_deposit());

        if (uploadCurrent.getPay_status().trim().equals("0")){
            holder.status.setText("Pending Confirmation");
        }else {
            holder.status.setText("Payment Done");
        }

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (uploadCurrent.getPay_status().trim().equals("1")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uploadCurrent.getPay_id()));
                    mContext.startActivity(browserIntent);
                }else {
                    Toast.makeText(mContext, "Pending Confirmation", Toast.LENGTH_SHORT).show();
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
        public TextView  date,amount,status;

        public ImageViewHolder(View itemView) {

            super(itemView);
            date = itemView.findViewById(R.id.date);
            //pay_date=itemView.findViewById(R.id.paydate);
            //  mem_id=itemView.findViewById(R.id.memberid);
            //  mem_name=itemView.findViewById(R.id.membername);
            amount=itemView.findViewById(R.id.paid_amount);
            //  mem_flat_type=itemView.findViewById(R.id.flattype);
            status=itemView.findViewById(R.id.status);
             /*pay_deposit=itemView.findViewById(R.id.deposite);
            // pay_remaining=itemView.findViewById(R.id.remaining);
             pay_month=itemView.findViewById(R.id.month);
             pay_status=itemView.findViewById(R.id.status);
*/
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