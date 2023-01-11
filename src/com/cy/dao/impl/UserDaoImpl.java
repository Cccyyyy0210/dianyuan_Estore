package com.cy.dao.impl;

import com.cy.dao.UserDao;
import com.cy.model.User;
import com.cy.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
        r.update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getName(),user.getPhone(),user.getAddress(),user.isIsadmin(),user.isIsvalidate());

    }

    @Override
    public boolean isUsernameExist(String username) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user where username = ?";
        User u = r.query(sql, new BeanHandler<User>(User.class),username);
        if(u==null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean isEmailExist(String email) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user where email = ?";
        User u = r.query(sql, new BeanHandler<User>(User.class),email);
        if(u==null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public User selectByUsernamePassword(String username, String password) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user where username=? and password=?";
        return r.query(sql, new BeanHandler<User>(User.class),username,password);
    }

    @Override
    public User selectByEmailPassword(String email, String password) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user where email=? and password=?";
        return r.query(sql, new BeanHandler<User>(User.class),email,password);
    }

    @Override
    public User selectById(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user where id=?";
        return r.query(sql, new BeanHandler<User>(User.class),id);
    }

    @Override
    public void updateUserAddress(User user) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql ="update user set name = ?,phone=?,address=? where id = ?";
        r.update(sql,user.getName(),user.getPhone(),user.getAddress(),user.getId());
    }

    @Override
    public void updatePwd(User user) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql ="update user set password = ? where id = ?";
        r.update(sql,user.getPassword(),user.getId());
    }

    @Override
    public int selectUserCount() throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select count(*) from user";
        return r.query(sql, new ScalarHandler<Long>()).intValue();
    }

    @Override
    public List selectUserList(int pageNo, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from user limit ?,?";
        return r.query(sql, new BeanListHandler<User>(User.class), (pageNo-1)*pageSize,pageSize );
    }

    @Override
    public void delete(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "delete from user where id = ?";
        r.update(sql,id);
    }
}
