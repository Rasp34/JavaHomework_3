package homework_03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Упаковка класса в файл и загрузка его из файла
 */
public class ClassPackager {
    private static String name;

    public static void main(String[] args) throws Exception {

        /**
         * Создадим новый класс который будет сериализовать в файл
         * */
        MyClass oneClass = new MyClass(10, "ONE");

        /**
         * Создадим файл врайтер для записи в файл
        * */
        FileWriter fileWriter = new FileWriter("classkeeper.txt");

        /**
         * Запишем имя класса
         * */
        Class serClass = oneClass.getClass();
        name = serClass.getName();

        fileWriter.write(name + "\n");

        /*
        * Считаем поля класса и значения
        * */

        Field[] fields = serClass.getDeclaredFields();
        String currentField;
        String fieldValue;


        for (Field field :  fields) {

//            Необходимо проверять модификатор
            currentField = field.getName();
            if (Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
                fieldValue = field.get(oneClass).toString();
            }
            if (Modifier.isPublic(field.getModifiers())){
                fieldValue = field.get(oneClass).toString();

            }


        }

        fileWriter.close();

    }


}
