package com.isharpever.game.pursuit;

public class Level56 extends AbstractLevel {

    @Override
    public void doInit() {
        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_2).place(2, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_1).place(18, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_4).place(28, getBoard());
    }

    @Override
    protected int getThiefPosition() {
        return 19;
    }
}
