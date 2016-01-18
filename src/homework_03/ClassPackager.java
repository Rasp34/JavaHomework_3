package homework_03;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Упаковка класса в файл и загрузка его из файла
 */
public class ClassPackager {
    private static String name;

    public static void main(String[] args) throws Exception {

        /**
         * Создадим новый класс который будет сериализовать в файл
         * */
        MyClass oneClass = new MyClass(10, "OldString");
        /**
         * Запишем класс в файл ----------------------------------------------
         */
        /**
         * Создадим файл врайтер для записи в файл
        * */
        FileWriter fileWriter = new FileWriter("classkeeper.txt");

        /**
         * Запишем имя класса
         * */
        Class serClass = oneClass.getClass();
        name = serClass.getName();

        fileWriter.write(name + "\n\n");

        /*
        * Считаем поля класса и значения
        * */

        Field[] fields = serClass.getDeclaredFields();
        String currentFieldName = " ";
        String currentFieldType = " ";
        String currentFieldValue = " ";


        for (Field field :  fields) {


//            Считаем имя
            currentFieldName = field.getName();

//            Считаем тип
            currentFieldType = field.getType().toString();

//            Считаем поля
//            Необходимо проверять модификатор
            if (Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
                currentFieldValue = field.get(oneClass).toString();
            }else {
                    currentFieldValue = field.get(oneClass).toString();
                }


//            Запишем сроку в файл
            fileWriter.write(currentFieldType + " " + currentFieldName + " " + currentFieldValue + "\n");


        }


        fileWriter.close();

        /**
         * Считаем класс из файла ----------------------------------------
         */

        /**
         * Создадим объект
          */
        Constructor constr = serClass.getDeclaredConstructor(Integer.TYPE, String.class);
        MyClass newClass = (MyClass) constr.newInstance(13, "New Object");


        /**
         * Создадим сканнер
         */
        File file = new File("classkeeper.txt");
        Scanner scanner = new Scanner(file);
        String word = " ";
//        Для доступа к приватной переменной создадим поле
        Field privateField = MyClass.class.getDeclaredField("protectedInt");
        privateField.setAccessible(true);

        String str;
        while (scanner.hasNext()){

            word = scanner.next();
            switch (word){
                case "publicInt":
                    newClass.publicInt = scanner.nextInt();
                    break;

                case "localString":
                    newClass.localString = scanner.next();
                    break;

                case "protectedInt":
                    newClass.protectedInt = scanner.nextInt();
                    break;

                case "aByte":
                    newClass.aByte = scanner.nextByte();
                    break;

                case "aShort":
                    newClass.aShort = scanner.nextShort();
                    break;

                case "aInt":
                    newClass.aInt = scanner.nextInt();
                    break;

                case "aLong":
                    newClass.aLong = scanner.nextLong();
                    break;

                case "aFloat":
                        newClass.aFloat = Float.parseFloat(scanner.next());
                    break;

                case "aDouble":
                    newClass.aDouble = Double.parseDouble(scanner.next());
                    break;

                case "aChar":
                    newClass.aChar = scanner.next().charAt(0);
                    break;

                case "aBoolean":
                    newClass.aBoolean = scanner.nextBoolean();
                    break;

                case "privateInt":
//                    ????????????????????????????
//                    Не получается получить доступ к переменной через setAccessible()
//                    поэтому через геттер
                    newClass.setPrivateInt(scanner.nextInt());
                    break;

                default:
                    break;

            }
        }
        System.out.println(newClass.publicInt);
        System.out.println(newClass.localString);
        System.out.println(newClass.protectedInt);
        System.out.println(newClass.aByte);
        System.out.println(newClass.aShort);
        System.out.println(newClass.aInt);
        System.out.println(newClass.aLong);
        System.out.println(newClass.aFloat);
        System.out.println(newClass.aDouble);
        System.out.println(newClass.aChar);
        System.out.println(newClass.aBoolean);
        System.out.println(newClass.getPrivateInt());

    }


}
