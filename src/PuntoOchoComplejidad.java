import java.util.*;

/**
 * Punto 8 - Algoritmos
 * --------------------
 * Esta clase compara gráficamente diferentes órdenes de complejidad computacional
 * para valores grandes de n (10^3, 10^4, 10^5, etc.).
 * <p>
 * Se muestran las funciones:
 * - O(1)
 * - O(log n)
 * - O(n)
 * - O(n^2)
 * - O(2^n)
 * <p>
 * Objetivo:
 * - Demostrar gráficamente que O(1) < O(log n) < O(n) < O(n^2) < O(2^n)
 * cuando n tiende a infinito.
 */

public class PuntoOchoComplejidad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el valor máximo de n (ejemplo 20): ");
        int maxN = sc.nextInt();

        System.out.println("\n--- Comparación de Complejidad ---");
        System.out.printf("%-8s %-8s %-10s %-10s %-15s %-20s\n",
                "n", "O(1)", "O(log n)", "O(n)", "O(n^2)", "O(2^n)");
        System.out.println("-------------------------------------------------------------------------------");

        for (int n = 1; n <= maxN; n++) {
            long O1 = 1;
            double Olog = Math.log(n) / Math.log(2); // log base 2
            long On = n;
            long On2 = (long) Math.pow(n, 2);
            long O2n;

            // Evitar overflow cuando 2^n se vuelve muy grande
            if (n <= 30) {
                O2n = (long) Math.pow(2, n);
            } else {
                O2n = Long.MAX_VALUE; // aproximación para números muy grandes
            }

            System.out.printf("%-8d %-8d %-10.2f %-10d %-15d %-20s\n",
                    n, O1, Olog, On, On2, (O2n == Long.MAX_VALUE ? "Muy grande" : O2n));
        }

        System.out.println("\n--- Conclusión ---");
        System.out.println("Se observa que: O(1) < O(log n) < O(n) < O(n^2) < O(2^n).");
        System.out.println("El crecimiento exponencial O(2^n) se dispara muy rápido incluso con n pequeño.");
    }
}
