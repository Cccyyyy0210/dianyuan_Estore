package com.cy.test;

import com.cy.model.Type;
import com.cy.service.TypeService;
import com.cy.service.impl.TypeServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TypeServiceTest {
TypeService typeService=new TypeServiceImpl();
    @Test
    public void getAllType() throws SQLException {
        List<Type> list=typeService.GetAllType();
        for(Type type:list){
            System.out.println(type.getName());
        }
    }

    @Test
    public void selectTypeNameByID() throws SQLException {
       Type type=typeService.selectTypeNameByID(1);
        System.out.println(type.getName());

    }

    @Test
    public void select() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}