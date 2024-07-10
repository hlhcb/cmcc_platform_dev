package com.cmcc.web.controller.system;
import com.alibaba.fastjson2.JSONObject;
import com.cmcc.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

/**
 * @author:terry
 * @create: 2024-04-14 06:19
 * @Description: 测试sql注入
 */
@RestController
@RequestMapping("/test")
public class TestOpenRaspWebService {
    private final Logger logger = LoggerFactory.getLogger(TestOpenRaspWebService.class);
    private String resultSetString1;
    private String resultSetString2;
    private String resultSetString3;
    private String resultSetString4;

    @PostMapping("/openrasp")
    public AjaxResult getRecidDateData(@RequestParam("userId") String userId) throws ClassNotFoundException, SQLException {

        logger.info("请求->测试opensasp->输入参数【userId:"+userId+"】");
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://81.69.194.235:23386/officer_app?useUnicode=true&characterEncoding=utf8", "root", "chQ*Mq%=2p!x");

        //3.创建执行sql语句的对象
        Statement statement = connection.createStatement();

        //4.sql语句
        String sql = "select * from sys_user where user_id= "+userId+";";

        //5.执行sql语句
        ResultSet resultSet = statement.executeQuery(sql);
        //6.对结果集进行处理
        if(resultSet.next()){
            logger.info("取值成功");
            //获取第一列和第二列的值，不是从0开始
            resultSetString1 = resultSet.getString(1);
            resultSetString2 = resultSet.getString(2);
            resultSetString3 = resultSet.getString(3);
            resultSetString4 = resultSet.getString(4);
            logger.info(resultSetString1+"--"+resultSetString2+"--"+resultSetString3+"--"+resultSetString4);
        }else {
            logger.info("取值失败");
        }

        //7.关闭
        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
        if (connection != null){
            connection.close();
        }

        JSONObject jsonObject = new JSONObject(16);
        jsonObject.put("data",resultSetString1+"--"+resultSetString2+"--"+resultSetString3+"--"+resultSetString4);
        logger.info("数据库返回结果->测试opensasp->返回结果参数【result:"+jsonObject+"】");
        return AjaxResult.success(jsonObject);
    }
}
