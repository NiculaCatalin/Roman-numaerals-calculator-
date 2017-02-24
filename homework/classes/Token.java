package homework.classes;

import homework.interfaces.IToken;

public class Token implements IToken {
    private String symbol;
    public Token(final String newSymbol) {
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
