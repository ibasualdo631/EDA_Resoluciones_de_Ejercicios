# Matriz de pruebas

| Caso | Datos de entrada | Resultado esperado | Resultado |
|---|---|---|---|
| Ranking descendente | Ana 1200, Pedro 900, Lucia 1500 | Lucia 1500, Ana 1200, Pedro 900 | Aprobado |
| Empate estable | Ana 1200, Pedro 1200, Lucia 1500 | Lucia 1500, Ana 1200, Pedro 1200; Ana permanece antes que Pedro | Aprobado |

Se compiló y ejecutó el programa para ambos casos. `List.sort` es estable y el comparador solo considera el puntaje, por lo que conserva el orden original entre puntajes iguales.