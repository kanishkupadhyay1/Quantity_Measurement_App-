public class Quantity_Measurement_App {

    public static void main(String[] args) {

        System.out.println(
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .equals(new Quantity<>(1000.0, WeightUnit.GRAM))
        );

        System.out.println(
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .equals(new Quantity<>(2.20462, WeightUnit.POUND))
        );

        System.out.println(
                new Quantity<>(500.0, WeightUnit.GRAM)
                        .equals(new Quantity<>(0.5, WeightUnit.KILOGRAM))
        );

        System.out.println(
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM)
        );

        System.out.println(
                new Quantity<>(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM)
        );

        System.out.println(
                new Quantity<>(500.0, WeightUnit.GRAM)
                        .convertTo(WeightUnit.POUND)
        );

        System.out.println(
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM))
        );

        System.out.println(
                new Quantity<>(500.0, WeightUnit.GRAM)
                        .add(new Quantity<>(0.5, WeightUnit.KILOGRAM))
        );

        System.out.println(
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.GRAM)
        );

        System.out.println(
                new Quantity<>(1.0, WeightUnit.POUND)
                        .add(new Quantity<>(453.592, WeightUnit.GRAM), WeightUnit.POUND)
        );

        System.out.println(
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM)
        );

        System.out.println(
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(0.0, WeightUnit.GRAM))
        );

        System.out.println(
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(-2000.0, WeightUnit.GRAM))
        );
    }
}