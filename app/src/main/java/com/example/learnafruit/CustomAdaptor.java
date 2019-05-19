package com.example.learnafruit;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * CustomAdaptor
 *
 * The CustomAdaptor implements the ListAdapter to provide a customized list view for listing fruits
 */
class CustomAdaptor implements ListAdapter {
    ArrayList<FruitModel> arrayList;
    Context context;

    public CustomAdaptor(Context context, ArrayList<FruitModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Overridden method to customize list view item loading data
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FruitModel fruitModel = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.fruit_item, null);
        }
        convertView.setTag(fruitModel.getId());
        View oneFruitItem = convertView.findViewById(R.id.oneFruitItem);
        oneFruitItem.setTag(fruitModel.getId());
        TextView name = convertView.findViewById(R.id.name);
        ImageView imgView = convertView.findViewById(R.id.img);
        name.setText(fruitModel.getName());
        Picasso.get()
                .load("https://damp-sea-11322.herokuapp.com"+fruitModel.getImg_path())
                .placeholder(R.drawable.loading)
                .error(R.drawable.broken)
                .fit()
                .into(imgView);


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
