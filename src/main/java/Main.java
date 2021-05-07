import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Main {

    private final static Logger logger = (Logger) LogManager.getRootLogger();

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
        logger.debug("Empieza el programa");
    }
}

