package DZHibernate;

//Урок 4. Базы данных и инструменты взаимодействия с ними
//        Создайте базу данных (например, SchoolDB).
//        В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
//        Настройте Hibernate для работы с вашей базой данных.
//        Создайте Java-класс Course, соответствующий таблице Courses,
//        с необходимыми аннотациями Hibernate.
//        используя Hibernate, напишите код для вставки, чтения,
//        обновления и удаления данных в таблице Courses.
//        Убедитесь, что каждая операция выполняется в отдельной транзакции.
public class Program {

    static final String createMysql = """
            CREATE SCHEMA `schooldb`
            """;
    static final String dropMysql = """
            DROP SCHEMA `schooldb`
            """;
    static final String useMysql = """
            USE `schooldb`
            """;

    static final String createTable = "CREATE TABLE `courses` (`id` INT NOT NULL, `title` VARCHAR(45) NULL, `duration` INT NULL, PRIMARY KEY(`id`))";

    public static void main(String[] args) {
        CoursesController controller = new CoursesController();
        //ConnectorMysql con = controller.getConnectorMysql();
        Course user1 = Course.builder()
                .title("история")
                .id(3)
                .duration(10)
                .build();

        try{
//            con.setRiderMysql(createMysql);
//            con.setRiderMysql(useMysql);
//            con.setRiderMysql(dropMysql);
            controller.createData(user1);
            System.out.println("создан обьект");

            user1 = controller.getData(3);
            System.out.println("обьект загружен");
            System.out.println(user1);

            user1.setTitle("java");
            user1.setDuration(100);
            controller.updateData(user1);
            user1 = controller.getData(3);
            System.out.println(user1);
            controller.deleteData(user1);






        }catch (Exception e){
            e.getStackTrace();
        }

    }
}
