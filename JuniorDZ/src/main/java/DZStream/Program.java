package DZStream;
/*
Урок 1. Лямбды и Stream API.
Напишите программу, которая использует Stream API для обработки списка чисел.
 Программа должна вывести на экран среднее значение всех четных чисел в списке.

2. *Дополнительная задача: Переработать метод балансировки корзины товаров cardBalancing()
 с использованием Stream API
 */

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        int[] chisla = {1, 2, 4, 5, 12, 6, 3, 8, 9, 10};
        String average = Arrays.stream(chisla)
                .filter(c -> c % 2 == 0)
                .average()
                .toString();
        System.out.println(average);

    }
}
