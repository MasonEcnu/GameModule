package com.mason.game.fsm.complex.state;

import com.mason.game.fsm.State;
import com.mason.game.fsm.complex.Hero;

/**
 * Created by mwu on 2019/12/23
 * 站立状态
 */
public class StandingState implements IState {

    @Override
    public void handleInput(Hero hero, String input) {
        switch (input) {
            case "up":
                hero.setState(State.STATE_JUMPING);
                break;
            case "down":
                hero.setState(State.STATE_DUCKING);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Hero hero) {

    }
}
