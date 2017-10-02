package com.example.pro.analysebrand;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BrandAdapter extends ArrayAdapter<BrandAnalysisResult> {
    public BrandAdapter(@NonNull Context context, @NonNull List<BrandAnalysisResult> objects){
        super(context,android.R.layout.simple_list_item_1,objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent){
        BrandListViewHolder holder = null;
        View row = converView;

        if(row == null){
            row = ((Activity)getContext()).getLayoutInflater()
                    .inflate(R.layout.list_row_layout,
                            parent,false);

            holder = new BrandListViewHolder(row);
            row.setTag(holder);
        }else{

            holder = (BrandListViewHolder) row.getTag();


        }
        holder.getTxtBrandName().setText(getItem(position).getBrandName());
        holder.getTxtTweetCount().setText(getItem(position).getTweetCount());
        //holder.getImgBrand().setImageResource(getItem(position).get);

        return row;
    }
    class BrandListViewHolder{
        private View base;
        private TextView txtBrandName;
        private TextView txtTweetCount;
        //private ImageView imgBrand;


        public BrandListViewHolder(View base) {
            this.base = base;
        }

        public TextView getTxtBrandName() {
            if(txtBrandName == null){
                txtBrandName = (TextView)base.findViewById(R.id.txtBrandName);
            }


            return txtBrandName;
        }

        public TextView getTxtTweetCount() {
            if(txtTweetCount == null){
                txtTweetCount = (TextView)base.findViewById(R.id.txtTweetCount);
            }
            return txtTweetCount;
        }

        /*public ImageView getImgBrand() {

            if(imgBrand == null){
                imgBrand = (ImageView)base.findViewById(R.id.imgBrand);
            }
            return imgBrand;
        }*/
    }
}
