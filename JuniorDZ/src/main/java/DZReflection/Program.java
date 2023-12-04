package DZReflection;

import java.lang.reflect.InvocationTargetException;

/*
Урок 2. Reflection API.
        Задача 1:
        Создайте абстрактный класс "Animal" с полями "name" и "age".
        Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat")
         с уникальными полями и методами.
        Создайте массив объектов типа "Animal" и с использованием Reflection API
        выполните следующие действия:
        Выведите на экран информацию о каждом объекте.
        Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.

        Дополнительная задача:
        Доработайте метод генерации запроса на удаление объекта из таблицы БД
        (DELETE FROM <Table> WHERE ID = '<id>')
        В классе QueryBuilder который мы разработали на семинаре.
*/
public class Program {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Animal[] animal = {
                new Dog("Миша", "2","черный"),
                new Dog("Буян","4","белый"),
                new Cat("Мурзик","3"),
                new Cat("Кнопа","1")
        };
        ReflectionClasses cls = new ReflectionClasses();
        for (Animal an : animal){
            cls.setClazz(an);
            cls.analisClass();
            cls.callMethod("makeSound");
        }

    }
}
