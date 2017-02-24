package homework.classes.operands;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.IOperandsFactory;

public final class OperandsFactory implements IOperandsFactory {
    private static OperandsFactory instance = null;
    private final String acceptedCharacters = "IVXLCDM";

    public static OperandsFactory getInstance() {
        if (instance == null) {
            instance = new OperandsFactory();
        }
        return instance;
    }

    private OperandsFactory() {
    }

    @Override
    public IOperand createOperand(final String token) {
        return new Operand(token);
    }

    @Override
    public IOperand convertToRomanNumber(final Number arabNumber) {
        Operand op = new Operand("");
        op.setSymbolValue(arabNumber);
        return op;
    }

    @Override
    public IOperand convertToArabNumber(final String romanNumber) {
        for (Character c : romanNumber.toCharArray()) {
            if (acceptedCharacters.indexOf(c) < 0) {
                return null;
            }
        }

        Operand op = new Operand(romanNumber);
        return op;
    }
}
