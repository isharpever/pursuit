package com.isharpever.game.pursuit;

public class Level64 extends AbstractLevel {

    @Override
    public void init() {
        // 初始化棋盘
        getBoard().release();

        // 初始化建筑并放到棋盘
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_反L_3).place(0, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_A_1).place(10, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_L_4).place(26, getBoard());
        new ChessPiece(ChessPieceType.BUILDING, ChessPieceShape.B_I_1).place(23, getBoard());

        // 初始化小偷并放到棋盘
        new ChessPiece(ChessPieceType.THIEF, ChessPieceShape.BLOCK).place(14, getBoard());

        // 初始化警察
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_L_1, ChessPieceShape.P_L_2,
                        ChessPieceShape.P_L_3, ChessPieceShape.P_L_4}));
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_反L_1, ChessPieceShape.P_反L_2,
                        ChessPieceShape.P_反L_3, ChessPieceShape.P_反L_4}));
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_T_1, ChessPieceShape.P_T_2,
                        ChessPieceShape.P_T_3, ChessPieceShape.P_T_4}));
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_I_1, ChessPieceShape.P_I_2}));
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_A1_1, ChessPieceShape.P_A1_2,
                        ChessPieceShape.P_A1_3, ChessPieceShape.P_A1_4}));
        getPoliceChessPieces().add(new ChessPiece(ChessPieceType.POLICE,
                new ChessPieceShape[]{ChessPieceShape.P_A2_1, ChessPieceShape.P_A2_2,
                        ChessPieceShape.P_A2_3, ChessPieceShape.P_A2_4}));
    }
}
