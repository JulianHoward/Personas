import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Iterator;

public class ListaPersonas<T> implements Iterable<T> {

    protected Contenedor<T> raiz;
    protected int tamano;

    private final static Logger logger = (Logger) LogManager.getRootLogger();

    public ListaPersonas() {
        raiz = null;
        tamano = 0;
    }

    public Contenedor<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Contenedor<T> raiz) {
        this.raiz = raiz;
    }

    public void insertar(T o) {
        Contenedor<T> nuevo = new Contenedor<T>(o);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        tamano++;
        logger.debug("Se añade una nueva persona a mi lista");
    }

    public int tamano() {

        return tamano;
		/*int resultado = 0;
		Contenedor<T> actual = raiz;

		while(actual != null) {
			resultado++;
			actual = actual.getSiguiente();
		}

		return resultado;*/
    }

    public void add(T o) {
        if (raiz == null) {
            insertar(o);
            return;
        }

        Contenedor<T> actual = raiz;
        Contenedor<T> nuevo = new Contenedor<T>(o);
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();

        }
        // Aqui tenemos al ultimo
        actual.setSiguiente(nuevo);
        tamano++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Contenedor<T> actual = raiz;
        while (actual != null) {
            sb.append("[" + actual.getContenido().toString() + "]---");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new IteradorLista<T>(raiz);
    }

///////////////////////////////// clase aparte //////////////////////////////////////
    class IteradorLista<T> implements Iterator<T> {

        private Contenedor<T> actual; //objeto de clase de abajo

        public IteradorLista(Contenedor<T> inicio) {
            actual = inicio;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T result = actual.getContenido();
            actual = actual.getSiguiente();
            return result;
        }

    }

    //////////////////////////// clase aparte /////////////////////////
    class Contenedor<T> {
        private T contenido;
        private Contenedor<T> siguiente; // instancia de la misma clase

        public Contenedor(T c) {
            contenido = c;
            siguiente = null;
        }

        public T getContenido() {
            return contenido;
        }

        public void setContenido(T contenido) {
            this.contenido = contenido;
        }

        public Contenedor<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Contenedor<T> siguiente) {
            this.siguiente = siguiente;
        }
    }
}

