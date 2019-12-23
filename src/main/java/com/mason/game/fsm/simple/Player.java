package com.mason.game.fsm.simple;

import com.mason.game.fsm.State;

/**
 * Created by mwu on 2019/12/23
 */
public class Player {
  private String name;  // 姓名
  private int level;  // 等级
  private int attackPot;  // 攻击力
  private State state;

  public Player(String name) {
    this.name = name;
    this.level = 0;
    this.attackPot = 1;
    this.state = State.STATE_STANDING;
  }

  @Override
  public String toString() {
    return String.format("Player[name:%s, level:%s, attackPot:%s, state:%s]", this.name, this.level, this.attackPot, this.state);
  }

  public void handleInput(String input) {
    switch (state) {
      case STATE_STANDING:
        switch (input) {
          case "up":
            state = State.STATE_JUMPING;
            break;
          case "down":
            state = State.STATE_DUCKING;
            break;
          default:
            break;
        }
        break;
      case STATE_JUMPING:
        if ("down".equals(input)) {
          state = State.STATE_DIVING;
        }
        break;
      case STATE_DUCKING:
        if ("up".equals(input)) {
          state = State.STATE_STANDING;
        }
        break;
      case STATE_DIVING:
        if ("reset".equals(input)) {
          state = State.STATE_STANDING;
        }
        break;
      default:
        System.out.format("Player %s state wrong:%s\n", this.name, this.state);
        break;
    }
  }
}
