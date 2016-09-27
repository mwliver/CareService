package com.github.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright & Author
 * michal
 */
@Entity
public class Equilibrium implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> firstPlayer = new ArrayList<>();

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> secondPlayer = new ArrayList<>();

    public List<String> getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(List<String> firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public List<String> getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(List<String> secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
