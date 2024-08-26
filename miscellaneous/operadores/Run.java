import static java.lang.Math.pow;

public class Run {

    public static void main(String[] args) {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(" ");
        
        System.out.println(
            pow((((pow((6 * (3 + 2)), 2)) / (3 * 2)) - pow((((1 - 5) * (2 - 7)) / 2), 2)), 3) / pow(10, 3)
        );

        var numA = pow(6 * (3 + 2), 2);
        var denA = 3 * 2;
        var superiorA = numA / denA;

        var numB = (1 - 5) * (2 - 7);
        var denB = 2;
        var superiorB = pow(numB / denB, 2);

        var superior = pow(superiorA - superiorB, 3);
        var inferior = pow(10, 3);

        var resultado = superior / inferior;
        System.out.println(resultado);
    }
}