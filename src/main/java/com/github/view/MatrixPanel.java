package com.github.view;

import com.github.algorithm.application.AlgorithmType;
import com.github.algorithm.approximate.ApproximateService;
import com.github.algorithm.lemke_howson.LemkeHowson;
import com.github.model.Application;
import com.github.model.Equilibrium;
import com.github.util.Util;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Copyright & Author
 * michal
 */
@Service
@Scope("prototype")
public class MatrixPanel extends JFrame {

    @Autowired
    LemkeHowson lemkeHowson;

    @Autowired
    ApproximateService approximateService;

    private JPanel panel1;
    private JSpinner secondValue;
    private JSpinner thirdValue;
    private JSpinner firstValue;
    private JTextField firstStrategy;
    private JTextField secondStrategy;
    private JTextField thirdStrategy;
    private JButton obliczButton;
    private JButton dodajButton;
    private JButton powrótButton;
    private JTextField playersCount;
    private JTextField algorithm;
    private JLabel info;
    private JTextArea matrixs;

    private Application application;

    public MatrixPanel(Application application) {
        add(panel1);
        this.application = application;

        firstValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getFirstMatrix()[0][0]), 0.00, 100.00, 1.00));
        secondValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getFirstMatrix()[0][1]), 0.00, 100.00, 1.00));
        thirdValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getFirstMatrix()[0][2]), 0.00, 100.00, 1.00));

        firstStrategy.setText(application.getFirstStrategy());
        firstStrategy.setEnabled(false);
        secondStrategy.setText(application.getSecondStrategy());
        secondStrategy.setEnabled(false);
        thirdStrategy.setText(application.getThirdStrategy());
        thirdStrategy.setEnabled(false);

        playersCount.setText("0");
        playersCount.setEnabled(false);
        algorithm.setText(AlgorithmType.LEMKE_HOWSON.name());
        algorithm.setEnabled(false);

        matrixs.setEnabled(false);

        info.setVisible(false);

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ustaw ilość graczy przy dodawaniu
                MatrixPanel.this.application.setNumberOfPlayers(MatrixPanel.this.application.getNumberOfPlayers() != null ? MatrixPanel.this.application.getNumberOfPlayers() + 1 : 1);

                if (MatrixPanel.this.application.getNumberOfPlayers() == 1) {
                    playersCount.setText("1");
                    algorithm.setText(AlgorithmType.LEMKE_HOWSON.name());
                    // todo logika mapowania wypłat
                    // przepisz wartości wypłat do application
                    MatrixPanel.this.application.setFirstMatrix(new String[][]
                            {
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())}
                            });
                    // zmień model na następną macierz w spinie
                    firstValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getSecondMatrix()[0][0]), 0.00, 100.00, 1.00));
                    secondValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getSecondMatrix()[0][1]), 0.00, 100.00, 1.00));
                    thirdValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getSecondMatrix()[0][2]), 0.00, 100.00, 1.00));

                    // Wyświetl macierze w informacjach
                    StringBuilder sb = new StringBuilder();
                    sb.append("Zysk pierwszego gracza:").append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][0]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][1]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][2]);
                    matrixs.setText(sb.toString());
                }
                if (MatrixPanel.this.application.getNumberOfPlayers() == 2) {
                    playersCount.setText("2");
                    algorithm.setText(AlgorithmType.LEMKE_HOWSON.name());
                    // todo logika mapowania wypłat
                    // przepisz wartości wypłat do application
                    MatrixPanel.this.application.setSecondMatrix(new String[][]
                            {
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())}
                            }
                    );
                    // zmień model na następną macierz w spinie
                    firstValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getThirdMatrix()[0][0]), 0.00, 100.00, 1.00));
                    secondValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getThirdMatrix()[0][1]), 0.00, 100.00, 1.00));
                    thirdValue.setModel(new SpinnerNumberModel(Double.parseDouble(application.getThirdMatrix()[0][2]), 0.00, 100.00, 1.00));

                    // Wyświetl macierze w informacjach
                    StringBuilder sb = new StringBuilder();
                    sb.append("Zysk pierwszego gracza:").append("\t").append("Zysk drugiego gracza:").append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][0]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][0]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][1]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][1]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][2]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][2]);

                    matrixs.setText(sb.toString());

                    // pokaż info że trzeba obliczyć
                    info.setText("Oblicz lub dodaj gracza");
                    info.setVisible(true);
                    repaint();
                }
                if (MatrixPanel.this.application.getNumberOfPlayers() == 3) {
                    playersCount.setText("3");
                    algorithm.setText(AlgorithmType.PRZYBLIZONY_DOKLADNY.name());
                    // todo logika mapowania wypłat
                    // przepisz wartości wypłat do application
                    MatrixPanel.this.application.setThirdMatrix(new String[][]
                            {
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())},
                                    {String.valueOf(firstValue.getValue()), String.valueOf(secondValue.getValue()), String.valueOf(thirdValue.getValue())}
                            });

                    // Wyświetl macierze w informacjach
                    StringBuilder sb = new StringBuilder();
                    sb.append("Zysk pierwszego gracza:").append("\t").append("Zysk drugiego gracza:").append("\t").append("Zysk trzeciego gracza:").append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][0]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][0]).append("\t\t").append(MatrixPanel.this.application.getThirdMatrix()[0][0]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][1]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][1]).append("\t\t").append(MatrixPanel.this.application.getThirdMatrix()[0][1]).append("\n")
                            .append(MatrixPanel.this.application.getFirstMatrix()[0][2]).append("\t\t").append(MatrixPanel.this.application.getSecondMatrix()[0][2]).append("\t\t").append(MatrixPanel.this.application.getThirdMatrix()[0][2]);

                    matrixs.setText(sb.toString());

                    // pokaż info że trzeba obliczyć
                    info.setText("Należy obliczyć wynik");
                    info.setVisible(true);

                    // zablokuj edycję wypłat
                    firstValue.setEnabled(false);
                    secondValue.setEnabled(false);
                    thirdValue.setEnabled(false);
                    dodajButton.setEnabled(false);

                    repaint();
                }
            }
        });

        obliczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (MatrixPanel.this.application.getNumberOfPlayers() == 2) {
                    long startTime = System.currentTimeMillis();

                    MatrixPanel.this.application.setLines(7);
                    MatrixPanel.this.application.setColumns(8);

                    Equilibrium equilibrium = lemkeHowson.lemkeHowson(MatrixPanel.this.application);

                    for (String value : equilibrium.getFirstPlayer()) {
                        System.out.println("Player 1 Equilibrum " + value + "\n");
                    }
                    for (String value : equilibrium.getSecondPlayer()) {
                        System.out.println(" Player 2 Equilibrum " + value + "\n");
                    }

                    application.setResultLabel("Czas działania");

                    long finishTime = System.currentTimeMillis();

                    String result = Util.format(finishTime - startTime);
                    System.out.println(result);
                    application.setResult(result);
                }
                if (MatrixPanel.this.application.getNumberOfPlayers() == 3) {
                    String result = approximateService.approximate(MatrixPanel.this.application);

                    System.out.println(result);
                    application.setResult(result);
                    application.setResultLabel("Epsilon");
                }

                if (MatrixPanel.this.application.getNumberOfPlayers() == 2 || MatrixPanel.this.application.getNumberOfPlayers() == 3) {
                    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "beans-datasource.xml");
                    ResultPanel resultPanel = (ResultPanel) context.getBean("resultPanel", application);
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (IllegalAccessException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException e1) {
                        e1.printStackTrace();
                    }
                    resultPanel.setContentPane(resultPanel.getContentPane());
                    resultPanel.pack();
                    resultPanel.setSize(700, 700);
                    resultPanel.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - resultPanel.getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - resultPanel.getSize().height) / 2);
                    resultPanel.setVisible(true);
                    resultPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    MatrixPanel.this.dispose();
                    repaint();
                }
            }
        });

        powrótButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "beans-datasource.xml");
                MainForm mainForm = (MainForm) context.getBean("mainForm");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (IllegalAccessException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException e1) {
                    e1.printStackTrace();
                }
                mainForm.setContentPane(mainForm.getContentPane());
                mainForm.pack();
                mainForm.setSize(700, 500);
                mainForm.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - mainForm.getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - mainForm.getSize().height) / 2);
                mainForm.setVisible(true);
                mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                MatrixPanel.this.dispose();
                repaint();
            }
        });
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        firstValue = new JSpinner();
        panel2.add(firstValue, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secondValue = new JSpinner();
        panel2.add(secondValue, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thirdValue = new JSpinner();
        panel2.add(thirdValue, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstStrategy = new JTextField();
        firstStrategy.setText("");
        panel2.add(firstStrategy, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        secondStrategy = new JTextField();
        panel2.add(secondStrategy, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        thirdStrategy = new JTextField();
        panel2.add(thirdStrategy, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dodajButton = new JButton();
        dodajButton.setText("Dodaj");
        panel2.add(dodajButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        obliczButton = new JButton();
        obliczButton.setText("Oblicz");
        panel2.add(obliczButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        powrótButton = new JButton();
        powrótButton.setText("Powrót");
        panel2.add(powrótButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 20));
        label1.setText("Informacje");
        panel4.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        info = new JLabel();
        info.setEnabled(false);
        info.setText("Oblicz lub dodaj gracza");
        panel4.add(info, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        matrixs = new JTextArea();
        panel5.add(matrixs, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Ilość graczy");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Algorytm");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playersCount = new JTextField();
        playersCount.setEnabled(true);
        panel3.add(playersCount, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        algorithm = new JTextField();
        panel3.add(algorithm, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
