package Service;

import Entities.TaskType;
import Interfaces.Mappers.EmployeeMapper;
import Interfaces.Mappers.TaskMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class BatisUtil {
    public static SqlSessionFactory getSessionFactory() throws IOException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword("HolyPreacher");
        dataSource.setUser("root");
        dataSource.setDatabaseName("employees_and_tasks");
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(EmployeeMapper.class);
        configuration.addMapper(TaskMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
