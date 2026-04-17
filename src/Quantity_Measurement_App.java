import java.util.*;
public class Quantity_Measurement_App {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
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
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }




    public static void main(String[] args) {

        // Yard to Feet
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("Yard to Feet: " + q1.equals(q2));

        // Yard to Inches
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);
        System.out.println("Yard to Inches: " + q3.equals(q4));

        // CM to Inches
        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);
        System.out.println("CM to Inches: " + q5.equals(q6));

        // Same unit check
        QuantityLength q7 = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength q8 = new QuantityLength(2.0, LengthUnit.YARDS);
        System.out.println("Yard same: " + q7.equals(q8));
    }
    }

