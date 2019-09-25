package com.isharpever.game.pursuit;

/**
 * 关卡接口
 */
public interface Level {

    /**
     * 初始化
     */
    void init();

    /**
     * 破解
     */
    void solve();

    /**
     * 是否检查答案
     * @return
     */
    default boolean answerCheck() {
        return true;
    }
}
