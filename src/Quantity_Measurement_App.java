import java.util.*;

public class Quantity_Measurement_App {

    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),     // 1 inch = 1/12 feet
        YARDS(3.0),             // 1 yard = 3 feet
        CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
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
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }
    }

    public static void main(String[] args) {

        System.out.println("Yard to Feet: " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET)));

        System.out.println("Yard to Inches: " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(36.0, LengthUnit.INCHES)));

        System.out.println("CM to Inches: " +
                new QuantityLength(1.0, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(0.393701, LengthUnit.INCHES)));

        System.out.println("Yard same: " +
                new QuantityLength(2.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(2.0, LengthUnit.YARDS)));

        System.out.println("Yard different: " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(2.0, LengthUnit.YARDS)));

        System.out.println("CM to CM: " +
                new QuantityLength(2.0, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(2.0, LengthUnit.CENTIMETERS)));

        System.out.println("CM to Feet: " +
                new QuantityLength(1.0, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(1.0, LengthUnit.FEET)));

        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCHES);

        System.out.println("Transitive (A=B, B=C, A=C): " +
                (a.equals(b) && b.equals(c) && a.equals(c)));

        System.out.println("Compare with null: " +
                new QuantityLength(1.0, LengthUnit.YARDS).equals(null));

        // Same reference
        QuantityLength ref = new QuantityLength(5.0, LengthUnit.FEET);
        System.out.println("Same reference: " + ref.equals(ref));
    }
}