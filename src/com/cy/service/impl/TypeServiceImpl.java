package com.cy.service.impl;

import com.cy.dao.TypeDao;
import com.cy.dao.impl.TypeDaoImpl;
import com.cy.model.Type;
import com.cy.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    TypeDao tDao=new TypeDaoImpl();
    @Override
    public List<Type> GetAllType() throws SQLException {
        List<Type> list=null;
        list=tDao.GetAllType();
        return list;
    }

    @Override
    public Type selectTypeNameByID(int typeid) throws SQLException {
        Type type=null;
        type=tDao.selectTypeNameByID(typeid);
        return type;
    }

    @Override
    public Type select(int id) throws SQLException {
        Type t=null;
        t = tDao.select(id);
        return t;
    }

    @Override
    public void insert(Type t) throws SQLException {
        tDao.insert(t);
    }

    @Override
    public void update(Type t) throws SQLException {
        tDao.update(t);
    }

    @Override
    public boolean delete(int id) {
        try {
            tDao.delete(id);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
