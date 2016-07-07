package com.androidtrainingtask2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

    public String title;
    public String description;

    public News(){}
}
