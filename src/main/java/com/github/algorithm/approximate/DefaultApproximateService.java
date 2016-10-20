package com.github.algorithm.approximate;

import com.github.algorithm.lemke_howson.MatrixUtil;
import com.github.model.Application;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("approximateService")
public class DefaultApproximateService implements ApproximateService {


    @Override
    public String[][] convertToMatrix(String[][] firstMatrix, String[][] secondMatrix, String[][] thirdMatrix) {
        String[][] result = new String[9][3];

        String[] first = firstMatrix[0];
        String[] second = secondMatrix[0];
        String[] third = thirdMatrix[0];


        result[0][0] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[0])) / 3);
        result[0][1] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[1])) / 3);
        result[0][2] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[2])) / 3);

        result[1][0] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[0])) / 3);
        result[1][1] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[1])) / 3);
        result[1][2] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[2])) / 3);

        result[2][0] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[0])) / 3);
        result[2][1] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[1])) / 3);
        result[2][2] = String.valueOf((MatrixUtil.valueOf(first[0]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[2])) / 3);


        result[3][0] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[0])) / 3);
        result[3][1] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[1])) / 3);
        result[3][2] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[2])) / 3);

        result[4][0] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[0])) / 3);
        result[4][1] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[1])) / 3);
        result[4][2] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[2])) / 3);

        result[5][0] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[0])) / 3);
        result[5][1] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[1])) / 3);
        result[5][2] = String.valueOf((MatrixUtil.valueOf(first[1]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[2])) / 3);


        result[6][0] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[0])) / 3);
        result[6][1] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[1])) / 3);
        result[6][2] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[0]) + MatrixUtil.valueOf(third[2])) / 3);

        result[7][0] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[0])) / 3);
        result[7][1] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[1])) / 3);
        result[7][2] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[1]) + MatrixUtil.valueOf(third[2])) / 3);

        result[8][0] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[0])) / 3);
        result[8][1] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[1])) / 3);
        result[8][2] = String.valueOf((MatrixUtil.valueOf(first[2]) + MatrixUtil.valueOf(second[2]) + MatrixUtil.valueOf(third[2])) / 3);

        return result;
    }

    @Override
    public String[] calculateMixed(String[][] values, String[] probability) {
        String[] toReturn = new String[3];

        String result = String.valueOf(
                (MatrixUtil.valueOf(probability[0]) *
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][0])))
                                + MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[3][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][0])))
                                + MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[6][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][0]))))) +
                        (MatrixUtil.valueOf(probability[1]) *
                                (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][1])))
                                        + MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[3][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][1])))
                                        + MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[6][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][1]))))) +
                        (MatrixUtil.valueOf(probability[2]) *
                                (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][2])))
                                        + MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[3][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][2])))
                                        + MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[6][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][2]))))));

        toReturn[0] = result;
        toReturn[1] = result;
        toReturn[2] = result;

        return toReturn;
    }

    @Override
    public String[][] calculatePure(String[][] values, String[] probability) {
        String[][] toReturn = new String[3][3];

        // First row
        String firstFirst =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[0][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[0][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[1][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[2][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][2])))));
        toReturn[0][0] = firstFirst;

        String firstSecond =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[3][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[3][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[4][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[5][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][2])))));
        toReturn[0][1] = firstSecond;

        String firstThird =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[6][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[6][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[7][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[8][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][2])))));
        toReturn[0][2] = firstThird;

        // Second row
        String secondFirst =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][0])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][0])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][0])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][0])))));
        toReturn[1][0] = secondFirst;

        String secondSecond =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][1])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][1])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][1])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][1])))));
        toReturn[1][1] = secondSecond;

        String secondThird =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][2])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][2])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][2])))));
        toReturn[1][2] = secondThird;

        // Third row
        String thirdFirst =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[0][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[0][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[0][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[3][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[3][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[6][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[6][2])))));
        toReturn[2][0] = thirdFirst;

        String thirdSecond =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[1][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[1][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[1][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[4][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[4][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[7][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[7][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[7][2])))));
        toReturn[2][1] = thirdSecond;

        String thirdThird =
                String.valueOf(
                        (MatrixUtil.valueOf(probability[0]) * ((MatrixUtil.valueOf(probability[0]) * MatrixUtil.valueOf(values[2][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[2][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[2][2])))) +
                                (MatrixUtil.valueOf(probability[1]) * ((MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[5][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[5][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[5][2])))) +
                                (MatrixUtil.valueOf(probability[2]) * ((MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][0])) + (MatrixUtil.valueOf(probability[1]) * MatrixUtil.valueOf(values[8][1])) + (MatrixUtil.valueOf(probability[2]) * MatrixUtil.valueOf(values[8][2])))));
        toReturn[2][2] = thirdThird;


        return toReturn;
    }

    @Override
    public String[] randomValues() {
        String[] result = new String[3];
        double all = 1L;
        double random = Math.random();

        double value = all - (all - random);
        result[0] = String.valueOf(value);
        all = all - value;

        random = Math.random();
        while (random > all) {
            random = Math.random();
        }
        value = all - (all - random);
        result[1] = String.valueOf(value);

        all = all - value;
        result[2] = String.valueOf(all);

        return result;
    }

    @Override
    public String approximate(Application application) {
        String[][] firstMatrix = application.getFirstMatrix();
        String[][] secondMatrix = application.getSecondMatrix();
        String[][] thirdMatrix = application.getThirdMatrix();

        String[][] matrix = convertToMatrix(firstMatrix, secondMatrix, thirdMatrix);

        String[] probability = randomValues();

        application.setProbabilityFirst(probability[0]);
        application.setProbabilitySecond(probability[1]);
        application.setProbabilityThird(probability[2]);

        String[] mixed = calculateMixed(matrix, probability);

        String[][] pure = calculatePure(matrix, probability);

        // max z 0,0 1,0 2,0 | 0,1 1,1 2,1 | 0,2 1,2 2,2
        Double firstMax = getMax(MatrixUtil.valueOf(pure[0][0]), MatrixUtil.valueOf(pure[1][0]), MatrixUtil.valueOf(pure[2][0]));
        Double secondMax = getMax(MatrixUtil.valueOf(pure[0][1]), MatrixUtil.valueOf(pure[1][1]), MatrixUtil.valueOf(pure[2][1]));
        Double thirdMax = getMax(MatrixUtil.valueOf(pure[0][2]), MatrixUtil.valueOf(pure[1][2]), MatrixUtil.valueOf(pure[2][2]));

        Double firstResult = firstMax - MatrixUtil.valueOf(mixed[0]);
        Double secondResult = secondMax - MatrixUtil.valueOf(mixed[1]);
        Double thirdResult = thirdMax - MatrixUtil.valueOf(mixed[2]);

        return String.valueOf(getMax(firstResult, secondResult, thirdResult));
    }

    private Double getMax(Double first, Double second, Double third) {
        return Math.max(Math.max(first, second), third);
    }
}
