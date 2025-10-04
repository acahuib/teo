#include <iostream>
#include <thread>
#include <vector>
#include <cmath>
#include <mutex>
using namespace std;

class Funcion {
public:
    double evaluar(double x) {
        return 2 * x * x + 3 * x + 0.5;
    }
};

class Integrador {
    int n;
    double a, b, resultado;
    Funcion f;
public:
    Integrador(int n, double a, double b): n(n), a(a), b(b), resultado(0) {}
    void calcular() {
        double h = (b - a) / n;
        double suma = 0.0;
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += f.evaluar(x);
        }
        resultado = (h / 2) * (f.evaluar(a) + 2 * suma + f.evaluar(b));
    }
    double getResultado() { return resultado; }
};

int main() {
    Funcion f;
    double anterior = -1;
    int n = 1;
    while (true) {
        Integrador* integ = new Integrador(n, 2, 20);
        thread t(&Integrador::calcular, integ);
        t.join();
        double actual = integ->getResultado();
        cout << "n=" << n << " -> " << actual << endl;
        if (actual == anterior) break;
        anterior = actual;
        n++;
        delete integ;
    }
    return 0;
}
