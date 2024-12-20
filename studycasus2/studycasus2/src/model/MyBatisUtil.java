package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;

public class MyBatisUtil {

    private static SqlSessionFactory factory;

    static {
        try {
            String resource = "mybatis-config.xml"; // Nama file konfigurasi MyBatis Anda
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}
