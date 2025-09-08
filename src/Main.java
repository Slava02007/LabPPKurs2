import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = 0;

        while (true) {
            System.out.print("Введите x (-1 < x < 1): ");
            x = scanner.nextDouble();
            if (x > -1 && x < 1) break;
            System.out.println("Ошибка: x должен быть в диапазоне (-1, 1)");
        }

        System.out.print("Введите k: ");
        int k = scanner.nextInt();

        double epsilon = Math.pow(10, -k);
        double taylorResult = calculateArcsinTaylor(x, epsilon);
        double exactResult = Math.asin(x);

        System.out.println("\nРезультаты:");
        System.out.printf("Ряд Тейлора: %.3f%n", taylorResult);
        System.out.printf("Arcsin:   %.3f%n", exactResult);
        System.out.printf("Разница:     %.6f%n", Math.abs(taylorResult - exactResult));

        scanner.close();
    }

    private static double calculateArcsinTaylor(double x, double epsilon) {
        double sum = x;
        double term = x;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            double coefficient = (double) (2 * n - 1) * (2 * n - 1) / (2 * n * (2 * n + 1));
            term = term * coefficient * x * x;
            sum += term;
            n++;

            if (n > 10000) break;
        }

        return sum;
    }
}