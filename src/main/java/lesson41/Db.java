package lesson41;

import org.hibernate.Session;

import java.sql.*;

public class Db {
    static String sql = """
    DROP SCHEMA game;
""";
    public static void con()  {
         try(var con = ConnectionManager.get(); Statement statement = con.createStatement();){
            System.out.println(con.getTransactionIsolation());

            //statement.execute("CREATE SCHEMA `test`");
            //statement.execute(sql);
            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY(`id`));");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)" +
                    "VALUES (1,'иванов','иван');");
//            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)" +
//                    "VALUES (2,'петров','петр');");
//            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)" +
//                    "VALUES (3,'сидоров','сидр');");
//            ResultSet set  = statement.executeQuery("SELECT * FROM test.table;");
//            while (set.next()) {
//                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
//                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void conHibernate(){
        Connector connector = new Connector();
        try(Session session = connector.getSession()){
            /** создание обектов в базе.
            Magic magic = new Magic("волшебная стрела", 10,10,0);
            session.beginTransaction();
            session.save(magic);
            magic = new Magic("волшебная стрела", 10,10,0);session.save(magic);
            magic = new Magic("волшебная стрела", 10,10,0);session.save(magic);
            magic = new Magic("волшебная стрела", 10,10,0);session.save(magic);
            magic = new Magic("волшебная стрела", 10,10,0);session.save(magic);
            session.getTransaction().commit();
            session.close();
            */
            /** просмотр из базы обьектов.
            List<Magic> bocks =session.createQuery("FROM Magic", Magic.class).getResultList();
            bocks.forEach(System.out::println);
            */
            /** изменение объекта в базе по id.
            String hql = "from Magic where id = :id";
            Query<Magic> query = session.createQuery(hql, Magic.class);
            query.setParameter("id", 85);
            Magic magic = query.getSingleResult();
            System.out.println(magic);
            magic.setAttBonus(100);
            magic.setName("ярость");
            session.beginTransaction();
            session.update(magic);
            session.getTransaction().commit();
             */
            /** удаление объектов с базы
            Transaction t = session.beginTransaction();
            List<Magic> bocks =session.createQuery("FROM Magic", Magic.class).getResultList();
            bocks.forEach(session::delete);
            t.commit();
             */

        }catch (Exception e){
            e.getStackTrace();
        }
        /** создание одного объекта в базе.
//        Magic magic = new Magic("волшебная стрела", 10,10,0);
//        session.beginTransaction();
//        session.save(magic);
//        session.getTransaction().commit();
//        session.close();
         */
    }
}
