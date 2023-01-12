package com.example.appproyecto.modelo;

public class Issue {


    public String date;
    public String informer;
    public String message;

    public Issue(){}

    public Issue(String date, String informer, String message) {
        this.date = date;
        this.informer = informer;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        informer = informer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
