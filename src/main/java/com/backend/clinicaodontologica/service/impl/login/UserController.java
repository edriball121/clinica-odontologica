package com.backend.clinicaodontologica.service.impl.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @GetMapping("/")
    public String home() {
        return "redirect:/turno.html";
    }

    @GetMapping("/user")
    public String user() {
        return "redirect:/turno.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/odontologo.html";
    }
}
