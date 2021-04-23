package com.github.pongraczl.example.graphqlserverjavademo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String welcome() {
        return "<h2>This is a GraphQL Server Demo Application</h2>" +
                "The GraphQL endpoint is /graphql<br>" +
                "<em>See README.md for more details...</em>";
    }
}
