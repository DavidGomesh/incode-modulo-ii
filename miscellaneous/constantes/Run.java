/**
 * Run
 */
public class Run {

    public static void main(String[] args) {
        final double FATOR = 5.0 / 9.0;
        final double AJUSTE = 32;
        
        double fahrenheit = 86;
        double celsius = (fahrenheit - AJUSTE) * FATOR;

        System.out.println(fahrenheit + "°F");
        System.out.println(celsius + "°C");
    }
}