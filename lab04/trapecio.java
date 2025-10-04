import java.util.ArrayList;
import java.util.List;

class Funcion {
    public double evaluar(double x) {
        return 2 * x * x + 3 * x + 0.5;
    }
}

class Integrador extends Thread {
    private int n;
    private double a, b;
    private double resultado;
    private Funcion f;

    public Integrador(int n, double a, double b, Funcion f) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.f = f;
    }

    public void run() {
        double h = (b - a) / n;
        double suma = 0.0;
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += f.evaluar(x);
        }
        resultado = (h / 2) * (f.evaluar(a) + 2 * suma + f.evaluar(b));
    }

    public double getResultado() {
        return resultado;
    }
}

public class trapecio {
    public static void main(String[] args) throws InterruptedException {
        Funcion f = new Funcion();
        double anterior = -1;
        int n = 1;

        while (true) {
            Integrador hilo = new Integrador(n, 2, 20, f);
            hilo.start();
            hilo.join();
            double actual = hilo.getResultado();
            System.out.println("n=" + n + " -> " + actual);
            if (actual == anterior)
                break;
            anterior = actual;
            n++;
        }
    }
}
