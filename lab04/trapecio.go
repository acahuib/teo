package main

import (
    "fmt"
    "sync"
)

func f(x float64) float64 {
    return 2*x*x + 3*x + 0.5
}

func integrar(n int, a, b float64, wg *sync.WaitGroup, resultado *float64) {
    defer wg.Done()
    h := (b - a) / float64(n)
    suma := 0.0
    for i := 1; i < n; i++ {
        x := a + float64(i)*h
        suma += f(x)
    }
    *resultado = (h / 2) * (f(a) + 2*suma + f(b))
}

func main() {
    anterior := -1.0
    n := 1
    for {
        var wg sync.WaitGroup
        var res float64
        wg.Add(1)
        go integrar(n, 2, 20, &wg, &res)
        wg.Wait()
        fmt.Printf("n=%d -> %.5f\n", n, res)
        if res == anterior {
            break
        }
        anterior = res
        n++
    }
}
