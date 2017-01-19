// Ryan Backa
// Jav1 - 1609
// CustomAdapter

package com.ce06.backaryan.backaryan_ce06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ryankbacka on 9/8/16.
 */
public class CustomAdapter extends BaseAdapter {
    public static final int ID_CONSTANT = 0x01000000;
    Person[] people;
    Context context;

    public CustomAdapter(MainActivity mainActivity, Person[] ppl){
        context = mainActivity;
        people = ppl;
    }

    @Override
    public int getCount() {
        if (people != null){
            return people.length;
        } else {
            return 0;
        }
    }

    @Override
    public Person getItem(int i) {
        if (people != null && i >= 0 && i<people.length){
            return people[i];
        } else {
            return null;
        }

}

    @Override
    public long getItemId(int i) {
        if (people != null && i >= 0 && i<people.length) {
            return ID_CONSTANT + i;
        } else {
            return 0;
        }
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_adapter, viewGroup, false);
            holder.nameView = (TextView) convertView.findViewById(R.id.customName);
            holder.birthdayView = (TextView) convertView.findViewById(R.id.customBirthday);
            holder.faceView = (ImageView) convertView.findViewById(R.id.face);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Person person = getItem(i);



        holder.nameView.setText(person.firstName + " " + person.lastName);
        holder.birthdayView.setText(person.birthday);
        holder.faceView.setImageResource(person.id);

        return convertView;
    }

    public static class ViewHolder{
        public TextView nameView;
        public TextView birthdayView;
        public ImageView faceView;
    }
}
