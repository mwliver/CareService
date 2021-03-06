package com.github.algorithm.lemke_howson;

import com.github.model.Application;
import com.github.model.Equilibrium;

/**
 * Copyright & Author
 * michal
 */
public interface EquilibriumService {

    /**
     * Tworzy obiekt równowagi
     */
    Equilibrium createEquilibrium(String[][] matrix, Application application);
}
