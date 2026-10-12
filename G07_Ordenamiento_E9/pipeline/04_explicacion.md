# Interpretación de los resultados

Los tres algoritmos imprimen el mismo arreglo ordenado: `[11, 12, 22, 25, 25, 34, 64, 90]`. Para los datos de ejemplo (`[64, 34, 25, 12, 22, 11, 90, 25]`), la tabla registra:

- **Bubble Sort:** 27 comparaciones y 17 intercambios.
- **Selection Sort:** 28 comparaciones y 6 intercambios.
- **Insertion Sort:** 21 comparaciones y 17 desplazamientos.

Estas cantidades cuentan operaciones, no el tiempo real de ejecución; además, los movimientos no son exactamente equivalentes: Bubble y Selection cuentan intercambios, mientras que Insertion cuenta desplazamientos a la derecha y no suma la escritura final de la clave.

Insertion Sort puede ser mucho más rápido con un arreglo casi ordenado porque recorre cada elemento y solo lo desplaza cuando encuentra valores mayores antes de él. Si hay pocos elementos fuera de lugar, hace pocas comparaciones y desplazamientos —su costo se acerca a lineal—. Selection Sort, en cambio, busca el mínimo en el resto del arreglo en cada pasada y realiza cerca de `n²/2` comparaciones aunque ya esté ordenado. Bubble Sort puede terminar pronto si no encuentra intercambios en una pasada, pero con desorden debe repetir pasadas e intercambiar elementos vecinos. La entrada concreta de este programa no está casi ordenada, por lo que no demuestra por sí sola esa ventaja de Insertion Sort.