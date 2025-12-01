package com.woori.BAM;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("== 프로그램 시작 ==");
            System.out.printf("cmd) ");
            command = sc.nextLine();
            System.out.println("명령어) "+command);

            if(command.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            }
        }
    }
}
