package DZHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectorMysql {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private final SessionFactory sessionFactory;
    private static Connection connect = null;

    public ConnectorMysql(){
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

    public void setRiderMysql(String sql) throws SQLException {
        try (PreparedStatement statement = connect.prepareStatement(sql)) {
            statement.execute();
            connect.commit();
            connect.close();

        }
    }
}
