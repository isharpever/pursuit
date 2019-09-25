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
     * @param includeNotSieged 是否包含不能围住小偷的答案
     */
    void solve(boolean includeNotSieged);
}
