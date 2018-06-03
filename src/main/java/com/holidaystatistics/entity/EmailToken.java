package com.holidaystatistics.entity;

import javax.persistence.*;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 14:27
 */

@Entity
public class EmailToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String sendTime;

    @Column
    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    public EmailToken() {
    }

    public EmailToken(String email, String userId, String sendTime, EmailType emailType) {
        this.email = email;
        this.userId = userId;
        this.sendTime = sendTime;
        this.emailType = emailType;
    }

    public enum EmailType
    {
        //重置密码
        REST_PASSWORD,
        //绑定邮箱
        BIND_EMAIL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }
}

