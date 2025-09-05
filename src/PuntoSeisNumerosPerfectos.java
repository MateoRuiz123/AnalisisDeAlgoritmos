import java.util.Scanner;


/**
 * Punto 6 - Números Perfectos
 * <p>
 * Un número es perfecto si la suma de sus divisores propios (excepto el mismo)
 * es igual al número.
 * Ejemplo: 6 -> divisores: 1, 2, 3, (6 no cuenta), 1+2+3 = 6
 */
public class PuntoSeisNumerosPerfectos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese límite superior n: ");
        int n = sc.nextInt();

        long inicio = System.nanoTime();

        System.out.println("Números perfectos entre 1 y " + n + ":\n");

        int contadorFrecuencias = 0;
        int encontrados = 0;

        for (int i = 2; i <= n; i++) {
            int suma = 0;

            // Calcular la suma de los divisores propios de i
            for (int j = 1; j <= i / 2; j++) {
                contadorFrecuencias++;
                if (i % j == 0) {
                    suma += j;
                }
            }

            // Mostrar solo los perfectos con su suma
            if (suma == i) {
                System.out.println("Número: " + i + " → Suma de divisores propios = " + suma + " ✅ Perfecto");
                encontrados++;
            }
        }

        long fin = System.nanoTime();

        System.out.println("\n--- Análisis del algoritmo ---");
        System.out.println("a) Contador de frecuencias: " + contadorFrecuencias);
        System.out.println("   Orden de magnitud: O(n^2)");
        System.out.println("b) Tiempo de ejecución (ns): " + (fin - inicio));
        System.out.println("c) Se encontraron " + encontrados + " números perfectos.");
        System.out.println("d) Memoria aproximada usada: " + (n * 4) + " bytes."); // estimación
    }
}
