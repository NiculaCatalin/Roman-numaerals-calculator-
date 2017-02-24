package homework.classes.operands;

import homework.interfaces.operands.IOperand;
import java.util.LinkedList;
import java.util.List;

public class Operand implements IOperand {
    private String symbol;
    private static final int I = 1;
    private static final int V = 5;
    private static final int X = 10;
    private static final int L = 50;
    private static final int C = 100;
    private static final int D = 500;
    private static final int M = 1000;
    private static final int BIGGER_THEN_M = 1001;
    private static char[] romanNums = new char[] {
            'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    private static int[] arabTrans = new int[] {
            I, V, X, L, C, D, M };
    private static List<Character> romanNumerals;
    private Double leftOvers = 0.0;
    public final void setLeftOvers(final Double number) {
        leftOvers = number;
    }
    public Operand(final String newSymbol) {
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

    @Override
    public final Double getSymbolValue() {
        Double value = 0.0;
        int previousVal = BIGGER_THEN_M;
        boolean negative = false;
        if (symbol.length() >= 1) {
            if (symbol.charAt(0) == '-') {
                symbol = symbol.substring(2);
                negative = true;
            }
        }

        for (Character c : symbol.toCharArray()) {
            int currentVal;
            currentVal = Operand.getNumeralForLetter(c);

            if (previousVal < currentVal) {
                value += currentVal - 2 * previousVal;
            } else {
                value += currentVal;
            }
            previousVal = currentVal;
        }
        value += leftOvers;
        if (negative) {
            value *= -1;
        }
        return value;
    }

    @Override
    public final void setSymbolValue(final Number number) {
        Number value = number;
        boolean negative = false;
        if (value.doubleValue() < 0) {
            negative = true;
            value = value.doubleValue() * -1;
        }
        int val = (int) Math.floor(value.doubleValue());
        symbol = "";
        String auxSymbol = "";
        int max = 0;

        while (val > 0) {
            for (RomanTable entry : RomanTable.values()) {

                if (RomanTable.valueOf(entry.name()).getValue() <= val) {
                    max = RomanTable.valueOf(entry.name()).getValue();
                    auxSymbol = entry.name();
                }
            }

            symbol += auxSymbol;
            val -= max;
        }

        if (negative) {
            symbol = "- " + symbol;
        }
    }

    private static int getNumeralForLetter(final Character c) {
        if (romanNumerals == null) {
            romanNumerals = new LinkedList<>();
            for (int i = 0; i < romanNums.length; i++) {
                romanNumerals.add(romanNums[i]);
            }
        }
        return arabTrans[romanNumerals.indexOf(c)];
    }

}
