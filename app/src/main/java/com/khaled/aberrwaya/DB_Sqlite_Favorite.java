package com.khaled.aberrwaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Khaled on 8/19/2017.
 */

public class DB_Sqlite_Favorite extends SQLiteOpenHelper {
    public static final String BDname = "Favorite.db";

    public DB_Sqlite_Favorite(Context context) {
        super(context,BDname , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table favorite(id INTEGER PRIMARY KEY  AUTOINCREMENT, Title TEXT,ContentTitle TEXT , Page_id INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE OF EXISTS favorite ");
        onCreate(db);

    }
    public boolean Insert_to_favorite(String Title, String ContentTitle ,int Page_id ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title",Title);
      contentValues.put("ContentTitle", ContentTitle);
        contentValues.put("Page_id", Page_id);

        long result = db.insert("favorite", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }

   public ArrayList getAllFavorite() {
        ArrayList<StoryModel> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(" select * from favorite",null);
        if (rs!=null&& rs.getCount()>0) {
            if (rs.moveToFirst()){
                do {
                    int id = rs.getInt(rs.getColumnIndex("id"));
                    String Title = rs.getString(rs.getColumnIndex("Title"));
                    String ContentTitle = rs.getString(rs.getColumnIndex("ContentTitle"));
                    int Page_id = rs.getInt(rs.getColumnIndex("Page_id"));
                    StoryModel mod = new StoryModel(id,Title,ContentTitle,Page_id);
                    arrayList.add(mod);



                    //arrayList.add(new StoryModel(id,Title ,ContentTitle,Page_id));

                }while (rs.moveToNext());
            }
          // rs.close();
        }
        return arrayList;
    }

  /*  public ArrayList<StoryModel> getAllFavorite(){
        ArrayList<StoryModel> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select from * favorite", null );
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){

            int t1 = cursor.getInt(cursor.getColumnIndex("id"));
            String t2 = cursor.getString(cursor.getColumnIndex("Title"));
        String t3 = cursor.getString(cursor.getColumnIndex("ContentTitle"));
            int t4 = cursor.getInt(cursor.getColumnIndex("Page_id"));

            arrayList.add(new StoryModel(t1,t2,t3,t4));
            cursor.moveToNext();

        }
        return arrayList;
    }*/


    public ArrayList getTitlesFavorite(){
        ArrayList<StoryModelM> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select from * favorite", null );
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){


            String t1 = cursor.getString(cursor.getColumnIndex("Title"));
            String t2 = cursor.getString(cursor.getColumnIndex("ContentTitle"));


            arrayList.add(new StoryModelM(t1,t2));
            cursor.moveToNext();

        }
        return arrayList;
    }
    public int get_check_List_Favorite(String Title) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("select * from favorite Where Title Like'" + Title + "'", null);

        int count = rs.getCount();

        rs.close();




        return count;

    }





    /*public Integer DeletRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("favorite", "id = ?", new String[]{id});
    }*/

    public Integer DeletRow(String Title){ //in job app we better use ID which in MySql
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("favorite", "Title = ?", new String[]{Title});
    }
}