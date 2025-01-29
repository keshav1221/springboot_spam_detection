package com.example.demo.DTO;

public class SearchDisplay {
    private String name;
    private String phoneNumber;
    private long spamCount;

    public SearchDisplay(String name, String phoneNumber, long spamCount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.spamCount = spamCount;
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
}
