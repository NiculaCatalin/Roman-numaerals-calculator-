package homework.classes.operators.unaryOperators;

import homework.classes.operands.Operand;
import homework.classes.operators.Operator;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;

public class UnaryOperator extends Operator implements IUnaryOperator {
    public UnaryOperator(final String symbol) {
        this.symbol = symbol;
        this.type = Operator.getType(symbol);
    }

    @Override
    public final IOperand calculate(final Number operand) {
        double a = operand.doubleValue();
        double result = 0;
        switch (type) {
            case SQRT:
                result = Math.sqrt(a);
                break;
            case LOG:
                result = Math.log(a);
                break;
            default:
        }
        Operand op = new Operand("");
        op.setSymbolValue(result);
        op.setLeftOvers(result % 1);

        return op;
    }
}
