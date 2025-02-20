package com.lab;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    static Minesweeper initMineField() {
        Minesweeper game = new Minesweeper(9, 9);
        game.setMineCell(0, 1);
        game.setMineCell(1, 5);
        game.setMineCell(1, 8);
        game.setMineCell(2, 4);
        game.setMineCell(3, 6);
        game.setMineCell(4, 2);
        game.setMineCell(5, 4);
        game.setMineCell(6, 2);
        game.setMineCell(7, 2);
        game.setMineCell(8, 6);
        return game;
    }

    static Minesweeper initMineFieldFromFile(String minefieldFile) {
        return new Minesweeper(minefieldFile);
    }

    static void playGame(Minesweeper game) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            game.displayField();
            System.out.println("Enter row and column to reveal (or -1 -1 to exit): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            
            if (row == -1 && col == -1) {
                System.out.println("Exiting game.");
                break;
            }
            
            if (!game.revealCell(row, col)) {
                System.out.println("Game Over! You hit a mine.");
                game.displayField();
                break;
            }
            
            if (game.isGameWon()) {
                System.out.println("Congratulations! You cleared the minefield.");
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select a minefield template:");
        System.out.println("1. Custom Minefield");
        System.out.println("2. Minefield from File (minefield01.txt)");
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        Minesweeper game = null;

        switch (choice) {
            case 1:
                game = initMineField();
                break;
            case 2:
                game = initMineFieldFromFile("minefield/minefield01.txt");
                break;
            default:
                System.out.println("Invalid choice. Using default custom minefield.");
                game = initMineField();
        }

        playGame(game);
        scanner.close();
    }
}

class Minesweeper {
    private int rows, cols;
    private boolean[][] mines;
    private boolean[][] revealed;
    
    public Minesweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
    }
    
    public Minesweeper(String filePath) {
        // Assume implementation loads mines from file
    }
    
    public void setMineCell(int row, int col) {
        mines[row][col] = true;
    }
    
    public boolean revealCell(int row, int col) {
        if (mines[row][col]) {
            return false;
        }
        revealed[row][col] = true;
        return true;
    }
    
    public boolean isGameWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j] && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void displayField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    System.out.print(mines[i][j] ? "* " : "0 ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }
}