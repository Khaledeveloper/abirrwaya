package com.khaled.aberrwaya;

/**
 * Created by khaled on 17/08/2017.
 */

/**
 * Created by Khaled on 8/17/2017.
 */

public class StoryModel {

    private int Idrow;
    private String TitleModel ;

    private String ContentModel;
    private int Pageid;



    public StoryModel(int idrow, String titleModel, String contentModel,int pageid) {
        TitleModel = titleModel;
       ContentModel = contentModel;
        Idrow = idrow;
        Pageid = pageid;
    }

    public int getIdrow() {
        return Idrow;
    }

    public void setIdrow(int idrow) {
        Idrow = idrow;
    }

    public String getTitleModel() {
        return TitleModel;
    }

    public void setTitleModel(String titleModel) {
        TitleModel = titleModel;
    }

    public String getContentModel() {
        return ContentModel;
    }

    public void setContentModel(String contentModel) {
        ContentModel = contentModel;
    }

    public int getPageid() {
        return Pageid;
    }

    public void setPageid(int pageid) {
        Pageid = pageid;
    }

    @Override
    public String toString() {
        return
                 Idrow +
            TitleModel +
               ContentModel +
              Pageid;
    }


}
