package com.khaled.aberrwaya;

/**
 * Created by khaled on 17/08/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by Khaled on 8/17/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ItemClickListener mItemClickListener;
    DB_Sqlite_Favorite db_fav;
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    private static final int AD_VIEW_TYPE = 1;
    ArrayList<Object> arrayList = new ArrayList<>();
    public Context context;
    ArrayList<StoryModel> List_favorite = new ArrayList<>();





    public MyAdapter(ArrayList<Object> arrayList){
        this.arrayList=arrayList;
        //db_fav = new DB_Sqlite_Favorite(context);
    }
    /*public MyAdapter(){

    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case AD_VIEW_TYPE:
                View adbannerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adviewrecyclerview, parent,false);

                return new adviewholder(adbannerview);
            case MENU_ITEM_VIEW_TYPE:
                default:

            View MeunItemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

            return new MyViewHolder(MeunItemview);

        }

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
   int viewType = getItemViewType(position);


      switch (viewType) {
          case AD_VIEW_TYPE:
              adviewholder adviewholderc=(adviewholder)holder;
              AdView adView=(AdView)arrayList.get(position);

              ViewGroup adCardView =(ViewGroup)adviewholderc.itemView;
              adCardView.removeAllViews();

              if (adView.getParent()!=null){
                  ((ViewGroup)adView.getParent()).removeView(adView);
              }

              adCardView.addView(adView);
              break;


          case MENU_ITEM_VIEW_TYPE:
              default:

          StoryModelM story = (StoryModelM) arrayList.get(position);
          MyViewHolder myViewHolder = (MyViewHolder) holder;

          myViewHolder.Listindex.setText(story.getTitleModel());
          myViewHolder.ListContent.setText(story.getContentModel());

          String mTitle = myViewHolder.Listindex.getText().toString();

          Context context = holder.itemView.getContext();


          SharedPreferences sharedPreferences = context.getSharedPreferences("tgpref1", Context.MODE_PRIVATE);
          boolean tgpref = sharedPreferences.getBoolean("tgpref" + position, false);


          if (tgpref) {
              myViewHolder.toggleButton.setChecked(true);
          } else {
              myViewHolder.toggleButton.setChecked(false);
          }
          break;

      }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // ////////////////////////////////ADCLASS

    public class adviewholder extends RecyclerView.ViewHolder{

        public adviewholder(View itemView) {
            super(itemView);
        }
    }

    ///////////////////////////////////////////////////////////////
    //MyViewholder Class


    public  class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Listindex;
      TextView ListContent;
      ToggleButton toggleButton;

/////////////////////////////////////////////////////////////////////////////////////

        //togbtn method
        public  void togbtn(View v, Context context){
          int position = getAdapterPosition();
            if (toggleButton.isChecked()) {
                String mTitle = Listindex.getText().toString();
                String mContent =ListContent.getText().toString();

                //Toast.makeText(context, mTitle, Toast.LENGTH_SHORT).show();
                int check = db_fav.get_check_List_Favorite(mTitle);
                if (check>0){
                    Toast.makeText(context, "عفوا العنوان موجود بالمفضلة", Toast.LENGTH_SHORT).show();
                }else {
                    db_fav.Insert_to_favorite(mTitle,mContent,position);
                    Toast.makeText(context, "تم الاضافة الي المفضلة", Toast.LENGTH_SHORT).show();
                }



                Animation plus = AnimationUtils.loadAnimation(v.getContext(), R.anim.pulse);
                toggleButton.startAnimation(plus);
                SharedPreferences sharedPreferences = context.getSharedPreferences("tgpref1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("tgpref"+position, true);

                editor.commit();



            }


            else {

                         /*  int x;
                           for (x = 0; x<List_favorite.size(); x++) {
                               int idint = List_favorite.get(x).getIdrow();
                               String idfinal = String.valueOf(idint+position);

                           }*/

                db_fav.DeletRow(Listindex.getText().toString());
                Toast.makeText(context, "تم الازلالة من المفضلة", Toast.LENGTH_SHORT).show();







                SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("tgpref1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("tgpref"+position, false);
                editor.commit();









            }

        }

////////////////////////////////////////////////////////////////////////////--





//MyViewhold Constructor--->

        public MyViewHolder(final View itemView) {

            super(itemView);





            final Context context = itemView.getContext();
            db_fav = new DB_Sqlite_Favorite(context);
            List_favorite = db_fav.getAllFavorite();















                Listindex =(TextView) itemView.findViewById(R.id.TextViewTitleID);
           ListContent = (TextView)itemView.findViewById(R.id.TextViewContentID);
            toggleButton =(ToggleButton)itemView.findViewById(R.id.ToggleHeartID);
            String mTitle = Listindex.getText().toString();

            //not working

            int check = db_fav.get_check_List_Favorite(mTitle);
            if (check>0){
                toggleButton.setChecked(true);
            }else {
                toggleButton.setChecked(false);
            }



            itemView.setOnClickListener(this);
           toggleButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   togbtn(v, context);



               }
           });





        }


        /////////////////////////////////////////////////////////////////

        @Override
        public void onClick(View v) {
            if (mItemClickListener!=null)
                mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItemId());


            }




    }

    public void setFilter(ArrayList<Object> newList) {

        arrayList= new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
    public void setmItemClickListener(ItemClickListener itemClickListener ){
        this.mItemClickListener= itemClickListener;
    }




    public interface ItemClickListener {
        void onItemClick(View view, int position , long id );

    }

    public interface ToggleInterface {
        void toggleonClick(View view);
    }


    @Override
    public int getItemViewType(int position) {
        //every 8 item there will be an ad

        return (position% 4 == 0) ? AD_VIEW_TYPE: MENU_ITEM_VIEW_TYPE;
    }
}

