package com.mason.game.fsm.complex.state;

import com.mason.game.fsm.complex.Hero;

/**
 * Created by mwu on 2019/12/23
 * 状态接口
 */
public interface IState {

  void handleInput(Hero hero, String input);

  void update(Hero hero);
}
