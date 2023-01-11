package com.cy.dao;

import com.cy.model.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GoodsDao {
    //select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=2 and r.goods_id=g.id and g.type_id=t.id
    //获取热销商品
    public List<Map<String,Object>> getGoodsList(int recommendType) throws SQLException;
    public List<Goods> selectGoods(int typeId,int pageNo,int pageSize) throws SQLException;
    //获取条幅商品
    public Map<String,Object> getScrollGood() throws SQLException;
   //根据品类获取商品
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException;
   //获取某品类商品种数
    public int getCountOfGoodsByTypeID(int typeID) throws SQLException;
    public List<Goods> selectGoodsbyRecommend(int type,int pageNumber,int pageSize) throws SQLException;
    public int getRecommendCountOfGoodsByTypeID(int type) throws SQLException;
    public Goods getGoodsById(int id) throws SQLException;
    public int getSearchCount(String keyword) throws SQLException;
    public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) throws SQLException;
    public boolean isScroll(Goods g) throws SQLException;
    public boolean isHot(Goods g) throws SQLException;
    public boolean isNew(Goods g) throws SQLException;
    public boolean isRecommend(Goods g,int type) throws SQLException;
    public void addRecommend(int id,int type) throws SQLException;
    public void removeRecommend(int id,int type) throws SQLException;
    public void insert(Goods g) throws SQLException;
    public void update(Goods g) throws SQLException;
    public void delete(int id) throws SQLException;
}
