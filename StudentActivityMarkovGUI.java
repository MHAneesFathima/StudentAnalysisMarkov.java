
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentActivityMarkovGUI extends JFrame {

    // Activity names
    private final String[] activities = {
        "Class", "Study", "Assignment", "Break"
    };

    // Default transition probability matrix
    private final double[][] defaultMatrix = {
        {0.10, 0.40, 0.30, 0.20},
        {0.25, 0.35, 0.25, 0.15},
        {0.20, 0.40, 0.20, 0.20},
        {0.30, 0.25, 0.15, 0.30}
    };

    // Default initial state vector
    private final double[] defaultState = {
        0.25, 0.35, 0.25, 0.15
    };

    private final JTextField[][] matrixFields =
            new JTextField[4][4];

    private final JTextField[] stateFields =
            new JTextField[4];

    private final JTextArea resultArea = new JTextArea();

    private final JLabel statusLabel =
            new JLabel("Ready to calculate");

    private final ActivityChart chart = new ActivityChart();

    // Theme colors
    private final Color navy = new Color(25, 42, 70);
    private final Color teal = new Color(20, 150, 135);
    private final Color background = new Color(242, 246, 250);

    public StudentActivityMarkovGUI() {

        setTitle("Student Activity Markov Analysis System");

        setSize(1200, 850);
        setMinimumSize(new Dimension(950, 700));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(background);
        setLayout(new BorderLayout(15, 15));

        add(createHeader(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        loadDefaultValues();
    }

    // ==========================================
    // HEADER
    // ==========================================

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(navy);
        header.setBorder(
            new EmptyBorder(20, 25, 20, 25)
        );

        JLabel title = new JLabel(
            "STUDENT ACTIVITY MARKOV ANALYSIS"
        );

        title.setForeground(Color.WHITE);
        title.setFont(
            new Font("Arial", Font.BOLD, 23)
        );

        JLabel subtitle = new JLabel(
            "Linear Algebra & Vector Calculus | MAT2303"
        );

        subtitle.setForeground(
            new Color(205, 220, 235)
        );

        subtitle.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        JPanel textPanel = new JPanel(
            new GridLayout(2, 1, 0, 5)
        );

        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(subtitle);

        header.add(textPanel, BorderLayout.CENTER);

        return header;
    }

    // ==========================================
    // MAIN CONTENT
    // ==========================================

    private JPanel createMainContent() {

        JPanel main = new JPanel(new BorderLayout(15, 15));

        main.setOpaque(false);

        main.setBorder(
            new EmptyBorder(0, 20, 0, 20)
        );

        JPanel leftPanel = createInputPanel();

        JPanel rightPanel = createResultsPanel();

        // Scrollable input section
        JScrollPane inputScroll = new JScrollPane(leftPanel);

        inputScroll.setBorder(null);

        inputScroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        inputScroll.getVerticalScrollBar().setUnitIncrement(15);

        inputScroll.getViewport().setBackground(background);

        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            inputScroll,
            rightPanel
        );

        split.setResizeWeight(0.48);
        split.setDividerLocation(520);
        split.setBorder(null);
        split.setOpaque(false);

        main.add(split, BorderLayout.CENTER);

        return main;
    }

    // ==========================================
    // INPUT PANEL
    // ==========================================

    private JPanel createInputPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(
            new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        panel.setBackground(background);

        panel.add(createMatrixPanel());

        panel.add(Box.createVerticalStrut(15));

        panel.add(createStatePanel());

        return panel;
    }

    // ==========================================
    // TRANSITION MATRIX PANEL
    // ==========================================

    private JPanel createMatrixPanel() {

        JPanel card = createCard(
            "TRANSITION PROBABILITY MATRIX"
        );

        JPanel grid = new JPanel(
            new GridLayout(5, 5, 6, 6)
        );

        grid.setOpaque(false);

        grid.add(new JLabel("From / To"));

        for (String name : activities) {

            JLabel label = new JLabel(
                name, SwingConstants.CENTER
            );

            label.setFont(
                new Font("Arial", Font.BOLD, 11)
            );

            grid.add(label);
        }

        for (int row = 0; row < 4; row++) {

            JLabel rowLabel = new JLabel(
                activities[row]
            );

            rowLabel.setFont(
                new Font("Arial", Font.BOLD, 12)
            );

            grid.add(rowLabel);

            for (int col = 0; col < 4; col++) {

                JTextField field = new JTextField(
                    String.valueOf(defaultMatrix[row][col])
                );

                field.setHorizontalAlignment(
                    JTextField.CENTER
                );

                field.setFont(
                    new Font("Arial", Font.PLAIN, 13)
                );

                matrixFields[row][col] = field;

                grid.add(field);
            }
        }

        card.add(grid, BorderLayout.CENTER);

        JLabel note = new JLabel(
            "Each row must add up to 1.00"
        );

        note.setForeground(teal);

        note.setBorder(
            new EmptyBorder(12, 0, 0, 0)
        );

        card.add(note, BorderLayout.SOUTH);

        card.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 260)
        );

        return card;
    }

    // ==========================================
    // INITIAL STATE PANEL
    // ==========================================

    private JPanel createStatePanel() {

        JPanel card = createCard(
            "INITIAL STATE VECTOR"
        );

        JPanel grid = new JPanel(
            new GridLayout(2, 4, 8, 8)
        );

        grid.setOpaque(false);

        for (int i = 0; i < 4; i++) {

            JLabel label = new JLabel(
                activities[i],
                SwingConstants.CENTER
            );

            label.setFont(
                new Font("Arial", Font.BOLD, 12)
            );

            grid.add(label);
        }

        for (int i = 0; i < 4; i++) {

            JTextField field = new JTextField(
                String.valueOf(defaultState[i])
            );

            field.setHorizontalAlignment(
                JTextField.CENTER
            );

            stateFields[i] = field;

            grid.add(field);
        }

        card.add(grid, BorderLayout.CENTER);

        JLabel note = new JLabel(
            "Initial probabilities must add up to 1.00"
        );

        note.setForeground(teal);

        note.setBorder(
            new EmptyBorder(12, 0, 0, 0)
        );

        card.add(note, BorderLayout.SOUTH);

        card.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 170)
        );

        return card;
    }

    // ==========================================
    // BUTTON PANEL
    // ==========================================

    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(
            new GridLayout(1, 2, 10, 0)
        );

        panel.setOpaque(false);

        JButton calculateButton = new JButton(
            "Calculate Results"
        );

        calculateButton.setBackground(teal);
        calculateButton.setForeground(Color.WHITE);

        calculateButton.setFocusPainted(false);

        calculateButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        calculateButton.addActionListener(
            e -> calculateResults()
        );

        JButton resetButton = new JButton(
            "Reset Values"
        );

        resetButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        resetButton.addActionListener(e -> {

            loadDefaultValues();

            resultArea.setText(
                "Enter your probabilities and click\n" +
                "'Calculate Results' to begin.\n\n" +
                "Results will appear here."
            );

            chart.setHistory(new ArrayList<>());

            statusLabel.setText(
                "Default values restored"
            );
        });

        panel.add(calculateButton);
        panel.add(resetButton);

        return panel;
    }

    // ==========================================
    // RESULTS PANEL
    // BUTTONS ARE NOW INSIDE THIS PANEL
    // ==========================================

    private JPanel createResultsPanel() {

        JPanel card = createCard(
            "CALCULATION RESULTS"
        );

        resultArea.setEditable(false);

        resultArea.setFont(
            new Font("Consolas", Font.PLAIN, 13)
        );

        resultArea.setBackground(
            new Color(248, 250, 252)
        );

        resultArea.setBorder(
            new EmptyBorder(12, 12, 12, 12)
        );

        resultArea.setText(
            "Enter your probabilities and click\n" +
            "'Calculate Results' to begin.\n\n" +
            "Results will appear here."
        );

        JScrollPane scroll = new JScrollPane(resultArea);

        scroll.setBorder(null);

        // Buttons placed above the results area
        JPanel content = new JPanel(
            new BorderLayout(0, 12)
        );

        content.setBackground(Color.WHITE);

        content.add(
            createButtonPanel(),
            BorderLayout.NORTH
        );

        content.add(
            scroll,
            BorderLayout.CENTER
        );

        card.add(content, BorderLayout.CENTER);

        return card;
    }

    // ==========================================
    // FOOTER AND GRAPH
    // ==========================================

    private JPanel createFooter() {

        JPanel footer = new JPanel(
            new BorderLayout(10, 10)
        );

        footer.setBackground(background);

        footer.setBorder(
            new EmptyBorder(0, 20, 15, 20)
        );

        JPanel graphCard = createCard(
            "ACTIVITY CONVERGENCE GRAPH"
        );

        graphCard.add(
            chart,
            BorderLayout.CENTER
        );

        graphCard.setPreferredSize(
            new Dimension(800, 230)
        );

        statusLabel.setForeground(teal);

        statusLabel.setFont(
            new Font("Arial", Font.BOLD, 12)
        );

        footer.add(
            graphCard,
            BorderLayout.CENTER
        );

        footer.add(
            statusLabel,
            BorderLayout.SOUTH
        );

        return footer;
    }

    // ==========================================
    // REUSABLE CARD DESIGN
    // ==========================================

    private JPanel createCard(String heading) {

        JPanel card = new JPanel(
            new BorderLayout(10, 15)
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(220, 228, 236),
                    1,
                    true
                ),
                new EmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel title = new JLabel(heading);

        title.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        title.setForeground(navy);

        card.add(title, BorderLayout.NORTH);

        return card;
    }

    // ==========================================
    // RESET DEFAULT VALUES
    // ==========================================

    private void loadDefaultValues() {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                matrixFields[i][j].setText(
                    String.valueOf(defaultMatrix[i][j])
                );
            }

            stateFields[i].setText(
                String.valueOf(defaultState[i])
            );
        }
    }

    // ==========================================
    // READ TRANSITION MATRIX
    // ==========================================

    private double[][] readMatrix() {

        double[][] matrix = new double[4][4];

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                matrix[i][j] = Double.parseDouble(
                    matrixFields[i][j].getText().trim()
                );
            }
        }

        return matrix;
    }

    // ==========================================
    // READ INITIAL STATE
    // ==========================================

    private double[] readState() {

        double[] state = new double[4];

        for (int i = 0; i < 4; i++) {

            state[i] = Double.parseDouble(
                stateFields[i].getText().trim()
            );
        }

        return state;
    }

    // ==========================================
    // VALIDATE TRANSITION MATRIX
    // ==========================================

    private boolean validateMatrix(double[][] matrix) {

        for (int i = 0; i < 4; i++) {

            double sum = 0;

            for (int j = 0; j < 4; j++) {

                double value = matrix[i][j];

                if (!Double.isFinite(value)
                        || value < 0 || value > 1) {

                    return false;
                }

                sum += value;
            }

            if (Math.abs(sum - 1.0) > 1e-9) {
                return false;
            }
        }

        return true;
    }

    // ==========================================
    // VALIDATE INITIAL STATE VECTOR
    // ==========================================

    private boolean validateState(double[] state) {

        double sum = 0;

        for (double value : state) {

            if (!Double.isFinite(value)
                    || value < 0 || value > 1) {

                return false;
            }

            sum += value;
        }

        return Math.abs(sum - 1.0) <= 1e-9;
    }

    // ==========================================
    // MATRIX MULTIPLICATION
    // ==========================================

    private double[] multiply(
            double[] vector,
            double[][] matrix) {

        double[] result = new double[4];

        for (int j = 0; j < 4; j++) {

            for (int i = 0; i < 4; i++) {

                result[j] +=
                    vector[i] * matrix[i][j];
            }
        }

        return result;
    }

    // ==========================================
    // STEADY STATE CALCULATION
    // ==========================================

    private double[] findSteadyState(
            double[][] matrix) {

        double[] state = {
            0.25, 0.25, 0.25, 0.25
        };

        for (int iteration = 0;
             iteration < 10000;
             iteration++) {

            double[] next = multiply(state, matrix);

            double error = maxDifference(state, next);

            if (error < 1e-12) {
                return next;
            }

            state = next;
        }

        return state;
    }

    // ==========================================
    // CALCULATE RESULTS
    // ==========================================

    private void calculateResults() {

        try {

            double[][] matrix = readMatrix();

            double[] initial = readState();

            // Input validation
            if (!validateMatrix(matrix)) {

                throw new IllegalArgumentException(
                    "Every matrix row must contain probabilities " +
                    "between 0 and 1 and sum to 1."
                );
            }

            if (!validateState(initial)) {

                throw new IllegalArgumentException(
                    "Initial state probabilities must be between " +
                    "0 and 1 and sum to 1."
                );
            }

            List<double[]> history = new ArrayList<>();

            double[] state = initial.clone();

            history.add(state.clone());

            StringBuilder output = new StringBuilder();

            output.append(
                "STUDENT ACTIVITY ANALYSIS\n"
            );

            output.append(
                "============================\n\n"
            );

            // Initial distribution
            output.append(
                "INITIAL DISTRIBUTION\n"
            );

            appendVector(output, initial);

            // State evolution
            output.append(
                "\nSTATE EVOLUTION\n"
            );

            for (int step = 1; step <= 20; step++) {

                state = multiply(state, matrix);

                history.add(state.clone());

                if (step == 1 || step == 5
                        || step == 10 || step == 20) {

                    output.append(
                        "\nAfter Step " + step + "\n"
                    );

                    appendVector(output, state);
                }
            }

            // Steady state
            double[] steady = findSteadyState(matrix);

            output.append(
                "\nSTEADY-STATE DISTRIBUTION\n"
            );

            appendVector(output, steady);

            // Verification
            double[] verification =
                multiply(steady, matrix);

            output.append(
                "\nSTEADY-STATE VERIFICATION\n"
            );

            appendVector(output, verification);

            output.append(
                "\nVerification error: "
            );

            output.append(
                String.format(
                    "%.12f",
                    maxDifference(steady, verification)
                )
            );

            // Update result area
            resultArea.setText(output.toString());

            resultArea.setCaretPosition(0);

            // Update graph
            chart.setHistory(history);

            statusLabel.setText(
                "Calculation completed successfully!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter valid numerical values.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================
    // DISPLAY VECTOR
    // ==========================================

    private void appendVector(
            StringBuilder output,
            double[] vector) {

        for (int i = 0; i < 4; i++) {

            output.append(
                String.format(
                    "%-12s : %.4f (%6.2f%%)%n",
                    activities[i],
                    vector[i],
                    vector[i] * 100
                )
            );
        }
    }

    // ==========================================
    // MAXIMUM DIFFERENCE
    // ==========================================

    private double maxDifference(
            double[] a,
            double[] b) {

        double max = 0;

        for (int i = 0; i < 4; i++) {

            max = Math.max(
                max,
                Math.abs(a[i] - b[i])
            );
        }

        return max;
    }

    // ==========================================
    // CUSTOM CONVERGENCE GRAPH
    // ==========================================

    class ActivityChart extends JPanel {

        private List<double[]> history =
                new ArrayList<>();

        private final Color[] colors = {

            new Color(40, 110, 220),
            new Color(20, 150, 135),
            new Color(230, 145, 40),
            new Color(155, 90, 190)
        };

        public ActivityChart() {

            setBackground(Color.WHITE);

            setPreferredSize(
                new Dimension(700, 180)
            );
        }

        public void setHistory(List<double[]> data) {

            history = new ArrayList<>(data);

            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            if (history.isEmpty()) {

                g.setColor(Color.GRAY);

                g.drawString(
                    "Click Calculate Results to display the graph.",
                    30, 45
                );

                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            int left = 55;

            int right = getWidth() - 25;

            int top = 20;

            int bottom = getHeight() - 35;

            int width = right - left;

            int height = bottom - top;

            // Horizontal grid lines
            g2.setColor(
                new Color(225, 230, 235)
            );

            for (int i = 0; i <= 5; i++) {

                int y = bottom - (i * height / 5);

                g2.drawLine(left, y, right, y);

                g2.setColor(Color.DARK_GRAY);

                g2.drawString(
                    (i * 20) + "%",
                    12,
                    y + 5
                );

                g2.setColor(
                    new Color(225, 230, 235)
                );
            }

            // Draw activity lines
            for (int activity = 0;
                 activity < 4;
                 activity++) {

                g2.setColor(colors[activity]);

                g2.setStroke(
                    new BasicStroke(2.5f)
                );

                for (int step = 1;
                     step < history.size();
                     step++) {

                    int x1 = left +
                        (step - 1) * width /
                        (history.size() - 1);

                    int x2 = left +
                        step * width /
                        (history.size() - 1);

                    int y1 = bottom - (int)(
                        history.get(step - 1)[activity] * height
                    );

                    int y2 = bottom - (int)(
                        history.get(step)[activity] * height
                    );

                    g2.drawLine(x1, y1, x2, y2);
                }
            }

            // X-axis label
            g2.setColor(Color.DARK_GRAY);

            g2.drawString(
                "Time Step",
                getWidth() / 2,
                getHeight() - 8
            );

            // Graph legend
            int legendX = left;

            for (int i = 0; i < 4; i++) {

                g2.setColor(colors[i]);

                g2.fillRect(
                    legendX,
                    getHeight() - 20,
                    12,
                    12
                );

                g2.setColor(Color.DARK_GRAY);

                g2.drawString(
                    activities[i],
                    legendX + 17,
                    getHeight() - 9
                );

                legendX += 120;
            }

            g2.dispose();
        }
    }

    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {

                // Use default Swing appearance.
            }

            new StudentActivityMarkovGUI().setVisible(true);
        });
    }
}
