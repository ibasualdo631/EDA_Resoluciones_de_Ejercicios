# Análisis — Ejercicio 6: comparar dos vectores

## Objetivo

Implementar en Java un algoritmo que determine si dos vectores son iguales. La comparación debe finalizar inmediatamente cuando encuentra una diferencia; no debe seguir recorriendo los elementos restantes.

## Requerimientos funcionales

- Comparar los elementos de ambos vectores en el mismo orden.
- Considerar iguales dos vectores solo si tienen la misma longitud y todos sus elementos correspondientes son iguales.
- Detener la comparación al encontrar el primer elemento distinto.
- Resolver explícitamente los casos de referencias `null`, vectores vacíos y longitudes diferentes.
- Incluir el análisis del mejor, peor y caso promedio, indicando las hipótesis empleadas para el caso promedio.

## Convenciones propuestas

Para que el comportamiento sea inequívoco, el prompt define los vectores como arreglos `int[]` y establece estas reglas:

- Si cualquiera de las referencias es `null` (incluidas ambas), el método lanzará `IllegalArgumentException`, según el requisito de implementación.
- Dos vectores vacíos se consideran iguales; uno vacío y otro no vacío se consideran diferentes.
- Si las longitudes son distintas, se consideran diferentes sin comparar elementos.
- En los demás casos, se comparan los elementos desde el índice cero y se retorna `false` en cuanto se encuentra una diferencia.

La validación de referencias y longitudes se realiza antes de acceder a los elementos, evitando errores de acceso y siguiendo el contrato solicitado para la implementación.

## Análisis algorítmico esperado

Sea `n` la longitud de los dos vectores cuando tienen igual longitud. Las verificaciones de referencias y longitudes cuestan tiempo constante.

- **Mejor caso:** `O(1)`, por ejemplo, si las longitudes difieren o si el primer elemento comparado es distinto. También es `O(1)` cuando ambos vectores son vacíos.
- **Peor caso:** `O(n)`, cuando los vectores son iguales y hay que revisar todos sus elementos, o cuando la única diferencia está en la última posición.
- **Caso promedio:** depende de cómo se distribuyen las diferencias y de la probabilidad de que los vectores sean iguales. Bajo la hipótesis de que, condicionada a que sean distintos, la primera diferencia es igualmente probable en cualquiera de las `n` posiciones, se esperan `(n + 1) / 2` comparaciones: `Θ(n)`. Si se supone una frecuencia no despreciable de vectores idénticos, esa situación requiere recorrer los `n` elementos y también mantiene un promedio `Θ(n)` para una longitud `n`.
- **Espacio adicional:** `O(1)`, porque solo se necesitan índices o variables auxiliares, sin crear estructuras proporcionales al tamaño de entrada.

## Prompt inicial para implementación

> Implementá en Java un método que reciba dos arreglos `int[]` y retorne `true` si representan vectores iguales o `false` en caso contrario. Si cualquiera de las referencias es `null`, lanzá `IllegalArgumentException`. Dos arreglos vacíos son iguales. Si sus longitudes son distintas, retorná `false` sin recorrerlos. Si tienen la misma longitud, compará sus elementos en orden desde el índice cero y finalizá inmediatamente al encontrar la primera diferencia. No uses una comparación que recorra innecesariamente el resto del arreglo después de detectar una diferencia.
>
> Incluí una explicación breve del algoritmo y casos de prueba para: un argumento `null`, ambos argumentos `null`, dos arreglos vacíos, un arreglo vacío y otro no vacío, longitudes distintas, arreglos iguales y diferencias en la primera, una posición intermedia y la última posición. Verificá que cada prueba con `null` compruebe `IllegalArgumentException`.
>
> Agregá el análisis de complejidad temporal del mejor, peor y caso promedio, explicitando el supuesto probabilístico utilizado para el promedio, y el espacio adicional. Para arreglos de longitud `n`, considerá como mejor caso una diferencia detectada al inicio (o un retorno por longitudes distintas), como peor caso recorrer todos los elementos y, para el promedio, asumí que la primera diferencia de dos arreglos distintos es equiprobable entre sus `n` posiciones.