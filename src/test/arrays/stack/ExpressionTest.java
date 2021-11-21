package arrays.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressionTest {

    @Test
    public void testInfixToPrefix() throws Exception {
        Assertions.assertEquals("+A*BC", InfixToPrefix.convert("A+(B*C)"));
        Assertions.assertEquals("++-+KL*MN*//*^OPWUVTQ", InfixToPrefix.convert("K+L-M*N+(O^P)*W/U/V*T+Q"));
    }

    @Test
    public void testInvalidExpression() {
        Assertions.assertThrows(Exception.class, () -> {
            InfixToPrefix.convert("A+(B*C))");
        });

        Assertions.assertThrows(Exception.class, () -> {
            InfixToPrefix.convert("(A+(B*C)");
        });

        Assertions.assertThrows(Exception.class, () -> {
            InfixToPrefix.convert("(A+(B*C");
        });
    }

    @Test
    public void testInfixToPostfix() {
        InfixToPostfix.convert("A+(B*C)");
    }
}
