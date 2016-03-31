package com.github.maykeye.arithtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class ArithTest
{
    @Parameters(name="{index} -- ArithTest of {0} {2} {1} = {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0,0,"+",0},
                {1,1,"/",1},
                {2,1,"*",2},
                {2,1,"??",2},
                {2,0,"/",2},
                {1,-1,"-",2},
        });
    }

    private int operand1;
    private int operand2;
    private int expected;
    private String operation;

    public ArithTest(int operand1, int operand2, String operation, int expected){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.expected = expected;
    }

    @Test
    public void implTest(){
        int actual=0;

        if ("+".equals(operation)){
            actual = operand1+operand2;
        } else if ("-".equals(operation)) {
            actual = operand1-operand2;
        } else if ("*".equals(operation)) {
            actual = operand1*operand2;
        } else if ("/".equals(operation)) {
            actual = operand1/operand2;
        } else {
            fail(String.format("Unexpected operation %s", operation));
        }

        assertEquals(expected, actual);
    }


}
