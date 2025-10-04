package main

import (
    "fmt"
    "sync"
)

func f(x float64) float64 {
    return 2*x*x + 3*x + 0.5
}

func integrar(n int, a, b float64, ch chan float64) {
    h := (b - a) / float64(n)
    suma := 0.0
    for i := 1; i < n; i++ {
        suma += f(a + float64(i)*h)
    }
    ch <- (h / 2) * (f(a) + 2*suma + f(b))
}

func main() {
    var anterior float64 = -1
    n := 1
    pool := make(chan struct{}, 4) // Pool de 4 workers
    for {
        pool <- struct{}{}
        ch := make(chan float64)
        go func(n int) {
            integrar(n, 2, 20, ch)
            <-pool
        }(n)
        resultado := <-ch
        fmt.Printf("n=%d -> %.5f\n", n, resultado)
        if resultado == anterior {
            break
        }
        anterior = resultado
        n++
    }
}
