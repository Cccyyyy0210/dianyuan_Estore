package com.cy.dao;

import com.cy.model.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    public List<Type> GetAllType() throws SQLException;
    public Type selectTypeNameByID(int typeid) throws SQLException;
    public Type select(int id) throws SQLException;
    public void insert(Type t) throws SQLException;
    public void update(Type t) throws SQLException;
    public void delete(int id) throws SQLException;
}
