package com.codeup.springblog.controllers;
import java.io.IOException;

import com.codeup.springblog.services.SendgridMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value = "/api")
public class MailController {

    @Autowired
    SendgridMailService SendgridMailService;

    @PostMapping("/send-text")
    public String send() throws IOException {
        return SendgridMailService.sendTextEmail();
    }


}