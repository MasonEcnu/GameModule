package com.mason.game.findpath.finders.jump_point;

import com.mason.game.findpath.core.DiagonalMovement;
import com.mason.game.findpath.core.FindPathOption;

/**
 * Created by mwu on 2020/3/10
 */
class JumpPointFactory {

    static IJumpPointFinder getFinder(FindPathOption opt) {
        if (opt.diagonalMovement == DiagonalMovement.Never) {
            return new JPFNeverMoveDiagonally(opt);
        } else if (opt.diagonalMovement == DiagonalMovement.Always) {
            return new JPFAlwaysMoveDiagonally(opt);
        } else if (opt.diagonalMovement == DiagonalMovement.OnlyWhenNoObstacles) {
            return new JPFMoveDiagonallyIfNoObstacles(opt);
        } else {
            return new JPFMoveDiagonallyIfAtMostOneObstacle(opt);
        }
    }
}
