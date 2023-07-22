import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];

    JButton restartButton = new JButton();
    boolean player1Turn;

    int counter = 0;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());


        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Arial", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(true);
        textField.setText("Tic-Tac-Toe");


        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textField);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Century Gothic", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }


        restartButton.setVisible(false);
        restartButton.setFont(new Font("Century Gothic", Font.BOLD, 40));
        restartButton.setText("RESET");
        restartButton.addActionListener(this);

        titlePanel.add(restartButton);

        frame.add(buttonPanel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setVisible(true);

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println("Problem with sleep");
        }
        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == restartButton) {
            restartGame();
        }

        for (JButton button : buttons) {
            if (e.getSource() == button) {
                if (player1Turn) {
                    if (button.getText().equals("")) {
                        button.setForeground(Color.GREEN);
                        button.setText("X");
                        textField.setText("O turn");
                        check(true);
                        player1Turn = false;
                    }
                } else {
                    if (button.getText().equals("")) {
                        button.setForeground(Color.RED);
                        button.setText("O");
                        textField.setText("X turn");
                        check(false);
                        player1Turn = true;
                    }
                }
            }
        }
    }


    public void firstTurn() {

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        } else {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    public void check(boolean player) {

        String playerSignature;

        if (player) {
            playerSignature = "X";
        } else {
            playerSignature = "O";
        }

        if (counter >= 4) {

            if (
                    (buttons[0].getText().equals(playerSignature)) &&
                    (buttons[1].getText().equals(playerSignature)) &&
                    (buttons[2].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[3].getText().equals(playerSignature)) &&
                    (buttons[4].getText().equals(playerSignature)) &&
                    (buttons[5].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[6].getText().equals(playerSignature)) &&
                    (buttons[7].getText().equals(playerSignature)) &&
                    (buttons[8].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[0].getText().equals(playerSignature)) &&
                    (buttons[3].getText().equals(playerSignature)) &&
                    (buttons[6].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[1].getText().equals(playerSignature)) &&
                    (buttons[4].getText().equals(playerSignature)) &&
                    (buttons[7].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[2].getText().equals(playerSignature)) &&
                    (buttons[5].getText().equals(playerSignature)) &&
                    (buttons[8].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[0].getText().equals(playerSignature)) &&
                    (buttons[4].getText().equals(playerSignature)) &&
                    (buttons[8].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
            if (
                    (buttons[2].getText().equals(playerSignature)) &&
                    (buttons[4].getText().equals(playerSignature)) &&
                    (buttons[6].getText().equals(playerSignature))
            ) {
                winner(playerSignature);
            }
        }

       else if (counter == 8) {
            draw();
        }
        counter++;
    }


    public void winner(String playerSignature) {

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText(playerSignature + " wins!");
        restartButton.setVisible(true);

    }

    public void draw() {
        textField.setText("draw!");

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        restartButton.setVisible(true);
    }

    public void restartGame() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText("");
        }

        restartButton.setVisible(false);
        firstTurn();
        counter = 0;
    }
}
