import java.util.Comparator;

public class ComparadorPersona implements Comparator<Persona> {
    int x;

    @Override
    public int compare(Persona o1, Persona o2) {
        /*if (o2.getAltura() == o1.getAltura()) {
            return 0;
        }
        if (o2.getAltura() < o1.getAltura()) {
            return -1;
        }
        if (o2.getAltura() > o1.getAltura()) {
            return 1;
        }*/
        if(o2.getEdad() == o1.getEdad()){
            return 0;
        }
        if(o2.getEdad() < o1.getEdad()){
            return -1;
        }
        if(o2.getEdad() > o1.getEdad()){
            return 1;
        }
        return o2.getNombre().compareTo(o1.getNombre());
    }
}
