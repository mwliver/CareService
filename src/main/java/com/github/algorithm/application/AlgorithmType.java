package com.github.algorithm.application;

/**
 * Copyright & Author
 * michal
 */
public enum AlgorithmType {

    LEMKE_HOWSON("Lemke-Howson"),
    PRZYBLIZONY_DOKLADNY("Przyblizony-dokladny");

    private String value;

    AlgorithmType(String value) {
        this.value = value;
    }
}
