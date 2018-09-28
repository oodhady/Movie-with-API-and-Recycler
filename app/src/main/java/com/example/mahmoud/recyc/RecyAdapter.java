package com.example.mahmoud.recyc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.myViewHolder> {
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    List <movie> dataList;
    Context context;
    private static OnItemClickListener mListener;

    public RecyAdapter(List <movie> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public static void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyc_row, parent, false);
        myViewHolder holder=new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        movie curr=dataList.get(position);
        holder.tvmovname.setText(curr.title);
        holder.tvreldate.setText(curr.relase_date);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+curr.poster_path).into(holder.ivposter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




//////////////////////////////////////////
    class myViewHolder extends RecyclerView.ViewHolder{

        TextView tvmovname,tvreldate;
        ImageView ivposter;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmovname=itemView.findViewById(R.id.tvtitle);
            tvreldate=itemView.findViewById(R.id.tvdate);
            ivposter=itemView.findViewById(R.id.ivimg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
