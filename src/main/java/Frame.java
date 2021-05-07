
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private final static Logger logger = (Logger) LogManager.getRootLogger();
    private Persona persona = new Persona();
    PanelPersona panelPersona = new PanelPersona(persona);
    int x = 0;

    public Frame() {
        setTitle("ORDENAMIENTO PERSONAS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }


    private void init() {
        persona.addObserver(panelPersona);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panelPersona, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu mnuPersonas = new JMenu("Personas");
        JMenu mnuOrdenar = new JMenu("Ordenar");
        JMenu mnuHelp = new JMenu("Help");

        JMenuItem item1 = new JMenuItem("Nueva Persona");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Cliente crea nueva persona");
                nuevo();
            }
        });
        mnuPersonas.add(item1);

        JMenuItem item2 = new JMenuItem("Eliminar Todo");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Cliente elimina todas las personas de la lista");
                panelPersona.deleteLista();
                x = 0;
            }
        });
        mnuPersonas.add(item2);

        JMenuItem item3 = new JMenuItem("Por Nombre");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea proceder y ver la lista de personas creadas ordenadas por su nombre alfabéticamente?", "Confirmación de ordenamiento por nombre", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    logger.debug("Cliente ordena la lista de personas por su nombre");
                }
            }
        });
        mnuOrdenar.add(item3);

        JMenuItem item4 = new JMenuItem("Por Edad");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea proceder y ver la lista de personas creadas ordenadas por su edad?", "Confirmación de ordenamiento por edad", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    logger.debug("Cliente ordena la lista de personas por su edad");
                }
            }
        });
        mnuOrdenar.add(item4);

        JMenuItem item5 = new JMenuItem("Por Altura");
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea proceder y ver la lista de personas creadas ordenadas por su altura?", "Confirmación de ordenamiento por su altura", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    logger.debug("Cliente ordena la lista de personas por su altura");
                }
            }
        });
        mnuOrdenar.add(item5);

        JMenuItem item6 = new JMenuItem("About");
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Abre ventana explicando el funcionamiento de la app");
                JOptionPane.showMessageDialog(rootPane, "Funcionamiento de mi app:\n " +
                        "  1.- Presionar boton 'Personas'.\n" +
                        "  2.- Presionar boton 'Nueva Persona' e insertar los valores requeridos.\n" +
                        "  3.- Presionar boton 'Eliminar Todo' para eliminar la lista con las personas creadas y guardadas.\n" +
                        "  4.- Presionar boton 'Por Nombre' para ordenar a nuestras personas creadas por orden alfabético.\n" +
                        "  5.- Presionar boton 'Por Edad' para ordenar a nuestras personas creadas por su edad.\n" +
                        "  6.- Presionar boton 'Por Altura' para ordenar a nuestras personas creadas por su altura.\n" +
                        "  7.- Presionar boton 'Help' seguido del boton 'Exit' para terminar esta increíble app.");
            }
        });
        mnuHelp.add(item6);

        JMenuItem item7 = new JMenuItem("Exit");
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea cerrar mi increible app?", "Cerrar app", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    logger.debug("Usuario sale de la ventana y finaliza app");
                    System.exit(0);
                }
            }
        });
        mnuHelp.add(item7);


        menuBar.add(mnuPersonas);
        menuBar.add(mnuOrdenar);
        menuBar.add(mnuHelp);
        this.setJMenuBar(menuBar);
        this.pack();

    }


    public void nuevo() {
        JTextField txtNombre = new JTextField("");
        JTextField txtEdad = new JTextField("");
        JTextField txtAltura = new JTextField("");
        JTextField txtSexo = new JTextField("");

        Object[] formulario = {
                "Nombre:", txtNombre,
                "Edad:", txtEdad,
                "Altura:", txtAltura,
                "Sexo:", txtSexo
        };
        int option = JOptionPane.showConfirmDialog(rootPane, formulario, "Nueva Persona", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int edad = Integer.parseInt(txtEdad.getText());
                int altura = Integer.parseInt(txtAltura.getText());
                persona = new Persona(txtNombre.getText(), edad, altura, txtSexo.getText(), x);
                panelPersona.setPersona(persona);
                x = x + altura /2;
                logger.debug("Nombre: " + txtNombre.getText() + " --> Edad: " + edad
                        + " --> Altura: " + altura + " --> Sexo: " + txtSexo.getText());
            } catch (Exception e) {
                logger.debug("Error al guardar la información");
            }
        }
    }
}

