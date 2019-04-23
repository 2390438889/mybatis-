package javaDIYFree.typeHandler;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Hearts
 * @date 2019/4/22
 * @desc
 */
@Intercepts({@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
            ,@Signature(type = Executor.class,method = "update",args = {MappedStatement.class, Object.class})})
public class MybatisSqlLog implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        final Object param = invocation.getArgs()[1];
        final String sql = ms.getBoundSql(param).getSql();
        System.out.println(formatSql(sql,"[ \n]+"));
        return invocation.proceed();
    }
    private String formatSql(String sql,String replaceRegexWithNone){
        StringBuffer format = new StringBuffer();
        final String[] words = sql.split(replaceRegexWithNone);
        for (String word : words) {
            format.append(word+" ");
        }
        return format.toString();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {

    }
}
