package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<?> aClass = Class.forName("reflection.data.BasicData");

        final Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        final Object instance = constructor.newInstance("hello");
        System.out.println("instance = " + instance);

        final Method method1 = aClass.getDeclaredMethod("call");
        method1.invoke(instance);
    }
}
