package com.isharpever.game.pursuit;

public class Level14 extends AbstractLevel {

    @Override
    public void doInit() {
        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_2).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_1).place(4, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(12, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_4).place(27, getBoard());
    }

    @Override
    protected int getThiefPosition() {
        return 28;
    }
}
