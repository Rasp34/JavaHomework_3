package homework_03;

/**
 * Created by a.krivosheev on 12.01.2016.
 */
public class MyClass {

    /*
    * Поля класса разных типов
    * */
    public int publicInt = 3;
    public String localString = "ASD2123ASA";
    protected int protectedInt = 2;
    byte aByte = 10;
    short aShort = 111;
    int aInt = 1213;
    long aLong = 1225324234;
    float aFloat = 21432.1f;
    double aDouble = 12345.5678;
    char aChar = 'A';
    boolean aBoolean = true;
    private int privateInt = 1;

    /*
    * Несколько методов для приватной переменной
    * */

//    Конструктор для метода
    public MyClass(int publicInt, String localString) {
        this.publicInt = publicInt;
        this.localString = localString;
    }

    public void setPrivateInt(int privateInt) {
        this.privateInt = privateInt;
    }

    public int getPrivateInt() {
        return privateInt;
    }

}
