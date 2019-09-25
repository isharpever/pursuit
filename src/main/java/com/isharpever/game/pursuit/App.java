package com.isharpever.game.pursuit;

/**
 * 迷宫大追捕
 */
public class App {
    public static void main(String[] args) {
        // 64关
        Level level = new Level01();
        level.init();

        // 求解
        level.solve();
    }
}
