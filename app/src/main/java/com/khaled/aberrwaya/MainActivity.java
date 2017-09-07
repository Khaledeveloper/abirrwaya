package com.khaled.aberrwaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MyAdapter.ItemClickListener{
    ArrayList<StoryModelM> mModel = new ArrayList<>();
    ArrayList<StoryModel>List_Favorite = new ArrayList<>();
   // ArrayList<StoryModel>List_FavorteALL = new ArrayList<>();
   // DB_Sqlite_Favorite db_Fav = new DB_Sqlite_Favorite(this);
    //String List_Type;
    RecyclerView mRecycleview;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter adapter;
    Toolbar mToolbar;
    TextView mTextView , rowtext,ContentText ;
    StoryModelM storyModelM;
    ToggleButton toggleButton;
   /* private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private  ActionBarDrawerToggle drawerToggle;*/
   Button sharebutton;

    NavigationView mDrawer;
    DrawerLayout mDreawerLayout;
    ActionBarDrawerToggle drawerToggle;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar =(Toolbar)findViewById(R.id.ToolbarmainID);
        setSupportActionBar(mToolbar);

      AdView adView =(AdView)findViewById(R.id.AdBannerMainID);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


       // getPre();

     //   mToolbar.setTitleTextColor(Color.WHITE);
      //  mTextView = (TextView) findViewById(R.id.TextViewMainID);




        List_Index();



    }


    public void List_Index() {
        mRecycleview =(RecyclerView)findViewById(R.id.RecyclerViewMainID);
         //  List_Type ="Index";
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("روايات عبير");
        }

       // mTextView.setText("روايات عبير");

        layoutManager = new LinearLayoutManager(this);
        mRecycleview.setLayoutManager(layoutManager);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setItemAnimator(new DefaultItemAnimator());
        mRecycleview.setNestedScrollingEnabled(false);

        mModel = new ArrayList<>();
        final String[] ListIndex = getResources().getStringArray(R.array.TitleIndex);
        final String[] ListContest = getResources().getStringArray(R.array.ContentIndex);

        int count = 0;

        for (String Name : ListIndex ){
            mModel.add(new StoryModelM(Name,ListContest[count]));
            count++;
        }
        adapter = new MyAdapter(mModel);
        mRecycleview.setAdapter(adapter);

        adapter.setmItemClickListener(this);

    }


    @Override
    public void onItemClick(View view, int position, long id) {
        sharebutton=(Button)view.findViewById(R.id.shareCardID);
       // sharebutton.setOnClickListener(new View.OnClickListener() {


        toggleButton =(ToggleButton) view.findViewById(R.id.ToggleHeartID);


       rowtext =(TextView)view.findViewById(R.id.TextViewTitleID);

        String mTitle = rowtext.getText().toString();

        ContentText =(TextView)view.findViewById(R.id.TextViewContentID);
        String mContent = ContentText.getText().toString();



       /* int PageNum = 0;
        if (List_Type.equals("Index")){*/
          int  PageNum = position;
       /* }else if (List_Type.equals("Favorite")){
            PageNum =List_Favorite.get(position).getPageid();
        }*/

        Intent intent = new Intent(MainActivity.this, webviewhtml.class);
        intent.putExtra("TitleKey", mTitle);
        intent.putExtra("PageKey", PageNum);
        intent.putExtra("ContentKey", mContent);
        startActivity(intent);





       //tring mTitle = adapter.getItem(position);








        Toast.makeText(this, PageNum + mTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        MenuItem searchItem= menu.findItem(R.id.SearchID);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);








        return true;
    }

    public void getPre(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean tgpref = preferences.getBoolean("tgpref",true);
        toggleButton.setChecked(tgpref);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.signupmenu){

            Intent intent = new Intent(MainActivity.this,signup.class);
            startActivity(intent);


        }

      if (id == R.id.FavoritListID) {
            //Toast.makeText(this,"Favorite List", Toast.LENGTH_SHORT).show();
          //  List_Type = "Favorite";
         /*   if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Favorites");
            }*/
         //   List_Favorite = db_Fav.getAllFavorite();


           Intent intent = new Intent(MainActivity.this, FavoriteContent.class);
                startActivity(intent);
         /*    Intent intent= new Intent(MainActivity.this, listvieww.class);
          startActivity(intent);*/

             /*   mModel.clear();
              //  for (int i = 0; i < List_Favorite.size(); i++) {
                 //  mModel.add(List_Favorite.get(i).getTitleModel(),List_Favorite.get(i).getContentModel());
                mModel.addAll(db_Fav.getTitlesFavorite());




                // mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();*/

    }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        ArrayList<StoryModelM> newList = new ArrayList<>();
        for (StoryModelM storyModel : mModel) {
            String name = storyModel.getTitleModel();
            if (name.contains(newText))
                newList.add(storyModel);
        }
        adapter.setFilter(newList);

        return true;
    }

    public void Toggle(View view) {
      /*  ToggleButton toggleButton =(ToggleButton)findViewById(R.id.ToggleHeartID);
        Animation pulse = AnimationUtils.loadAnimation(this,R.anim.pulse);
        toggleButton.startActionMode(pulse);*/
    }

   /* public void DeleteR(View view) {
    }*/
}
