package com.mason.game.fsm.complex;

import com.mason.game.fsm.State;
import com.mason.game.fsm.complex.state.IState;

/**
 * Created by mwu on 2019/12/23
 */
public class Hero {
  private String name;  // 姓名
  private int level;  // 等级
  private int attackPot;  // 攻击力
  private State state;

  public Hero(String name) {
    this.name = name;
    this.level = 0;
    this.attackPot = 1;
    this.state = State.STATE_STANDING;
  }

  private StateManager stateManager = StateManager.getInstance();

  @Override
  public String toString() {
    return String.format("Hero[name:%s, level:%s, attackPot:%s, state:%s]", this.name, this.level, this.attackPot, this.state);
  }

  public void handleInput(String input) {
    IState stateHandler = stateManager.STATE_HANDLER_MAP.get(state);
    if (stateHandler != null) {
      stateHandler.handleInput(this, input);
    }
  }

  public void update() {
    IState stateHandler = stateManager.STATE_HANDLER_MAP.get(state);
    if (stateHandler != null) {
      stateHandler.update(this);
    }
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
}
