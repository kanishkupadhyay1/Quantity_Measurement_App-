import java.util.*;

public class Quantity_Measurement_App {

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 0.0001;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue = this.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(baseValue);
            return new QuantityLength(result, targetUnit);
        }

        private QuantityLength addInternal(QuantityLength other, LengthUnit targetUnit) {
            double sum = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(sum);
            return new QuantityLength(result, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }
            return addInternal(other, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            return addInternal(other, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println(
                new QuantityLength(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES)
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET)
        );

        System.out.println(
                new QuantityLength(36.0, LengthUnit.INCHES)
                        .equals(new QuantityLength(1.0, LengthUnit.YARDS))
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS)
        );

        System.out.println(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                        .convertTo(LengthUnit.INCHES)
        );

        System.out.println(
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.FEET)
        );

        System.out.println(
                LengthUnit.FEET.convertToBaseUnit(12.0)
        );

        System.out.println(
                LengthUnit.INCHES.convertToBaseUnit(12.0)
        );
    }
}