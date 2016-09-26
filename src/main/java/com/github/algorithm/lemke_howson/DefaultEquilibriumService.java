package com.github.algorithm.lemke_howson;

import com.github.model.Equilibrium;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("equilibriumService")
public class DefaultEquilibriumService implements EquilibriumService {

    @Override
    public Equilibrium createEquilibrium(Double[][] matrix) {
        Equilibrium equilibrium = new Equilibrium();

//        equilibrium.setFirstPlayer();
//        equilibrium.setSecondPlayer();
        return null;
    }
}
