package com.isharpever.game.pursuit;

/**
 * 棋子形状
 */
public enum ChessPieceShape {
    P_L_1(4, new int[]{0, Board.COL_NUM, Board.COL_NUM * 2, Board.COL_NUM * 2 + 1}, 0, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    P_L_2(4, new int[]{0, 1, 2, Board.COL_NUM}, 2, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    P_L_3(4, new int[]{0, 1, 1 + Board.COL_NUM, 1 + Board.COL_NUM * 2}, 1 + Board.COL_NUM * 2, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    P_L_4(4, new int[]{2, Board.COL_NUM, Board.COL_NUM + 1, Board.COL_NUM + 2}, Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 3),

    P_反L_1(4, new int[]{1, 1 + Board.COL_NUM, 1 + Board.COL_NUM * 2, Board.COL_NUM * 2}, 1 + Board.COL_NUM * 2, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    P_反L_2(4, new int[]{0, Board.COL_NUM, Board.COL_NUM + 1, Board.COL_NUM + 2}, Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    P_反L_3(4, new int[]{1, 0, Board.COL_NUM, Board.COL_NUM * 2}, 0, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    P_反L_4(4, new int[]{0, 1, 2, 2 + Board.COL_NUM}, 2, Board.ROW_NUM - 2, Board.COL_NUM - 3),

    P_T_1(4, new int[]{1, Board.COL_NUM, Board.COL_NUM + 1, Board.COL_NUM + 2}, 1, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    P_T_2(4, new int[]{0, Board.COL_NUM, Board.COL_NUM * 2, Board.COL_NUM + 1}, Board.COL_NUM + 1, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    P_T_3(4, new int[]{0, 1, 2, 1 + Board.COL_NUM}, 1 + Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    P_T_4(4, new int[]{1, 1 + Board.COL_NUM, 1 + Board.COL_NUM * 2, Board.COL_NUM}, Board.COL_NUM, Board.ROW_NUM - 3, Board.COL_NUM - 2),

    P_A1_1(3, new int[]{1, 1 + Board.COL_NUM, Board.COL_NUM}, 1 + Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A1_2(3, new int[]{0, Board.COL_NUM, Board.COL_NUM + 1}, Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A1_3(3, new int[]{1, 0, Board.COL_NUM}, 0, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A1_4(3, new int[]{0, 1, 1 + Board.COL_NUM}, 1, Board.ROW_NUM - 2, Board.COL_NUM - 2),

    P_A2_1(3, new int[]{1, 1 + Board.COL_NUM, Board.COL_NUM}, Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A2_2(3, new int[]{0, Board.COL_NUM, Board.COL_NUM + 1}, 0, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A2_3(3, new int[]{1, 0, Board.COL_NUM}, 1, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    P_A2_4(3, new int[]{0, 1, 1 + Board.COL_NUM}, 1 + Board.COL_NUM, Board.ROW_NUM - 2, Board.COL_NUM - 2),

    P_I_1(3, new int[]{0, Board.COL_NUM, Board.COL_NUM * 2}, Board.COL_NUM, Board.ROW_NUM - 3, Board.COL_NUM - 1),
    P_I_2(3, new int[]{0, 1, 2}, 1, Board.ROW_NUM - 1, Board.COL_NUM - 3),

    B_L_1(4, new int[]{0, Board.COL_NUM, Board.COL_NUM * 2, Board.COL_NUM * 2 + 1}, -1, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    B_L_2(4, new int[]{0, 1, 2, Board.COL_NUM}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    B_L_3(4, new int[]{0, 1, 1 + Board.COL_NUM, 1 + Board.COL_NUM * 2}, -1, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    B_L_4(4, new int[]{2, Board.COL_NUM, Board.COL_NUM + 1, Board.COL_NUM + 2}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 3),

    B_反L_1(4, new int[]{1, 1 + Board.COL_NUM, 1 + Board.COL_NUM * 2, Board.COL_NUM * 2}, -1, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    B_反L_2(4, new int[]{0, Board.COL_NUM, Board.COL_NUM + 1, Board.COL_NUM + 2}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 3),
    B_反L_3(4, new int[]{1, 0, Board.COL_NUM, Board.COL_NUM * 2}, -1, Board.ROW_NUM - 3, Board.COL_NUM - 2),
    B_反L_4(4, new int[]{0, 1, 2, 2 + Board.COL_NUM}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 3),

    B_A_1(3, new int[]{1, 1 + Board.COL_NUM, Board.COL_NUM}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    B_A_2(3, new int[]{0, Board.COL_NUM, Board.COL_NUM + 1}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    B_A_3(3, new int[]{1, 0, Board.COL_NUM}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 2),
    B_A_4(3, new int[]{0, 1, 1 + Board.COL_NUM}, -1, Board.ROW_NUM - 2, Board.COL_NUM - 2),

    B_I_1(3, new int[]{0, Board.COL_NUM, Board.COL_NUM * 2}, -1, Board.ROW_NUM - 3, Board.COL_NUM - 1),
    B_I_2(3, new int[]{0, 1, 2}, -1, Board.ROW_NUM - 1, Board.COL_NUM - 3),

    BLOCK(1, new int[]{0}, -1, Board.ROW_NUM - 1, Board.COL_NUM - 1);

    /**
     * 占棋盘格数
     */
    private final int cellNum;

    /**
     * 棋子放在棋盘上第0格时,所占棋盘格在棋盘上的序号
     */
    private final int[] cellOrders;

    /**
     * 棋子放在棋盘上第0格时,警车的棋盘格在棋盘上的序号
     */
    private final int policeCarOrder;

    /**
     * 棋子能放到棋盘上的最大行号
     */
    private final int maxRowNo;

    /**
     * 棋子能放到棋盘上的最大列号
     */
    private final int maxColNo;

    ChessPieceShape(int cellNum, int[] cellOrders, int policeCarOrder, int maxRowNo, int maxColNo) {
        this.cellNum = cellNum;
        this.cellOrders = cellOrders;
        this.policeCarOrder = policeCarOrder;
        this.maxRowNo = maxRowNo;
        this.maxColNo = maxColNo;
    }

    public int getCellNum() {
        return cellNum;
    }

    public int[] getCellOrders() {
        return cellOrders;
    }

    public int getPoliceCarOrder() {
        return policeCarOrder;
    }

    public int getMaxRowNo() {
        return maxRowNo;
    }

    public int getMaxColNo() {
        return maxColNo;
    }
}
