import java.util.concurrent.*;

class Funcion {
    public double evaluar(double x) { return 2 * x * x + 3 * x + 0.5; }
}

class TareaTrapecio implements Callable<Double> {
    private int n; private double a, b; private Funcion f;
    public TareaTrapecio(int n, double a, double b, Funcion f) {
        this.n = n; this.a = a; this.b = b; this.f = f;
    }
    public Double call() {
        double h = (b - a) / n;
        double suma = 0.0;
        for (int i = 1; i < n; i++) suma += f.evaluar(a + i * h);
        return (h / 2) * (f.evaluar(a) + 2 * suma + f.evaluar(b));
    }
}

public class TrapecioPool {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Funcion f = new Funcion();
        double anterior = -1; int n = 1;

        while (true) {
            Future<Double> resultado = pool.submit(new TareaTrapecio(n, 2, 20, f));
            double actual = resultado.get();
            System.out.println("n=" + n + " -> " + actual);
            if (actual == anterior) break;
            anterior = actual;
            n++;
        }
        pool.shutdown();
    }
}
