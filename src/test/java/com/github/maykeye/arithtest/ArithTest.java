package com.github.maykeye.arithtest;

import java.io.BufferedInputStream;
import java.io.InputStream;

import java.util.*;

import ru.yandex.qatools.allure.annotations.Parameter;

/*
 Expectes test.dat of form

 operand1;operand2;operation;result
 operand1;operand2;operation;result
 operand1;operand2;operation;result
 operand1;operand2;operation;result


*/
abstract class ArithTest
{
    static Map<String, ArrayList<Object[]>> lines;


    @Parameter("Operand1")
    protected int operand1;

    @Parameter("Operand2")
    protected int operand2;

    @Parameter("Operation")
    protected String operation;

    @Parameter("Expected")
    protected int expected;

    protected static Collection<Object[]> data(String oper) {
        if (lines == null){
            lines = new HashMap<String, ArrayList<Object[]>>();
            lines.put("+", new ArrayList<Object[]>());
            lines.put("-", new ArrayList<Object[]>());
            lines.put("*", new ArrayList<Object[]>());
            lines.put("/", new ArrayList<Object[]>());

            final InputStream resourceAsStream = ArithTest.class.getClassLoader().getResourceAsStream("test.dat");
            assert resourceAsStream != null : "Can't open test.dat resource";

            final Scanner scanner = new Scanner(new BufferedInputStream(resourceAsStream));
            int lineNo=0;
            while(scanner.hasNextLine()){
                final String[] fields = scanner.nextLine().split(";");
                lineNo++;

                if (fields.length != 4) {
                    System.err.printf("Expected 4 fields at line %d\n", lineNo);
                    continue;
                }

                if (fields[2].length() != 1 || !"+-*/".contains(fields[2])) {
                    System.err.printf("Invalid operation at line %d\n", lineNo);
                    continue;
                }

                ArrayList<Object[]> list = lines.get(fields[2]);
                try {
                    list.add(new Object[]{
                            Integer.parseInt(fields[0]),
                            Integer.parseInt(fields[1]),
                            fields[2],
                            Integer.parseInt(fields[3]),
                    });
                } catch (NumberFormatException ex) {
                    System.err.printf("Invalid operands/result in line %d\n", lineNo);
                }
            }
        }
        return lines.get(oper);
    }

    public ArithTest(int operand1, int operand2, String operation, int expected){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.expected = expected;
    }
}

