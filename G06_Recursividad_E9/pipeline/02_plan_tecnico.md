# Plan técnico: palíndromo recursivo

## Firma del método

```python
def esPalindromo(palabra: str) -> bool:
```

El método recibe una cadena y devuelve `True` si es palíndromo o `False` si no lo es.

## Estructura y secuencia

1. Recibir la palabra como cadena.
2. **Caso base:** si `len(palabra) <= 1`, devolver `True`. Una cadena vacía o de un carácter se lee igual en ambos sentidos.
3. Comparar el primer carácter (`palabra[0]`) con el último (`palabra[-1]`).
4. **Caso base de rechazo:** si son distintos, devolver `False`; no hace falta seguir comparando.
5. Si son iguales, descartar ambos extremos y devolver el resultado de la llamada recursiva con `palabra[1:-1]`.

Cada llamada reduce la cadena en dos caracteres. Por eso, la recursión llega al caso base de longitud menor o igual a uno.

## Instrucciones de código

```python
def esPalindromo(palabra: str) -> bool:
    if len(palabra) <= 1:
        return True

    if palabra[0] != palabra[-1]:
        return False

    return esPalindromo(palabra[1:-1])
```

## Verificación esperada

```python
esPalindromo("radar")      # True
esPalindromo("reconocer")  # True
esPalindromo("hola")       # False
```

El método compara los caracteres tal como están en la cadena recibida; no normaliza mayúsculas, espacios ni signos de puntuación.