package com.mason.game.fsm.complex;

import com.mason.game.fsm.State;
import com.mason.game.fsm.complex.state.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 2019/12/23
 */
class StateManager {

    private static StateManager instance;

    static {
        try {
            instance = new StateManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static StateManager getInstance() {
        return instance;
    }

    private StateManager() {
    }

    final Map<State, IState> STATE_HANDLER_MAP = new HashMap<State, IState>() {{
        put(State.STATE_STANDING, new StandingState());
        put(State.STATE_JUMPING, new JumpingState());
        put(State.STATE_DUCKING, new DuckingState());
        put(State.STATE_DIVING, new DivingState());
    }};
}
