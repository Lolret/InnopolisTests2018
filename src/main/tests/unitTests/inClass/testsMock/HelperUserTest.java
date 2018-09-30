package unitTests.inClass.testsMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HelperUserTest {

    private HelperUser helperUser;
    private Helper helper = Mockito.mock(Helper.class);

    @BeforeEach
    void beforeEach() {
        helperUser = new HelperUser(helper);
    }

    @Test
    void helperUserMethodTest() {
        when(helper.someHelperMethod(anyInt(), anyInt())).thenReturn(1);
        Integer res = helperUser.helperUserMethod(1,2);
        verify(helper).someHelperMethod(9, 24);
        assertEquals(new Integer(1), res);
    }
}