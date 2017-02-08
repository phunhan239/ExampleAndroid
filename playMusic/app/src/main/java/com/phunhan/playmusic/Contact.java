package com.phunhan.playmusic;

import java.io.Serializable;

/**
 * Created by Nhan on 05/01/2017.
 */

public class Contact implements Serializable {
    private String name;
    private String phone;
    private String gender;
    private String language;

    public Contact(String name, String phone, String gender, String language) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getInfo() {
        String content = "";
        content += "Name " + name + "\n";
        content += "Phone " + phone + "\n";
        content += "Gender " + gender + "\n";
        content += "Language " + language + "\n";

        return content;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
