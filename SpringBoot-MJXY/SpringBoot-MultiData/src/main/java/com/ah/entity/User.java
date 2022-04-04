package com.ah.entity;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:15 PM
 */
public class User {

    private int id;

    private String username;

    private String sex;

    @Override
    public String toString() {
        return "com.ah.entity.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
