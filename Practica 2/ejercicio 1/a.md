# Ejercicio 1 · a) Declaración de interfaces y constantes

## Código analizado

```java
interface ColPrimarios {
    int ROJO=1, VERDE=2, AZUL=4;
}

interface ColArcoIris extends ColPrimarios {
    int AMARILLO=3, NARANJA=5, INDIGO=6, VIOLETA=7;
}

interface ColImpresion extends ColPrimarios {
    int AMARILLO=8, CYAN=16, MAGENTA=32;
}

interface TodosLosColores extends ColImpresion, ColArcoIris {
    int FUCSIA=17, BORDO=ROJO+90;
}

class MisColores implements ColImpresion, ColArcoIris {
    public MisColores() {
        int unColor=AMARILLO;
    }
}
```

## Respuesta

**Las cuatro interfaces son correctas. La clase `MisColores` no compila.**
Hay un único error:

```
Colores.java:19: error: reference to AMARILLO is ambiguous
        int unColor=AMARILLO;
                    ^
  both variable AMARILLO in ColImpresion and variable AMARILLO in ColArcoIris match
1 error
```

## Por qué las interfaces sí son legales

### Los campos de una interface son `public static final`

Aunque estén escritos como `int ROJO=1;`, el compilador los trata como
`public static final int ROJO = 1;`. De ahí se siguen dos cosas:

- **Obligan a inicializarse en la declaración** — no hay constructor que pueda
  asignarlas después. Las cuatro interfaces las inicializan, así que están bien.
- **No son atributos de instancia.** Una interface no puede tener estado; lo que
  declara son constantes asociadas a la interface.

`TestColores` lo verifica por reflexión:

```
ROJO es public? true
ROJO es static? true
ROJO es final?  true
```

### Una interface puede extender varias interfaces

`interface TodosLosColores extends ColImpresion, ColArcoIris` es válido. La
restricción de herencia simple (`extends` de una sola clase) aplica a **clases**;
las interfaces admiten `extends` múltiple, y una clase admite `implements`
múltiple. Es la forma en que Java permite herencia múltiple **de tipo** sin
heredar estado.

### `BORDO=ROJO+90` no es ambiguo

Este es el punto fino. `TodosLosColores` hereda `ROJO` por **dos caminos**
(vía `ColImpresion` y vía `ColArcoIris`), lo que parece el problema del diamante:

```
        ColPrimarios          ROJO=1
         /        \
ColImpresion   ColArcoIris
         \        /
       TodosLosColores
```

Pero no hay ambigüedad, porque los dos caminos llevan a la **misma** constante:
la única declaración de `ROJO` está en `ColPrimarios`. Heredar una constante dos
veces por el mismo origen la deja siendo una sola. Por eso `ROJO+90` compila y
`BORDO` vale `91`.

Además, `BORDO=ROJO+90` es una expresión constante en tiempo de compilación
(`ROJO` es `final` con valor literal), lo cual es requisito para inicializar un
campo de interface.

## Por qué `MisColores` falla

`AMARILLO` es un caso distinto de `ROJO`: hay **dos declaraciones diferentes**,
con valores distintos, en dos interfaces que no se relacionan entre sí:

| Interface | `AMARILLO` |
| --- | --- |
| `ColArcoIris` | `3` |
| `ColImpresion` | `8` |

`MisColores implements ColImpresion, ColArcoIris` hereda las dos. Cuando el
constructor escribe `AMARILLO` sin calificar, el compilador no tiene forma de
elegir, y no adivina: da error. Es el comportamiento correcto — si eligiera una
por orden de declaración, un cambio inocuo en el `implements` cambiaría el valor
del programa en silencio.

Detalle importante: **el error está en el uso, no en la herencia**. Heredar dos
constantes homónimas es legal; `TodosLosColores` lo hace y compila. El error
aparece únicamente al **referenciar** el nombre ambiguo.

## Corrección

Calificar la constante con la interface de la que se la quiere:

```java
public class MisColores implements ColImpresion, ColArcoIris {

    private final int unColor;

    public MisColores() {
        this.unColor = ColImpresion.AMARILLO;   // vale 8
    }
}
```

Archivos: [`a/ColPrimarios.java`](a/ColPrimarios.java),
[`a/ColArcoIris.java`](a/ColArcoIris.java),
[`a/ColImpresion.java`](a/ColImpresion.java),
[`a/TodosLosColores.java`](a/TodosLosColores.java),
[`a/MisColores.java`](a/MisColores.java),
[`a/TestColores.java`](a/TestColores.java).

Las otras dos salidas posibles serían usar `ColArcoIris.AMARILLO` (vale `3`), o
—si el nombre corto se usa mucho— definir en la propia clase una constante que
resuelva la elección una sola vez, como hace `TodosLosColores`:

```java
int AMARILLO_IMPRESION = ColImpresion.AMARILLO;
int AMARILLO_ARCOIRIS  = ColArcoIris.AMARILLO;
```

## Compilar y ejecutar

```bash
cd "Practica 2/ejercicio 1/a" && javac *.java && java TestColores
```

## Salida

```
--- Constantes heredadas de ColPrimarios ---
ROJO  = 1
VERDE = 2
AZUL  = 4

--- ROJO llega por dos caminos, pero es UNA constante ---
ColPrimarios.ROJO    = 1
ColArcoIris.ROJO     = 1
ColImpresion.ROJO    = 1
TodosLosColores.ROJO = 1
Por eso BORDO = ROJO + 90 = 91 (no es ambiguo)

--- AMARILLO son DOS constantes distintas ---
ColArcoIris.AMARILLO  = 3
ColImpresion.AMARILLO = 8
Sin calificar: error de compilacion (reference is ambiguous)

--- Las constantes son public static final implicitamente ---
ROJO es public? true
ROJO es static? true
ROJO es final?  true

--- MisColores corregida ---
unColor (ColImpresion.AMARILLO) = 8
```

## Lo que deja el ejercicio

Las constantes en interfaces heredan por tipo, no por objeto, y colisionan por
**nombre**. Dos interfaces independientes con la misma constante son legales de
declarar y de heredar, pero inutilizan el nombre corto en quien las combine. Es
uno de los motivos por los que hoy se prefiere agrupar constantes en un `enum`
(que tiene identidad de tipo, y por lo tanto no colisiona) antes que en una
interface de constantes.
