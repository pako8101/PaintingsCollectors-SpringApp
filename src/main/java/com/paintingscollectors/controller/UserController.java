package com.paintingscollectors.controller;

import com.paintingscollectors.config.LoggedUser;
import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }


    @ModelAttribute("registerData")
    public UserRegisterDto registerDTO() {
        return new UserRegisterDto();
    }@ModelAttribute("loginData")
    public UserLoginDto useLoginDTO() {
        return new UserLoginDto();
    }

    @GetMapping("/register")
    public String register() {
        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto data, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData",
                    data);
            redirectAttributes.addFlashAttribute("org.springframework" +
                            ".validation.BindingResult" +
                            ".registerData",
                    bindingResult);
            return "redirect:/register";
        }
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerData",
                    data);
            redirectAttributes.addFlashAttribute("diffPassword", true);
            return "redirect:/register";
        }

        boolean success = userService.register(data);

        if (!success) {
            return "redirect:/register";
        }

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login(){
        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto data,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginData",
                    data);
            redirectAttributes.addFlashAttribute("org.springframework" +
                            ".validation.BindingResult" +
                            ".loginData",
                    bindingResult);
            return "redirect:/login";
        }
        boolean success =
                userService.login(data);

        if (!success){
            redirectAttributes.addFlashAttribute("loginData",
                    data);
            redirectAttributes.addFlashAttribute("loginError",
                    true);
            return "redirect:/login";
        }
        return "redirect:/home";

    }
    @PostMapping("/logout")
    public String logout(){
        if (!loggedUser.isLoggedIn()) {
            return "redirect:/";
        }
        loggedUser.logout();
        return "redirect:/";
    }


}
