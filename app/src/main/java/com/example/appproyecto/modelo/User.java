package com.example.appproyecto.modelo;

import java.util.List;

public class User {
    private int idUsuario;
    private String mail;
    private String password;
    private String name;
    private String username;
    private String lastName;
    private Integer dinero;
    private Integer xp;

    public User(){}

    public User(String Username,String Mail, String Name, String LastName, String Pasword) {
        this.username = Username;
        this.xp = 0;
        this.mail = Mail;
        this.name = Name;
        this.lastName = LastName;
        this.password = Pasword;
        this.dinero = 100;
    }

    public User(int idUsuario, int xp, String username, String mail, String name, String lastName, String password, Integer dinero) {
        this.idUsuario = idUsuario;
        this.xp = xp;
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.dinero = dinero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }
}
