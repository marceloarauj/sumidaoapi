package com.example.sorteador.controllers;

import java.util.List;

import com.example.sorteador.models.UserDTO;
import com.example.sorteador.models.UserReturn;
import com.example.sorteador.services.SorteadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SorteadorWebSocket {

    @Autowired
    private SorteadorService sortServ;

    @MessageMapping("/sortear")
    @SendTo("/get/users")
    public List<UserReturn> getAllParticipants(UserDTO user){
        


        return sortServ.getAllParticipants();
    }
}