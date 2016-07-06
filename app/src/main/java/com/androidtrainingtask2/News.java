package com.androidtrainingtask2;

import android.support.annotation.Size;

public class News {

    public String title;
    @Size(min = 10 , max = 20)
    public String description;

    public News(){}
}
