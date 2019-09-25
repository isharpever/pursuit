package com.isharpever.game.pursuit;

public class Level64 extends AbstractLevel {

    @Override
    public void doInit() {
        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_1).place(10, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_4).place(26, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_1).place(23, getBoard());
    }

    @Override
    protected int getThiefPosition() {
        return 14;
    }

    @Override
    public boolean answerCheck() {
        return false;
    }
}
