package com.mason.game.fsm.complex.state;

import com.mason.game.fsm.State;
import com.mason.game.fsm.complex.Hero;

/**
 * Created by mwu on 2019/12/23
 * 下斩状态
 */
public class DivingState implements IState {

  @Override
  public void handleInput(Hero hero, String input) {
    if ("up".equals(input)) {
      hero.setState(State.STATE_STANDING);
    }
  }

  @Override
  public void update(Hero hero) {

  }
}
