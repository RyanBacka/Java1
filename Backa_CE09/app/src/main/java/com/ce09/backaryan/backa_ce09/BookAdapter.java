// Ryan Backa
// Jav1 - 1609
// BookAdapter

package com.ce09.backaryan.backa_ce09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 9/18/16.
 */
public class BookAdapter extends BaseAdapter {
    public static final int ID_CONSTANT = 0x01000000;
    ArrayList<Book> books;
    Context context;

    public BookAdapter(MainActivity mainActivity, ArrayList<Book> books){
        this.context = mainActivity;
        this.books = books;
    }
    @Override
    public int getCount() {
        if (books!=null){
            return books.size();
        } else {
            return 0;
        }
    }

    @Override
    public Book getItem(int i) {
        if (books!=null && i>=0 && i<books.size()){
            return books.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        if (books!=null && i>=0 && i<books.size()){
            return ID_CONSTANT + i;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.book_adapter, viewGroup, false);
            holder.titleView = (TextView) convertView.findViewById(R.id.bookTitle);
            holder.bookImage = (ImageView) convertView.findViewById(R.id.bookImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book book = getItem(i);
        holder.titleView.setText(book.title);
        Picasso.with(context).load(book.smallThumbnail).into(holder.bookImage);

        return convertView;
    }

    public static class ViewHolder{
        public TextView titleView;
        public ImageView bookImage;
    }
}
