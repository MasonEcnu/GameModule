package com.mason.game.ai.behavior_tree.plan_a.ifs.composite;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IComposite;

/**
 * Created by mwu on 2020/3/30
 * 选择器节点
 * 依次执行每个子节点
 * 直到其中一个执行成功
 * 或返回RUNNING状态
 */
public interface ISelector extends IComposite {
}
