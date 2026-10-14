# Pruebas — Lista enlazada genérica

## Alcance

Verificar compilación con tipado genérico estricto y el comportamiento de inserción y recorrido con valores primitivos envueltos y objetos personalizados, además de los casos límite de la lista.

## Ejecución

Desde el directorio del ejercicio:

```powershell
javac -Xlint:all -Werror -d $env:TEMP\g09-lista-generica-classes .\ListaEnlazada.java
java -cp $env:TEMP\g09-lista-generica-classes ListaEnlazada
```

Resultado observado: compilación exitosa con `-Werror` (ninguna advertencia) y ejecución exitosa del `main`; todas las verificaciones finalizaron con `OK`.

## Casos verificados

| Tipo | Caso | Resultado |
|---|---|---|
| `Integer` | Lista vacía, eliminar desde lista vacía | Correcto |
| `Integer` | Insertar al inicio y al final; recorrido con orden `10 -> 20 -> 30` | Correcto |
| `Integer` | Tamaño; eliminación en medio, cabeza y último nodo | Correcto |
| `Integer` | Queda vacía con tamaño cero después de eliminar todos los elementos | Correcto |
| `String` | Insertar en ambos extremos; recorrido con orden `hola -> mundo -> !` | Correcto |
| `String` | Eliminar por igualdad, dato inexistente y elemento `null` | Correcto |
| `Alumno` | Insertar objetos personalizados distintos al inicio y al final | Correcto |
| `Alumno` | Salida exacta del recorrido conserva representación y orden: `Ana (legajo 101) -> Luis (legajo 202)` | Correcto |
| `Alumno` | Eliminar usando igualdad lógica por legajo; no elimina un legajo ausente | Correcto |
| `Alumno` | Recorrido y eliminación de una lista con un único objeto | Correcto |

El tipo `Alumno` no implementa interfaces de ordenamiento ni operaciones aritméticas. Sus datos y su orden se comprueban comparando la salida completa de `imprimir()` con el resultado esperado.

## Conclusión

El archivo compila sin errores ni advertencias bajo `javac -Xlint:all -Werror`. Las aserciones del `main` confirman inserción y recorrido correctos para `Integer`, `String` y objetos `Alumno`, sin que la lista necesite interpretar el tipo genérico para enlazar o visitar nodos.