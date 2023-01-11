package com.cy.service;

import com.cy.model.Goods;
import com.cy.model.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GoodsService {
    public List<Map<String,Object>> getGoodsList(int recommendType) throws SQLException;
    public Map<String,Object> getScrollGood() throws SQLException;
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException;
    public Page selectPageByTypeID(int typeID, int pageNumber) throws SQLException;
    public Page getGoodsRecommendPage(int type,int pageNumber) throws SQLException;
    public Goods getGoodsById(int id) throws SQLException;
    public Page getSearchGoodsPage(String keyword, int pageNumber) throws SQLException;
    public void addRecommend(int id,int type) throws SQLException;
    public void removeRecommend(int id,int type) throws SQLException;
    public void insert(Goods goods) throws SQLException;
    public void update(Goods goods) throws SQLException;
    public void delete(int id) throws SQLException;
    public Page getGoodsPage(int typeId,int pageNo);
    public List<Goods> selectGoods(int typeId,int pageNo,int pageSize);
}
