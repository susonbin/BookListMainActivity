package com.jnu.booklistmainactivity;

public class Book {
    private int CoverResourceId;
    private String Title;

    public Book( String title,int coverResourceId) {
        CoverResourceId = coverResourceId;
        Title = title;
    }

    public int getCoverResourceId() {
        return CoverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        CoverResourceId = coverResourceId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
