package com.isharpever.game.pursuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractLevel implements Level {

    private Board board = Board.getInstance();
    private List<ChessPiece> policeChessPieces = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();

    @Override
    public void init() {
        // 初始化棋盘
        getBoard().release();

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

        // 初始化小偷并放到棋盘
        new ChessPiece(ChessPieceType.THIEF, ChessPieceShape.BLOCK).place(getThiefPosition(), getBoard());

        // 其他初始化(建筑..)
        doInit();
    }

    protected abstract int getThiefPosition();

    protected abstract void doInit();

    @Override
    public void solve(boolean includeNotSieged) {
        doSolve(0, includeNotSieged);

        // 打印答案
        printAnswers();
    }

    /**
     * 破解
     * @param cpIndex
     * @param includeNotSieged
     * @return
     */
    protected boolean doSolve(int cpIndex, boolean includeNotSieged) {
        // 所有棋子都放成功后,判断是否能围住小偷
        if (cpIndex > getPoliceChessPieces().size() - 1) {
            List<ChessPiece> chessPieces = new ArrayList<>();
            for (int i = 0; i < getPoliceChessPieces().size(); i++) {
                ChessPiece cp = getPoliceChessPieces().get(i);
                try {
                    chessPieces.add(cp.clone());
                } catch (CloneNotSupportedException e) {
                }
            }

            Answer thisAnswer = new Answer(chessPieces);
            if (thiefBesieged()) {
                thisAnswer.setTheifBeSieged(true);
                getAnswers().add(thisAnswer);
            } else if (includeNotSieged) {
                getAnswers().add(thisAnswer);
            }

            // 继续找其他解法
            return false;
        }

        Cell[] allCells = getBoard().getCells();
        ChessPiece currentCp = getPoliceChessPieces().get(cpIndex);

        // 放当前棋子
        for (int spIndex = 0; spIndex < currentCp.getPossibleShapes().length; spIndex++) {
            currentCp.setCurrentShapeIndex(spIndex);
            for (Cell cell : allCells) {
                try {
                    currentCp.place(cell, getBoard());

                    // 成功后,放下一棋子
                    boolean nextResult = doSolve(cpIndex + 1, includeNotSieged);
                    if (nextResult) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        // 回溯处理前一棋子前,确保当前棋子离开棋盘
        currentCp.takeAway();
        return false;
    }

    /**
     * 检查是否能围住小偷
     * @return true:能围住 false:不能围住
     */
    protected boolean thiefBesieged() {
        // 小偷位置
        int theifPosition = getThiefPosition();

        Map<Integer, Boolean> cache = new HashMap<>();
        return !leadingToBorder(theifPosition, cache);
    }

    /**
     * 检测指定位置能否通往边界
     * @param position
     * @param cache
     * @return true:能通往边界 false:不能通往边界
     */
    protected Boolean leadingToBorder(int position, Map<Integer, Boolean> cache) {
        if (cache.containsKey(position)) {
            // 这里可能返回null,表示正在检测
            return cache.get(position);
        }

        // 标记当前位置正在检测
        cache.put(position, null);

        // 当前位置有建筑或警车,不能通往边界
        if (getBoard().isPoliceCar(position) || getBoard().isBuilding(position)) {
            cache.put(position, Boolean.FALSE);
            return false;
        }

        // 当前位置是边界、且不是建筑不是警车
        Boolean current = Board.isOnOrOutOfBorder(position);
        if (current) {
            cache.put(position, Boolean.TRUE);
            return true;
        }

        // 上
        Boolean up = leadingToBorder(position - Board.COL_NUM, cache);
        if (up != null && up) {
            cache.put(position, Boolean.TRUE);
            return true;
        }

        // 下
        Boolean down = leadingToBorder(position + Board.COL_NUM, cache);
        if (down != null && down) {
            cache.put(position, Boolean.TRUE);
            return true;
        }

        // 左
        Boolean left = leadingToBorder(position - 1, cache);
        if (left != null && left) {
            cache.put(position, Boolean.TRUE);
            return true;
        }

        // 右
        Boolean right = leadingToBorder(position + 1, cache);
        if (right != null && right) {
            cache.put(position, Boolean.TRUE);
            return true;
        }

        // 其上下左右都不能通往边界
        cache.put(position, Boolean.FALSE);
        return false;
    }

    /**
     * 打印答案
     */
    protected void printAnswers() {
        if (getAnswers().size() == 0) {
            System.out.println("无解");
        }

        int asIndex = 0;
        for (Answer answer : getAnswers()) {
            asIndex++;
            System.out.println(String.format("答案%s(%s):", asIndex,
                    answer.isTheifBeSieged() ? "能围住小偷" : "不能围住小偷"));

            int index = 0;
            for (ChessPiece cp : answer.getChessPieces()) {
                index++;
                StringBuilder sb = new StringBuilder("棋子").append(index).append("的位置===");
                for (Cell cell : cp.getCells()) {
                    sb.append(cell.getCellOrder());
                    if (cell.isPoliceCar()) {
                        sb.append("(警车)");
                    }
                    sb.append("  ");
                }
                System.out.println(sb.toString());
            }

            System.out.println("\n");
        }
    }

    protected List<ChessPiece> getPoliceChessPieces() {
        return policeChessPieces;
    }

    protected Board getBoard() {
        return board;
    }

    protected List<Answer> getAnswers() {
        return answers;
    }
}
