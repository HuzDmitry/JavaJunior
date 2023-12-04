package DZReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionClasses {
    private Class<?> clazz;
    private Object obj;

    public void setClazz(Object obj) {
        this.obj = obj;
        this.clazz = obj.getClass();
    }
    public void analisClass() throws IllegalAccessException {
        StringBuilder build = new StringBuilder();
        build.append("=======================");
        build.append(" \n класс: ");
        build.append(clazz.getSimpleName());

        Constructor[] construct = clazz.getDeclaredConstructors();
        build.append(getConstructor(construct));

        Method[] methods = clazz.getDeclaredMethods();
        build.append(getMethod(methods));

        Field[] field = clazz.getDeclaredFields();
        build.append(getField(field));

        build.append("\n родительский класс: ");
        build.append(clazz.getSuperclass());
        if (clazz.getSuperclass() != Object.class) {
            Method[] met = clazz.getSuperclass().getDeclaredMethods();
            build.append(getMethod(met));
            Field[] superField = clazz.getSuperclass().getDeclaredFields();
            build.append(getField(superField));
        }
        build.append("\n=======================");
        System.out.printf("%s", build.toString());
    }

    private String getField(Field[] field) throws IllegalAccessException {
        StringBuilder build1 = new StringBuilder();
        for (Field f : field){
            f.setAccessible(true);
            build1.append(" \n поле: ")
                    .append(f.getName())
                    .append(" = ")
                    .append(f.get(obj));
        }
        return build1.toString();
    }

    private String getMethod(Method[] methods){
        StringBuilder build1 = new StringBuilder();
        for (Method m : methods){
            build1.append("\n мотод: ")
                    .append(m.getName())
                    .append("\n параметры метода ")
                    .append(Arrays.toString(m.getParameters()));

        }
        return  build1.toString();
    }
    private String getConstructor(Constructor[] constructors){
        StringBuilder build1 = new StringBuilder();
        for (Constructor c : constructors){
            build1.append("\n конструктор: ")
                    .append(c.getName())
                    .append("\nпараметры конструктора: ")
                    .append(Arrays.toString(c.getParameters()));


        }
        return build1.toString();
    }
    public boolean callMethod(String nameMethod) throws InvocationTargetException, IllegalAccessException {
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method){
            if (m.getName().equals(nameMethod)) {
                System.out.printf("\nметод [%s] есть в этом объекте. ", nameMethod);
                System.out.println("вызываю найденный метод:");
                m.invoke(obj);
                return true;
            }
        }
        System.out.printf("\n [%s] метода нет.\n", nameMethod);
        return false;
    }

}
