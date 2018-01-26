package com.example.vendorapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by ayanc on 1/18/2018.
 */

public class CouponAdp extends ArrayAdapter<CouponDetails> {
    private Activity context;
    List<CouponDetails> shpsv;

    public CouponAdp(Activity context, List<CouponDetails> objects) {
        //super(context,R.layout.item_list,objects);
        super(context,R.layout.item_list,objects);
        this.context=context;
        this.shpsv=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //  LayoutInflater inflater = context.getLayoutInflater();
        //  View listViewItem = inflater.inflate(R.layout.item_list, null, true);
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_list, parent, false);
        }

        TextView coupNm = (TextView) convertView.findViewById(R.id.textView13);
        TextView coupDesc = (TextView) convertView.findViewById(R.id.textView15);
        TextView coupSts = (TextView) convertView.findViewById(R.id.textView17);
        TextView coupValidity = (TextView) convertView.findViewById(R.id.textView19);
        TextView coupValue = (TextView) convertView.findViewById(R.id.textView21);
        TextView coupCatg = (TextView) convertView.findViewById(R.id.textView23);

        CouponDetails shp=shpsv.get(position);

        coupNm.setText(shp.getScnm());
        coupDesc.setText(shp.getScdesc());
        coupSts.setText(shp.getScsts());
        coupValidity.setText(shp.getScval_from()+" to "+shp.getScval_to());
        coupValue.setText(shp.getSc_percentage());
        coupCatg.setText(shp.getSc_catg());

        return convertView;
        //return listViewItem;
    }
}