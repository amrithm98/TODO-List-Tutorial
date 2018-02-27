package com.workstation.amrith.todo;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amrith on 2/27/18.
 */

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener{

    ArrayList<Entry> arrayList;
    Context context;

    ListViewAdapter(ArrayList<Entry> e,Context c)
    {
        arrayList = e;
        context = c;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ChildHolder viewHolder;
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.list_content,viewGroup,false);
            viewHolder = new ChildHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ChildHolder) view.getTag();
        }

        Entry ent = arrayList.get(i);
        viewHolder.contentTV.setText(ent.content);
        viewHolder.dateTV.setText(ent.date);
        viewHolder.del.setOnClickListener(this);
        viewHolder.del.setTag(i);
        return view;
    }

    @Override
    public void onClick(View view) {
        int pos = (int)view.getTag();
        Entry obj = arrayList.get(pos);
        obj.delete();
        Snackbar.make(view,"Deleted",Snackbar.LENGTH_SHORT).setAction("OK",null).show();
        arrayList.remove(pos);
        notifyDataSetChanged();
    }

    static class ChildHolder
    {
        TextView contentTV,dateTV;
        Button del;
        public ChildHolder(View view)
        {
            contentTV = (TextView)view.findViewById(R.id.content);
            dateTV = (TextView)view.findViewById(R.id.date);
            del = (Button)view.findViewById(R.id.del);
        }
    }
}
