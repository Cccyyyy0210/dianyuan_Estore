package com.cy.dao.impl;

import com.cy.dao.TypeDao;
import com.cy.model.Type;
import com.cy.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> GetAllType() throws SQLException {
        QueryRunner r=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from type";
        return r.query(sql,new BeanListHandler<Type>(Type.class));
    }

    @Override
    public Type selectTypeNameByID(int typeid) throws SQLException {
        QueryRunner r=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from type where id=?";
        return r.query(sql,new BeanHandler<Type>(Type.class),typeid);
    }

    @Override
    public Type select(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from type where id = ?";
        return r.query(sql, new BeanHandler<Type>(Type.class),id);
    }

    @Override
    public void insert(Type t) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into type(name) values(?)";
        r.update(sql,t.getName());
    }

    @Override
    public void update(Type t) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "update type set name=? where id = ?";
        r.update(sql,t.getName(),t.getId());
    }

    @Override
    public void delete(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "delete from type where id = ?";
        r.update(sql,id);
    }
}
