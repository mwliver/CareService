package com.github.algorithm.application;

/**
 * Copyright & Author
 * michal
 */
public enum DiseaseEnum {

    GRYPA("GRYPA"),
    OSPA("OSPA"),
    ROZYCZKA("ROZYCZKA");

    private String type;

    DiseaseEnum(String type) {
        this.type = type;
    }
}
