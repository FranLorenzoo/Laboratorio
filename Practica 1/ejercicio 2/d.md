# Ejercicio 2 · d) Acceso por defecto desde una subclase de otro paquete

## Planteo

Se parte de la clase `Alpha` del inciso [c](c.md), con `x` y `unMetodoA()` con
**acceso por defecto** (*package-private*):

```java
package griego;

public class Alpha {
    int x;
    void unMetodoA() {
        System.out.println("Un mét. paquete");
    }
}
```

La pregunta es si el acceso mejora cuando quien intenta usarlos, en vez de ser
una clase cualquiera de `romano`, es una **subclase** de `Alpha` declarada en
`romano`:

```java
package romano;

import griego.*;

class Epsilon extends Alpha {        // ✔ la declaración es legal
    void unMetodoE() {
        x = 10;                      // ✘ ERROR de compilación
        this.x = 10;                 // ✘ ERROR de compilación
        unMetodoA();                 // ✘ ERROR de compilación
        super.unMetodoA();           // ✘ ERROR de compilación
    }
}
```

## Respuesta

**No. El acceso sigue siendo inválido.** Ser subclase no cambia nada: los
miembros con acceso por defecto siguen siendo invisibles fuera de `griego`, aun
para las clases que heredan de `Alpha`.

## Justificación

**1. Heredar no amplía la visibilidad.**
`extends` no otorga ningún privilegio extra sobre los miembros *package-private*
de la superclase. El único modificador que "atraviesa la frontera del paquete
por la vía de la herencia" es `protected` (y `public`, que la atraviesa siempre,
haya herencia o no). Por eso el enunciado plantea el inciso [e](e.md) con
`protected`: ahí sí cambia el resultado.

**2. Esos miembros ni siquiera se heredan.**
Formalmente, una subclase hereda de su superclase únicamente los miembros que le
son **accesibles**. Como `x` y `unMetodoA()` no le son accesibles a `Epsilon`,
no forman parte de los miembros heredados: no existen dentro del "vocabulario"
de `Epsilon`. Por eso el error no es solo de permisos, sino directamente de
símbolo no encontrado en el caso de la forma sin calificar:

```
error: cannot find symbol
        x = 10;
        ^
  symbol:   variable x
  location: class Epsilon
```

Ojo con no confundir dos planos: en memoria, **el objeto `Epsilon` sí contiene
físicamente el campo `x`** (todo objeto contiene el estado completo de sus
superclases). Lo que no existe es una forma legal de nombrarlo desde el código
de `Epsilon`. El dato está, pero el compilador no deja tocarlo.

**3. La declaración de la subclase sí es legal.**
`class Epsilon extends Alpha` compila sin problema, porque `Alpha` es `public` y
su constructor implícito también (ver [b](b.md)). El problema aparece recién al
usar los miembros.

## Consecuencia adicional: la sobrescritura accidental que no sucede

Si en `Epsilon` se declarara un método con la misma firma:

```java
package romano;

class Epsilon extends Alpha {
    void unMetodoA() {                    // NO es un override
        System.out.println("Epsilon");
    }
}
```

ese método **no sobrescribe** al de `Alpha`: como el original no es visible
desde `romano`, no hay relación de sobrescritura, sino dos métodos
independientes que casualmente comparten el nombre. Consecuencias prácticas:

- Anotarlo con `@Override` produce un error de compilación
  (*"method does not override or implement a method from a supertype"*).
- No hay polimorfismo: si un método de `griego` invoca `unMetodoA()` sobre un
  objeto `Epsilon`, se ejecuta **la versión de `Alpha`**, no la de `Epsilon`.

Este es un caso clásico de bug silencioso al heredar entre paquetes.

## Cómo se arreglaría

Cambiar el modificador de los miembros en `Alpha` a `protected`:

```java
package griego;

public class Alpha {
    protected int x;
    protected void unMetodoA() { ... }
}
```

Con eso, `Epsilon` accede sin problemas a `x`, `this.x`, `unMetodoA()` y
`super.unMetodoA()`, porque en todos esos casos el receptor es el propio objeto
(de tipo `Epsilon`). La restricción exacta que rige ese permiso es el tema del
inciso [e](e.md).
