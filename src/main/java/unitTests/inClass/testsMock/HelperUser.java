package unitTests.inClass.testsMock;

public class HelperUser {

    Helper helper;

    public HelperUser(Helper helper) {
        this.helper = helper;
    }

    public Integer helperUserMethod(Integer a, Integer b) {
        Integer a1 = (a + 10 - b);
        Integer a2 = b * 12;

        Integer res = helper.someHelperMethod(a1, a2);
        return res;
    }
}