import java.util.*;

public class Quantity_Measurement_App {

    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

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

        private double toFeet() {
            return unit.toFeet(value);
        }

        private QuantityLength addInternal(QuantityLength other, LengthUnit targetUnit) {
            double sumInFeet = this.toFeet() + other.toFeet();
            double resultValue = targetUnit.fromFeet(sumInFeet);
            return new QuantityLength(resultValue, targetUnit);
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
            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println(
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET)
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES)
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS)
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS)
        );

        System.out.println(
                new QuantityLength(36.0, LengthUnit.INCHES)
                        .add(new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET)
        );

        System.out.println(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                        .add(new QuantityLength(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS)
        );

        System.out.println(
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.YARDS)
        );

        System.out.println(
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES)
        );

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.FEET), LengthUnit.YARDS);

        System.out.println(a.equals(b));
    }
}