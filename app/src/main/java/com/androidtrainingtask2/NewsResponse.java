package com.androidtrainingtask2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse {

    public List<News> item;

    public NewsResponse(){}
}
