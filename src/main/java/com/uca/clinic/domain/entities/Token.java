package com.uca.clinic.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;
    private Date timestamp;
    private boolean active;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Token(String content, User user){
        this.active = true;
        this.content = content;
        this.user = user;
        this.timestamp = Date.from(Instant.now());
    }


    @Override
    public String toString(){
        return "Token{" +
               "id=" + id +
               ", content='" + content + '\'' +
               ", timestamp=" + timestamp +
               ", active=" + active +
               ", user=" + user +
               '}';
    }

}
