# Pruebas: cola genérica

## Alcance

Se verificó la implementación enlazada `ColaEnlazada<T>` con una cola de nombres (`String`) y otra de objetos `Cliente`. Los casos ejecutables están en `Main.java` y cubren FIFO, consulta del frente, vaciado, reutilización y aislamiento entre instancias.

## Casos ejecutados

| ID | Caso | Pasos y resultado esperado | Resultado |
|---|---|---|---|
| T-01 | FIFO de nombres | Encolar `"Ana"`, `"Luis"` y `"Mora"`; confirmar que el frente es `"Ana"` y desencolar los nombres en ese mismo orden. | Aprobado |
| T-02 | Consulta no destructiva | Con los nombres encolados, consultar el frente y luego desencolar; el primer elemento debe continuar siendo `"Ana"`. | Aprobado |
| T-03 | FIFO de clientes | Encolar las instancias `clienteA`, `clienteB` y `clienteC`; comprobar por identidad que salen en el mismo orden. | Aprobado |
| T-04 | Aislamiento | Intercalar operaciones en `ColaEnlazada<String>` y `ColaEnlazada<Cliente>`; la actividad en una cola no debe modificar la otra. | Aprobado |
| T-05 | Operaciones con cola vacía | `desencolar()` y `frente()` deben lanzar `IllegalStateException`. | Aprobado |
| T-06 | Elemento único y vaciado | Encolar un elemento, retirarlo y comprobar que la cola queda vacía. | Aprobado |
| T-07 | Reutilización | Encolar y retirar un nuevo elemento tras vaciar la cola; debe recuperarse correctamente. | Aprobado |

## Verificación de tipos

El ejecutable compila usando directamente las declaraciones `ColaEnlazada<String>` y `ColaEnlazada<Cliente>`, y las operaciones de extracción devuelven el tipo correspondiente sin casts explícitos. La firma parametrizada de `encolar(T)` impone el tipo de cada instancia en compilación.

## Ejecución

Desde el directorio del ejercicio:

```powershell
$outDir = Join-Path $env:TEMP 'cola-generica-e04-test'
New-Item -ItemType Directory -Path $outDir -Force | Out-Null
javac -Xlint:all -d $outDir .\ColaEnlazada.java .\Cliente.java .\Main.java
if ($LASTEXITCODE -eq 0) { java -cp $outDir Main }
```

**Resultado observado:** compilación correcta sin advertencias y salida `Todas las pruebas de la cola genérica pasaron.`

## Conclusión

Los casos ejecutados confirman el orden FIFO para `String` y `Cliente`, sin que la estructura compare ni inspeccione el tipo de los elementos. También pasan las comprobaciones del frente, cola vacía, transición al estado vacío, reutilización e independencia de colas.
