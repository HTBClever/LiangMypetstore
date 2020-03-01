package org.csu.mypetstore.persistence;

import java.sql.*;

public class DBUtil {
        /* 加载驱动包 */
        static {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        /* 获得连接的方法 */
        public static Connection getconn() {
            //数据库服务器路径，bookSys为数据库名
            String url = "jdbc:mysql://localhost:3306/mypetstore?useUnicode=yes&characterEncoding=UTF-8";
            String user = "root";
            String password = "lfz521...";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 返回连接对象
            return conn;
        }

        /* 关闭连接的方法 */
        public static void closeAll(Connection conn, PreparedStatement ps,
                                    ResultSet rs) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("链接成功！");
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

}
