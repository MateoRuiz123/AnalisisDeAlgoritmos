import java.util.Random;
import java.util.Scanner;

/**
 * Punto 7 - Simulación de registro de temperaturas
 * <p>
 * Se generan temperaturas aleatorias (entre -10 y 45 grados)
 * para 3 años. Se calcula el promedio anual y se analiza el algoritmo.
 */
public class PuntoSieteTemperaturas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Leer cantidad de temperaturas por año
        System.out.print("Ingrese cantidad de registros por año (n): ");
        int n = sc.nextInt();

        int[][] temperaturas = new int[3][n];

        // Generar datos aleatorios
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                temperaturas[i][j] = rand.nextInt(56) - 10; // [-10, 45]
            }
        }

        long inicio = System.nanoTime();

        // Calcular promedios
        double[] promedios = new double[3];
        int contadorFrecuencias = 0;

        for (int i = 0; i < 3; i++) {
            long suma = 0;
            for (int j = 0; j < n; j++) {
                suma += temperaturas[i][j];
                contadorFrecuencias++; // contar operaciones principales
            }
            promedios[i] = (double) suma / n;
        }

        long fin = System.nanoTime();

        // Mostrar resultados
        System.out.println("\n--- Resultados ---");
        for (int i = 0; i < 3; i++) {
            System.out.println("Promedio año " + (i + 1) + ": " + promedios[i] + " °C");
        }

        // Análisis
        System.out.println("\n--- Análisis del algoritmo ---");
        System.out.println("a) Tiempo de ejecución (ns): " + (fin - inicio));
        System.out.println("b) Contador de frecuencias: " + contadorFrecuencias);
        System.out.println("   Orden de magnitud: O(n)"); // recorrido lineal
        System.out.println("c) El cálculo del promedio se considera operación elemental (división).");
        System.out.println("d) Memoria usada: " + (3 * n * 4) + " bytes (aprox).");
    }
}
