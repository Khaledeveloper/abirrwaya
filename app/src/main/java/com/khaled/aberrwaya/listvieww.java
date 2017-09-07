package com.khaled.aberrwaya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class listvieww extends AppCompatActivity {
    ListView listView;
    DB_Sqlite_Favorite db_sqlite_favorite = new DB_Sqlite_Favorite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listvieww);
        listView =(ListView)findViewById(R.id.ListViewIDd);
        showdata();


    }
    public void showdata(){
        ArrayList<StoryModel> listdata = db_sqlite_favorite.getAllFavorite();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listdata);
        listView.setAdapter(arrayAdapter);
    }
}
