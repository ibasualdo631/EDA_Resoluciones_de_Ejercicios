# Explicación simple: complejidad de contar ocurrencias

## 1. ¿Por qué el tiempo es siempre O(n) y no hay un mejor caso O(1)?

El método revisa cada elemento del vector para saber cuántas veces aparece el objetivo. Aunque encuentre una coincidencia en la primera posición, tiene que seguir recorriendo el resto: podría haber más coincidencias.

Por eso, si el vector tiene `n` elementos, el método hace una cantidad de trabajo proporcional a `n`. No puede resolverlo en tiempo constante (`O(1)`) para vectores arbitrariamente grandes. En realidad, el recorrido realiza `n` comparaciones tanto si encuentra muchas coincidencias como si no encuentra ninguna; por eso el tiempo es `Θ(n)` en todos los casos. La comprobación de `null` sí toma tiempo constante, pero no evita recorrer el vector cuando es válido.

## 2. ¿Cuál es el espacio adicional y por qué es O(1)?

El método usa un contador y, en el recorrido, una variable para el elemento actual. Esas variables ocupan una cantidad fija de espacio: no se crea una colección ni una copia cuyo tamaño dependa del vector. Por eso, el espacio adicional es `O(1)`.

## 3. Preguntas para comprobar la comprensión

1. Si el objetivo aparece en la primera posición, ¿por qué el método debe seguir recorriendo el vector?

Porque la consigna pide contar todas las apariciones, no solo comprobar si el valor aparece. Aunque se encuentre una coincidencia en la primera posición, el objetivo podría repetirse en posiciones posteriores; por eso hay que revisar el vector completo.

2. Si duplicamos la cantidad de elementos del vector, ¿qué ocurre aproximadamente con la cantidad de comparaciones?

También se duplica aproximadamente la cantidad de comparaciones. El algoritmo compara el objetivo con cada elemento una vez, así que si el vector tiene el doble de elementos, realiza el doble de comparaciones. Esto corresponde a una complejidad temporal lineal, `O(n)`.