package com.darty.kata.controllers;

import com.darty.kata.services.KataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kata")
public class KataController {
    @Autowired
    private KataService kataService;

    // URL en local : http://localhost:8080/kata/
    @RequestMapping("/")
    public Mono<String> greetTheDartyTeam() {
        return kataService.greetTheDartyTeam();
    }

    // URL en local: http://localhost:8080/kata/palindrome/{number}
    @RequestMapping("/palindrome/{number}")
    public Mono<String> checkPalindrome(@PathVariable int number) {
        return kataService.checkPalindrome(number);
    }

    // URL en local : http://localhost:8080/kata/unaryencoding/{message}
    @RequestMapping("/chucknorris/{message}")
    public Flux<String> generateUnaryEncoding(@PathVariable String message) {
        return kataService.unaryEncoding(message);
    }
}
