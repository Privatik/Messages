package com.io.messages.domain;

import org.hibernate.annotations.RowId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "photo_max_orig")
    private String photo_max_orig;

  /*  @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats;*/

   /* @OneToMany(mappedBy="user")
    private List<Message> messages;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto_max_orig() {
        return photo_max_orig;
    }

    public void setPhoto_max_orig(String photo_max_orig) {
        this.photo_max_orig = photo_max_orig;
    }

/* public Set<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(Set<Chat> chatList) {
        this.chatList = chatList;
    }*/
}
