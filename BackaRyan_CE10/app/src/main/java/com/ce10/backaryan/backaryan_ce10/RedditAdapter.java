//Ryan Backa
//Jav1 - 1609
//RedditAdapter

package com.ce10.backaryan.backaryan_ce10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 9/18/16.
 */
public class RedditAdapter extends BaseAdapter {
    public static final int ID_CONSTANT = 0x01000000;
    ArrayList<Reddit> reddits;
    Context context;

    public RedditAdapter(MainActivity mainActivity, ArrayList<Reddit> reddits){
        this.context = mainActivity;
        this.reddits = reddits;
    }

    @Override
    public int getCount() {
        if(reddits!=null){
            return reddits.size();
        } else {
            return 0;
        }
    }

    @Override
    public Reddit getItem(int i) {
        if (reddits!=null && i>=0 && i<reddits.size()){
            return reddits.get(i);
        }else {
            return null;
        }

    }

    @Override
    public long getItemId(int i) {
        if (reddits!=null && i>=0 && i<reddits.size()){
            return ID_CONSTANT + i;
        }else {
            return 0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.reddit_adapter, viewGroup,false);
            holder.titleView = (TextView) view.findViewById(R.id.listTitle);
            holder.authorView = (TextView) view.findViewById(R.id.listAuthor);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Reddit reddit = getItem(i);
        holder.authorView.setText(reddit.author);
        holder.titleView.setText(reddit.title);
        return view;
    }

    public static class ViewHolder{
        public TextView titleView;
        public TextView authorView;
    }
}
