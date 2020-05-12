package lambdasinaction.mybatis.mapper;

import java.util.List;
import java.util.Map;

public interface BookMapper {

    /**
     * 查询得到所有的图书
     *
     * @return
     */
    List<Map<Object, Object>> queryAllBook();
}
