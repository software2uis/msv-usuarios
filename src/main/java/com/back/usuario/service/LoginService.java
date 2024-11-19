package com.back.usuario.service;

import com.back.usuario.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final Map<String, User> users = new HashMap<>();

    // Variables globales para la sesión activa
    private String activeUsername = null;
    private boolean isActiveSession = false;

    public LoginService() {
        users.put("pepe", new User("pepe", "741"));
        users.put("luis", new User("luis", "852"));
        users.put("jose", new User("jose", "963"));
    }

    public String login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            // Actualizamos la sesión activa
            activeUsername = username;
            isActiveSession = true;
            return "Login successful for user: " + username;
        }
        return "Invalid username or password";
    }

    public String logout(String username) {
        User user = users.get(username);
        if (user != null && user.isLoggedIn()) {
            user.setLoggedIn(false);
            // Restablecemos las variables globales si el usuario activo cierra sesión
            if (username.equals(activeUsername)) {
                activeUsername = null;
                isActiveSession = false;
            }
            return "User " + username + " logged out successfully.";
        }
        return "User " + username + " is not logged in.";
    }

    public boolean isLoggedIn(String username) {
        User user = users.get(username);
        return user != null && user.isLoggedIn();
    }

    public Map<String, Object> getActiveSession() {
        Map<String, Object> response = new HashMap<>();
        response.put("username", activeUsername != null ? activeUsername : "No active user");
        response.put("isLoggedIn", isActiveSession);
        return response;
    }
}

