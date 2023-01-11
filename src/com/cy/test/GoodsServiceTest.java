package com.cy.test;

import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GoodsServiceTest {
GoodsService goodsService=new GoodsServiceImpl();
    @Test
    public void getGoodsList() throws SQLException {
        System.out.println(goodsService.getGoodsList(1));
    }

    @Test
    public void getScrollGood() throws SQLException {
        System.out.println(goodsService.getScrollGood());
    }

    @Test
    public void selectGoodsByTypeID() throws SQLException {
        System.out.println(goodsService.selectGoodsByTypeID(1, 1, 5));
    }

    @Test
    public void selectPageByTypeID() throws SQLException {
        System.out.println(goodsService.selectPageByTypeID(2, 1));
    }

    @Test
    public void getGoodsRecommendPage() {
    }

    @Test
    public void getGoodsById() throws SQLException {
        System.out.println(goodsService.getGoodsById(8));
    }

    @Test
    public void getSearchGoodsPage() {
    }

    @Test
    public void addRecommend() {
    }

    @Test
    public void removeRecommend() {
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