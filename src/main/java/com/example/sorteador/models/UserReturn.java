package com.example.sorteador.models;

public class UserReturn {

    private String nick;
    private String ip;
    private long classe;
    private long pontuacao;

    public UserReturn(){}
    public UserReturn(String nick, String ip, long classe, long pontuacao){
        this.nick = nick;
        this.ip = ip;
        this.classe = classe;
        this.pontuacao = pontuacao;
    }

    public long getClasse() {
        return classe;
    }
    public String getIp() {
        return ip;
    }

    public String getNick() {
        return nick;
    }

    public void setClasse(long classe) {
        this.classe = classe;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPontuacao(long pontuacao) {
        this.pontuacao = pontuacao;
    }

    public long getPontuacao() {
        return pontuacao;
    }
}