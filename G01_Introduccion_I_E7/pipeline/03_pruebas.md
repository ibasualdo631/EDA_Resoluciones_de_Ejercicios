# Matriz de casos de prueba

## 1. Objetivo

Validar que el programa `Ejercicio7.java` cumple con la consigna del ejercicio: mostrar una secuencia numérica usando un ciclo `while`, con una condición de corte clara y una actualización del contador que impide el bucle infinito.

## 2. Código bajo prueba

```java
public class Ejercicio7 {
    public static void main(String[] args) {
        int limite = 10;
        int contador = inicializarContador();

        mostrarNumerosHasta(limite, contador);
    }

    public static int inicializarContador() {
        return 1;
    }

    public static void mostrarNumerosHasta(int limite, int contador) {
        while (contador <= limite) {
            System.out.println(contador);
            contador++;
        }
    }
}
```

## 3. Matriz de casos de prueba

| ID | Caso | Entrada / Estado inicial | Resultado esperado | Evaluación |
|----|------|--------------------------|--------------------|------------|
| TC-01 | Ejecución normal | `limite = 10`, `contador = 1` | Se imprime `1, 2, 3, ..., 10` en consola | Correcto |
| TC-02 | Condición de corte | Al llegar a `contador = 11` | El ciclo termina sin imprimir más números | Correcto |
| TC-03 | Incremento del contador | Cada iteración | El valor aumenta en 1 | Correcto |
| TC-04 | Prevención de bucle infinito | La lógica no modifica `contador` | El programa no quedaría atrapado en un ciclo sin fin | Cumple por la instrucción `contador++` |
| TC-05 | Valor inicial válido | `contador = 1` | El primer valor impreso es 1 | Correcto |
| TC-06 | Límite superior | `limite = 10` | Se imprime exactamente 10 valores | Correcto |
| TC-07 | Método `inicializarContador` | Llamada al método | Devuelve `1` | Correcto |
| TC-08 | Método `mostrarNumerosHasta` | `mostrarNumerosHasta(10, 1)` | Ejecuta la secuencia completa hasta el límite | Correcto |

## 4. Casos de borde

### Caso borde 1: contador igual al límite
Entrada:

```java
contador = 10
limite = 10
```

Salida esperada:

```text
10
```

Se imprime el valor final porque la condición `contador <= limite` sigue siendo verdadera.

### Caso borde 2: contador mayor al límite
Entrada:

```java
contador = 11
limite = 10
```

Resultado esperado:

- el ciclo no se ejecuta
- no se imprime nada

### Caso borde 3: contador inicial menor que 1
Si se modifica la inicialización a un valor menor, por ejemplo `0`, la secuencia sería:

```text
0
1
2
...
10
```

Esto también responde correctamente a la lógica del `while` siempre que la condición se mantenga consistente.

## 5. Resultado observado

Se compiló y ejecutó el programa con éxito. La salida obtenida fue:

```text
1
2
3
4
5
6
7
8
9
10
```

Esto confirma que:

- el ciclo se ejecuta la cantidad correcta de veces
- la condición de corte es válida
- el contador avanza correctamente
- la secuencia cumple con la finalidad del ejercicio

## 6. Conclusión

El programa cumple con los requisitos funcionales del ejercicio. La prueba principal confirma que la solución produce la secuencia esperada en consola y que el uso del `while` está implementado correctamente.
