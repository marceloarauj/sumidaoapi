package com.example.sorteador.services;

import java.util.List;
import java.util.Random;

import com.example.sorteador.models.CredentialsDTO;
import com.example.sorteador.models.UserDTO;
import com.example.sorteador.models.UserReturn;
import com.example.sorteador.repquery.SorteioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SorteadorService {

    @Autowired
    private WebServletService webServ;

    @Autowired
    private SorteioRepository sorteio;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private Environment env;
    
    public UserReturn roll(UserDTO user){
        
        if(!canRoll())
            return null;

        Random random = new Random();
        int value = random.nextInt(1000) + 1;
        
        while(valueAlreadyExists(value)){
            value = random.nextInt(1000) + 1;
        }

        String ip = webServ.getClientIp();
        UserReturn userReturn = new UserReturn();
        userReturn.setNick(user.getNick());
        userReturn.setClasse(user.getClasse());
        userReturn.setPontuacao(value);
        userReturn.setIp(ip);
        
        sorteio.persistData(userReturn);
        
        this.template.convertAndSend("/get/users",this.getAllParticipants());
        return userReturn;
    }
    
    public boolean valueAlreadyExists(int value){
        
        List<UserReturn> users = sorteio.checkValueExists(value);

        if( users != null & users.size() != 0)
            return true;

        return false;
    }

    public boolean canRoll(){

        List<UserReturn> users = sorteio.checkIpExists(webServ.getClientIp());

        if( users != null & users.size() != 0)
            return false;

        return true;
    }

    public List<UserReturn> getAllParticipants(){
        return sorteio.getAllParticipants();
    }
    public boolean resetar(CredentialsDTO credentials){
        
        if(credentials.getLogin().equals(env.getProperty("loginreset")) 
            && credentials.getPassword().equals(env.getProperty("passwordreset"))){

            sorteio.clearData();
            this.template.convertAndSend("/get/users",this.getAllParticipants());
            return true;
        }
        
        return false;
    }
}