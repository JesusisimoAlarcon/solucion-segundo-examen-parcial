package algorithms;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GUI_NReinas extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<int[]> solutions;
    private int NUM_REINAS;
    private JButton[][] board;
    private int[] solution;
    private static final int SIZE = 600;
    private int numSol;

    // component GUI
    private JMenuItem mOpen;
    private JMenuItem mSave;
    JButton btnPreview;
    JButton btnNext;
    JTextField txtReinas;
    JPanel panel_board;
    JLabel lbnCantSol;
    JLabel lbnSol;

    public GUI_NReinas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Problem N Reinas");
        setSize(SIZE, SIZE);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(BorderLayout.NORTH, menu2());
        add(BorderLayout.SOUTH, footer_options());
        panel_board = new JPanel();
        setLocationRelativeTo(this);
    }

    public void init(int NUM_REINAS) {
        this.NUM_REINAS = NUM_REINAS;
        numSol = 0;
        board = new JButton[NUM_REINAS][NUM_REINAS];
        solutions = new LinkedList<>();
    }

    public JPanel menu2() {
        JPanel panel = new JPanel();
        JLabel lbnReinas = new JLabel("Numero de reinas: ");
        txtReinas = new JTextField("", 3);
        JButton btnInit = new JButton("Go");
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NUM_REINAS = Integer.parseInt(txtReinas.getText());
                init(NUM_REINAS);
                add(BorderLayout.CENTER, board());
                panel_board.revalidate();
                panel_board.repaint();
                // search_Nreina();
                // paintSolution();
            }
        });
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search_NreinaAllSolutions();
                paintSolution(solutions.get(numSol));
                btnPreview.setEnabled(false);
                btnNext.setEnabled(true);
            }
        });
        panel.add(lbnReinas);
        panel.add(txtReinas);
        panel.add(btnInit);
        panel.add(btnStart);
        return panel;
    }

    public JPanel menu() {
        // FlowLayout flow = new FlowLayout();
        JPanel panel_menu = new JPanel();
        // panel_menu.setLayout(flow);
        JMenuBar menuBar = new JMenuBar();
        JMenu operations = new JMenu("Operaciones");
        mOpen = new JMenuItem("Abrir");
        mSave = new JMenuItem("Guardar como");
        operations.add(mOpen);
        operations.add(mSave);
        menuBar.add(operations);
        panel_menu.add(menuBar);
        return panel_menu;
    }

    public JPanel footer_options() {
        JPanel panel = new JPanel();
        GridLayout manager = new GridLayout(1, 4);
        panel.setLayout(manager);
        lbnCantSol = new JLabel("Cant de soluciones: ");
        lbnSol = new JLabel("Solucion: ", JLabel.CENTER);
        btnPreview = new JButton("Anterior");
        btnPreview.setEnabled(false);
        btnNext = new JButton("Siguiente");
        btnPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numSol--;
                paintSolution(solutions.get(numSol));
                if (numSol <= 0) {
                    btnPreview.setEnabled(false);
                }
                btnNext.setEnabled(true);
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numSol++;
                paintSolution(solutions.get(numSol));
                if (numSol >= solutions.size() - 1) {
                    btnNext.setEnabled(false);
                }
                btnPreview.setEnabled(true);
            }
        });

        panel.add(lbnCantSol);
        panel.add(btnPreview);
        panel.add(lbnSol);
        panel.add(btnNext);
        return panel;
    }

    public JPanel board() {
        panel_board.removeAll();
        GridLayout manager = new GridLayout(this.NUM_REINAS, this.NUM_REINAS);
        panel_board.setLayout(manager);
        for (int i = 0; i < NUM_REINAS; i++) {
            for (int j = 0; j < NUM_REINAS; j++) {
                JButton cell = new JButton("");
                cell.setBackground(Color.WHITE);
                if ((j + i) % 2 == 0) {
                    cell.setBackground(Color.GRAY);
                }
                cell.setEnabled(false);
                board[i][j] = cell;
                panel_board.add(cell);
            }
        }
        return panel_board;
    }

    public void initBoard() {
        for (int i = 0; i < NUM_REINAS; i++) {
            for (int j = 0; j < NUM_REINAS; j++) {
                board[i][j].setEnabled(false);
                board[i][j].setIcon(null);
            }
        }
    }

    public boolean backtracking(int[] solution, int reina, String output) {
        boolean success = false;
        if (reina < NUM_REINAS) {
            solution[reina] = -1;
            do {
                solution[reina]++;
                boolean valid = isValid(solution, reina);
                output += reina + " " + Arrays.toString(solution) + " " + valid + " "
                        + ((valid ? "*" + ((reina == NUM_REINAS - 1) ? " Solucion" : "") : "") + "\n");
                if (valid) {
                    success = backtracking(solution, reina + 1, output);
                    if (reina == NUM_REINAS - 1) {
                        solutions.add(solution);
                        success = true;
                    }
                }
            } while (solution[reina] < (NUM_REINAS - 1) && (!success));
        } else {
            success = false;
            System.out.println(output);
        }
        return success;
    }

    public void backtrackingAll(int[] solution, int reina, String output) {
        if (reina < NUM_REINAS) {
            do {
                solution[reina]++;
                boolean valid = isValid(solution, reina);
                output += reina + " " + Arrays.toString(solution) + " " + valid + " "
                        + ((valid ? "*" + ((reina == NUM_REINAS - 1) ? " Solucion" : "") : "") + "\n");
                if (valid) {
                    if (reina == NUM_REINAS - 1) {
                        // System.out.println(Arrays.toString(solution));
                        int s[] = solution.clone();
                        solutions.add(s);
                    } else {
                        backtrackingAll(solution, reina + 1, output);
                    }
                }
            } while (solution[reina] < (NUM_REINAS - 1));
            solution[reina] = -1;
        }
    }

    public boolean isValid(int[] solution, int reina) {
        boolean ok = true;
        for (int i = 0; i < reina; i++) {
            if ((solution[i] == solution[reina]) || (Math.abs(solution[i] - solution[reina]) == Math.abs(i - reina))) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public void search_Nreina() {
        initBacktracking();
        backtracking(solution, 0, "");
    }

    public void search_NreinaAllSolutions() {
        initBacktracking();
        backtrackingAll(solution, 0, "");
        lbnCantSol.setText("Cant. Soluciones: " + solutions.size());
    }

    public void initBacktracking() {
        solution = new int[NUM_REINAS];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = -1;
        }
    }

    public void paintSolution() {
        for (int i = 0; i < NUM_REINAS; i++) {
            // board[solution[i]][i].setText("R");
            // board[solution[i]][i].setBackground(Color.BLACK);
            Icon icon = new ImageIcon(System.getProperty("user.dir") + "\\resource\\reina24.png");
            board[solution[i]][i].setIcon(icon);
            board[solution[i]][i].setEnabled(true);
        }
    }

    public void paintSolution(int[] sol) {
        initBoard();
        for (int i = 0; i < NUM_REINAS; i++) {
            // board[solution[i]][i].setText("R");
            // board[solution[i]][i].setBackground(Color.BLACK);
            Icon icon = new ImageIcon(System.getProperty("user.dir") + "\\resource\\reina24.png");
            board[sol[i]][i].setIcon(icon);
            board[sol[i]][i].setEnabled(true);
        }
        lbnSol.setText(numSol + 1 + "");
    }

    public static void main(String[] args) {
        GUI_NReinas reina = new GUI_NReinas();
        reina.setVisible(true);

    }

}
