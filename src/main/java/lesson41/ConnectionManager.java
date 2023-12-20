package lesson41;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;

    static {
        initConnectionPool();
    }

    private static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertesUtils.get(URL_KEY),
                    PropertesUtils.get(USER_KEY),
                    PropertesUtils.get(PASSWORD_KEY));
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
    private static void initConnectionPool(){
        String pollSize = PropertesUtils.get("db.poll.size");
        int size = pollSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(pollSize);
        pool = new ArrayBlockingQueue(size);

        for (int i = 0; i < size; i++){
            Connection connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args)->method.getName().equals("close") ?
                    pool.add((Connection) proxy) : method.invoke(connection, args));
            pool.add(proxyConnection);
        }
    }
    public static Connection get(){
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private ConnectionManager(){}
}
