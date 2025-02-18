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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Task 3: Implement a menu to select the mine field template
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

        
        game.displayField();
    }
}
