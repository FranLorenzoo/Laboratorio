# Ejercicio 2 · b) Acceso `public` desde otro paquete

## Código analizado

```java
package griego;

public class Alpha {
    public int x;

    public void unMetodoA() {
        System.out.println("Un Método Público");
    }
}
```

```java
package romano;

import griego.*;

class Beta {
    void unMetodoB() {
        Alpha a = new Alpha();
        a.x = 10;           // ✔ válido
        a.unMetodoA();      // ✔ válido
    }
}
```

## Respuesta

**Sí, los dos accesos son válidos.** La clase `Beta` compila sin errores aunque
esté en un paquete distinto (`romano`) y no herede de `Alpha`.

## Justificación

Para que un acceso entre paquetes distintos funcione tienen que cumplirse
**tres** condiciones independientes. Acá se cumplen todas:

**1. El tipo tiene que ser visible desde el otro paquete.**
`Alpha` está declarada `public`, así que se exporta fuera de `griego`. Si fuera
una clase con acceso de paquete (como en el inciso [a](a.md)), `Beta` ni
siquiera podría escribir `Alpha a;` — el error sería *"Alpha is not public in
griego; cannot be accessed from outside package"*, y el `import` no lo
arreglaría.

**2. Los miembros tienen que ser visibles.**
Tanto `x` como `unMetodoA()` están declarados `public`. Cada miembro lleva su
propio modificador: que la clase sea pública **no** hace públicos a sus
miembros. Este punto es justamente el que falla en el inciso [c](c.md).

**3. El constructor tiene que ser accesible.**
`Alpha` no declara ningún constructor, así que el compilador genera el
constructor por defecto sin argumentos y le asigna **el mismo nivel de acceso
que la clase**. Como `Alpha` es `public`, ese constructor implícito es `public`
y `new Alpha()` es legal desde `romano`.

## Detalle importante: `import` no otorga permisos

`import griego.*;` es solo una comodidad sintáctica: le permite al compilador
resolver el nombre corto `Alpha` como `griego.Alpha`. **No cambia ni relaja
ningún control de acceso.** El código de arriba es equivalente a escribir, sin
ningún `import`:

```java
package romano;

class Beta {
    void unMetodoB() {
        griego.Alpha a = new griego.Alpha();
        a.x = 10;
        a.unMetodoA();
    }
}
```

Quien decide si el acceso se permite es siempre el modificador del tipo y del
miembro, nunca el `import`.

## Detalle sobre `Beta`

`Beta` está declarada con acceso de paquete (no es `public`). Eso es irrelevante
para el análisis: el modificador de `Beta` limita **quién puede usar a `Beta`**,
no **a quién puede acceder `Beta`**.

## Observación de diseño

Aunque el código compile, exponer el campo `x` como `public` rompe el
encapsulamiento: cualquier clase del programa puede modificar el estado interno
de un `Alpha` sin ningún control. La práctica recomendada es dejar los atributos
`private` (o `protected`) y publicar el acceso mediante métodos `getX()` /
`setX()`, que permiten validar el valor antes de asignarlo.
