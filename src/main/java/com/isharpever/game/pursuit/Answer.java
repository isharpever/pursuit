package com.isharpever.game.pursuit;

import java.util.List;
import lombok.Data;

/**
 * 答案
 */
@Data
public class Answer {

    /** 是否能围住小偷 */
    private boolean theifBeSieged;

    /** 棋子位置信息 */
    private List<ChessPiece> chessPieces;

    public Answer(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
        this.theifBeSieged = false;
    }
}
