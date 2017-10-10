package com.khaled.aberrwaya;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.ArrayList;

public class webviewhtml extends AppCompatActivity {
    WebView mWebview;
    int page;
    String StoryTitle, StoryContent;
    ArrayList<StoryModelM> List_Favorite = new ArrayList<>();
    DB_Sqlite_Favorite db_fav = new DB_Sqlite_Favorite(this);
    private Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewhtml);
        this.menu = menu;






        Toolbar WebToolbar =(Toolbar)findViewById(R.id.ToolbarWebID);
        setSupportActionBar(WebToolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.backicon);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


       /* int check = db_fav.get_check_List_Favorite(StoryTitle);
        if (check>0)*/




        mWebview =(WebView)findViewById(R.id.WebViewID);

        Intent data = getIntent();



        StoryTitle = data.getExtras().getString("TitleKey");

        page =data.getExtras().getInt("PageKey");

        StoryContent = data.getExtras().getString("ContentKey");



        mWebview.loadUrl("file:///android_asset/html/"+ page+".htm");





        if (getSupportActionBar()!= null) {
            getSupportActionBar().setTitle(StoryTitle);
        }
        WebToolbar.setTitleTextColor(Color.WHITE);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuweb, menu);

        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.FavoritID)  {








            int check= db_fav.get_check_List_Favorite(StoryTitle);
            if (check>0) {
                /*
                int check = db_fav.get_check_List_Favorite(StoryTitle);
        if (check>0) {

            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fullstar));
        }else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.notfullstar));
        }
                 */



                Toast.makeText(this, "عفوا، العنوان موجود بالمفضلة", Toast.LENGTH_SHORT).show();

            }else {
                db_fav.Insert_to_favorite(StoryTitle,StoryContent,page);
                Toast.makeText(this, "تم الاضافة الي المفضلة بنجاح", Toast.LENGTH_SHORT).show();
               // menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fullstar));

            }

        }


        return super.onOptionsItemSelected(item);
    }

    }

