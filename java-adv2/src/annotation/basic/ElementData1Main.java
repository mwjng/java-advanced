package annotation.basic;

import java.util.Arrays;

public class ElementData1Main {

    public static void main(String[] args) {
        final Class<ElementData1> annoClass = ElementData1.class;
        final AnnoElement annotation = annoClass.getAnnotation(AnnoElement.class);

        final String value = annotation.value();
        System.out.println("value = " + value);

        final int count = annotation.count();
        System.out.println("count = " + count);

        final String[] tags = annotation.tags();
        System.out.println("tags = " + Arrays.toString(tags));
    }
}
