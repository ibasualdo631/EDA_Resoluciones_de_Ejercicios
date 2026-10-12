# Método envoltorio (wrapper) en una función recursiva

Un **método envoltorio**, también llamado *wrapper*, es un método público que presenta una interfaz sencilla y se encarga de preparar la llamada a otro método auxiliar, normalmente privado. El auxiliar contiene los detalles de implementación que hacen falta para resolver el problema.

En una búsqueda recursiva, el método auxiliar necesita información de control —por ejemplo, el índice desde el que debe continuar— que no es necesario pedirle a quien usa la búsqueda. El wrapper oculta ese detalle y comienza la recursión con el estado inicial apropiado.

## Aplicación a esta búsqueda

La clase separa la operación pública de su mecanismo recursivo:

```java
public static int buscar(int[] arreglo, int valor) {
    if (arreglo == null) {
        throw new IllegalArgumentException("El arreglo no puede ser null.");
    }
    return buscarDesde(arreglo, valor, 0);
}

private static int buscarDesde(int[] arreglo, int valor, int indice) {
    if (indice >= arreglo.length) {
        return -1;
    }
    if (arreglo[indice] == valor) {
        return indice;
    }
    return buscarDesde(arreglo, valor, indice + 1);
}
```

Quien llama solo necesita escribir `buscar(arreglo, valor)`. No tiene que conocer el índice inicial ni proporcionar un índice en cada llamada. El wrapper valida que el arreglo no sea `null` y delega al auxiliar con `indice = 0`.

El método privado `buscarDesde` maneja el avance recursivo:

1. **Caso base de búsqueda fallida:** si `indice >= arreglo.length`, ya se examinaron todas las posiciones y retorna `-1`. La comprobación ocurre antes de acceder a `arreglo[indice]`, por lo que evita intentar leer fuera de los límites.
2. **Caso base de búsqueda exitosa:** si el elemento actual coincide con `valor`, retorna el índice actual. Como se avanza desde cero, la primera coincidencia es la que se devuelve.
3. **Paso recursivo:** si no coincide, llama de nuevo a `buscarDesde` con `indice + 1`.

## Por qué es una buena práctica de encapsulamiento

- **Oculta detalles internos:** los usuarios de la clase no necesitan saber cómo se representa el progreso de la recursión.
- **Reduce errores de uso:** al fijar el índice inicial dentro del wrapper, evita que alguien comience accidentalmente en una posición incorrecta o fuera de rango.
- **Ofrece una API clara y estable:** la firma pública expresa la intención (“buscar este valor en este arreglo”) sin exponer parámetros auxiliares.
- **Centraliza validaciones e inicialización:** el wrapper puede verificar argumentos y establecer las condiciones iniciales en un único lugar antes de iniciar la recursión.
- **Permite cambiar la implementación con menor impacto:** el auxiliar puede modificarse —por ejemplo, cambiar cómo se recorre el arreglo— sin obligar a los llamadores a cambiar, siempre que la interfaz pública conserve su comportamiento.

En síntesis, el wrapper separa **qué operación se ofrece** de **cómo se implementa recursivamente**. Así, la recursión conserva el estado que necesita internamente, mientras que el resto del programa usa un método simple y seguro.