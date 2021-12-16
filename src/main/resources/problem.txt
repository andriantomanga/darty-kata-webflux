package com.darty.kata.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KataService {
    public Mono<String> greetTheDartyTeam() {
        return Mono.empty();
    }

    public Mono<String> checkPalindrome(int number) {
        return Mono.empty();
    }

    public Flux<String> unaryEncoding(String message) {
        return Flux.empty();
    }
}
