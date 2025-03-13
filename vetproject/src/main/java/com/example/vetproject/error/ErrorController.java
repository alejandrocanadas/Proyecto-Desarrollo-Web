package com.example.vetproject.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NotFoundMascotException.class)
    public String handleNotFoundExceptionMascota(Model model, NotFoundMascotException ex) {
        model.addAttribute("id", ex.getId());
        return "errorMascota";
    }

    @ExceptionHandler(NotFoundClientException.class)
    public String handleNotFoundExceptionCliente(Model model, NotFoundClientException ex) {
        model.addAttribute("id", ex.getId());
        return "errorCliente";
    }

}
