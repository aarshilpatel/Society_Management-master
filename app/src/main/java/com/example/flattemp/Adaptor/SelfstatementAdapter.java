package com.example.flattemp.Adaptor;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flattemp.Model.Recipt;
import com.example.flattemp.Model.View_Statement_Self_Model;
import com.example.flattemp.R;

import java.util.List;

/**
 * Created by Manoranjan on 4/23/2018.
 */

public class SelfstatementAdapter extends RecyclerView.Adapter<SelfstatementAdapter.ImageViewHolder> {
    private Context mContext;
    private List<View_Statement_Self_Model> mUploads;
    private OnItemClickListener mListener;
    public SelfstatementAdapter(Context mContext, List<View_Statement_Self_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.selfstatement_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        View_Statement_Self_Model uploadCurrent = mUploads.get(position);

        holder.pay_date.setText(uploadCurrent.getPay_date());
        holder.diposit_amount.setText(uploadCurrent.getPay_deposit());
       // holder.pay_date.setText(uploadCurrent.getPay_date());
        //holder.mem_id.setText(uploadCurrent.getMem_id());
        //holder.mem_name.setText(uploadCurrent.getMem_name());
       // holder.mem_flat_num.setText(uploadCurrent.getMem_flat_num());
       // holder.mem_flat_type.setText(uploadCurrent.getMem_flat_type());
        holder.remaining_amount.setText(uploadCurrent.getPay_remaining());
       // holder.pay_deposit.setText(uploadCurrent.getPay_deposit());
        //holder.pay_remaining.setText(uploadCurrent.getPay_remaining());

       /* if (uploadCurrent.getPay_status().equals("0")){
            holder.pay_status.setTextColor(Color.parseColor("#2196F3"));
            holder.pay_status.setText("Pending");

        }else
        if (uploadCurrent.getPay_status().equals("1")){
            holder.pay_status.setTextColor(Color.parseColor("#11EE1A"));
            holder.pay_status.setText("Paid");

        }
        else
        if (uploadCurrent.getPay_status().equals("2")){
            holder.pay_status.setTextColor(Color.parseColor("#F30A5B"));
            holder.pay_status.setText("Due");

        }*/
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView  pay_id,pay_date,mem_id,mem_name, mem_flat_num, diposit_amount, pay_fixed, pay_deposit,remaining_amount, pay_month,pay_status;;

         public ImageViewHolder(View itemView) {

             super(itemView);
            // pay_id = itemView.findViewById(R.id.payid);
             pay_date=itemView.findViewById(R.id.date);
           //  mem_id=itemView.findViewById(R.id.memberid);
           //  mem_name=itemView.findViewById(R.id.membername);
             diposit_amount=itemView.findViewById(R.id.diposit_amount);
           //  mem_flat_type=itemView.findViewById(R.id.flattype);
           //  pay_fixed=itemView.findViewById(R.id.Ammount);
             remaining_amount=itemView.findViewById(R.id.remaining_amount);
            // pay_remaining=itemView.findViewById(R.id.remaining);
           //  pay_month=itemView.findViewById(R.id.month);
           //  pay_status=itemView.findViewById(R.id.status);

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