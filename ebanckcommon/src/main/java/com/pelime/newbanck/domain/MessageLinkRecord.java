package com.pelime.newbanck.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class MessageLinkRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String phones;

    private String body;

    private LocalDateTime savaTime;

    public MessageLinkRecord() {
    }

    public MessageLinkRecord(MessageLink messageLink) {
        this.userId=messageLink.getUserId();
        this.phones=messageLink.getPhones();
        this.body=messageLink.getBody();
        this.savaTime=LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getSavaTime() {
        return savaTime;
    }

    public void setSavaTime(LocalDateTime savaTime) {
        this.savaTime = savaTime;
    }
}
