package com.darty.kata.controllers;

import com.darty.kata.services.KataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = KataController.class)
@Import(KataService.class)
class KataControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Devrait saluer l'equipe Darty")
    void should_greet_the_darty_team() {
        webClient.get().uri("/kata/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello P&C Team ! Bienvenue dans ce petit kata");
    }

    @Test
    @DisplayName("Devrait indiquer qu'un nombre est un palindrome")
    void should_indicate_whether_a_number_is_a_palindrome() {
        var number = 151;
        webClient.get().uri("/kata/palindrome/{number}", number)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(String.format("%d est un palindrome", number));
    }

    @Test
    @DisplayName("Devrait indiquer qu'un nombre n'est pas un palindrome")
    void should_indicate_whether_a_number_is_not_a_palindrome() {
        var number = 152;
        webClient.get().uri("/kata/palindrome/{number}", number)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(String.format("%d n'est pas un palindrome", number));
    }

    @Test
    @DisplayName("Devrait convertir une chaine de caracteres en code Chuck Norris")
    void should_convert_to_chuck_norris_code() {
        // Premier test
        var message = "C";
        webClient.get().uri("/kata/chucknorris/{message}", message)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("0 0 00 0000 0 00");
        // Deuxieme test
        message = "CC";
        webClient.get().uri("/kata/chucknorris/{message}", message)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("0 0 00 0000 0 000 00 0000 0 00");
    }
}