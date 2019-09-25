package com.isharpever.game.pursuit;

/**
 * 迷宫大追捕
 */
public class App {
    public static void main(String[] args) {
        // 关卡
        Level level = new Level02();
        level.init();

        // 求解
        level.solve(true);
    }
}
