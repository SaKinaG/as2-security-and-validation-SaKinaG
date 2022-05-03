package com.securityAndLogging.hw.controller;

import com.securityAndLogging.hw.entity.XUser;
import com.securityAndLogging.hw.service.XUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class XUserController {

    private static final Logger log = LoggerFactory.getLogger(XUserController.class);

    private final XUserService userService;

    public XUserController(XUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        log.info("Home page is requested with / GET request");
        return "home";
    }
    @GetMapping("/home")
    public String homeP() {
        log.info("Home page is requested with /home GET request");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        log.info("Login page is requested with GET request");
        return "login";
    }
    @GetMapping("/sign-up")
    public String signUpGet(){
        log.warn("Registration will be called");
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String signUp(@RequestParam("full_name") String fullName,
                               @RequestParam("password") String password,
                               @RequestParam("username") String username) {
        try {
            XUser xUser = new XUser();
            xUser.setUsername(username);
            xUser.setPassword(password);
            xUser.setFullName(fullName);
            log.info("sign-up is called for user " + xUser);
            userService.createUser(xUser);
        } catch (Exception e) {
            log.error("While creating new user, following error occurred " + e);
        }
        log.info("Sign Up is called");
        return "sign-up";
    }
}
