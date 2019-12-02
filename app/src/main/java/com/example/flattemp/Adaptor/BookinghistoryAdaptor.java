package com.example.flattemp.Adaptor;

import android.app.Activity;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.BookingHistory;
import com.example.flattemp.Model.Booking;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Recipt;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Manoranjan on 4/23/2018.
 */

public class BookinghistoryAdaptor extends RecyclerView.Adapter<BookinghistoryAdaptor.ImageViewHolder> {
    //String url_date="http://pivotnet.co.in/SocietyManagement/Android/cancel_booking.php";
    String id;
    private Context mContext;
    private List<Booking> mUploads;
    private OnItemClickListener mListener;
    public BookinghistoryAdaptor(Context mContext, List<Booking> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.bookinghistory_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Booking uploadCurrent = mUploads.get(position);

        holder.booking_date.setText(uploadCurrent.getBooked_date());
        holder.facility.setText(uploadCurrent.getFacility());
        holder.reason.setText(uploadCurrent.getBooking_reason());
        holder.date.setText(uploadCurrent.getDate_from()+" to "+uploadCurrent.getDate_to());

        if (uploadCurrent.getBooked_status().trim().equals("0")) {
            holder.status.setText("Booked");
        }else {
            holder.status.setText("Booking Cancel");
            holder.txtcancel.setVisibility(View.GONE);
        }

        holder.txtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.cancel_facility_booking,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("success")){
                                    Toast.makeText(mContext, "SuccessFull Cancel", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(mContext,BookingHistory.class);
                                    mContext.startActivity(intent);
                                    ((Activity)mContext).finish();

                                }else {
                                    Toast.makeText(mContext, "Some Problem", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        //Adding parameters to request
                        params.put("mem_user",uploadCurrent.getBooking_id());
                        //returning parameter
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(mContext).add(stringRequest);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView  booking_date,facility,reason,date, status,txtcancel;

         public ImageViewHolder(View itemView) {

             super(itemView);
             booking_date = itemView.findViewById(R.id.booking_date);
             //pay_date=itemView.findViewById(R.id.paydate);
           //  mem_id=itemView.findViewById(R.id.memberid);
           //  mem_name=itemView.findViewById(R.id.membername);
             facility=itemView.findViewById(R.id.facility);
           //  mem_flat_type=itemView.findViewById(R.id.flattype);
             reason=itemView.findViewById(R.id.reason);
             date=itemView.findViewById(R.id.date);
            // pay_remaining=itemView.findViewById(R.id.remaining);
             status=itemView.findViewById(R.id.status);
             txtcancel=itemView.findViewById(R.id.txtcancel);
           /*  pay_status=itemView.findViewById(R.id.status);
               cancelbooking=itemView.findViewById(R.id.txtcancel);*/
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


    private void loaddate() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.cancel_booking,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)){

                            //Creating a shared preferenc
                            Toast.makeText(mContext, "Sucessfully Canceled your Booking", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(mContext,BookingHistory.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(i);

                        }else{

                            Toast.makeText(mContext, "Sucessfully Canceled your Booking", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //You can handle error here if you want
                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("cat",id);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }

}