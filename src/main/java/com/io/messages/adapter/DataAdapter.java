package com.io.messages.adapter;

import com.io.messages.domain.Message;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataAdapter {

    @ToJson String toJson(@DataFormat LocalDateTime localDateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @FromJson @DataFormat LocalDateTime fromJson(String data)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(data,formatter);
    }
}
