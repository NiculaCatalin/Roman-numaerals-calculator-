package homework.classes.operators.binaryOperators;

import homework.classes.operands.Operand;
import homework.classes.operators.Operator;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;

public class BinaryOperator extends Operator implements IBinaryOperator {

    public BinaryOperator(final String symbol) {
        this.symbol = symbol;
        this.type = Operator.getType(symbol);
    }

    @Override
    public final IOperand calculate(
            final Number leftOperand, final Number rightOperand) {
        double a = leftOperand.doubleValue();
        double b = rightOperand.doubleValue();
        double result = 0;
        switch (type) {
        case ADD:
            result = a + b;
            break;
        case DIV:
            result = a / b;
            break;
        case MULT:
            result = a * b;
            break;
        case SUB:
            result = a - b;
            break;
        case POW:
            result = Math.pow(a, b);
            break;
        default:
        }
        Operand op = new Operand("");
        op.setSymbolValue(result);
        if (result >= 0) {
            op.setLeftOvers(result % 1);
        } else {
            op.setLeftOvers(-(result % 1));
        }

        return op;
    }
}
