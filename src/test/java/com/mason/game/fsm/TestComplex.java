package com.mason.game.fsm;

import com.mason.game.fsm.complex.Hero;

import java.util.Scanner;

/**
 * Created by mwu on 2019/12/23
 */
public class TestComplex {
  public static void main(String[] args) {
    Hero hero = new Hero("Mason");
    System.out.println(hero.toString());
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next().toLowerCase();
    while (!"exit".equals(input)) {
      hero.handleInput(input);
      System.out.println(hero.toString());
      input = scanner.next().toLowerCase();
    }
    scanner.close();
  }
}
