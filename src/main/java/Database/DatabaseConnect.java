package database_console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect{
  Connection con = DriverManager.getConnection( "diethelper-201120:us-east1:users://35.185.24.120", "root", "DietHelper" );
}