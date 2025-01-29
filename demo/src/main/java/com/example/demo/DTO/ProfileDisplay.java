package com.example.demo.DTO;

public class ProfileDisplay {

    private String name;
    private String phoneNumber;
    private long spamCount;
    private String email;
    public ProfileDisplay(String name, String phoneNumber, long spamCount,String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.spamCount = spamCount;
        this.email = email;
    }

    public ProfileDisplay() {
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getSpamCount() {
        return spamCount;
    }

    public void setSpamCount(long spamCount) {
        this.spamCount = spamCount;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
