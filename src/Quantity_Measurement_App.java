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
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);
        System.out.println(v1.equals(v2)); // true

        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));
// Quantity(1000.0, MILLILITRE)

        System.out.println(v1.add(v2));
// Quantity(2.0, LITRE)

        System.out.println(v3.convertTo(VolumeUnit.LITRE));
// Quantity(3.78541, LITRE)

        System.out.println(
                v1.add(v3, VolumeUnit.MILLILITRE)
        );
// Quantity(4785.41, MILLILITRE)

        Quantity<VolumeUnit> v4 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v5 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println(v4.subtract(v2));
// Expected: Quantity(4.5, LITRE)

        System.out.println(v1.subtract(v5, VolumeUnit.MILLILITRE));
// Expected: Quantity(4500.0, MILLILITRE)

        System.out.println(v1.divide(v5));
// Expected: 10.0

        // Subtraction (UC12 functionality via UC13 refactored logic)
        System.out.println(
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5000.0, WeightUnit.GRAM))
        );

        System.out.println(
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE))
        );

// Subtraction with explicit unit
        System.out.println(
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE)
        );

// Division
        System.out.println(
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM))
        );

        System.out.println(
                new Quantity<>(24.0, VolumeUnit.MILLILITRE)
                        .divide(new Quantity<>(2.0, VolumeUnit.LITRE))
        );

// Edge case (optional demo)
        System.out.println(
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(10.0, WeightUnit.KILOGRAM))
        );
    }
}