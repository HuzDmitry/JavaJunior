package lesson1.task2;

    /*

    1. Осуществлять учет продовольственных и непродовольственных товаров, которыми торгует UMarket.

    2. Предоставлять покупателям возможность фильтрации продовольственных товаров по следующим видам:
        • Снеки;
        • Полуфабрикаты;
        • Продукты для приготовления;
        • Все продовольственные товары.
    3. Формировать онлайн корзину из продовольственных товаров.
    4. Осуществлять балансировку онлайн корзины с целью получения такого набора продуктов питания,
        который включает все основные питательные элементы.


    Особенности бизнес-логики сервиса UMarket.

    Модуль онлайн покупок должен осуществлять контроль за попадающими в покупательскую корзину продуктами, а именно:
        • в онлайн корзину можно добавить только продовольственные товары;
        • в зависимости от желания покупателя, в онлайн корзине могут находиться как все присутствующие в UMarket
          продовольственные товары, так и исключительно товары той категории,
          которую выбрал покупатель посредством настроек фильтрации (фильтрация по видам продовольственных товаров).

    Необходимо предусмотреть типобезопасность онлайн корзины, так как в момент «автоматической балансировки» она
    должна пополняться продовольственными товарами из списка товаров UMarket.
    Необходимо учесть, что в случае формирования онлайн корзины конкретного вида продовольственных товаров,
    дополнять корзину необходимо товарами именно из выбранной покупателем категории продовольственных товаров.

    С целью упрощения алгоритма балансировки онлайн корзины, будем считать, что каждый продовольственный
    продукт содержит всего лишь один питательный элемент: белки / жиры / углеводы.
    Следовательно, есть вероятность, что все выбранные товары будут представителями
    одного и того же питательного элемента (например, углеводными).
    В свою очередь, балансировка делает так, чтобы в корзине были представлены все питательные элементы.

    */

import java.util.Scanner;

public class Program {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UMarket market = new UMarket();
        System.out.println("Добро пожаловать в магазин U-Market");

        while (true) {
            System.out.println("================================================================");
            System.out.println("0 - Завершение работы приложения");
            System.out.println("1 - Отобразить полный список товаров");
            System.out.println("2 - Сформировать онлайн корзину из снеков");
            System.out.println("3 - Сформировать онлайн корзину из полуфабрикатов");
            System.out.println("4 - Сформировать онлайн корзину из продуктов для приготовления");
            System.out.println("5 - Сформировать онлайн корзину из любых продовольственные товаров");
            System.out.println("Выберите пункт меню: ");

            if (scanner.hasNextInt()) {
                int no = scanner.nextInt();
                scanner.nextLine();
                switch (no) {
                    case 0 -> {
                        System.out.println("Завершение работы приложения.");
                        return;
                    }
                    case 1 -> {
                        System.out.println("Список товаров:");
                        market.printThings(Thing.class);
                    }
                    case 2 -> CreateCart(Snack.class, market);
                    case 3 -> CreateCart(SemiFinishedFood.class, market);
                    case 4 -> CreateCart(HealthyFood.class, market);
                    case 5 -> CreateCart(Food.class, market);
                    default -> System.out.println("Пункт меню не существует.\nПожалуйста, повторите попытку ввода.");
                }
            }
            else{
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }

        }

    }

    /**
     * Сформировать онлайн корзину из продовольственных товаров
     * @param market Магазин и список товаров
     * @param <T> Тип товара
     */
    static <T extends Food> void CreateCart(Class<T> clazz, UMarket market)
    {
        Cart<T> cart = new Cart<>(clazz, market);
        while (true)
        {
            System.out.println("Список доступных товаров:");
            System.out.println("[0] Завершение формирования корзины + балансировка");
            market.printThings(clazz);
            System.out.print("Укажите номер товара для добавления: ");
            if (scanner.hasNextInt())
            {
                int no = scanner.nextInt();
                scanner.nextLine();
                if (no == 0) {
                    cart.cardBalancing();
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoodstuffs();
                    return;
                } else {
                    T thing = market.getThingByIndex(clazz, no);
                    if (thing == null) {
                        System.out.println("Некорректный номер товара.\nПожалуйста, повторите попытку ввода.");
                        continue;
                    }
                    cart.getFoodstuffs().add(thing);
                    System.out.println("Товар успешно добавлен в корзину.");
                    System.out.println("Ваша корзина содержит продукты:");
                    cart.printFoodstuffs();
                }
            }
            else{
                System.out.println("Некорректный пункт меню.\nПожалуйста, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
    }

}
