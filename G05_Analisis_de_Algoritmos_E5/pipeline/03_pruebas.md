# Pruebas — Ejercicio 5: Contar ocurrencias

Se compiló `ContadorOcurrencias.java` con `javac` y se ejecutó su método `main`. Los cuatro casos solicitados finalizaron sin errores y produjeron los resultados esperados.

| Caso | Vector | Objetivo | Esperado | Obtenido | Resultado |
|---|---|---:|---:|---:|---|
| Valor ausente | `{1, 2, 3}` | `9` | `0` | `0` | Aprobado |
| Valor repetido | `{4, 2, 4, 4, 1}` | `4` | `3` | `3` | Aprobado |
| Todos iguales | `{7, 7, 7, 7}` | `7` | `4` | `4` | Aprobado |
| Vector vacío | `{}` | `4` | `0` | `0` | Aprobado |

## Conclusión

El método no falla en estos casos. El recorrido completo cuenta tanto las apariciones separadas como las consecutivas; ante un vector vacío, el ciclo no se ejecuta y se devuelve el contador inicial (`0`). La validación de `null` lanza `IllegalArgumentException` antes de recorrer el vector.