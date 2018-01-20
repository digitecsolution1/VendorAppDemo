package com.example.vendorapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ayanc on 1/20/2018.
 */

public class CstmrAdp extends ArrayAdapter<Customer> {
    private Activity context;
    List<Customer> shpsv;

    public CstmrAdp(Activity context, List<Customer> objects) {
        //super(context,R.layout.item_list,objects);
        super(context,R.layout.customer_list,objects);
        this.context=context;
        this.shpsv=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //  LayoutInflater inflater = context.getLayoutInflater();
        //  View listViewItem = inflater.inflate(R.layout.item_list, null, true);
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.customer_list, parent, false);
        }



        TextView cstmrNm = (TextView) convertView.findViewById(R.id.textView5);
        TextView phn = (TextView) convertView.findViewById(R.id.textView7);
        TextView city = (TextView) convertView.findViewById(R.id.textView9);



        Customer shp=shpsv.get(position);



        cstmrNm.setText(shp.getName());
       phn.setText(shp.getPhone());
        city.setText(shp.getCity());

        return convertView;
        //return listViewItem;
    }

}
