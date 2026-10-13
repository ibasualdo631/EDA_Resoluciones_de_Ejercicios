# Relación entre `TorrePlatos` y la pila de ejecución de la JVM

## La idea compartida: LIFO

`TorrePlatos` representa una pila explícita: cada llamada a `apilar` agrega un plato en el tope y `desapilar` retira el último agregado. Por ejemplo, si se apilan `A`, `B` y `C`, se retiran en el orden `C`, `B`, `A`.

La JVM también aplica una organización LIFO para las llamadas a métodos de cada hilo. Cuando un método comienza, se crea un marco de activación (*stack frame*) con información necesaria para esa ejecución, como sus variables locales y el punto al que debe volver. Ese marco queda en el tope de la pila de ejecución. Cuando el método termina, su marco se retira y la ejecución continúa en el método que lo llamó.

Ejemplo conceptual:

```text
main llama a metodoA()
metodoA() llama a metodoB()
```

Mientras `metodoB()` se ejecuta, los marcos están ordenados así:

```text
Tope: metodoB()
      metodoA()
Base: main()
```

Al terminar `metodoB()`, se retira primero su marco y continúa `metodoA()`. Al terminar `metodoA()`, se retira el suyo y continúa `main()`. El método llamado más recientemente es el primero en completar y devolver el control: la misma regla LIFO que se observa al retirar los platos.

## Diferencias importantes

La semejanza es el orden de entrada y salida, no que ambas pilas sean el mismo objeto ni que guarden la misma clase de información:

- `TorrePlatos` es una estructura de datos creada explícitamente por el programa. En esta implementación se representa con un arreglo `Object[]` y el índice `top`.
- La pila de ejecución la administra la JVM para cada hilo. Sus marcos contienen datos de las llamadas activas, no platos.
- El arreglo de platos y la instancia `TorrePlatos` son objetos que viven en el heap; el marco de cada método que opera sobre ellos pertenece a la pila de ejecución del hilo.
- `TorrePlatos.desapilar()` y `consultarSuperior()` lanzan `NoSuchElementException` si no hay platos. Eso es una regla de esta estructura y no significa que la pila de ejecución de la JVM esté vacía.
- Una profundidad excesiva de llamadas, por ejemplo por una recursión que no termina, puede agotar la pila de ejecución y producir `StackOverflowError`. No es el mismo caso que intentar retirar un plato de una torre vacía.

## Pregunta conceptual

**Pregunta:** Si tanto `TorrePlatos` como la pila de ejecución de la JVM siguen el principio LIFO, ¿qué sucede cuando un método invoca a otro y por qué la JVM no vuelve inmediatamente al método que llamó primero?

**Respuesta:** Cada invocación crea un nuevo marco de activación en el tope de la pila de ejecución. Si `main` llama a `metodoA()` y este llama a `metodoB()`, el marco de `metodoB()` queda arriba de los otros. La JVM debe terminar o retornar primero desde `metodoB()`; entonces retira su marco y reanuda `metodoA()` en el punto donde hizo la llamada. Solo después de que `metodoA()` termine puede retirarse su marco y continuar `main()`. Así se conserva el estado de cada llamada y se respeta el orden LIFO.