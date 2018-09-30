package unitTests.inClass;
import java.io.IOException;

public class SomeClass {

    public Integer getSumm(int a, int b) {
        return a + b;
    }

    public void doSome(int a) {
        if (a==5) try {
            throw new IOException("Some exeption");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}