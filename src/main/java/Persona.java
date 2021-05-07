import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;

public class Persona implements Comparable<Persona>{

    private int altura;
    private String sexo = "";
    private int edad;
    private String nombre;
    private int hair;
    private int longHair;
    private int x;
    private int y;
    private int porcentajeCabeza;
    private int porcentajeTronco;
    private Color color;
    private Comparator<Persona> comparador;

    private final static Logger logger = (Logger) LogManager.getRootLogger();

    private PropertyChangeSupport observed;


    public Persona() {
        observed = new PropertyChangeSupport(this);
    }

    public Persona(String nombre, int edad, int altura, String sexo, int x) {
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.sexo = sexo;
        this.x = x;
        porcentajeCabeza = (int) (altura * 0.25);
        porcentajeTronco = (int) (altura * 0.35);
        hair = (int) (altura * 0.20);
        longHair = (int) (altura * 0.05);
        observed = new PropertyChangeSupport(this);
        this.comparador = new ComparadorPersona();
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addObserver(PropertyChangeListener panel) {

        observed.addPropertyChangeListener(panel);
    }

    public void cambioOk() {
        observed.firePropertyChange("Persona", 1, 2);
    }

    public void dibujar(Graphics g) {
        y = 400 - altura;
        if (edad < 20) {
            color = new Color(255,255,0);
            g.setColor(color);
        }
        if(edad > 20) {
            color = new Color(55,190,80);
            g.setColor(color);
        }
        if(edad == 20){
            color = new Color(155,222,43);
            g.setColor(color);
        }

        g.drawOval(x, y, porcentajeCabeza, porcentajeCabeza);
        //logger.debug("Se dibuja la cabeza");
        g.drawLine(x + porcentajeCabeza / 2, y + porcentajeCabeza, x + porcentajeCabeza / 2, y + porcentajeCabeza + porcentajeTronco);
        //logger.debug("Se dibuja el torso");
        g.drawLine(x, y + porcentajeCabeza + porcentajeTronco / 2, x + porcentajeCabeza, y + porcentajeCabeza + porcentajeTronco / 2);
        //logger.debug("Se dibujan los brazos");
        g.drawLine(x + porcentajeCabeza / 2, y + porcentajeCabeza + porcentajeTronco, x, y + altura);
        //logger.debug("Se dibuja una pierna");
        g.drawLine(x + porcentajeCabeza / 2, y + porcentajeCabeza + porcentajeTronco, x + porcentajeCabeza, y + altura);
        //logger.debug("Se dibuja la otra pierna");

        if (sexo.toUpperCase().equals("FEMENINO")) {
            g.setColor(Color.BLUE);
            g.fillRect(x + hair, y + longHair, longHair, porcentajeCabeza);
        }
    }

    public Comparator<Persona> getComparador() {
        return comparador;
    }

    public void setComparador(Comparator<Persona> comparador) {
        this.comparador = comparador;
    }

    @Override
    public int compareTo(Persona o) {
        // Si perro o es IGUAL a nosotros entonces devuelve 0
        // Si perro o viene ANTES que nosotros entonces devuelve -1
        // Si perro o viene DESPUES que nosotros entonces devuelve 1
        return comparador.compare(this, o);
    }
}




