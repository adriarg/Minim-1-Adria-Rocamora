Minim 1 Adrià Rocamora


Component Java

-Interfície definida: El fitxer JuegoVirtualManager inclou totes les operacions necessàries.

-Façana implementada com a Singleton: La classe JuegoVirtualManagerImpl implementa totes les operacions.

-Estructures de dades: Llistes List per emmagatzemar usuaris i punts d'interès.

-Traça de LOG4J: Tots els mètodes registren informació clau, incloent:
-Paràmetres al començament i resultats al final.
-Nivells addicionals ERROR, FATAL per a excepcions.

Tests

-S'han desenvolupat proves JUnit per validar les operacions principals al component Java. He inclòs un test per cada operació per a verificar-ne el funcionament.

Actualment, totes les proves passen correctament.

En quant al servei REST, puc fer 6 de les 8 operacions demanades, les que funcionen responen amb codi correcte i quan no ho han de fer amb l'error corresponent. 
