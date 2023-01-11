package com.cy.service.impl;

import com.cy.dao.GoodsDao;
import com.cy.dao.impl.GoodsDaoImpl;
import com.cy.model.Goods;
import com.cy.model.Page;
import com.cy.service.GoodsService;
import com.cy.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao gDao=new GoodsDaoImpl();
    public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException {
        List<Map<String,Object>> list=null;
        list=gDao.getGoodsList(recommendType);
        return list;
    }

    public Map<String, Object> getScrollGood() throws SQLException {
        Map<String,Object> scroolGood=null;
        scroolGood=gDao.getScrollGood();
        return scroolGood;
    }

    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException {
        List<Goods> list=null;
        list=gDao.selectGoodsByTypeID(typeID,pageNumber,pageSize);
        return list;
    }

    @Override
    public Page selectPageByTypeID(int typeID, int pageNumber) throws SQLException {
        Page p=new Page();
        p.setPageNumber(pageNumber);
        int totalCount=0;
        totalCount=gDao.getCountOfGoodsByTypeID(typeID);
        p.SetPageSizeAndTotalCount(8,totalCount);

        List list=null;
        list=gDao.selectGoodsByTypeID(typeID,pageNumber,8);
        p.setList(list);
        return p;
    }

    @Override
    public Page getGoodsRecommendPage(int type, int pageNumber) throws SQLException {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        totalCount = gDao.getRecommendCountOfGoodsByTypeID(type);
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        list = gDao.selectGoodsbyRecommend(type, pageNumber, 8);
        for(Goods g : (List<Goods>)list) {
            g.setScroll(gDao.isScroll(g));
            g.setHot(gDao.isHot(g));
            g.setNew(gDao.isNew(g));

    }
        p.setList(list);
        return p;
    }

    @Override
    public Goods getGoodsById(int id) throws SQLException {
        Goods g=null;
        g = gDao.getGoodsById(id);
        return g;
    }

    @Override
    public Page getSearchGoodsPage(String keyword, int pageNumber) throws SQLException {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        totalCount = gDao.getSearchCount(keyword);
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        list = gDao.selectSearchGoods(keyword,pageNumber,8);
        p.setList(list);
        return p;
    }

    @Override
    public void addRecommend(int id, int type) throws SQLException {
        gDao.addRecommend(id, type);

    }

    @Override
    public void removeRecommend(int id, int type) throws SQLException {
        gDao.removeRecommend(id, type);

    }

    @Override
    public void insert(Goods goods) throws SQLException {
        gDao.insert(goods);

    }

    @Override
    public void update(Goods goods) throws SQLException {
        gDao.update(goods);

    }

    @Override
    public void delete(int id) throws SQLException {
        gDao.delete(id);
    }
    public Page getGoodsPage(int typeId,int pageNo) {
        Page p = new Page();
        p.setPageNumber(pageNo);
        int totalCount = 0;
        try {
            totalCount = gDao.getCountOfGoodsByTypeID(typeId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        try {
            list = gDao.selectGoods(typeId, pageNo, 8);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    public List<Goods> selectGoods(int typeId,int pageNo,int pageSize){
        List<Goods> list=null;
        try {
            list = gDao.selectGoods(typeId, pageNo, pageSize);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
