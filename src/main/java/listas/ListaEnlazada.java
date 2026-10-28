package listas;

import java.util.NoSuchElementException;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 *    Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test). 
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 *    java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return this.cabeza == null;
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return this.cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
       
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
        this.cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {

        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            this.cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = this.cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        this.cantidad++;
    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {

        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return this.cabeza.getDato();
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {

        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        Nodo<T> actual = this.cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {

        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = this.cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {

        Nodo<T> actual = this.cabeza;
        while (actual != null) {
            if ((dato == null && actual.getDato() == null) || 
                (dato != null && dato.equals(actual.getDato()))) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {

        Nodo<T> actual = this.cabeza;
        int indice = 0;
        while (actual != null) {
            if ((dato == null && actual.getDato() == null) || 
                (dato != null && dato.equals(actual.getDato()))) {
                return indice;
            }
            actual = actual.getSiguiente();
            indice++;
        }
        return -1;
    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {

        if (indice < 0 || indice > this.cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (indice == 0) {
            agregarAlInicio(dato);
        } else if (indice == this.cantidad) {
            agregarAlFinal(dato);
        } else {
            Nodo<T> nuevoNodo = new Nodo<>(dato);
            Nodo<T> actual = this.cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
            this.cantidad++;
        }
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {

        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = this.cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        T datoAnterior = actual.getDato();
        actual.setDato(dato);
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {

        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T datoEliminado = this.cabeza.getDato();
        this.cabeza = this.cabeza.getSiguiente();
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {

        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        if (this.cantidad == 1) {
            return eliminarPrimero();
        }
        Nodo<T> actual = this.cabeza;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        T datoEliminado = actual.getSiguiente().getDato();
        actual.setSiguiente(null);
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {

        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (indice == 0) {
            return eliminarPrimero();
        }
        Nodo<T> actual = this.cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.getSiguiente();
        }
        T datoEliminado = actual.getSiguiente().getDato();
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        this.cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {

        Nodo<T> actual = this.cabeza;
        Nodo<T> anterior = null;
        while (actual != null) {
            if ((dato == null && actual.getDato() == null) || 
                (dato != null && dato.equals(actual.getDato()))) {
                if (anterior == null) {
                    this.cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                this.cantidad--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> actual = this.cabeza;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {

        Nodo<T> anterior = null;
        Nodo<T> actual = this.cabeza;
        Nodo<T> siguiente = null;

        while (actual != null) {
            siguiente = actual.getSiguiente(); // Guardar el siguiente nodo
            actual.setSiguiente(anterior); // Invertir la referencia
            anterior = actual; // Mover 'anterior' hacia adelante
            actual = siguiente; // Mover 'actual' hacia adelante
        }
        this.cabeza = anterior; // Actualizar la cabeza de la lista
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {

        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        Nodo<T> lento = this.cabeza;
        Nodo<T> rapido = this.cabeza;

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente(); // Avanza de a 1
            rapido = rapido.getSiguiente().getSiguiente(); // Avanza de a 2
        }

        return lento.getDato();
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        
        Nodo<T> actual = this.cabeza;
        while (actual != null) {
            Nodo<T> runner = actual;
            while (runner.getSiguiente() != null) {
                if ((actual.getDato() == null && runner.getSiguiente().getDato() == null) || 
                    (actual.getDato() != null && actual.getDato().equals(runner.getSiguiente().getDato()))) {
                    // Eliminar el nodo duplicado
                    runner.setSiguiente(runner.getSiguiente().getSiguiente());
                    this.cantidad--;
                } else {
                    runner = runner.getSiguiente();
                }
            }
            actual = actual.getSiguiente();
        }
    }
}
