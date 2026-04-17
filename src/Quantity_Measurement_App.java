import java.util.*;
public class Quantity_Measurement_App {

    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value in feet: ");
            double val1 = scanner.nextDouble();

            System.out.print("Enter second value in feet: ");
            double val2 = scanner.nextDouble();

            Feet feet1 = new Feet(val1);
            Feet feet2 = new Feet(val2);

            boolean result = feet1.equals(feet2);

            if (result) {
                System.out.println("Equal (true)");
            } else {
                System.out.println("Not Equal (false)");
            }

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        } finally {
            scanner.close();
        }
    }
}
