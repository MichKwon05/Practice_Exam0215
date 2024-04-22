package mx.edu.utez.extradelextra.controller;

import jakarta.validation.Valid;
import mx.edu.utez.extradelextra.model.User;
import mx.edu.utez.extradelextra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    @Autowired
    LoginService loginService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("capturistas")
    public String transcribers() {
        return "transcriber_list";
    }

    @GetMapping("clientes")
    public String clients() {
        return "client_list";
    }

    @GetMapping("pedidos")
    public String orders() {
        return "order_list";
    }

    @GetMapping("productos")
    public String products() {
        return "product_list";
    }

    @GetMapping("bitacora")
    public String logins(Model model) {
        model.addAttribute("logins", loginService.findAll());
        return "login_list";
    }

    @GetMapping("registro")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register_form";
    }

    @PostMapping("registro")
    public String register(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", "Errores en los datos ingresados");
            return "register_form";
        }
        attributes.addFlashAttribute("successMessage", "Usuario validado");
        return "redirect:/";
    }
}
