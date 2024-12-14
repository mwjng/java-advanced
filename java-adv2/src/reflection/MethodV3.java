package reflection;

import reflection.data.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodV3 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("호출 메서드: ");
        final String methodName = scanner.nextLine();

        System.out.print("숫자1: ");
        final int num1 = scanner.nextInt();
        System.out.print("숫자2: ");
        final int num2 = scanner.nextInt();

        final Calculator calculator = new Calculator();
        final Class<? extends Calculator> calculatorClass = Calculator.class;
        final Method method = calculatorClass.getMethod(methodName, int.class, int.class);

        final Object returnValue = method.invoke(calculator, num1, num2);
        System.out.println("returnValue = " + returnValue);
    }
}
