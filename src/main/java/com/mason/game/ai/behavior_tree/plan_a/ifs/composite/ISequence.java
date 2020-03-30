package com.mason.game.ai.behavior_tree.plan_a.ifs.composite;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IComposite;

/**
 * Created by mwu on 2020/3/30
 * 顺序节点
 * 依次执行所有节点
 * 直到其中一个失败
 * 或全部成功
 */
public interface ISequence extends IComposite {
}
