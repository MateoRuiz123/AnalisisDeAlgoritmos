import java.util.Scanner;

/**
 * Clase para trabajar con polinomios utilizando listas ligadas
 * Punto 5 - Ejercicio propuesto
 */
public class PuntoCincoPolinomios {

    // Nodo de lista ligada para representar términos del polinomio
    static class Nodo {
        int coef;
        int exp;
        Nodo sig;

        Nodo(int coef, int exp) {
            this.coef = coef;
            this.exp = exp;
            this.sig = null;
        }
    }

    // Clase polinomio
    static class Polinomio {
        Nodo cabeza;

        void insertar(int coef, int exp) {
            Nodo nuevo = new Nodo(coef, exp);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo temp = cabeza;
                while (temp.sig != null) {
                    temp = temp.sig;
                }
                temp.sig = nuevo;
            }
        }

        // Mostrar polinomio
        void mostrar() {
            Nodo temp = cabeza;
            while (temp != null) {
                System.out.print(temp.coef + "x^" + temp.exp + " ");
                temp = temp.sig;
            }
            System.out.println();
        }

        // Sumar polinomios
        static Polinomio sumar(Polinomio p1, Polinomio p2) {
            Polinomio res = new Polinomio();
            Nodo a = p1.cabeza, b = p2.cabeza;
            while (a != null && b != null) {
                if (a.exp == b.exp) {
                    res.insertar(a.coef + b.coef, a.exp);
                    a = a.sig;
                    b = b.sig;
                } else if (a.exp > b.exp) {
                    res.insertar(a.coef, a.exp);
                    a = a.sig;
                } else {
                    res.insertar(b.coef, b.exp);
                    b = b.sig;
                }
            }
            while (a != null) {
                res.insertar(a.coef, a.exp);
                a = a.sig;
            }
            while (b != null) {
                res.insertar(b.coef, b.exp);
                b = b.sig;
            }
            return res;
        }

        // Restar polinomios
        static Polinomio restar(Polinomio p1, Polinomio p2) {
            Polinomio res = new Polinomio();
            Nodo a = p1.cabeza, b = p2.cabeza;
            while (a != null && b != null) {
                if (a.exp == b.exp) {
                    res.insertar(a.coef - b.coef, a.exp);
                    a = a.sig;
                    b = b.sig;
                } else if (a.exp > b.exp) {
                    res.insertar(a.coef, a.exp);
                    a = a.sig;
                } else {
                    res.insertar(-b.coef, b.exp);
                    b = b.sig;
                }
            }
            while (a != null) {
                res.insertar(a.coef, a.exp);
                a = a.sig;
            }
            while (b != null) {
                res.insertar(-b.coef, b.exp);
                b = b.sig;
            }
            return res;
        }

        // Derivar polinomio
        Polinomio derivar() {
            Polinomio res = new Polinomio();
            Nodo temp = cabeza;
            while (temp != null) {
                if (temp.exp != 0) {
                    res.insertar(temp.coef * temp.exp, temp.exp - 1);
                }
                temp = temp.sig;
            }
            return res;
        }

        // Evaluar polinomio en un valor x
        double evaluar(double x) {
            double res = 0;
            Nodo temp = cabeza;
            while (temp != null) {
                res += temp.coef * Math.pow(x, temp.exp);
                temp = temp.sig;
            }
            return res;
        }
    }

    // Calcular porcentaje de error usando serie de Taylor simulada (ejemplo con exp)
    static double porcentajeError(double real, double calculado) {
        return Math.abs((real - calculado) / real) * 100;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Polinomio f = new Polinomio();
        Polinomio g = new Polinomio();

        System.out.println("\n=== RESPUESTAS DEL PUNTO 5 ===");
        System.out.println("Ingrese cantidad de términos del polinomio f(x): ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Coeficiente: ");
            int coef = sc.nextInt();
            System.out.print("Exponente: ");
            int exp = sc.nextInt();
            f.insertar(coef, exp);
        }

        System.out.println("Ingrese cantidad de términos del polinomio g(x): ");
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.print("Coeficiente: ");
            int coef = sc.nextInt();
            System.out.print("Exponente: ");
            int exp = sc.nextInt();
            g.insertar(coef, exp);
        }

        System.out.println("\nf(x): ");
        f.mostrar();
        System.out.println("g(x): ");
        g.mostrar();

        Polinomio suma = Polinomio.sumar(f, g);
        System.out.println("\nSuma f(x)+g(x): ");
        suma.mostrar();

        Polinomio resta = Polinomio.restar(f, g);
        System.out.println("\nResta f(x)-g(x): ");
        resta.mostrar();

        Polinomio df = f.derivar();
        Polinomio dg = g.derivar();
        System.out.println("\nDerivada f'(x): ");
        df.mostrar();
        System.out.println("Derivada g'(x): ");
        dg.mostrar();

        System.out.print("\nIngrese el valor de a para evaluar f(a), g(a): ");
        double a = sc.nextDouble();
        double fa = f.evaluar(a);
        double ga = g.evaluar(a);
        double fpa = df.evaluar(a);
        double gpa = dg.evaluar(a);

        System.out.println("\nf(" + a + ") = " + fa);
        System.out.println("g(" + a + ") = " + ga);
        System.out.println("f'(" + a + ") = " + fpa);
        System.out.println("g'(" + a + ") = " + gpa);

        // ------------------ PUNTO FINAL ------------------
        System.out.println("\n--- Análisis del algoritmo ---");

        // a. Contador de frecuencias (aprox. depende de recorrer todos los nodos)
        int contadorFrecuencias = n + m;
        System.out.println("a) Contador de frecuencias: " + contadorFrecuencias);
        System.out.println("   Orden de magnitud: O(n)");

        // b. Uso de memoria (estimado: cada nodo tiene 3 enteros = ~12 bytes aprox.)
        int memoria = (n + m) * 12;
        System.out.println("b) Memoria aproximada usada: " + memoria + " bytes");

        // c. Porcentaje de error comparando con Math.exp (ejemplo genérico)
        double real = Math.exp(a); // valor real con librería
        double error = porcentajeError(real, fa);
        System.out.println("c) Porcentaje de error (ejemplo usando exp vs f(a)): " + error + "%");
    }
}
