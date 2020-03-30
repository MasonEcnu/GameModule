package com.mason.game.ai.plan_a;

import com.mason.game.ai.behavior_tree.plan_a.BehaviorTree;
import com.mason.game.ai.behavior_tree.plan_a.BehaviorTreeBuilder;
import com.mason.game.ai.behavior_tree.plan_a.common.ParallelPolicy;
import com.mason.game.ai.behavior_tree.plan_a.impl.action.ActionAttack;
import com.mason.game.ai.behavior_tree.plan_a.impl.action.ActionPatrol;
import com.mason.game.ai.behavior_tree.plan_a.impl.action.ActionRunaway;
import com.mason.game.ai.behavior_tree.plan_a.impl.composite.ParallelImpl;
import com.mason.game.ai.behavior_tree.plan_a.impl.composite.SelectorImpl;
import com.mason.game.ai.behavior_tree.plan_a.impl.composite.SequenceImpl;
import com.mason.game.ai.behavior_tree.plan_a.impl.condition.ConditionIsEnemyDead;
import com.mason.game.ai.behavior_tree.plan_a.impl.condition.ConditionIsHealthLow;
import com.mason.game.ai.behavior_tree.plan_a.impl.condition.ConditionIsSeeEnemy;
import com.mason.game.ai.behavior_tree.plan_a.impl.decorator.Repeat;

/**
 * Created by mwu on 2020/3/30
 */
public class TestBehaviorTree {

    public static void main(String[] args) {
        BehaviorTreeBuilder builder = new BehaviorTreeBuilder();
        BehaviorTree behaviorTree =
                builder.addBehavior(new SelectorImpl())
                        .addBehavior(new SequenceImpl())
                        .addBehavior(new ConditionIsSeeEnemy())
                        .back()
                        .addBehavior(new SelectorImpl())
                        .addBehavior(new SequenceImpl())
                        .addBehavior(new ConditionIsHealthLow())
                        .back()
                        .addBehavior(new ActionRunaway())
                        .back()
                        .back()

                        .addBehavior(new ParallelImpl(ParallelPolicy.REQUIRE_ALL, ParallelPolicy.REQUIRE_ONE))
                        .addBehavior(new ConditionIsEnemyDead(true))
                        .back()
                        .addBehavior(new Repeat())
                        .addBehavior(new ActionAttack())
                        .back()
                        .back()
                        .back()
                        .back()
                        .back()
                        .addBehavior(new ActionPatrol())
                        .end();

        //模拟执行行为树
        for (int i = 0; i < 10; ++i) {
            behaviorTree.tick();
            System.out.println("--------------" + i + "------------");
        }

        System.out.println("pause ");
    }
}
