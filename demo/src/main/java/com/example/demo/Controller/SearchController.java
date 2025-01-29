package com.example.demo.Controller;

import com.example.demo.DTO.ProfileDisplay;
import com.example.demo.DTO.SearchDisplay;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private UserService userService;

    @GetMapping("/searchByName")
    public List<SearchDisplay> searchByName(@RequestParam("name") String name) {
        return userService.searchByName(name);
    }
    @GetMapping("/searchByNumber")
    public List<SearchDisplay> searchByNumber(@RequestParam("phone_number") String number) {
        return userService.searchContactByPhoneNumber(number);
    }

    @GetMapping("/displayProfile")
    public ProfileDisplay DisplayPersonInfo(@RequestHeader("Authorization") String token,@RequestParam("phone_number") String contactToDisplay) {
        return userService.DisplayPersonInfo(token,contactToDisplay);
    }
}
