package com.example.sorteador.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.example.sorteador.models.CredentialsDTO;
import com.example.sorteador.models.UserDTO;
import com.example.sorteador.models.UserReturn;
import com.example.sorteador.services.SorteadorService;
import com.example.sorteador.services.WebServletService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/sorteador")
@RestController
public class SorteadorController {

    @Autowired
    private SorteadorService serv;

    @PostMapping("/participante")
    public ResponseEntity<?> adicionarParticipante(@RequestBody UserDTO user) {

        UserReturn userReturn = serv.roll(user);

        if (userReturn != null)
            return ResponseEntity.ok(userReturn);

        return ResponseEntity.badRequest().body("Você já está participando");
    }

    @GetMapping("/obter")
    public List<UserReturn> obterParticipantes() {
        return serv.getAllParticipants();
    }

    @PostMapping("/resetar")
    public ResponseEntity<?> resetar(@RequestBody CredentialsDTO credentials) {
        boolean reset = false;

        try {
            byte[] encode = Base64.encodeBase64(credentials.getPassword().getBytes());
            credentials.setPassword(new String(encode));
            reset = serv.resetar(credentials);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro");
        }
        if(!reset)
            return ResponseEntity.badRequest().body("Login ou senha incorretos");
        
        return ResponseEntity.ok().body("");
    }
}