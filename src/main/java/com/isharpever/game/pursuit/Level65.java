package com.isharpever.game.pursuit;

public class Level65 extends AbstractLevel {

    @Override
    public void doInit() {
        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_2).place(2, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_3).place(20, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_1).place(18, getBoard());
    }

    @Override
    protected int getThiefPosition() {
        return 19;
    }
}
