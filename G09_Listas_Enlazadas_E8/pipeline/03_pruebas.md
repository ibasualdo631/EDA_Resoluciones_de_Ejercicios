# Pruebas — `contarOcurrencias`

## Ejecución

Se compiló `ListaEnlazada.java` con `javac` y se ejecutó su método `main`. Los casos se validan comparando el resultado real con el esperado; cualquier discrepancia lanza una excepción.

## Casos y resultados

| Caso | Lista | Valor buscado | Esperado | Resultado |
|---|---|---:|---:|---|
| Lista vacía | `[]` | `10` | `0` | Pasó |
| Lista con un elemento presente | `[42]` | `42` | `1` | Pasó |
| Lista con un elemento ausente | `[42]` | `7` | `0` | Pasó |
| Valor repetido | `[10, 20, 10, 30, 10]` | `10` | `3` | Pasó |
| Valor presente una vez | `[10, 20, 10, 30, 10]` | `20` | `1` | Pasó |
| Valor ausente | `[10, 20, 10, 30, 10]` | `99` | `0` | Pasó |

## Resultado general

**Aprobado:** los seis casos devolvieron el conteo esperado; la ejecución imprimió `Todas las pruebas de contarOcurrencias pasaron.`.