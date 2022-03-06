package com.susu.generator.common;

import com.susu.generator.common.enums.DBType;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.dto.TableDTO;

import java.sql.*;

/**
 * <p>数据库连接</p>
 * @author sujay
 * @version 20:10 2022/3/5
 */
public class DBUtils {

    public static Boolean mySqlConnTest(String url,String username,String password) {

        //2.获得数据库链接
        Connection conn = null;
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static Boolean connTest(SourceDTO dto) {
        DBType type = DBType.getType(dto.getDbType());
        if (type == null) {
            return false;
        }
        switch (type) {
            case MY_SQL:
                return mySqlConnTest(dto.getConnUrl(),dto.getUsername(),dto.getPassword());
            case Order:
                System.out.println("");
                break;
            default :
                System.out.println("Invalid grade");
        }
        return false;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/generator?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        System.out.println(mySqlConnTest(url,username,password));
    }
}
