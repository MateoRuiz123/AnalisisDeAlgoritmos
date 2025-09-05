public class PuntoDosInduccionMatematica {
    /**
     * Punto 2:
     * Demostración por inducción de que 2n + 2 siempre es par.
     * <p>
     * Base: n = 0 → 2*0 + 2 = 2 (par).
     * HI: Supongamos que 2k + 2 es par.
     * Paso inductivo: 2(k+1) + 2 = 2k + 4 = (2k + 2) + 2.
     * Como 2k+2 es par (HI) y 2 es par, la suma es par.
     */

    public static boolean esSiemprePar(int n) {
        int valor = 2 * n + 2;
        return valor % 2 == 0;
    }

    public static void main(String[] args) {
        // --- Punto 2: Comprobar que 2n + 2 siempre es par ---
        System.out.println("Punto 2: Verificación de que 2n+2 siempre es par");
        for (int n = -2; n <= 3; n++) {
            System.out.println("n = " + n + " -> 2n+2 = " + (2 * n + 2) + " -> " + (esSiemprePar(n) ? "Par" : "Impar"));
        }
    }
}
