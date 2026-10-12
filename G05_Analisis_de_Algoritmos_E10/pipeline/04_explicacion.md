# Bubble Sort

`bubbleSort` ordena un arreglo de enteros de menor a mayor **en el mismo
arreglo**. En cada pasada compara elementos vecinos y los intercambia cuando
están en el orden incorrecto. Así, el mayor valor pendiente va quedando al
final; por eso, en cada pasada siguiente se revisa una posición menos.

La bandera `huboIntercambios` permite detener el algoritmo antes: si una pasada
no intercambia nada, el arreglo ya estaba ordenado. El método cuenta e imprime
comparaciones e intercambios; rechaza `null` con `IllegalArgumentException`.
El `main` prueba casos ordenados, invertidos, con duplicados, vacíos, de un
elemento y `null`.

Su espacio auxiliar es **O(1)** y el peor caso es **O(n²)**. Con la bandera, el
mejor caso es **Θ(n)** (una pasada). Es útil para aprender, pero suele ser
ineficiente con arreglos grandes.

## Preguntas trampa

1. **Si el arreglo está ordenado, ¿el algoritmo hace cero comparaciones?**  
   No. Recorre una pasada, hace `n - 1` comparaciones y, al no intercambiar,
   termina.

2. **¿La comparación `arr[i] > arr[i + 1]` intercambia los valores iguales?**  
   No. Solo intercambia si el de la izquierda es estrictamente mayor; por eso
   los duplicados no se intercambian entre sí.