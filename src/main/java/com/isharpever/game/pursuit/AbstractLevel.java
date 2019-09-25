package com.isharpever.game.pursuit;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel implements Level {

    private List<ChessPiece> policeChessPieces = new ArrayList<>();
    private Board board = Board.getInstance();
    private List<List<ChessPiece>> answers = new ArrayList<>();

    @Override
    public void solve() {
//        boolean result = placeChessPiece(0, 0);
        boolean result = placeChessPiece2(0);

//        if (result) {
            // 打印答案
            int asIndex = 0;
            for (List<ChessPiece> answer : answers) {
                asIndex++;
                System.out.println(String.format("答案%s:", asIndex));

                int index = 0;
                for (ChessPiece cp : answer) {
                    index++;
                    StringBuilder sb = new StringBuilder("棋子").append(index).append("的位置===");
                    for (Cell cell : cp.getCells()) {
                        sb.append(cell.getCellOrder()).append("  ");
                    }
                    System.out.println(sb.toString());
                }

                System.out.println("\n");
            }
//        }
    }

    protected List<ChessPiece> getPoliceChessPieces() {
        return policeChessPieces;
    }

    protected Board getBoard() {
        return board;
    }

    private boolean placeChessPiece(int currentCpIndex, int beginPosition) {
        Cell[] allCells = this.board.getCells();

        ChessPiece currentCp = this.policeChessPieces.get(currentCpIndex);

        // 放当前棋子
        int currentPosition = -1;
        for (int i = beginPosition; i < allCells.length; i++) {
            try {
                currentCp.place(i, this.board);
                currentPosition = i;
                break;
            } catch (Exception e) {
            }
        }

        // 若当前棋子放失败,使用其他形状重新放
        if (currentPosition == -1 && currentCp.hasNextShape()) {
            currentCp.nextShape();
            // 起始位置:0 !!!
            return placeChessPiece(currentCpIndex, 0);
        }

        // 当前棋子所有形状全都失败
        if (currentPosition == -1) {
            return false;
        }

        // 所有棋子都放成功后,校验警车是否围住了小偷
        if (currentCpIndex == this.policeChessPieces.size() - 1) {
            //todo
            return true;
        }

        // 当前棋子放成功后,继续放下一棋子
        // 置下一棋子为初始形状
        ChessPiece nextCp = this.policeChessPieces.get(currentCpIndex + 1);
        nextCp.resetShape();
        boolean nextResult = placeChessPiece(currentCpIndex + 1, 0);

        // 若下一棋子放失败,移动当前棋子重新放
        if (!nextResult) {
            if (currentPosition == allCells.length - 1) {
                return false;
            }
            // 重置当前棋子为初始形状
            currentCp.resetShape();
            return placeChessPiece(currentCpIndex, currentPosition + 1);
        }

        return true;
    }

    private boolean placeChessPiece2(int cpIndex) {
        if (cpIndex > this.policeChessPieces.size() - 1) {
            List<ChessPiece> answer = new ArrayList<>();
            for (int i = 0; i < this.policeChessPieces.size(); i++) {
                ChessPiece cp = this.policeChessPieces.get(i);
                try {
                    answer.add(cp.clone());
                } catch (CloneNotSupportedException e) {
                }
            }
            this.answers.add(answer);

            return false;
        }

        Cell[] allCells = this.board.getCells();
        ChessPiece currentCp = this.policeChessPieces.get(cpIndex);

        // 放当前棋子
        for (int spIndex = 0; spIndex < currentCp.getPossibleShapes().length; spIndex++) {
            currentCp.setCurrentShapeIndex(spIndex);
            for (Cell cell : allCells) {
                try {
                    currentCp.place(cell, this.board);

                    // 放成功后,放下一棋子
                    boolean nextResult = placeChessPiece2(cpIndex + 1);
                    if (nextResult) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
}
