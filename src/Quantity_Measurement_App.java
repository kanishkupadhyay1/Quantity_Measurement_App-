public class Quantity_Measurement_App {

    public static void main(String[] args) {

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .equals(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .equals(new QuantityWeight(2.20462, WeightUnit.POUND))
        );

        System.out.println(
                new QuantityWeight(500.0, WeightUnit.GRAM)
                        .equals(new QuantityWeight(0.5, WeightUnit.KILOGRAM))
        );

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM)
        );

        System.out.println(
                new QuantityWeight(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM)
        );

        System.out.println(
                new QuantityWeight(500.0, WeightUnit.GRAM)
                        .convertTo(WeightUnit.POUND)
        );

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );

        System.out.println(
                new QuantityWeight(500.0, WeightUnit.GRAM)
                        .add(new QuantityWeight(0.5, WeightUnit.KILOGRAM))
        );

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM)
        );

        System.out.println(
                new QuantityWeight(1.0, WeightUnit.POUND)
                        .add(new QuantityWeight(453.592, WeightUnit.GRAM), WeightUnit.POUND)
        );

        System.out.println(
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM)
        );

        System.out.println(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(0.0, WeightUnit.GRAM))
        );

        System.out.println(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(-2000.0, WeightUnit.GRAM))
        );
    }
}