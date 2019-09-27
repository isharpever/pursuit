package com.isharpever.game.pursuit;

public class Level13 extends AbstractLevel {

    @Override
    public void doInit() {
        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_3).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(18, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_1).place(17, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_1).place(22, getBoard());
    }

    @Override
    protected int getThiefPosition() {
        return 16;
    }
}
