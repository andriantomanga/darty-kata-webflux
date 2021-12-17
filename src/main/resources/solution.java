package com.darty.kata.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KataService {
    public Mono<String> greetTheDartyTeam() {
        return Mono.just("Hello P&C Team ! Bienvenue dans ce petit kata");
    }

    public Mono<String> checkPalindrome(int number) {
        int reversedNum = 0, remainder;
        int originalNum = number;
        while (number != 0) {
            remainder = number % 10;
            reversedNum = reversedNum * 10 + remainder;
            number /= 10;
        }
        if (originalNum == reversedNum) {
            return Mono.just(palindromeMessage(originalNum));
        }
        return Mono.just(notPalindromeMessage(originalNum));
    }

    private String palindromeMessage(int number) {
        return String.format("%d est un palindrome", number);
    }

    private String notPalindromeMessage(int number) {
        return String.format("%d n'est pas un palindrome", number);
    }

    public Flux<String> unaryEncoding(String message) {
        // Convertit le message en binaire
        String binary = "";
        for (int i = 0; i < message.length(); i++) {
            String charInBinary = Integer.toBinaryString((int) message.charAt(i));
            // Complete la representation binaire pour avoir exactement 7 bits
            charInBinary = "0000000".substring(charInBinary.length()) + charInBinary;
            binary += charInBinary;
        }
        // Convertit le binaire en code Chuck Norris
        char lastChar = ' ';
        String codedMessage = "";
        String[] encodedBits = new String[]{" 00 0", " 0 0"};
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) != lastChar) {
                lastChar = binary.charAt(i);
                codedMessage += encodedBits[lastChar - '0'];
            } else {
                codedMessage += "0";
            }
        }
        // Envoyer le résultat
        return Flux.just(codedMessage.substring(1));
    }
}
