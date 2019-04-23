package javaDIYFree.typeHandler;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import sun.security.provider.MD5;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Hearts
 * @date 2019/4/17
 * @desc
 */
@MappedJdbcTypes(JdbcType.VARCHAR)//需要替代的默认的JDBC类型,泛型类型String说明替代String类型
public class VarcharTypeHandler extends BaseTypeHandler<String> {

    /**
     * 对设置的值进行处理
     * @param preparedStatement
     * @param i
     * @param s
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        //我在传进来的字符串后面加了一串字符串
        preparedStatement.setString(i,s+">>>>>>>>>>>>>>>>");
    }

    /**
     * 对获取值的操作进行扩展,列名方式
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s);
    }

    /**
     * 对获取值的操作进行扩展，索引方式
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    /**
     * 对其他获取值的操作进行扩展
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i);
    }
}
