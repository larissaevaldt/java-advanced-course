package records;

// regNumber and owner are known as 'components'
public record CarRecord(String regNumber, String owner) implements I {

//    private final int age; // not allowed - instance fields must be listed in constructor signature above
    public static final String currentYear = "24";

    // custom canonical constructor
//    public CarRecord(String regNumber, String owner) {
//       if (regNumber.length() <= 4) {
//           throw new IllegalArgumentException();
//       }
//       this.regNumber = regNumber;
//       this.owner = owner;
//    }

    public CarRecord {
        if (regNumber.length() <= 4) {
            throw new IllegalArgumentException();
        }
    }

    public CarRecord() {
        // if the line below was not there we would get an error:
        // Non-canonical record constructor must delegate to another constructor
        this("     ", "");
    }

    public boolean isNewCar() {
        return regNumber().substring(0,2).equals(currentYear);
    }

    public static CarRecord createBlankCarRecord() {
        return new CarRecord("     ", "");
    }

    @Override
    public String owner() {
        return owner.toUpperCase();
    }
}

// not allowed: Cannot inherit from final 'records.CarRecord'
//class X extends CarRecord {}

interface I {}
