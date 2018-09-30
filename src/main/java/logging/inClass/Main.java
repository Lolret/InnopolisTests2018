package logging.inClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        System.err.println("ERRRROOOOOR");

        systemErrExample();






    }

    private static void systemErrExample() {
        try {
            System.setErr((new PrintStream(new FileOutputStream("errLog.log"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.err.println("Message to file");


        try {
            throw new Exception("Wild exeption occured!1 Fight him");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}