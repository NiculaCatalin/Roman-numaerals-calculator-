package homework.classes.brackets;

import homework.interfaces.brackets.IBracket;

public class Bracket implements IBracket {
    private String symbol;

    public Bracket(final String newSymbol) {
        this.symbol = newSymbol;
    }

    @Override
    public final String getSymbol() {
        return symbol;
    }

    @Override
    public final void setSymbol(final String newSymbol) {
        this.symbol = newSymbol;
    }
}

enum BracketType {
    ROUND, SQUARE, WIGGLED
}
