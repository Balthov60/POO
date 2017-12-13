package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServices {

    private static DBServices INSTANCE = new DBServices("res/baseComposite.sqlite");
    private Connection connection;

    private DBServices(String dbpath) {
        try
        {
            Class.forName("org.sqlite.JDBC").newInstance();
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbpath);
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {	return INSTANCE.connection;
    }
}
