package tools;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLog {
    private Logger logger;
    private FileHandler fh;
    private File file;
    private String filename;

    public CustomLog(String name) {
        logger = Logger.getLogger(name);
        filename = name;
    }

    public void Log (String message) {
        try {
            file = new File("target/" + filename + ".log");
            if (!file.exists()) {
                file.createNewFile();
            }
            fh = new FileHandler("target/" + filename + ".log");

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(message);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
