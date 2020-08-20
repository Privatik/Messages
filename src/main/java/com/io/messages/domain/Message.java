package com.io.messages.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.messages.adapter.DataFormat;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.Moshi;
import org.postgresql.util.PSQLException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DataFormat
    private LocalDateTime dateTime;

   /* @ManyToOne
    @JoinColumn(name="chat_id", nullable=false)
    private Chat chat;*/

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Transient
    private long sendId;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getSendId() {
        return sendId;
    }

    public void setSendId(long sendId) {
        this.sendId = sendId;
    }
}
