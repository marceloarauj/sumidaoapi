package com.example.sorteador.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebServletService {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    public String getClientIp(){
        String ip = "";

        if(request != null){
            ip = request.getHeader("X-FORWARDED-FOR");
            if(ip ==null || "".equals(ip))
                ip = request.getRemoteAddr();

        }

        return ip;
    }
}