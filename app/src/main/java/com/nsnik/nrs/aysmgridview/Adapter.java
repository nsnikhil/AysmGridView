package com.nsnik.nrs.aysmgridview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.nsnik.nrs.aysmgridview.data.tablenames.table1;


/**
 * Created by Nikhil on 05-Mar-17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    CursorAdapter cursorAdapter;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView2;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.itemTextView);
            textView2 = (TextView)itemView.findViewById(R.id.itemTextView2);
        }
    }

    Adapter(Context context,Cursor c){
        mContext = context;
        cursorAdapter = new CursorAdaptr(mContext,c);
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        v.setTag(myViewHolder);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        String name = cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(table1.mName));
        holder.textView.setText(name);
        if(cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(table1.mSurname))!=null){
            holder.textView2.setVisibility(View.VISIBLE);
            holder.textView2.setText(cursorAdapter.getCursor().getString(cursorAdapter.getCursor().getColumnIndex(table1.mSurname)));
        }else {
            holder.textView2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCursor().getCount();
    }
}
