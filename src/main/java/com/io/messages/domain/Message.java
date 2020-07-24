package com.io.messages.domain;


import com.squareup.moshi.Moshi;
import org.postgresql.util.PSQLException;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String text;

    @Override
    public String toString() {
        Moshi moshi = new Moshi.Builder().build();
        return moshi.adapter(Message.class).toJson(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}
