Laboratorio 03 - Método del Trapecio (Programación Paralela)
Descripción del problema

Se requiere calcular el área bajo la curva de una función en el primer cuadrante, utilizando el método del trapecio por aproximación.
La función utilizada es:
f(x) = 2x² + 3x + ½
con los límites a = 2 y b = 20.

El programa debe:

Empezar los cálculos desde 1 trapecio hasta N trapecios.

Detenerse cuando el valor hallado se repita (no haya mejora en la aproximación).

Simular programación paralela mediante hilos (threads).

Implementar una versión con Pool de Threads para mejorar el rendimiento.

Lenguajes utilizados

Java (orientado a objetos, uso de Threads)

C++ (gestión de memoria dinámica y uso de Threads)

Go (goroutines y canales para concurrencia)

Estructura del repositorio
Lab03_Trapecio/
│
├── Trapecio.java
├── trapecio.cpp
├── trapecio.go
│
├── trapecioPool.java
├── trapecioPool.cpp
├── trapecioPool.go
│
└── README.md

Requisitos

Java JDK 17 o superior

Compilador C++ compatible con C++11 o superior

Go 1.19 o superior

Ejecución
En Java

Compilar y ejecutar:

javac Trapecio.java
java Trapecio


Versión con Pool:

javac trapecioPool.java
java trapecioPool

En C++

Compilar y ejecutar:

g++ trapecio.cpp -o trapecio -pthread
./trapecio


Versión con Pool:

g++ trapecioPool.cpp -o trapecioPool -pthread
./trapecioPool

En Go

Ejecutar directamente:

go run trapecio.go
go run trapecioPool.go

Consideraciones técnicas

La versión de C++ usa memoria dinámica (new y delete) para gestionar los arreglos de hilos.

Las versiones con Pool implementan un grupo fijo de hilos o goroutines que reutilizan los recursos.

Se detiene el cálculo cuando el resultado de la integral se mantiene estable entre iteraciones consecutivas.

Autor

Nombre: Anthony Ronaldo Cahui Benegas
Curso: Tecnologia de Objetos
Universidad: Universidad Nacional San Agustin
Año: 2025