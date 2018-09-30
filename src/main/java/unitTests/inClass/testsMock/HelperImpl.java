package unitTests.inClass.testsMock;

public class HelperImpl implements Helper {
    public Integer someHelperMethod(Integer a, Integer b) {
        return a+b;
    }

    public void anotherHelperMethod(Integer a, Integer b) {
        System.out.println(a + ";" + b);
    }


}
