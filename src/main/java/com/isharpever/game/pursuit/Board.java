package com.isharpever.game.pursuit;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 棋盘
 */
@Data
public class Board {
    private static volatile Board instance;

    /** 棋盘行数 */
    public static final int ROW_NUM = 6;

    /** 棋盘列数 */
    public static final int COL_NUM = 6;

    /**
     * 行数
     */
    private int rowNum;

    /**
     * 列数
     */
    private int colNum;

    /**
     * 棋盘格
     */
    private Cell[] cells;

    private Board() {
        init();
    }

    private void init() {
        this.rowNum = ROW_NUM;
        this.colNum = COL_NUM;

        int cellNum = ROW_NUM * COL_NUM;
        this.cells = new Cell[cellNum];
        for (int i = 0; i < cellNum; i++) {
            int y = cellNum / this.colNum;
            int x = cellNum - y * this.colNum;
            this.cells[i] = new Cell(i, x, y);
        }
    }

    public static Board getInstance() {
        if (instance == null) {
            synchronized (Board.class) {
                if (instance == null) {
                    instance = new Board();
                }
            }
        }
        return instance;
    }

    /**
     * 返回指定序号位置的棋盘格
     * @param cellOrder
     * @return
     */
    public Cell getCell(int cellOrder) {
        return this.cells[cellOrder];
    }

    /**
     * 返回指定坐标位置的棋盘格
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x, int y) {
        int cellOrder = (y - 1) * this.colNum + x;
        return this.cells[cellOrder];
    }

    /**
     * 指定序号位置的棋盘格设置棋子
     * @param cellOrder
     * @param chessPiece
     */
    public void setChessPiece(int cellOrder, ChessPiece chessPiece) {
        this.cells[cellOrder].setChessPiece(chessPiece);
    }

    /**
     * 指定序号位置的棋盘格设置警车
     * @param cellOrder
     */
    public void setPoliceCar(int cellOrder) {
        this.cells[cellOrder].setPoliceCar(true);
    }

    /**
     * 返回指定序号位置的棋盘格里是否有棋子
     * @param cellOrder
     * @return
     */
    public boolean occupied(int cellOrder) {
        return !getCell(cellOrder).isEmpty();
    }

    /**
     * 释放所有棋盘格
     */
    public void release() {
        for (int i = 0; i < this.cells.length; i++) {
            this.cells[i].release();
        }
    }

    /**
     * 打印
     */
    public void print() {
        System.out.println(JSON.toJSONString(this.cells));
    }
}
