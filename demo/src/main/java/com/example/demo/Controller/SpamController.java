package com.example.demo.Controller;

import com.example.demo.Service.SpamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spam")
public class SpamController {
    @Autowired
    private SpamService spamService;

    @PostMapping("/report")
    public String reportSpam(@RequestParam("phone_number") String phoneNumber) {
        spamService.reportSpam(phoneNumber);
        return "Spam reported for number: " + phoneNumber;
    }
}
