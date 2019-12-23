package com.mason.game.fsm;

import com.mason.game.fsm.simple.Player;

import java.util.Scanner;

/**
 * Created by mwu on 2019/12/23
 */
public class TestSimple {
  public static void main(String[] args) {
    Player player = new Player("Mason");
    System.out.println(player.toString());
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next().toLowerCase();
    while (!"exit".equals(input)) {
      player.handleInput(input);
      System.out.println(player.toString());
      input = scanner.next().toLowerCase();
    }
    scanner.close();
  }
}
