package homework_03;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionAPI {

    public static void main(String[] args) {

        MyClass newClass = new MyClass();
        Class aclass = newClass.getClass();

        System.out.println(newClass + "\n" + aclass.getName() + "\n" + aclass.getSuperclass());

        /*
        * Выведем поля класса
        * */

        System.out.println("Fields class:");

        Field[] fields = aclass.getDeclaredFields();
        String currentValue = "";

        for (Field field :
                fields) {
            Class fieldType = field.getType();
//            Вытащим текущее значение поля
//            т.к метод get() не может читать значения с модификатором private
//            то что бы не было ошибки будем проверять модификатор доступа
//            а хер знает как это делать...
//            о нашел)
            try {
                if (Modifier.isPrivate(field.getModifiers())){
                    currentValue = "PRIVATE";
                }else
                currentValue = field.get(newClass).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            System.out.println("Name: " + field.getName() + "; Type: " + fieldType + "; Value: " + currentValue);




        }

    }
}
