package com.mavis.utils.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mavis.entity.Inventory;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class MyJdbc {

    //    静态连接池对象
    public static DataSource dataSource;

    static {
        boolean f = init();
        System.out.println("初始化连接池：" + f);
    }

    //    初始化加载配置
    private static boolean init() {
        try {
            Properties properties = new Properties();
            InputStream is = MyJdbc.class.getClassLoader().getResourceAsStream("druid.properties");
            if (is != null) {
                properties.load(is);
                dataSource = DruidDataSourceFactory.createDataSource(properties);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过连接池获取链接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConn4druid() throws Exception {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 一般方法获取链接
     *
     * @param ip
     * @param port
     * @param user
     * @param password
     * @param db
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConn(String ip, String port, String user, String password, String db) throws ClassNotFoundException, SQLException {
//        注册驱动
        Class.forName("com.mysql.jdbc.Driver");
//        定义url
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?useSSL=false";
//        获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
//        返回获取的链接
        return conn;
    }

    /**
     * 增删改类通用方法
     *
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int exUpdate(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        int i = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return i;
    }

    /**
     * 查找类通用方法
     *
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static List<HashMap> exQuery(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columuCountName = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columuCountName.add(metaData.getColumnName(i));
        }
        List<HashMap> obj = new ArrayList<>();
        while (rs.next()) {
            HashMap hashMap = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                hashMap.put(columuCountName.get(i), rs.getObject(i + 1));
            }
            obj.add(hashMap);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return obj;
    }

    /**
     * 查找类通用方法（包装成类）
     *
     * @param conn
     * @param T
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T> List<T> exQuery4class(Connection conn, Class T, String sql, Object... params) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columuCountName = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columuCountName.add(metaData.getColumnName(i));
        }
        List<HashMap> list = new ArrayList<>();
        while (rs.next()) {
            HashMap obj = new HashMap();
            for (int i = 0; i < columnCount; i++) {
                String name = columuCountName.get(i);
                Object value = rs.getObject(i + 1);
                obj.put(name, value);
            }
            list.add(obj);
        }

        List<T> rsT = new ArrayList<>();
        for (HashMap hashMap : list) {
            Constructor declaredConstructor = T.getDeclaredConstructor();
            T o = (T) declaredConstructor.newInstance();
            Set keys = hashMap.keySet();
            for (Object key : keys) {
                String name = (String) key;
                Field field = T.getDeclaredField(name);
                field.setAccessible(true);
                field.set(o, hashMap.get(key));
            }
            rsT.add(o);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return rsT;
    }

    //    测试功能区
    public static void main(String[] args) throws Exception {
        Connection conn = getConn4druid();
        String sql = "insert into Inventory values (null,101,10,'测试',now());";
        int i = MyJdbc.exUpdate(conn, sql);
        System.out.println(i);
    }
}