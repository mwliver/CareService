package com.github.model;

import com.github.algorithm.application.DiseaseEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright & Author
 * michal
 */
@Entity
public class Application implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer numberOfPlayers;                // liczba graczy

    @Enumerated(EnumType.STRING)
    private DiseaseEnum disease;                    // rodzaj choroby

    @Column(name = "lines_number")
    private Integer lines;                          // ilość wierszy

    @Column(name = "columns_number")
    private Integer columns;                        // ilość kolumn

    @OneToOne
    private Equilibrium equilibrium;                // równowaga

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public DiseaseEnum getDisease() {
        return disease;
    }

    public void setDisease(DiseaseEnum disease) {
        this.disease = disease;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Equilibrium getEquilibrium() {
        return equilibrium;
    }

    public void setEquilibrium(Equilibrium equilibrium) {
        this.equilibrium = equilibrium;
    }
}
