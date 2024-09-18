package sealed_classes;

sealed interface Driveable permits Vehicle {}
public sealed class Vehicle implements Driveable permits Car {
}

sealed class Car extends Vehicle permits Ford, Volvo {}

final class Ford extends Car {}

final class Volvo extends Car {}

// Error - 'Table' is not allowed in the sealed hierarchy
//class Table extends Vehicle {}

// Error - 'Table' is not allowed in the sealed hierarchy
//class Table implements Driveable {}