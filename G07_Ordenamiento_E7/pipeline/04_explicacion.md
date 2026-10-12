# Explicación del Código: Peor Caso de Quicksort (Pivote Fijo)

### 1. Dinámica del Código y el Problema del Pivote
El código implementa el algoritmo **Quicksort**, pero obliga al programa a elegir siempre el **primer elemento** de la sublista como pivote (`pivote = arreglo[izquierda]`). 
Como el arreglo que le pasamos ya viene ordenado de fábrica (`{1, 2, 3, 4, 5, 6, 7}`), el primer elemento es siempre el número más chico. 

Al momento de hacer la partición, el algoritmo intenta poner los menores a la izquierda y los mayores a la derecha. Sin embargo, como no hay ningún número menor que el pivote, la partición queda completamente desbalanceada:
- **Lado izquierdo:** $0$ elementos.
- **Lado derecho:** $n - 1$ elementos (todo el resto del arreglo).

### 2. Dictamen Asintótico Formal
| Complejidad Temporal $O$ (Peor) | Complejidad Espacial (RAM) | Justificación Asintótica |
| :--- | :--- | :--- |
| **$O(n^2)$ Cuadrática** | **$O(n)$ en el Call Stack** | En lugar de dividir el problema a la mitad ($O(n \log n)$), el algoritmo resta solo 1 elemento por llamada, forzando a iterar $n$ veces un arreglo de tamaño $n$. |

### 3. Impacto en Memoria (The Call Stack)
En un Quicksort óptimo, la profundidad de la recursividad es logarítmica. Pero en este peor caso, al achicar el problema de a un elemento por vez, las llamadas recursivas se apilan de forma lineal. Si le pasamos un arreglo ya ordenado de un millón de elementos, el programa colapsaría por un **StackOverflowError**, ya que intentaría apilar un millón de llamadas en la memoria RAM simultáneamente.

---

### 4. Preguntas de Autoevaluación (Examen)
Para comprobar que entendiste el concepto, intentá responder estas preguntas:
1. Si sabemos que los datos que vamos a procesar suelen estar casi ordenados, ¿por qué convendría usar *Insertion Sort* en lugar de esta versión de *Quicksort*?
2. Si estuviéramos obligados a usar Quicksort en un arreglo ordenado, ¿qué técnica de selección de pivote aplicarías para recuperar el rendimiento $O(n \log n)$?