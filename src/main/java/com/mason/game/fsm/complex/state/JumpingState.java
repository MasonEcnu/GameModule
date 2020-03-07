package com.mason.game.fsm.complex.state;

import com.mason.game.fsm.State;
import com.mason.game.fsm.complex.Hero;

/**
 * Created by mwu on 2019/12/23
 * 跳跃状态
 */
public class JumpingState implements IState {

    @Override
    public void handleInput(Hero hero, String input) {
        if ("down".equals(input)) {
            hero.setState(State.STATE_DIVING);
        }
    }

    @Override
    public void update(Hero hero) {

    }
}
