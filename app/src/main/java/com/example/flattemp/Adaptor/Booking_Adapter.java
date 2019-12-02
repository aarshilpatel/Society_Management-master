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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flattemp.Model.Booking_Model;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;
import com.example.flattemp.SendBookingdata;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Booking_Adapter extends RecyclerView.Adapter<Booking_Adapter.BookingViewHolder> {
    private Context mContext;
    private List<Booking_Model> mUploads;
    private OnItemClickListener mListener;
    public Booking_Adapter(Context mContext, List<Booking_Model> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        final Booking_Model model = mUploads.get(position);

        holder.name.setText(model.getName());

        final String imgurl= UrlsList.pdf_storage +model.getImage();
        Picasso.with(mContext)
                .load(imgurl)
                .fit()
                .into(holder.img);

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SendBookingdata.class);
                intent.putExtra("booking_id",model.getBooking_id());
                intent.putExtra("name",model.getName());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView name;
        public ImageView img;
        Button book;
        public BookingViewHolder(View itemView) {

            super(itemView);
            name= itemView.findViewById(R.id.txt_name);
            img = itemView.findViewById(R.id.img);
            book = itemView.findViewById(R.id.book);


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