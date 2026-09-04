# Ejercicio 1 · b) Implementación de interfaces: legalidad y método `default`

## Código analizado

```java
public interface InstrumentoMusical {
  void hacerSonar();
  String queEs();
  void afinar(){}
}

class abstract InstrumentoDeViento implements InstrumentoMusical {
  void hacerSonar(){
     System.out.println("Sonar Vientos");
  }
  public String queEs() {
     return "Instrumento de Viento";
  }
}

class InstrumentoDeCuerda implements InstrumentoMusical {
  void hacerSonar(){
     System.out.println("Sonar Cuerdas");
  }
  public String queEs() {
     return "Instrumento de Cuerda";
  }
}
```

## Respuesta

**No es legal.** Hay **cuatro** errores distintos. Se destapan por capas, porque
el de sintaxis es tan temprano que impide al compilador seguir analizando:

```
Instrumentos.java:7: error: <identifier> expected
class abstract InstrumentoDeViento implements InstrumentoMusical {
     ^
4 errors
```

Corregido ese, aparecen los demás:

```
Paso2.java:4: error: interface abstract methods cannot have body
  void afinar(){}
               ^
Paso2.java:8: error: hacerSonar() in InstrumentoDeViento cannot implement hacerSonar() in InstrumentoMusical
  void hacerSonar(){
       ^
  attempting to assign weaker access privileges; was public
Paso2.java:16: error: InstrumentoDeCuerda is not abstract and does not override abstract method afinar() in InstrumentoMusical
class InstrumentoDeCuerda implements InstrumentoMusical {
^
Paso2.java:17: error: hacerSonar() in InstrumentoDeCuerda cannot implement hacerSonar() in InstrumentoMusical
  void hacerSonar(){
       ^
  attempting to assign weaker access privileges; was public
5 errors
```

## Los errores, uno por uno

| # | Dónde | Error | Corrección |
| --- | --- | --- | --- |
| 1 | `class abstract InstrumentoDeViento` | `<identifier> expected` | `abstract class InstrumentoDeViento` |
| 2 | `void afinar(){}` en la interface | `interface abstract methods cannot have body` | `default void afinar(){}` |
| 3 | `void hacerSonar()` en ambas clases | `attempting to assign weaker access privileges; was public` | `public void hacerSonar()` |
| 4 | `InstrumentoDeCuerda` | `is not abstract and does not override abstract method afinar()` | se resuelve solo con la corrección 2 |

### 1 · Orden de los modificadores

Los modificadores van **antes** de la palabra clave `class`: `abstract class`,
nunca `class abstract`. El compilador lee `class` y espera a continuación el
nombre del tipo; encuentra `abstract`, que es palabra reservada, y corta ahí.
Los otros tres errores que reporta son ruido de recuperación del parser.

### 2 · Un método de interface no puede tener cuerpo... salvo que se lo declare

`void afinar(){}` declara un método **abstracto** (implícito en una interface) y
al mismo tiempo le da un cuerpo `{}`. Son cosas incompatibles: abstracto
significa "sin implementación".

Un cuerpo vacío `{}` no es lo mismo que "sin cuerpo" (`;`). Para tener cuerpo en
una interface hay que declararlo explícitamente:

| Forma | Significado |
| --- | --- |
| `void afinar();` | abstracto: cada clase lo implementa |
| `default void afinar(){...}` | implementación heredable, redefinible |
| `static void afinar(){...}` | de la interface, no se hereda ni se redefine |
| `private void afinar(){...}` | auxiliar interno de otros métodos de la interface |

### 3 · No se puede reducir la visibilidad al implementar

Los métodos de una interface son **implícitamente `public`**. Al implementarlos
con acceso por defecto (*package-private*), la clase estaría **reduciendo** la
visibilidad de un método que el tipo promete como público, y eso rompería el
polimorfismo: una variable de tipo `InstrumentoMusical` podría invocar
`hacerSonar()` desde cualquier paquete, pero el objeto concreto no lo permitiría.

Java admite **ampliar** la visibilidad al redefinir, nunca restringirla. Como
`public` ya es el máximo, la única opción legal es `public`.

Es el mismo criterio de los modificadores de acceso del
[ejercicio 2 de la práctica 1](../../Practica%201/ejercicio%202/a.md), visto
ahora del lado de la redefinición.

### 4 · Una clase concreta debe implementar todo lo abstracto

Con `void afinar();` abstracto, `InstrumentoDeCuerda` —que **no** es `abstract`—
queda obligada a implementarlo, y no lo hace. `InstrumentoDeViento` en cambio no
da error por esto, precisamente porque es `abstract`: puede dejar métodos sin
implementar y delegar la obligación a sus subclases concretas.

## La pregunta del enunciado

> ¿Cómo podría modificar el método `afinar()` para evitar realizar cambios en
> las clases que implementan `InstrumentoMusical`?

**Declarándolo `default`:**

```java
public interface InstrumentoMusical {

    void hacerSonar();

    String queEs();

    default void afinar() {
        System.out.println("Afinando " + queEs() + " (afinado estandar)");
    }
}
```

Un método `default` aporta una implementación que **todas** las clases que
implementan la interface heredan sin escribir una línea. Con eso:

- Desaparece el error 4: `InstrumentoDeCuerda` ya no está obligada a nada.
- Se arregla el error 2, porque `default` es justamente la forma legal de tener
  cuerpo en una interface.
- Cada clase puede **redefinirlo** si le hace falta, sin obligar a las demás.

Un `default` puede invocar a los métodos abstractos de la propia interface
(acá llama a `queEs()`), que es lo que lo hace útil: define comportamiento en
términos del contrato, no de una implementación concreta.

### Para qué se agregaron los `default` a Java

Los métodos `default` entraron en **Java 8** para resolver un problema de
**evolución de interfaces**. Antes, agregar un método a una interface publicada
rompía la compilación de todas las clases que la implementaban — que puede ser
código de terceros que no se controla. Con `default`, la interface crece y el
código existente sigue compilando y funcionando.

El caso emblemático es `java.util.Collection`, a la que se le pudieron agregar
`stream()`, `forEach()` y `removeIf()` sin romper ninguna implementación previa
de la interface en todo el ecosistema Java.

## Implementación

Archivos: [`b/InstrumentoMusical.java`](b/InstrumentoMusical.java),
[`b/InstrumentoDeViento.java`](b/InstrumentoDeViento.java),
[`b/InstrumentoDeCuerda.java`](b/InstrumentoDeCuerda.java),
[`b/Flauta.java`](b/Flauta.java),
[`b/Guitarra.java`](b/Guitarra.java),
[`b/TestInstrumentos.java`](b/TestInstrumentos.java).

Se agregaron dos clases concretas que el enunciado no pide, para poder probar
lo anterior:

- **`Flauta extends InstrumentoDeViento`** — hace instanciable la rama abstracta
  y hereda el `afinar()` por defecto.
- **`Guitarra extends InstrumentoDeCuerda`** — **redefine** `afinar()`, para
  mostrar que el `default` es un punto de partida, no una imposición.

## Compilar y ejecutar

```bash
cd "Practica 2/ejercicio 1/b" && javac *.java && java TestInstrumentos
```

## Salida

```
--- Polimorfismo sobre la interface ---
Flauta:
Sonar Vientos
Afinando Flauta (afinado estandar)

Instrumento de Cuerda:
Sonar Cuerdas
Afinando Instrumento de Cuerda (afinado estandar)

Guitarra:
Sonar Cuerdas
Afinando Guitarra: E-A-D-G-B-E
```

Vale mirar la primera: `Flauta` no define `hacerSonar()` ni `afinar()`. El
primero lo hereda de la clase abstracta `InstrumentoDeViento`, el segundo del
`default` de la interface, y el `default` a su vez llama al `queEs()` que
`Flauta` sí redefine — por eso imprime "Afinando Flauta" y no "Afinando
Instrumento de Viento".

## Nota sobre las pruebas de compilación

Los errores de arriba se capturaron poniendo las tres declaraciones en un mismo
archivo. Eso agrega un error extra que **no** es del enunciado:

```
error: interface InstrumentoMusical is public, should be declared in a file named InstrumentoMusical.java
```

Es un artefacto de la prueba: un tipo `public` debe vivir en un archivo con su
mismo nombre. En la versión implementada cada tipo está en su propio archivo y
ese error no aparece.
