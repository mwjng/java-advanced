package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());

        final Class<? extends User> userClass = user.getClass();
        final Field nameField = userClass.getDeclaredField("name");

        // private 필드에 접근 허용, private 메서드도 이렇게 호출
        nameField.setAccessible(true);
        nameField.set(user, "userB");
        System.out.println("변경된 이름 = " + user.getName());
    }
}