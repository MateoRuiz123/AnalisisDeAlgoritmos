import java.util.Scanner;

public class PuntoCuatroSeriesTaylor {

    /**
     * PUNTO 4: SERIES DE TAYLOR PARA SENO Y COSENO
     * <p>
     * Fórmulas implementadas:
     * sen(x) = x - x³/3! + x⁵/5! - x⁷/7! + ...
     * cos(x) = 1 - x²/2! + x⁴/4! - x⁶/6! + ...
     */

    // Contador global para análisis de frecuencias
    private static int contadorOperacionesSeno = 0;
    private static int contadorOperacionesCoseno = 0;

    public static double calcularFactorial(int numero) {
        if (numero == 0 || numero == 1) {
            return 1.0;
        }

        double resultado = 1.0;
        for (int i = 2; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static double calcularSenoTaylor(double valorX, int numeroTerminos) {
        contadorOperacionesSeno = 0;
        double resultadoSeno = 0.0;

        System.out.println("\n--- CALCULANDO SENO CON SERIE DE TAYLOR ---");
        System.out.println("sen(x) = x - x³/3! + x⁵/5! - x⁷/7! + ...");

        for (int i = 0; i < numeroTerminos; i++) {
            contadorOperacionesSeno++;

            // Calcular exponente: 2*i + 1 (1, 3, 5, 7, ...)
            int exponente = 2 * i + 1;
            contadorOperacionesSeno += 2;

            // Calcular potencia x^exponente
            double potencia = Math.pow(valorX, exponente);
            contadorOperacionesSeno++;

            // Calcular factorial del exponente
            double factorial = calcularFactorial(exponente);
            contadorOperacionesSeno += exponente;

            // Calcular término de la serie
            double termino = potencia / factorial;
            contadorOperacionesSeno++;

            if (i % 2 == 0) {
                resultadoSeno += termino;
                System.out.printf("Término %d: +%.8f\n", i + 1, termino);
            } else {
                resultadoSeno -= termino;
                System.out.printf("Término %d: -%.8f\n", i + 1, termino);
            }
            contadorOperacionesSeno += 2; // Operación de suma/resta y comparación
        }

        System.out.printf("Resultado seno: %.8f\n", resultadoSeno);
        return resultadoSeno;
    }

    public static double calcularCosenoTaylor(double valorX, int numeroTerminos) {
        contadorOperacionesCoseno = 0;
        double resultadoCoseno = 1.0; // cos(x) comienza en 1

        System.out.println("\n--- CALCULANDO COSENO CON SERIE DE TAYLOR ---");
        System.out.println("cos(x) = 1 - x²/2! + x⁴/4! - x⁶/6! + ...");
        System.out.println("Término 1: +1.00000000");

        for (int i = 1; i < numeroTerminos; i++) {
            contadorOperacionesCoseno++;

            // Calcular exponente: 2*i (2, 4, 6, 8, ...)
            int exponente = 2 * i;
            contadorOperacionesCoseno++;

            // Calcular potencia x^exponente
            double potencia = Math.pow(valorX, exponente);
            contadorOperacionesCoseno++;

            // Calcular factorial del exponente
            double factorial = calcularFactorial(exponente);
            contadorOperacionesCoseno += exponente;

            // Calcular término de la serie
            double termino = potencia / factorial;
            contadorOperacionesCoseno++;

            // Aplicar signo alternado (1 - + - + ...)
            if (i % 2 == 1) {
                resultadoCoseno -= termino;
                System.out.printf("Término %d: -%.8f\n", i + 1, termino);
            } else {
                resultadoCoseno += termino;
                System.out.printf("Término %d: +%.8f\n", i + 1, termino);
            }
            contadorOperacionesCoseno += 2;
        }

        System.out.printf("Resultado coseno: %.8f\n", resultadoCoseno);
        return resultadoCoseno;
    }

    /**
     * Calcula el porcentaje de error entre dos valores
     */
    public static double calcularPorcentajeError(double valorCalculado, double valorReal) {
        if (valorReal == 0) {
            return valorCalculado == 0 ? 0 : 100;
        }
        return Math.abs((valorCalculado - valorReal) / valorReal) * 100;
    }

    public static void mostrarAnalisisComplejidad(int numeroTerminos) {
        System.out.println("\n=== ANÁLISIS DE COMPLEJIDAD ===");

        System.out.println("\nCONTADOR DE FRECUENCIAS:");
        System.out.println("- Operaciones para seno: " + contadorOperacionesSeno);
        System.out.println("- Operaciones para coseno: " + contadorOperacionesCoseno);

        // Cálculo teórico vs real
        int operacionesTeoricasSeno = numeroTerminos * (numeroTerminos + 1) / 2 * 5;
        int operacionesTeoricasCoseno = (numeroTerminos - 1) * numeroTerminos / 2 * 5;

        System.out.println("\nCOMPARACIÓN TEÓRICA vs REAL:");
        System.out.printf("Seno - Teórico: ~%d, Real: %d\n", operacionesTeoricasSeno, contadorOperacionesSeno);
        System.out.printf("Coseno - Teórico: ~%d, Real: %d\n", operacionesTeoricasCoseno, contadorOperacionesCoseno);
    }

    public static void main(String[] args) {
        Scanner lectorEntrada = new Scanner(System.in);
        System.out.println("=== PUNTO 4: SERIES DE TAYLOR PARA SENO Y COSENO ===");

        try {
            System.out.print("\nIngrese el valor de x en radianes (entre 0 y 2π): ");
            double valorX = lectorEntrada.nextDouble();

            if (valorX < 0 || valorX > 2 * Math.PI) {
                System.out.println("Advertencia: x está fuera del rango recomendado [0, 2π]");
                System.out.println("Los resultados pueden ser menos precisos.");
            }

            System.out.print("Ingrese el número de términos n (recomendado: 10-30): ");
            int numeroTerminos = lectorEntrada.nextInt();

            if (numeroTerminos <= 0) {
                System.out.println("Error: El número de términos debe ser positivo.");
                return;
            }

            // Cálculos con series de Taylor
            double senoCalculado = calcularSenoTaylor(valorX, numeroTerminos);
            double cosenoCalculado = calcularCosenoTaylor(valorX, numeroTerminos);

            // Valores reales de Java
            double senoReal = Math.sin(valorX);
            double cosenoReal = Math.cos(valorX);

            // Análisis de errores
            double errorSeno = calcularPorcentajeError(senoCalculado, senoReal);
            double errorCoseno = calcularPorcentajeError(cosenoCalculado, cosenoReal);

            // Mostrar resultados finales
            System.out.println("\n=== RESULTADOS FINALES ===");
            System.out.printf("Valor de x: %.6f radianes (%.2f grados)\n", valorX, Math.toDegrees(valorX));
            System.out.printf("Número de términos utilizados: %d\n", numeroTerminos);

            System.out.println("\nCOMPARACIÓN DE RESULTADOS:");
            System.out.printf("%-20s %-15s %-15s %-15s\n", "Función", "Serie Taylor", "Java Math", "Error %");
            System.out.println("─".repeat(65));
            System.out.printf("%-20s %-15.8f %-15.8f %-15.6f\n", "sen(x)", senoCalculado, senoReal, errorSeno);
            System.out.printf("%-20s %-15.8f %-15.8f %-15.6f\n", "cos(x)", cosenoCalculado, cosenoReal, errorCoseno);

            // Mostrar análisis de complejidad
            mostrarAnalisisComplejidad(numeroTerminos);

        } catch (Exception e) {
            System.out.println("Error: Por favor ingrese valores numéricos válidos.");
        } finally {
            lectorEntrada.close();
        }
    }
}