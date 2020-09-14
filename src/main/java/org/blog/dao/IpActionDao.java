package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.IpAction;

import javax.sql.RowSet;
import javax.sql.RowSetReader;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface IpActionDao {
    Integer count();

    List<IpAction> list(@Param("ip") String ip);

    List<Map<String,Object>> ipActionCount(@Param("ip") String ip);

    void save(IpAction ipAction);

}
