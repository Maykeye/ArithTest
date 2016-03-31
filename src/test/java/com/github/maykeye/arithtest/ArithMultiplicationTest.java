package com.github.maykeye.arithtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Title;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@Title("Multiplication test")
@RunWith(Parameterized.class)
public class ArithMultiplicationTest extends ArithTest
{
    @Parameterized.Parameters(name="#{index} -- ArithTest of {0} {2} {1} = {3}")
    public static Collection<Object[]> data() {
        return ArithTest.data("*");
    }

    public ArithMultiplicationTest(int operand1, int operand2, String operation, int expected){
        super(operand1, operand2, operation, expected);
    }

    @Test
    public void testMultiplication(){
        int actual = operand1 * operand2;
        assertEquals(expected, actual);
    }
}
