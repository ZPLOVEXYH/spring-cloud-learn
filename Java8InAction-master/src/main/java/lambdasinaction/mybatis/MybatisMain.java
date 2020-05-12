package lambdasinaction.mybatis;

import lambdasinaction.mybatis.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MybatisMain {

    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = ssf.openSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<Map<Object, Object>> resultList = bookMapper.queryAllBook();
        System.out.println("返回得到的结果集为：" + resultList);
    }
}
