Minim 1 Adrià Rocamora

Actualment em trobo en els següents punts de les operacions requerides pel mínim.


● Añadir un usuario con la siguiente información: identificador, nombre,
apellidos, correo electrónico y fecha de nacimiento. [FUNCIONANT]

● Listar todos los usuarios ordenados alfabéticamente (apellidos, nombre). [FUNCIONANT]

● Consultar la información de un usuario usando su identificador. [FUNCIONANT]

● Añadir un punto de interés en el mapa, con la información de su posición
(coordenadas horizontal y vertical) y su tipo. [FUNCIONANT]

● Registrar que un usuario pasa por un punto de interés, identificándolo
con el id del usuario y la posición del punto de interés (coordenadas
horizontal y vertical). Si el usuario o el punto de interés en esas
coordenadas no existen, se deberá informar del error. [FUNCIONANT]

● Consultar los puntos de interés por los que un usuario ha pasado, en el
orden en que se ha registrado. [Error: Internal Server Error Code 500]

● Listar los usuarios que han pasado por un punto de interés identificado
por sus coordenadas. Si el punto de interés no existe en esa posición, se
deberá informar del error.  [Error: Internal Server Error Code 500]

● Consultar los puntos de interés del mapa que sean de un tipo
(ElementType) determinado. El resultado mostrará el listado de puntos
de interés con sus coordenadas (horizontal y vertical) que sean
del tipo especificado. [FUNCIONANT]

El servidor inicia, accedeixo a la URL del Swagger i puc fer 6 de les 8 operacions demanades,
les que funcionen responen amb codi correcte i quan no funcionen amb l'error corresponent.

En quant al test, per ara passa 7 dels 8.