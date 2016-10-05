package com.github.algorithm.lemke_howson;

import com.github.model.Application;
import com.github.model.Equilibrium;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright & Author
 * michal
 */
@Component("equilibriumService")
public class DefaultEquilibriumService implements EquilibriumService {

    @Override
    public Equilibrium createEquilibrium(String[][] matrix, Application application) {
        Equilibrium equilibrium = new Equilibrium();

        HashMap<String, String> map = new HashMap<>();

        int valueIndex = application.getColumns() - 2;
        int symbolIndex = application.getColumns() - 1;

        for (int i = 0; i < application.getLines(); i++) {
            String value = matrix[i][valueIndex];
            String symbol = matrix[i][symbolIndex];
            map.put(symbol, value);
        }

        ArrayList<String> firstPlayer = new ArrayList<>();
        ArrayList<String> secondPlayer = new ArrayList<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (stringStringEntry.getKey().equals("z0") ||
                    stringStringEntry.getKey().equals("z1") ||
                    stringStringEntry.getKey().equals("z2")) {
                firstPlayer.add(stringStringEntry.getValue());
            }
            if (stringStringEntry.getKey().equals("z3") ||
                    stringStringEntry.getKey().equals("z4") ||
                    stringStringEntry.getKey().equals("z5")) {
                secondPlayer.add(stringStringEntry.getValue());
            }

            if (stringStringEntry.getKey().equals("r0") ||
                    stringStringEntry.getKey().equals("r1") ||
                    stringStringEntry.getKey().equals("r2")) {
                firstPlayer.add("0.00");
            }
            if (stringStringEntry.getKey().equals("r3") ||
                    stringStringEntry.getKey().equals("r4") ||
                    stringStringEntry.getKey().equals("r5")) {
                secondPlayer.add("0.00");
            }
        }

        equilibrium.setFirstPlayer(getValuesForEquilibrium(firstPlayer));

        equilibrium.setSecondPlayer(getValuesForEquilibrium(secondPlayer));

        application.setEquilibrium(equilibrium);

        return equilibrium;
    }

    private List<String> getValuesForEquilibrium(List<String> equilibrium) {
        Double sum = new Double("0.00");
        for (String value : equilibrium) {
            Double dob = MatrixUtil.valueOf(value);
            sum += dob;
        }
        ArrayList<String> toReturn = new ArrayList<>();
        for (String val : equilibrium) {
            toReturn.add(String.valueOf(MatrixUtil.valueOf(val) / sum));
        }
        return toReturn;
    }

}
