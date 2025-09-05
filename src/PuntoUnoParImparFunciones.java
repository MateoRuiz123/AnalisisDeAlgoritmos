public class PuntoUnoParImparFunciones {

    public static int f1(int n) {
        return 2 * n + 4;
    }

    public static int f2(int n) {
        return 4 * n + 5;
    }

    public static int f3(int n) {
        return 6 * n * n - 2 * n + 1;
    }

    // Método para verificar si un número es par o impar
    public static String parImpar(int valor) {
        return (valor % 2 == 0) ? "Par" : "Impar";
    }

    public static void main(String[] args) {
        int[] valores = {0, 1, 2, 3};

        for (int n : valores) {
            System.out.println("n = " + n);
            System.out.println("f1(n) = " + f1(n) + " -> " + parImpar(f1(n)));
            System.out.println("f2(n) = " + f2(n) + " -> " + parImpar(f2(n)));
            System.out.println("f3(n) = " + f3(n) + " -> " + parImpar(f3(n)));
            System.out.println("---------------------------");
        }
    }
}
