import java.util.Scanner;

public class PuntoTresTruncarDecimal {

        /*
        Punto 3:

        a. Tarea del algoritmo:
           - El algoritmo busca el valor máximo en un arreglo de n elementos.
           - Inicializa la variable 'mayor' con el primer elemento del arreglo.
           - Recorre el arreglo desde la posición 2 hasta n.
           - En cada iteración compara si el elemento actual A[i] es mayor que 'mayor'.
           - Si lo es, actualiza 'mayor' con el valor de A[i].
           - Al final, devuelve 'mayor' como el valor máximo.

        b. Contador de frecuencias y orden de magnitud:
           - Asignación inicial (mayor ← A[1]): 1 vez.
           - Comparaciones dentro del ciclo: (n - 1) veces.
           - Asignaciones dentro del ciclo (en el peor caso): (n - 1) veces.
           - Devolver mayor: 1 vez.
           - Total en el peor caso: 2n - 1 operaciones.

           El término dominante es n, por lo que el orden de magnitud del algoritmo es:
           O(n).
        */

    public static double truncarDecimales(double numeroOriginal, int numeroDecimales) {
        double diferencia, decimalesTruncados, decimalesComoEntero, divisionDecimales, numeroPositivo;
        int ultimoDigito, divisionEntera;

        int contadorOperaciones = 0;

        numeroPositivo = Math.abs(numeroOriginal);
        contadorOperaciones++;

        diferencia = numeroPositivo - (int) numeroPositivo;
        contadorOperaciones += 2;

        decimalesTruncados = (int) (diferencia * Math.pow(10, numeroDecimales + 1)) / Math.pow(10, numeroDecimales + 1);
        contadorOperaciones += 5;

        decimalesComoEntero = decimalesTruncados * Math.pow(10, numeroDecimales + 1);
        contadorOperaciones += 2;

        ultimoDigito = (int) decimalesComoEntero % 10;
        contadorOperaciones += 2;

        divisionEntera = (int) decimalesComoEntero / 10;
        contadorOperaciones += 2;

        divisionDecimales = divisionEntera / Math.pow(10, numeroDecimales);
        contadorOperaciones += 2;

        // Regla de redondeo: si el último dígito es ≥ 5, redondea hacia arriba
        if (ultimoDigito >= 5) {
            divisionDecimales += Math.pow(10, -numeroDecimales);
            contadorOperaciones += 2;
        }
        contadorOperaciones++;

        // Restaurar el signo original
        double resultado = numeroOriginal >= 0 ? (int) numeroPositivo + divisionDecimales : -((int) numeroPositivo + divisionDecimales);
        contadorOperaciones += 3;

        // Mostrar análisis de frecuencias
        System.out.println("Contador de frecuencias para esta ejecución: " + contadorOperaciones + " operaciones");

        return resultado;
    }

    public static void main(String[] args) {
        Scanner lectorEntrada = new Scanner(System.in);
        System.out.println("\n=== PRUEBAS INTERACTIVAS ===");

        char continuar = 0;
        do {
            try {
                // Solicitar datos al usuario
                System.out.print("\nIngrese el número decimal que desea truncar, ejemplo: 12,345: ");
                double numeroAtrunclear = lectorEntrada.nextDouble();

                System.out.print("Ingrese la cantidad de decimales a mantener: ");
                int cantidadDecimales = lectorEntrada.nextInt();

                if (cantidadDecimales < 0) {
                    System.out.println("Error: La cantidad de decimales no puede ser negativa.");
                    continue;
                }

                // Ejecutar el algoritmo
                System.out.println("\n--- EJECUTANDO ALGORITMO ---");
                double numeroTruncado = truncarDecimales(numeroAtrunclear, cantidadDecimales);

                // Mostrar resultados
                System.out.println("\n--- RESULTADOS ---");
                System.out.printf("Número original: %.10f%n", numeroAtrunclear);
                System.out.printf("Número truncado/redondeado a %d decimales: %." + cantidadDecimales + "f%n", cantidadDecimales, numeroTruncado);

                // Comparar con método nativo de Java
                double resultadoNativo = Math.round(numeroAtrunclear * Math.pow(10, cantidadDecimales)) / Math.pow(10, cantidadDecimales);
                System.out.printf("Comparación con Math.round(): %." + cantidadDecimales + "f%n", resultadoNativo);

                System.out.print("\n¿Desea probar con otro número? (s/n): ");
                continuar = lectorEntrada.next().toLowerCase().charAt(0);

            } catch (Exception e) {
                System.out.println("Error: Por favor ingrese un número válido. Ejemplo: 12,345");
                lectorEntrada.nextLine();
                continuar = 's';
            }

        } while (continuar == 's' || continuar == 'y');
        lectorEntrada.close();
    }
}