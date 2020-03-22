package com.example.sorteador.repquery;

import java.util.List;

import com.example.sorteador.models.UserReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SorteioRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void persistData(UserReturn user){

        jdbc.update(
        "INSERT INTO participante(classe,nick,ip,pontuacao) VALUES (?,?,?,?)"    
        ,user.getClasse()
        ,user.getNick()
        ,user.getIp()
        ,user.getPontuacao());
    }

    public List<UserReturn> checkIpExists(String ip){
        List<UserReturn> users = 
            jdbc.query(
                "SELECT * FROM participante WHERE ip = ?",new Object[]{ip},
                (rs,rowNum) -> 
                    new UserReturn(rs.getString("nick"),
                                   rs.getString("ip"),
                                   rs.getLong("classe"),
                                   rs.getLong("pontuacao")));

                                
           

        return users;
    }

    public List<UserReturn> checkValueExists(long value){
        List<UserReturn> users = 
            jdbc.query(
                "SELECT * FROM participante WHERE pontuacao = ?",new Object[]{value},
                (rs,rowNum) -> 
                    new UserReturn(rs.getString("nick"),
                                   rs.getString("ip"),
                                   rs.getLong("classe"),
                                   rs.getLong("pontuacao")));
        
        return users;
    } 

    public List<UserReturn> getAllParticipants(){
        
        List<UserReturn> users = 
            jdbc.query("SELECT * FROM participante",
                (rs,rowNum) ->
                    new UserReturn(rs.getString("nick"),
                    rs.getString("ip"),
                    rs.getLong("classe"),
                    rs.getLong("pontuacao")));

        return users;
    }
    public void clearData(){
        jdbc.execute("DELETE FROM participante");
    }
}