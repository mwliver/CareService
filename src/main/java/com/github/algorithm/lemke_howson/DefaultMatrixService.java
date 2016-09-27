package com.github.algorithm.lemke_howson;

import com.github.algorithm.application.DiseaseEnum;
import com.github.model.Application;
import com.github.model.Tuple;
import org.springframework.stereotype.Component;

/**
 * Copyright & Author
 * michal
 */
@Component("matrixService")
public class DefaultMatrixService implements MatrixService {

    /**
     * Zwraca macierz z domyślnymi wartościami dla podanej choroby
     */
    @Override
    public String[][] getDefaultMatrix(Application application) {
        if (application.getDisease() != null) {
            return generateDefaultMatrix(application.getDisease());
        }
        return new String[0][0];
    }

    private String[][] generateDefaultMatrix(DiseaseEnum disease) {
        switch (disease) {
            case GRYPA:
                return new String[][] {{"5.00", "5.00", "10.00"}, {"1.0", "8.00", "3.00"}};
            case OSPA:
                return new String[][] {{"2.00", "8.00", "2.00"}, {"3.00", "2.00", "7.00"}};
            case ROZYCZKA:
                return new String[][] {{"0.00", "1.00", "2.83"}, {"0.11", "0.23", "0.11"}};
        }
        return new String[0][0];
    }

    /**
     * Łączy dwie macierz w jedną odwracając jedną z danych wejściowych
     */
    @Override
    public String[][] mergeMatrix(Application application, String[][] firstMatrix, String[][] secondMatrix) {
        int lines = application.getLines();
        int columns = application.getColumns();
        String[][] matrix = new String[lines][columns];       // 6 wierszy 7 kolumn

        // inicjalizuj zerami
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = "0.00";
            }
        }

        // wiersze
        for (int i = 1, k = 0; i < 3 && k < 2; i++, k++) {
            // kolumny
            for (int j = 0, t = 2; j < 3; j++, t++) {
                // przesuń wiersze w prawo w nowej macierzy
                matrix[i][t] = "-" + firstMatrix[k][j];
            }
        }

        // wiersze
        for (int i = 0; i < 2; i++) {
            // kolumny
            for (int j = 0, t = 3; j < 3; j++, t++) {
                // odwóć i przesuń na dół
                matrix[t][i] = "-" + secondMatrix[i][j];
            }
        }

        // z0, z1, z2 ... u góry
        for (int j = 0; j < columns - 2; j++) {
            int i = 0;      // tylko pierwszy wiersz
            matrix[i][j] = "z" + j;
        }

        // 1 z prawej
        for (int i = 1; i < lines; i++) {
            int j = columns - 2;
            matrix[i][j] = "1.00";
        }

        // r z prawej
        for (int i = 1, k = 0; i < lines; i++, k++) {
            int j = columns - 1;
            matrix[i][j] = "r" + k;
        }

        return matrix;
    }

    /**
     * Przekształca dane w macierzy według algorytmu sympleks
     */
    @Override
    public void calculateMatrix(Application application, String min, String[][] matrix, Integer line, Integer column) {
        String[][] oldMatrix = new String[application.getLines()][application.getColumns()];
        for (int i = 0; i < application.getLines(); i++) {
            for (int j = 0; j < application.getColumns(); j++) {
                oldMatrix[i][j] = matrix[i][j];
            }
        }
        String pivot = matrix[line][column];
        // 1 / p
        matrix[line][column] = String.valueOf(1.00 / MatrixUtil.valueOf(pivot));

        for (int i = 1; i < application.getLines(); i++) {
            if (i != line) {            // ta sama kolumna co pivot za wyjątkiem pivotu
                // - r / p
                matrix[i][column] = String.valueOf(MatrixUtil.valueOf(oldMatrix[i][column]) / MatrixUtil.valueOf(pivot));
                validate(matrix, oldMatrix, i, column);
            }
        }

        if (column < 2) {
            for (int j = 0; j < 2; j++) {
                if (j != column) {      // ten sam wiersz co pivot za wyjątkiem pivotu
                    // q / p
                    matrix[line][j] = String.valueOf(MatrixUtil.valueOf("-" + oldMatrix[line][j]) / MatrixUtil.valueOf(pivot));
                    validate(matrix, oldMatrix, line, j);
                }
            }
            // zmien 1 z prawej
            matrix[line][application.getColumns() - 2] = String.valueOf(MatrixUtil.valueOf("-" + oldMatrix[line][application.getColumns() - 2]) / MatrixUtil.valueOf(pivot));
        } else {
            for (int j = 2; j < application.getColumns() - 1; j++) {
                if (j != column) {
                    // q / p
                    matrix[line][j] = String.valueOf(MatrixUtil.valueOf("-" + oldMatrix[line][j]) / MatrixUtil.valueOf(pivot));
                    validate(matrix, oldMatrix, line, j);
                }
            }
            // nie trzeba bo jesteśmy przy prawej stronie więc pętla to obsłuży
//            // zmien 1 z prawej
//            matrix[line][application.getColumns() - 2] = String.valueOf(MatrixUtil.valueOf("-" + oldMatrix[line][application.getColumns() - 2]) / MatrixUtil.valueOf(pivot));
        }

        // kolumny i wiersze poza współrzędnymi pivota
        if (column < 2 || line > 3) {
            for (int i = 3; i < application.getLines(); i++) {
                for (int j = 0; j < 2; j++) {
                    if (i != line && j != column) {
                        // s - q * r / p
                        Double q = MatrixUtil.valueOf(getQ(i, j, line, column, oldMatrix));
                        Double r = MatrixUtil.valueOf(getR(i, j, line, column, oldMatrix));
                        Double val = q * r;
                        matrix[i][j] = String.valueOf(MatrixUtil.valueOf(oldMatrix[i][j]) - (val / MatrixUtil.valueOf(pivot)));
                        validate(matrix, oldMatrix, i, j);
                    }
                }
            }
            // zmien 1 z prawej
            for (int i = 3; i < application.getLines(); i++) {
                int j = application.getColumns() - 2;
                if (i != line && j != column) {
                    // s - q * r / p
                    Double q = MatrixUtil.valueOf(getQ(i, j, line, column, oldMatrix));
                    Double r = MatrixUtil.valueOf(getR(i, j, line, column, oldMatrix));
                    Double val = q * r;
                    matrix[i][j] = String.valueOf(MatrixUtil.valueOf(oldMatrix[i][j]) - (val / MatrixUtil.valueOf(pivot)));
                    validate(matrix, oldMatrix, i, j);
                }
            }
        } else {
            for (int i = 1; i < 3; i++) {
                for (int j = 2; j < application.getColumns() - 1; j++) {
                    if (i != line && j != column) {
                        // s - q * r / p
                        Double q = MatrixUtil.valueOf(getQ(i, j, line, column, oldMatrix));
                        Double r = MatrixUtil.valueOf(getR(i, j, line, column, oldMatrix));
                        Double val = q * r;
                        matrix[i][j] = String.valueOf(MatrixUtil.valueOf(oldMatrix[i][j]) - (val / MatrixUtil.valueOf(pivot)));
                        validate(matrix, oldMatrix, i, j);

                    }
                }
            }
            // nie trzeba bo jesteśmy przy prawej stronie więc pętla to obsłuży
//            // zmien 1 z prawej
//            for (int i = 1; i < 3; i++) {
//                int j = application.getColumns() - 2;
//                if (i != line && j != column) {
//                    // s - q * r / p
//                    Double q = MatrixUtil.valueOf(getQ(i, j, line, column, oldMatrix));
//                    Double r = MatrixUtil.valueOf(getR(i, j, line, column, oldMatrix));
//                    Double val = q * r;
//                    matrix[i][j] = String.valueOf(MatrixUtil.valueOf(oldMatrix[i][j]) - (val / MatrixUtil.valueOf(pivot)));
//                    validate(matrix, oldMatrix, i, j);
//                }
//            }
        }

        matrix[0][column] = oldMatrix[line][application.getColumns() - 1];
        matrix[line][application.getColumns() - 1] = oldMatrix[0][column];

        System.out.println("CalculateMatrix finish");
    }

    private void validate(String[][] matrix, String[][] oldMatrix, int i, Integer column) {
        if (matrix[i][column].contains("-0.0")) {
            matrix[i][column] = oldMatrix[i][column];
        }
    }

    /**
     * Zwraca indexy tych danych które będą zamienione
     */
    @Override
    public Tuple<Integer, Integer> getIndexes(Application application, Integer line, Integer column, String[][] matrix) {
        String left = matrix[line][application.getColumns() - 1];
        String right = matrix[0][column];
        return new Tuple<Integer, Integer>(new Integer(left.substring(1, 2)), new Integer(right.substring(1, 2)));
    }

    private String getQ(int i, int j, Integer line, Integer column, String[][] matrix) {
        return matrix[line][j];
    }

    private String getR(int i, int j, Integer line, Integer column, String[][] matrix) {
        return matrix[i][column];
    }
}
