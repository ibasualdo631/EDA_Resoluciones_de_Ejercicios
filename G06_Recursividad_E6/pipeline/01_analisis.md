# Ejercicio 6 — Análisis: contar dígitos recursivamente

## Objetivo

Redactar un prompt para OpenCode que solicite implementar una función recursiva para contar la cantidad de dígitos de un número entero positivo. La solución debe apoyarse en la división entera por 10 y explicar con claridad el caso base, el avance recursivo y los resultados esperados.

## Requerimientos funcionales

- Recibir un entero positivo y devolver la cantidad de dígitos que contiene.
- Resolver el problema mediante recursión, sin reemplazarla por un ciclo ni por una conversión a texto.
- En cada llamada recursiva, reducir el número mediante la división entera por 10.
- Retornar 1 al llegar a un número menor que 10.
- Para los demás valores, sumar 1 al resultado de contar los dígitos del cociente entero entre 10.
- Incluir pruebas que cubran números de uno y varios dígitos, especialmente el límite entre ambos casos.

## Regla recursiva y justificación

Para un entero positivo `n`, la función puede describirse así:

- Si `n < 10`, devuelve `1`: todo entero positivo menor que 10 consta de exactamente un dígito. Este es el caso base porque ya no hace falta reducirlo más y permite que la recursión termine.
- Si `n >= 10`, devuelve `1 + contar_digitos(n // 10)`: la división entera por 10 elimina el último dígito decimal. Se suma 1 para contar ese dígito eliminado, y la llamada recursiva cuenta los restantes.

La reducción progresa hacia el caso base: por ejemplo, `583 // 10 = 58`, `58 // 10 = 5`; por lo tanto, `contar_digitos(583) = 1 + contar_digitos(58) = 1 + 1 + contar_digitos(5) = 3`.

## Reglas y límites del problema

- El dominio indicado por la consigna es el de los enteros positivos (`n >= 1`).
- El cero y los negativos quedan fuera del dominio de esta función según el enunciado; no se debe inventar un comportamiento distinto sin una decisión explícita del proyecto.
- Los ceros a la izquierda no forman parte de la representación de un entero, así que no se cuentan.
- Debe usarse división entera (cociente truncado hacia abajo para positivos), no división real.
- La implementación debe respetar el lenguaje, estilo y estructura que ya utilice el proyecto. La imagen no especifica un lenguaje concreto.

## Pruebas sugeridas

| Entrada | Resultado esperado | Motivo |
|---:|---:|---|
| `1` | `1` | Menor positivo de un dígito; caso base |
| `9` | `1` | Último valor de un dígito |
| `10` | `2` | Primer valor de dos dígitos |
| `47` | `2` | Dos dígitos; reduce `47 → 4` |
| `1000` | `4` | Varios ceros internos/finales; reduce `1000 → 100 → 10 → 1` |

## Prompt inicial formal para OpenCode

> Implementá una función recursiva que reciba un entero positivo y devuelva cuántos dígitos tiene en base decimal. Antes de modificar archivos, revisá el proyecto para identificar el lenguaje, las convenciones y la ubicación apropiada para la función y sus pruebas; seguí esos patrones y limitá los cambios a lo necesario para este ejercicio.
>
> La función debe reducir el número en cada llamada usando división entera por 10, que elimina su último dígito. Definí como caso base `n < 10` y devolvé `1`, porque todo entero positivo menor que 10 contiene un solo dígito. Para `n >= 10`, devolvé `1 + contar_digitos(n // 10)`: el `1` cuenta el dígito eliminado y la llamada recursiva cuenta los que quedan. Asegurate de que cada llamada acerque el argumento al caso base y de que la solución sea recursiva, sin ciclos ni conversión del número a cadena.
>
> El dominio de entrada son enteros positivos; no agregues reglas para cero o negativos que no estén establecidas por el proyecto. Agregá o actualizá pruebas para `1 → 1`, `9 → 1`, `10 → 2`, `47 → 2` y `1000 → 4`. Verificá las pruebas existentes o las pertinentes y resumí qué cambiaste y qué validación ejecutaste.