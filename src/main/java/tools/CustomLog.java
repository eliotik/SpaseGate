package tools;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLog {

    public static void Log (String filename, String message) {
        try {
            Logger logger = Logger.getLogger(filename);

            File file = new File("target/" + filename + ".log");
            if (!file.exists()) {
                System.out.println("exist");
                file.createNewFile();
            }
            FileHandler fh = new FileHandler("target/" + filename + ".log", true);

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
