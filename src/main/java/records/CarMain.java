package records;

public class CarMain {

    public static void main(String[] args) {
        // 1. Regular class
        Car car = new Car("231G1234", "Joe Bloggs");
        System.out.println(car);
        System.out.println(car.getOwner());
        System.out.println(car.getRegNumber());

        // 1. Using a record
        CarRecord carRecord = new CarRecord("241G4321", "Mary Bloggs");
        System.out.println(carRecord);
        System.out.println(carRecord.owner()); // slightly different accessor methods
        System.out.println(carRecord.regNumber());

        // 3. Define an instance method
        System.out.println(carRecord.isNewCar());

        // 4. Define a static field
        System.out.println(CarRecord.currentYear);

        // 5. Define a static method
        CarRecord blankCar = CarRecord.createBlankCarRecord();
        System.out.println("blank owner: " + blankCar.owner());
        System.out.println("blank reg number: " + blankCar.regNumber());

        // 6. Custom canonical constructor and compact constructor
        // 7. Non-canonical record constructor must delegate to another constructor
        // 8. Override the owner() accessor method
        // 9. Cannot define a subtype based on a recode
        // 10. Can implement an interface
        // 11. When defining your record, you cannot extend from another type
    }
}
