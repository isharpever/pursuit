package com.isharpever.game.pursuit;

import java.util.Arrays;
import lombok.Data;

/**
 * 棋子
 */
@Data
public class ChessPiece implements Cloneable {

    /**
     * 棋子类型
     */
    private ChessPieceType chessPieceType;

    /**
     * 棋子可变换的形状
     */
    private final ChessPieceShape[] possibleShapes;

    /**
     * 棋子当前形状在possibleShapes的下标
     */
    private int currentShapeIndex;

    /**
     * 状态
     * <br>0:不在棋盘上 1:在棋盘上
     */
    private byte state = 0;

    /**
     * 占据的棋盘格
     */
    private final Cell[] cells;

    /**
     * 棋子当前所在位置(棋盘格序号)
     */
    private int position = -1;

    public ChessPiece(ChessPieceType chessPieceType, ChessPieceShape currentShape) {
        this.chessPieceType = chessPieceType;
        this.possibleShapes = new ChessPieceShape[]{currentShape};
        this.currentShapeIndex = 0;
        this.cells = new Cell[currentShape.getCellNum()];
    }

    public ChessPiece(ChessPieceType chessPieceType, ChessPieceShape[] possibleShapes) {
        this.chessPieceType = chessPieceType;
        this.possibleShapes = possibleShapes;
        this.currentShapeIndex = 0;
        this.cells = new Cell[possibleShapes[0].getCellNum()];
    }

    /**
     * 棋子放到指定棋盘格
     * @param cell
     * @param board
     */
    public void place(Cell cell, Board board) {
        if (cell == null) {
            throw new IllegalStateException("棋盘格不能为空");
        }
        place(cell.getCellOrder(), board);
    }

    /**
     * 棋子放到棋盘的指定序号位置
     * @param position
     * @param board
     * @return true:成功 false:失败
     */
    public void place(int position, Board board) {
        checkPlacePosition(position);

        synchronized (this) {
            takeAway();

            this.state = 1;
            this.position = position;

            // 占据哪些棋盘格
            for (int i = 0; i < getCellOrders().length; i++) {
                int cellOrder = getCellOrders()[i] + position;

                // 冲突检测
                if (board.isNotEmpty(cellOrder)) {
                    takeAway();
                    throw new IllegalStateException("棋盘格已有棋子 cellOrder=" + cellOrder);
                }

                board.setChessPiece(cellOrder, this);
                this.cells[i] = board.getCell(cellOrder);
            }

            if (this.chessPieceType == ChessPieceType.POLICE) {
                board.setPoliceCar(getPoliceOrder() + position);
            }
        }
    }

    /**
     * 棋子从棋盘上拿走
     */
    public void takeAway() {
        synchronized (this) {
            if (this.state == 0) {
                return;
            }

            this.state = 0;
            this.position = -1;

            // 释放占据的棋盘格
            for (int i = 0; i < this.cells.length; i++) {
                if (this.cells[i] == null) {
                    continue;
                }
                this.cells[i].release(this);
                this.cells[i] = null;
            }
        }
    }

    private void checkPlacePosition(int position) {
        if (position < 0 || Board.getY(position) > getMaxRowNo()
                || Board.getX(position) > getMaxColNo()) {
            String message = String.format("不能放在这个位置(%s),允许的最大行=%s,最大列=%s",
                    position, getMaxRowNo(), getMaxColNo());
            throw new IllegalStateException(message);
        }
    }

    public boolean hasNextShape() {
        int length = this.possibleShapes.length;
        return this.currentShapeIndex < length - 1;
    }

    public void nextShape() {
        this.currentShapeIndex += 1;
    }

    /**
     * 重置棋子为初始形状
     */
    public void resetShape() {
        if (this.possibleShapes != null) {
            this.currentShapeIndex = 0;
        }
    }

    private int[] getCellOrders() {
        return this.possibleShapes[this.currentShapeIndex].getCellOrders();
    }

    private int getMaxRowNo() {
        return this.possibleShapes[this.currentShapeIndex].getMaxRowNo();
    }

    private int getMaxColNo() {
        return this.possibleShapes[this.currentShapeIndex].getMaxColNo();
    }

    private int getPoliceOrder() {
        return this.possibleShapes[this.currentShapeIndex].getPoliceCarOrder();
    }

    @Override
    protected ChessPiece clone() throws CloneNotSupportedException {
        ChessPiece result = new ChessPiece(this.getChessPieceType(), this.getPossibleShapes());
        for (int i = 0; i < cells.length; i++) {
            result.getCells()[i] = this.getCells()[i].clone();
        }
        result.setCurrentShapeIndex(this.getCurrentShapeIndex());
        result.setState(this.getState());
        result.setPosition(this.getPosition());
        return result;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "chessPieceType=" + chessPieceType +
                ", possibleShapes=" + Arrays.toString(possibleShapes) +
                ", currentShapeIndex=" + currentShapeIndex +
                ", state=" + state +
                ", position=" + position +
                '}';
    }
}
