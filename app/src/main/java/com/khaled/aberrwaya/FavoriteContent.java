package com.khaled.aberrwaya;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteContent extends AppCompatActivity implements AdapterFav.ClickInterFace {
    RecyclerView recyclerViewFv;
  //  TextView textViewFv;
    Toolbar toolbarFav;
   // RecyclerView.LayoutManager layoutManager;
    AdapterFav favMyAdapter;
   // DB_Sqlite_Favorite DB_FAV = new DB_Sqlite_Favorite(this);
   ArrayList<StoryModel>List_Favorite = new ArrayList<>();

    DB_Sqlite_Favorite db_sqlite_favorite = new DB_Sqlite_Favorite(this);

    ArrayList<StoryModel> listFav = new ArrayList<>();
    TextView mTextView , rowtext,ContentText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_content);
        toolbarFav = (Toolbar) findViewById(R.id.ToolbarfavID);
        //  textViewFv = (TextView) findViewById(R.id.TextViewFavID);

        recyclerViewFv = (RecyclerView) findViewById(R.id.RecyclerViewFavID);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewFv.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFv.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFv.setHasFixedSize(true);
        recyclerViewFv.setItemAnimator(new DefaultItemAnimator());
       listFav = db_sqlite_favorite.getAllFavorite();
       favMyAdapter = new AdapterFav(listFav);
        recyclerViewFv.setAdapter(favMyAdapter);
        favMyAdapter.setInClickListenerFav(this);







      /*  recyclerViewFv = (RecyclerView) findViewById(R.id.RecyclerViewFavID);
        recyclerViewFv.setHasFixedSize(true);
        recyclerViewFv.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFv.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFv.setAdapter(db_sqlite_favorite.getAllFavorite(),R.layout.row2);*/






      /*  for (StoryModel storyModel: listFav){
            String name = storyModel.getTitleModel();
            String content = storyModel.getContentModel();
            int pageid = storyModel.getIdrow();
            int pagepos = storyModel.getPageid();
            //Collections.addAll()
            listFav.add(pageid,name,content,pagepos);*/
        }

    @Override
    public void OnItemClick(View view, int position) {
        rowtext =(TextView)view.findViewById(R.id.TextViewTitleIDFav);

        String mTitle = rowtext.getText().toString();

        ContentText =(TextView)view.findViewById(R.id.TextViewContentFav);

        String mContent = ContentText.getText().toString();
        List_Favorite = db_sqlite_favorite.getAllFavorite();

        int PageNum =List_Favorite.get(position).getPageid();


        Toast.makeText(this, "hi"+ PageNum, Toast.LENGTH_SHORT).show();



        Intent intent = new Intent(FavoriteContent.this, webviewhtml.class);
        intent.putExtra("TitleKey", mTitle);
        intent.putExtra("PageKey", PageNum);
        intent.putExtra("ContentKey", mContent);
        startActivity(intent);




    }
        /*
        int count = 0;

        for (String Name : ListIndex ){
            mModel.add(new StoryModelM(Name,ListContest[count]));
            count++;
        }
         */

        /*
          ArrayList<StoryModelM> newList = new ArrayList<>();
        for (StoryModelM storyModel : mModel) {
            String name = storyModel.getTitleModel();
            if (name.contains(newText))
                newList.add(storyModel);
         */

       // listFav.clear();
       // listFav.addAll(db_sqlite_favorite.getAllFavorite());

       // favMyAdapter = new  AdapterFav


      /*  recyclerViewFv = (RecyclerView) findViewById(R.id.RecyclerViewFavID);

        setSupportActionBar(toolbarFav);


        layoutManager = new LinearLayoutManager(this);
        recyclerViewFv.setLayoutManager(layoutManager);
        recyclerViewFv.setHasFixedSize(true);


        favMyAdapter = new AdapterFav(this,DB_FAV.getAllFavorite());
        recyclerViewFv.setAdapter(favMyAdapter);*/
        //  favMyAdapter.setmItemClickListener(this);

  /*  public void retrive(){
        listFav.clear();
        Cursor cursor= (Cursor) db_sqlite_favorite.getAllFavorite();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String Title = cursor.getString(1);
            String Content =cursor.getString(2);
            int pos =cursor.getInt(3);
           StoryModel storyModel = new StoryModel(id,Title,Content,pos);
            listFav.add(storyModel);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

            recyclerViewFv = (RecyclerView) findViewById(R.id.RecyclerViewFavID);
            recyclerViewFv.setLayoutManager(layoutManager);
            // recyclerViewFv.setItemAnimator(new DefaultItemAnimator());
            recyclerViewFv.setHasFixedSize(true);
            recyclerViewFv.setItemAnimator(new DefaultItemAnimator());
            // listFav = db_sqlite_favorite.getAllFavorite();
            favMyAdapter = new AdapterFav(listFav, R.layout.row2);
            recyclerViewFv.setAdapter(favMyAdapter);
        }
    }*/


    }

