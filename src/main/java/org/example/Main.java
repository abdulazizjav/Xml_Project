package org.example;

import model.User;
import service.UserService;

import java.util.Scanner;

public class Main {
    static Scanner scInt = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);
    static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register   2. Login    0. Exit");
            int command = scInt.nextInt();
            switch (command) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    register();
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String username = scStr.nextLine();
                    System.out.print("Enter password: ");
                    String password = scStr.nextLine();
                    User user = userService.login(username, password);
                    if (user != null) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Please check your username and password.");
                    }
                }
                default -> System.out.println("Invalid command. Please try again.");
            }

        }
    }

    private static void register() {
        System.out.print("Enter name: ");
        String name = scStr.nextLine();
        System.out.print("Enter username: ");
        String username = scStr.nextLine();
        System.out.print("Enter password: ");
        String password = scStr.nextLine();
        User user = new User(name, username, password);
        userService.addUser(user);
        System.out.println("User registered successfully!");
    }
}
