package com.khaled.aberrwaya;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by khaled on 21/08/2017.
 */

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.MyFavHolder> {
    ClickInterFace clickInterFace;


    ArrayList<StoryModel>arrayList = new ArrayList<>();
    AdapterFav(ArrayList<StoryModel> arrayList){
        this.arrayList = arrayList;

    }

    /*public AdapterFav(ArrayList allFavorite, int row2) {
        this.arrayList = allFavorite;
        this.r

    }*/


    @Override
    public MyFavHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2 , parent , false);
        return new MyFavHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFavHolder holder, int position) {

        holder.StoryT.setText(arrayList.get(position).getTitleModel());
       holder.StoryC.setText(arrayList.get(position).getContentModel());




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyFavHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView StoryT;
        TextView StoryC;

        public MyFavHolder(View itemView) {

            super(itemView);

            StoryT =(TextView)itemView.findViewById(R.id.TextViewTitleIDFav);
            StoryC =(TextView)itemView.findViewById(R.id.TextViewContentFav);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (clickInterFace!=null){
                clickInterFace.OnItemClick(itemView , getAdapterPosition());
            }

        }
    }
    public void setInClickListenerFav(ClickInterFace clickInterFaceOp){
        this.clickInterFace = clickInterFaceOp;
    }

    public interface ClickInterFace {
        void OnItemClick(View view, int position);

    }
}
