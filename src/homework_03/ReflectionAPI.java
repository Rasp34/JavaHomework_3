package homework_03;

import java.lang.reflect.*;

public class ReflectionAPI {

    public static void main(String[] args) {

        MyClass newClass = new MyClass(22, "DDRS12S223");
        Class aclass = newClass.getClass();

        MyClass createClass = null;
        Class bclass = null;


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
        System.out.println("Constructor:");

        Constructor[] constr = aclass.getDeclaredConstructors();
        for (Constructor currentConstr  :   constr) {
            Class[] paramTypes = currentConstr.getParameterTypes();
            System.out.print(currentConstr.getName() + " (");

            for (Class paramType    :   paramTypes ) {
                System.out.print(paramType.getName() + "; ");
            }
            System.out.println(")");

        }

        /*
        * Динамически создадим экземпляр класса
        * */

        try {
            System.out.println("Create new object");
            Constructor constrClass = aclass.getConstructor(Integer.TYPE, String.class);
            createClass = (MyClass) constrClass.newInstance(33, "It's new create object");
            //Проверим поля объекта
            System.out.println("Get field new object: " + createClass.publicInt + "; " + createClass.localString + ";");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /*
        * Прочтем методы класса, включая методы суперкласса
        * */

        Method[] methods = aclass.getMethods();
        System.out.println("Class methods:");
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
        }

        /*
        * Вызовем метод по названию
        * */

        Method oneMethod = null;

        try {
            System.out.print("Invoke method with specifc name: ");
            oneMethod = aclass.getMethod("setPrivateInt", Integer.TYPE);
            System.out.println(oneMethod.getName() + " [with parameter 78]");
            oneMethod.invoke(createClass, 78);
//            и выведем переменную для проверки
            System.out.println("Check with method Get: " + createClass.getPrivateInt());


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /*
        * Обратимся к приватному полю
        * */

        try {
            Field privateField = MyClass.class.getDeclaredField("privateInt");
//            Разрешим доступ к приватному полю
            privateField.setAccessible(true);
            System.out.println("View private field: " + privateField.getName() + " = " + privateField.get(createClass));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
