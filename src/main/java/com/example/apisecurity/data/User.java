package com.example.apisecurity.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@ToString
public class User {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @MappedCollection private final Set<Token> tokens = new HashSet<>();

    public static User of(String firstName,
                          String lastName,
                          String email,
                          String password){
        return new User(null,firstName,lastName,email,password,Collections.emptyList());
    }

    @PersistenceConstructor
    private User(Long id, String firstName, String lastName, String email, String password,Collection<Token> tokens) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.tokens.addAll(tokens);
    }

    public Boolean removeTokenId(Predicate<? super Token> predicate){
        return this.tokens.removeIf(predicate);
    }

    public Boolean removeToken(Token token){
        return this.tokens.remove(token);
    }

    public void addToken(Token token){
        this.tokens.add(token);
    }
}