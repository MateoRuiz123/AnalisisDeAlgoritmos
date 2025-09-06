// File: PuntoNueveBurbuja.java

import java.util.Random;
import java.util.Scanner;

public class PuntoNueveBurbuja {

    private int[] vector;
    private int tamaño;
    private long contadorOperaciones;
    private long tiempoEjecucion;

    // Constructor
    public PuntoNueveBurbuja(int tamaño) {
        this.tamaño = tamaño;
        this.vector = new int[tamaño];
        this.contadorOperaciones = 0;
        this.tiempoEjecucion = 0;
    }

    // Llenar el vector con números aleatorios
    public void llenarVectorAleatorio() {
        Random random = new Random();
        for (int i = 0; i < tamaño; i++) {
            vector[i] = random.nextInt(1000); // valores entre 0 y 999
        }
        if (tamaño <= 20) {
            System.out.print("Vector inicial: ");
            mostrarVector();
        }
    }

    // Algoritmo burbuja con contador y tiempo
    public void sortVector() {
        contadorOperaciones = 0;
        long inicio = System.currentTimeMillis();

        int aux;
        for (int i = 0; i < tamaño - 1; i++) {
            contadorOperaciones++; // comparación bucle externo
            for (int j = i + 1; j < tamaño; j++) {
                contadorOperaciones++; // comparación bucle interno
                if (vector[i] > vector[j]) {
                    contadorOperaciones++; // comparación if
                    aux = vector[i];
                    vector[i] = vector[j];
                    vector[j] = aux;
                    contadorOperaciones += 3; // asignaciones
                }
            }
        }

        long fin = System.currentTimeMillis();
        tiempoEjecucion = fin - inicio;

        if (tamaño <= 20) {
            System.out.print("Vector ordenado: ");
            mostrarVector();
        }
    }

    // Mostrar vector
    public void mostrarVector() {
        System.out.print("[");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(vector[i]);
            if (i < tamaño - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Verificar si está ordenado
    public boolean estaOrdenado() {
        for (int i = 0; i < tamaño - 1; i++) {
            if (vector[i] > vector[i + 1]) return false;
        }
        return true;
    }

    // Getters
    public long getOperaciones() {
        return contadorOperaciones;
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    // MAIN
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PUNTO 9: BURBUJA ===");

        System.out.println("1. Prueba rápida (vector pequeño)");
        System.out.println("2. Medición rendimiento (vectores grandes)");
        System.out.print("Elige: ");
        int opcion = input.nextInt();

        if (opcion == 1) {
            System.out.print("\nTamaño del vector: ");
            int tamaño = input.nextInt();

            PuntoNueveBurbuja burbuja = new PuntoNueveBurbuja(tamaño);
            burbuja.llenarVectorAleatorio();
            burbuja.sortVector();

            System.out.println("\nTiempo: " + burbuja.getTiempoEjecucion() + " ms");
            System.out.println("Operaciones: " + burbuja.getOperaciones());
            System.out.println("¿Ordenado? " + (burbuja.estaOrdenado() ? "Sí ✅" : "No ❌"));

        } else {
            int[] tamaños = {1_000, 10_000, 100_000};
            System.out.printf("%-10s %-15s %-15s\n", "Tamaño", "Tiempo (ms)", "Operaciones");
            System.out.println("─".repeat(45));

            for (int n : tamaños) {
                PuntoNueveBurbuja burbuja = new PuntoNueveBurbuja(n);
                burbuja.llenarVectorAleatorio();
                burbuja.sortVector();

                System.out.printf("%-10d %-15d %-15d\n",
                        n, burbuja.getTiempoEjecucion(), burbuja.getOperaciones());
            }
        }

        // ANÁLISIS TEÓRICO
        System.out.println("\n=== ANÁLISIS TEÓRICO ===");
        System.out.println("a) Frecuencias: ~ n(n-1)/2 comparaciones, hasta mismo # de intercambios.");
        System.out.println("b) Orden Big O: O(n^2) en mejor, peor y promedio.");
        System.out.println("c) Memoria: O(1) adicional, solo variable auxiliar.");
        System.out.println("d) Rendimiento: crece cuadráticamente con n, muy lento para n grandes.");

        input.close();
    }
}
