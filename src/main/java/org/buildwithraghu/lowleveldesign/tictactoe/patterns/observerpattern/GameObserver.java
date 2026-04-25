package org.buildwithraghu.lowleveldesign.tictactoe.patterns.observerpattern;

import org.buildwithraghu.lowleveldesign.tictactoe.entities.Board;

public interface GameObserver {
    void update(Board board);
}
