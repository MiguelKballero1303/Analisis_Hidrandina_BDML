package com.example.Proyecto_SWAD.Controladores;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorMenu {
    
    @GetMapping("/")
    public String Ferreteria() {
        return "Ferreteria"; // menu.html
    }

    @GetMapping("/Login")
    public String Login() {
        return "login"; // menu.html
    }

    @GetMapping("/Menu")
    public String Menu(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return "Principal";
    }
}
