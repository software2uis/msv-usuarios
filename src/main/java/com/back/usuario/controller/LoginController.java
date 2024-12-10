package com.back.usuario.controller;

import com.back.usuario.service.LoginService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/user/login")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        return loginService.logout(username);
    }

    @GetMapping("/status")
    public String checkLoginStatus(@RequestParam String username) {
        boolean isLoggedIn = loginService.isLoggedIn(username);
        return isLoggedIn ? "User " + username + " is logged in." : "User " + username + " is not logged in.";
    }

    @GetMapping("/active-session/{username}")
    public ResponseEntity<Boolean> getActiveSession(@PathVariable String username) {
        return new ResponseEntity<Boolean>(loginService.isLoggedIn(username), HttpStatus.OK);
    }

}
