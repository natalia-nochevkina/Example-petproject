package com.natanight.petproject.controller.errors;

import com.natanight.petproject.models.Message;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageNotFoundController implements ErrorController {
    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message pageNotFound() {
        return new Message("That's 404");
    }
}
