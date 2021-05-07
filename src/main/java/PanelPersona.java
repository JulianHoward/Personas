import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

public class PanelPersona extends JPanel implements PropertyChangeListener {

    ListaPersonas<Persona> lista = new ListaPersonas<Persona>();
    private Persona persona;
    private final static Logger logger = (Logger) LogManager.getRootLogger();

    public PanelPersona(Persona pp) {
        persona = pp;
        setBackground(Color.GRAY);
    }

    public void setPersona(Persona pp) {
        this.persona = pp;
        lista.insertar(persona);
    }

    public void deleteLista(){
        ListaPersonas<Persona> delete = new ListaPersonas<Persona>();
        lista = delete;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Iterator<Persona> iterador= lista.iterator();
        super.paintComponent(g);
        while (iterador.hasNext()){
            persona = iterador.next();
            persona.dibujar(g);
        }
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
