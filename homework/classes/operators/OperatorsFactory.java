package homework.classes.operators;

import homework.classes.operators.binaryOperators.BinaryOperator;
import homework.classes.operators.unaryOperators.UnaryOperator;
import homework.interfaces.IToken;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.IOperatorsFactory;


public final class OperatorsFactory implements IOperatorsFactory {
    private static OperatorsFactory instance = null;

    public static OperatorsFactory getInstance() {
        if (instance == null) {
            instance = new OperatorsFactory();
        }
        return instance;
    }

    private OperatorsFactory() {
    }

    private String operatorSymbols = "+-/*^sqrtlog";
    @Override
    public IOperator createOperator(final String token) {
        OperatorType type = Operator.getType(token);
        if (type.equals(OperatorType.SQRT) || type.equals(OperatorType.LOG)) {
            return new UnaryOperator(token);
        }
        return new BinaryOperator(token);
    }

    @Override
    public boolean isOperator(final IToken token) {
        return operatorSymbols.contains(token.getSymbol());
    }

    @Override
    public boolean isUnaryOperator(final IOperator operator) {
        OperatorType type = Operator.getType(operator.getSymbol());
        return type.equals(OperatorType.SQRT) || type.equals(OperatorType.LOG);
    }

    @Override
    public boolean isBinaryOperator(final IOperator operator) {
        return !isUnaryOperator(operator);
    }
}
