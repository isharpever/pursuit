package com.isharpever.game.pursuit;

import lombok.Data;

/**
 * 棋盘格
 */
@Data
public class Cell implements Cloneable {

    /**
     * 在棋盘上的序号,从0开始,按从左到右、从上到下的顺序递增
     */
    private int cellOrder;

    /**
     * 横坐标-行数,从0开始,从上到下递增
     */
    private int x;

    /**
     * 纵坐标-列数,从0开始,从左到右递增
     */
    private int y;

    /**
     * 占据此格的棋子
     */
    private ChessPiece chessPiece;

    /**
     * 有无警车
     */
    private boolean policeCar;

    public Cell(int cellOrder, int x, int y) {
        this.cellOrder = cellOrder;
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty() {
        return chessPiece == null;
    }

    @Override
    protected Cell clone() throws CloneNotSupportedException {
        return (Cell)super.clone();
    }

    /**
     * 棋盘格内是指定棋子时,释放
     * @param chessPiece
     */
    public void release(ChessPiece chessPiece) {
        synchronized (this) {
            if (this.chessPiece == chessPiece) {
                this.chessPiece = null;
                this.policeCar = false;
            }
        }
    }

    /**
     * 释放棋盘格
     */
    public void release() {
        synchronized (this) {
            this.chessPiece = null;
            this.policeCar = false;
        }
    }
}
