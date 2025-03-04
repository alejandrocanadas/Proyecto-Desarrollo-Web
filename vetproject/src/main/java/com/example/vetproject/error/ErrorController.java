/* package com.example.vetproject.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(Model model, NotFoundException ex) {
        model.addAttribute("id", ex.getId());
        return "error.html";
    }
}
*/