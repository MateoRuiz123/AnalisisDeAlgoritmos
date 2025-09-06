// File: PuntoDiez.java
import java.util.Scanner;
import java.util.Random;

public class PuntoDiezFunciones {

    // Algoritmo 2
    public static void algoritmo2(int n) {
        long operaciones = 0;
        long inicio = System.currentTimeMillis();

        int c, j, num;
        Random rand = new Random();

        for (int i = 1; i <= n; i++) {
            j = i;
            c = 0;

            while (j > 1) {
                num = rand.nextInt(10); // simula la lectura de num
                c = c + num * (j * j);
                j = j / 2;

                operaciones += 3; // asignación + multiplicación + suma
            }
            operaciones++;
            System.out.println("Valor de c: " + c);
        }

        long fin = System.currentTimeMillis();
        System.out.println("Algoritmo 2 -> n=" + n +
                " | Tiempo=" + (fin - inicio) + " ms" +
                " | Operaciones=" + operaciones);
    }

    // Algoritmo 3
    public static void algoritmo3(int hh, int mm, int ss) {
        long operaciones = 0;
        long inicio = System.currentTimeMillis();

        int h = 0, m, s;

        while (h < hh) {
            m = 0;
            while (m < mm) {
                s = 0;
                while (s < ss) {
                    operaciones++;
                    System.out.println(h + ":" + m + ":" + s);
                    s++;
                }
                m++;
            }
            h++;
        }

        long fin = System.currentTimeMillis();
        System.out.println("Algoritmo 3 -> hh=" + hh + " mm=" + mm + " ss=" + ss +
                " | Tiempo=" + (fin - inicio) + " ms" +
                " | Operaciones=" + operaciones);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== PUNTO 10: Análisis de Algoritmos ===");
        System.out.println("1. Ejecutar Algoritmo 2 (O(n log n))");
        System.out.println("2. Ejecutar Algoritmo 3 (O(hh*mm*ss))");
        System.out.println("3. Ejecutar pruebas de rendimiento");
        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        if (opcion == 1) {
            System.out.print("Ingresa n: ");
            int n = sc.nextInt();
            algoritmo2(n);

        } else if (opcion == 2) {
            System.out.print("Ingresa hh: ");
            int hh = sc.nextInt();
            System.out.print("Ingresa mm: ");
            int mm = sc.nextInt();
            System.out.print("Ingresa ss: ");
            int ss = sc.nextInt();
            algoritmo3(hh, mm, ss);

        } else if (opcion == 3) {
            System.out.println("\n=== PRUEBAS ALGORITMO 2 ===");
            int[] pruebas2 = {1000, 5000, 10000, 20000};
            for (int n : pruebas2) {
                algoritmo2(n);
            }

            System.out.println("\n=== PRUEBAS ALGORITMO 3 ===");
            int[][] pruebas3 = {
                    {5, 50, 50},
                    {10, 50, 50},
                    {20, 50, 50}
            };
            for (int[] caso : pruebas3) {
                algoritmo3(caso[0], caso[1], caso[2]);
            }
        }
        sc.close();
    }
}
