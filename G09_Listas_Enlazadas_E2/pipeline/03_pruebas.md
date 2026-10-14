# Pruebas — búsqueda en lista enlazada

## Validación ejecutada

- **Compilación:** `javac -Xlint:all -Werror -d <directorio-temporal> ListaEnlazadaSimple.java`
- **Ejecución:** `java -ea -cp <directorio-temporal> ListaEnlazadaSimple`
- **Resultado:** compilación exitosa, sin advertencias; 9 casos ejecutados correctamente (exit code 0).

## Casos y resultados

| Caso | Entrada / configuración | Esperado | Obtenido |
|---|---|---:|---:|
| Búsqueda al inicio | `[10, 20, 30, 40, 50]`, buscar `10` | `true` | `true` |
| Búsqueda en el medio | `[10, 20, 30, 40, 50]`, buscar `30` | `true` | `true` |
| Búsqueda al final | `[10, 20, 30, 40, 50]`, buscar `50` | `true` | `true` |
| Elemento inexistente | `[10, 20, 30, 40, 50]`, buscar `99` | `false` | `false` |
| Lista vacía (`head == null`) | `[]`, buscar `10` | `false` | `false` |
| Lista con un elemento, encontrado | `[7]`, buscar `7` | `true` | `true` |
| Lista con un elemento, ausente | `[7]`, buscar `9` | `false` | `false` |
| Valor repetido | `[4, 8, 4, 4]`, buscar `4` | `true` | `true` |
| Ausente en lista con repetidos | `[4, 8, 4, 4]`, buscar `9` | `false` | `false` |

Los casos validan que la búsqueda devuelva `false` al llegar a `null`, incluso con una lista vacía, y que los duplicados no afecten el resultado: basta encontrar una coincidencia.