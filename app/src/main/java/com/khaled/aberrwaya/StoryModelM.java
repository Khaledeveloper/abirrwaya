package com.khaled.aberrwaya;

/**
 * Created by Khaled on 8/19/2017.
 */

public class StoryModelM {

    private String TitleModel ;
    private String ContentModel;


    public StoryModelM(String titleModel, String contentModel) {
        TitleModel = titleModel;
        ContentModel = contentModel;

    }
    public StoryModelM(){

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



}

