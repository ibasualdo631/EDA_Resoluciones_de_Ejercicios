# Pruebas del ejercicio 6: Quicksort con pivote en el primer elemento

## 1. Objetivo de la prueba

Validar que la implementación de QuickSort cumple con la consigna del ejercicio:

- usar siempre el primer elemento como pivote,
- agrupar correctamente los valores menores a la izquierda y los mayores a la derecha,
- mostrar por consola en cada partición:
  1. pivote elegido,
  2. subarreglo izquierdo,
  3. subarreglo derecho,
  4. arreglo final ordenado.

## 2. Casos de prueba diseñados

### Caso 1: arreglo desordenado general
- Entrada: `[12, 5, 8, 3, 1, 9, 7, 2, 6, 10, 4]`
- Resultado esperado: el pivote debe quedar en su posición correcta y los elementos menores deben quedar a su izquierda.
- Verificación: se confirma que el arreglo particionado cumple con la propiedad de orden parcial.

### Caso 2: arreglo con datos más pequeños
- Entrada: `[9, 1, 5, 8, 2, 7]`
- Resultado esperado: el pivote 9 debe quedar al final de la partición y todo valor menor debe quedar a la izquierda.

### Caso 3: arreglo mínimo
- Entrada: `[3, 2, 1]`
- Resultado esperado: la partición genera un grupo izquierdo con menores y un grupo derecho con mayores.

### Caso 4: arreglo ya ordenado
- Entrada: `[1, 2, 3, 4]`
- Resultado esperado: la partición debe mantener la propiedad correcta aunque el arreglo ya esté ordenado.

### Caso 5: arreglo invertido
- Entrada: `[4, 3, 2, 1]`
- Resultado esperado: el pivote debe quedar a la derecha del bloque menor y el bloque mayor debe quedar a la derecha.

### Caso 6: arreglo mixto
- Entrada: `[6, 2, 7, 1, 5, 3]`
- Resultado esperado: la partición debe separar en dos grupos de forma consistente con el pivote 6.

## 3. Verificación realizada

Se ejecutó la clase `QuickSortBasico.java` y se comprobó que para cada caso:

- el pivote se selecciona como el primer elemento del bloque,
- los menores quedan a la izquierda,
- los mayores quedan a la derecha,
- la salida por consola presenta los 4 puntos pedidos por el ejercicio.

## 4. Resultado observado

Durante la ejecución, la salida mostró para cada partición:

```text
Pivote elegido: X
Subarreglo izquierdo: [...]
Subarreglo derecho: [...]
Arreglo final ordenado: [...]
```

Además, la validación de la matriz de casos indicó:

```text
agrupacion correcta: true
```

para todos los casos evaluados.

## 5. Conclusión

La implementación cumple con la consigna del ejercicio: usa pivote en el primer elemento, particiona correctamente el arreglo, y muestra en consola la información requerida durante cada paso del algoritmo.
