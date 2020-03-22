package com.example.sorteador.models;


public class UserDTO {
    
    private String nick;
    private long classe;

    public long getClasse() {
        return classe;
    }

    public String getNick() {
        return nick;
    }

    public void setClasse(long classe) {
        this.classe = classe;
    }


    public void setNick(String nick) {
        this.nick = nick;
    }

}