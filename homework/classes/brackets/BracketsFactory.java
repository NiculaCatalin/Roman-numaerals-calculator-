package homework.classes.brackets;

import homework.interfaces.IToken;
import homework.interfaces.brackets.IBracket;
import homework.interfaces.brackets.IBracketsFactory;

public class BracketsFactory implements IBracketsFactory {
    private static BracketsFactory instance = null;
    private String acceptedSymbols = "()[]{}";
    private String openedBracket = "([{";
    private String closedBracket = ")]}";

    protected BracketsFactory() {

    }

    public static BracketsFactory getInstance() {
        if (instance == null) {
            instance = new BracketsFactory();
        }
        return instance;
    }

    @Override
    public final IBracket createBracket(final String token) {
        final Bracket b = new Bracket(token);
        return b;
    }

    @Override
    public final boolean isBracket(final IToken token) {
        return acceptedSymbols.contains(token.getSymbol());
    }

    @Override
    public final boolean isOpenedBracket(final IBracket bracket) {
        return openedBracket.contains(bracket.getSymbol());
    }

    @Override
    public final boolean isClosedBracket(final IBracket bracket) {
        return closedBracket.contains(bracket.getSymbol());
    }

    @Override
    public final boolean isBracketPair(
            final IBracket openBracket, final IBracket closeBracket) {
        return false;
    }
}
