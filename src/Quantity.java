public class Quantity<U extends IMeasurable> {


    private enum ArithmeticOperation {
        ADD {
            @Override
            double compute(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            double compute(double a, double b) {
                return a - b;
            }
        },
        DIVIDE {
            @Override
            double compute(double a, double b) {
                if (Math.abs(b) < 0.0000001) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            }
        };

        abstract double compute(double a, double b);


    }
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }

    // -------------------------
    // BASE CONVERSION
    // -------------------------
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }



    private void validate(Quantity<U> other, boolean isDivision) {

        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (isDivision && other.toBaseUnit() == 0) {
            throw new ArithmeticException("Division by zero");
        }
    }

    // -------------------------
    // SINGLE ARITHMETIC ENGINE
    // -------------------------
    private double computeBase(Quantity<U> other, ArithmeticOperation op) {

        validate(other, op == ArithmeticOperation.DIVIDE);

        return op.compute(
                this.toBaseUnit(),
                other.toBaseUnit()
        );
    }

    // -------------------------
    // ADDITION
    // -------------------------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double result = computeBase(other, ArithmeticOperation.ADD);

        double converted = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), targetUnit);
    }

    // -------------------------
    // SUBTRACTION
    // -------------------------
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        double result = computeBase(other, ArithmeticOperation.SUBTRACT);

        double converted = targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(round(converted), targetUnit);
    }

    // -------------------------
    // DIVISION (SCALAR RESULT)
    // -------------------------
    public double divide(Quantity<U> other) {
        validate(other, true);

        return ArithmeticOperation.DIVIDE.compute(
                this.toBaseUnit(),
                other.toBaseUnit()
        );
    }

    // -------------------------
    // ROUNDING UTILITY
    // -------------------------
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // -------------------------
    // CONVERSION
    // -------------------------
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }

    // -------------------------
    // EQUALITY
    // -------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (this.unit.getClass() != other.unit.getClass()) return false;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(toBaseUnit());
        return (int) (bits ^ (bits >>> 32));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}