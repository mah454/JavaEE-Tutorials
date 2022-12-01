package ir.moke.javaee;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class ApplicationInitializer {

    @Resource(lookup = "java:comp/env/jdbc/h2-datasource")
    private DataSource dataSource;

    public void onStartup(@Observes @Initialized(ApplicationScoped.class) Object obj) {
        System.out.println("########################");
        System.out.println("Application Initializing");
        System.out.println("########################");

        initializeDatabase();
        insertData();
        printDatabaseRows();
    }

    private void printDatabaseRows() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from person");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                String str = String.format("Id: %s     Name: %s", id, name);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertData() {
        String[] names = new String[]{"Sina", "Ali", "Mahdi", "Mohammad"};
        String insertSql = "insert into person(id,name) values(person_seq.nextval,'%s')";
        for (String name : names) {
            executeSql(String.format(insertSql, name));
        }
    }

    private void initializeDatabase() {
        String drop = "drop table person; drop sequence person_seq";
        String creteTable = "create table if not exists person(id int,name varchar(20))";
        String creteSequence = "create sequence if not exists person_seq";
        executeSql(drop);
        executeSql(creteTable);
        executeSql(creteSequence);
    }

    private void executeSql(String sql) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
