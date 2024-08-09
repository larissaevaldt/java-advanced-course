package lambda;

interface I {
    void m(); // a functional interface i.e. it has only one abstract method
}

public class BasicLambdas {

    public static void main(String[] args) {
        // pre-Java 8
        I i = new I() {

            @Override
            public void m() {
                System.out.println("I::m()");
            }
        };
        i.m();

        // Java 8 - Lambda Expression
        I lambdaI = () -> {
            System.out.println("Lambda version!");
        };
        I lambdaI2 = () -> System.out.println("Lambda Version!");
        lambdaI.m();
        lambdaI2.m();
    }

}
