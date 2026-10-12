# Plan técnico: búsqueda recursiva en un arreglo

## 1. Diseño

Se encapsulará la búsqueda en una clase `BuscadorArreglo`. Su interfaz pública expondrá una operación que recibe el arreglo, su tamaño y el valor buscado, sin exigir al usuario conocer ni proporcionar la posición desde la que comienza el recorrido.

Un método privado realizará la recursión y recibirá además el índice actual. El método público actuará como *wrapper* (envoltorio): iniciará la búsqueda en el índice `0` y delegará el recorrido al método privado.

## 2. Interfaz y responsabilidades

```cpp
class BuscadorArreglo {
public:
    int buscar(const int arreglo[], int tamanio, int valor) const;

private:
    int buscarRecursivo(
        const int arreglo[],
        int tamanio,
        int valor,
        int indice
    ) const;
};
```

- `buscar`: interfaz pública; oculta el índice auxiliar y llama a `buscarRecursivo` con `indice = 0`.
- `buscarRecursivo`: compara la posición actual y decide si devolverla, terminar sin coincidencia o avanzar recursivamente al índice siguiente.
- El resultado es el índice de la primera coincidencia; si el valor no aparece, se devuelve `-1`.
- Se presupone que el arreglo es válido cuando `tamanio > 0`. Un tamaño igual a cero produce `-1` sin acceder a elementos.

## 3. Secuencia lógica

1. El usuario invoca `buscar(arreglo, tamanio, valor)`.
2. El wrapper inicia el proceso con `buscarRecursivo(arreglo, tamanio, valor, 0)`.
3. El método privado verifica primero si `indice >= tamanio`. Si se cumple, devuelve `-1`.
4. Si aún hay una posición por revisar y `arreglo[indice] == valor`, devuelve `indice`.
5. Si no hay coincidencia, llama a `buscarRecursivo` con `indice + 1` y devuelve el resultado de esa llamada.

Comprobar el límite antes de acceder a `arreglo[indice]` evita leer fuera del arreglo. Al avanzar de izquierda a derecha, la primera coincidencia encontrada es la que se devuelve.

## 4. Casos base y caso recursivo

- **Fallo:** `indice >= tamanio`; ya no quedan elementos y se devuelve `-1`.
- **Éxito:** `arreglo[indice] == valor`; se devuelve el índice actual.
- **Recursión:** si no se cumple ninguno de los casos anteriores, se continúa desde `indice + 1`.

## 5. Complejidad

Cada llamada realiza una cantidad constante de trabajo: comprueba el límite, compara como máximo un elemento y, si hace falta, efectúa una llamada recursiva. En el peor caso (elemento ausente o ubicado al final) se examinan los `N` elementos y se llega a una llamada adicional que detecta el fin:

```text
T(N) = T(N - 1) + Θ(1)  =>  T(N) = Θ(N), por lo tanto O(N).
```

Si el elemento está en la primera posición, el mejor caso es Θ(1). La pila de llamadas puede alcanzar O(N) en el peor caso.