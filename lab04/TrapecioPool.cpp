#include <iostream>
#include <future>
#include <cmath>
using namespace std;

double f(double x) { return 2 * x * x + 3 * x + 0.5; }

double integrar(int n, double a, double b) {
    double h = (b - a) / n;
    double suma = 0.0;
    for (int i = 1; i < n; i++) {
        double x = a + i * h;
        suma += f(x);
    }
    return (h / 2) * (f(a) + 2 * suma + f(b));
}

int main() {
    double anterior = -1;
    int n = 1;
    while (true) {
        future<double> fut = async(launch::async, integrar, n, 2.0, 20.0);
        double actual = fut.get();
        cout << "n=" << n << " -> " << actual << endl;
        if (actual == anterior) break;
        anterior = actual;
        n++;
    }
    return 0;
}
