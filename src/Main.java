import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = 0;

        while (true) {
            System.out.println("Введите x в диапазоне (-1,1):");
            x = scanner.nextDouble();

            if (x > -1 && x < 1) {
                break;
            } else {
                System.out.println("Неверно введён x! Попробуйте снова.");
            }
        }

        System.out.println("Введите k:");
        int k = scanner.nextInt();

        double epsilon = Math.pow(10, -k);

        double taylorResult = calculateArcsinTaylor(x, epsilon);
        double exactResult = Math.asin(x);

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫЧИСЛЕНИЙ ===");
        System.out.printf("Приближенное значение (ряд Тейлора): %.3f%n", taylorResult);
        System.out.printf("Точное значение (Math.asin): %.3f%n", exactResult);
        System.out.printf("Разница: %.6f%n", Math.abs(taylorResult - exactResult));
        System.out.printf("Точность ε: %.10f%n", epsilon);

        scanner.close();
    }

    private static double calculateArcsinTaylor(double x, double epsilon) {
        double sum = x;
        double term = x;
        int n = 1;

        System.out.println("\nВычисление ряда Тейлора:");
        System.out.printf("Слагаемое 0: %.10f%n", term);


        while (Math.abs(term) >= epsilon) {

            double coefficient = (double) (2 * n - 1) * (2 * n - 1) / (2 * n * (2 * n + 1));


            term = term * coefficient * x * x;


            sum += term;

            System.out.printf("Слагаемое %d: %.10f (сумма: %.10f)%n", n, term, sum);
            n++;

            
            if (n > 1000) {
                System.out.println("Предупреждение: достигнуто максимальное количество итераций");
                break;
            }
        }

        System.out.println("Всего итераций: " + (n - 1));
        return sum;
    }
}