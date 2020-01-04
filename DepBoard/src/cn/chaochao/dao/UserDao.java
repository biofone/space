package cn.chaochao.dao;

import cn.chaochao.domain.Msg;
import cn.chaochao.domain.User;
import cn.chaochao.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //根据用户名和密码查询用户
    public User findUserByNameAndPassword(String empName, String password) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= JDBCUtils.getConnection();
            ps=conn.prepareStatement("select*from user where username=? and password=?");
            ps.setString(1,empName);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                User user=new User();
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }
    //根据用户名查询用户
    public boolean findUserByName(String empName) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn= JDBCUtils.getConnection();
            ps=conn.prepareStatement("select*from user where username=?");
            ps.setString(1,empName);
            rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }
    //新增用户信息
    public void addUser(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=JDBCUtils.getConnection();
            ps=conn.prepareStatement("insert into user values(null,?,?)");
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }

    public void sendMsg(Msg msg) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("insert into msg values(null,?,?,?,now())");
            ps.setString(1, msg.getSendUser());
            ps.setString(2, msg.getMsgs());
            ps.setString(3, msg.getRecUser());
//            ps.setString(4, msg.getSendTime());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }
    public List<Msg> sendMsgMain() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Msg> listMsg = new ArrayList<>();

        try {
            conn=JDBCUtils.getConnection();
            ps = conn.prepareStatement("select*from msg ");
            rs = ps.executeQuery();
            while (rs.next()) {
                Msg msgg = new Msg();
                msgg.setId(rs.getInt(1));
                String ss=rs.getString("msgs");
                msgg.setMsgs(rs.getString("msgs"));
                msgg.setSendUser(rs.getString("sendUser"));
                msgg.setRecUser(rs.getString("recUser"));
                msgg.setSendTime(rs.getString("sendTime"));
                listMsg.add(msgg);
            }
            for (Msg mm:listMsg) {
                System.out.println(mm.getMsgs());
                System.out.println(mm.getRecUser());
            }
            return listMsg;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }

    public void delete(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn=JDBCUtils.getConnection();
            ps = conn.prepareStatement("delete from msg where id=?");
            ps.setString(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }
}
