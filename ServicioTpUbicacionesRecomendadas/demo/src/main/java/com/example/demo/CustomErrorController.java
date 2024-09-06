package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class CustomErrorController implements ErrorController {
/*
    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        // Custom error message
        return "Custom error message: The requested resource was not found.";
    }
*/
}
