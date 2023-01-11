package com.cy.dao.impl;

import com.cy.dao.GoodsDao;
import com.cy.model.Goods;
import com.cy.model.Recommend;
import com.cy.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    //获取推荐商品,品类为recommendType
    public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());

        //String sql="select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=? and r.goods_id=g.id and g.type_id=t.id";
        String sql="SELECT g.id,g.name,g.cover,g.price,t.name typename FROM goods g INNER JOIN TYPE t ON g.type_id=t.id WHERE t.id=?";
        return r.query(sql, new MapListHandler(),recommendType);
    }

    @Override
    //获取条幅商品
    public Map<String, Object> getScrollGood() throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql="select g.id,g.name,g.cover,g.price  from recommend r,goods g where type=4 and r.goods_id=g.id";
        return r.query(sql, new MapHandler());
    }

    @Override
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException {
        if(typeID==0)
        {
            String sql="select * from goods limit ? , ?";
            QueryRunner r=new QueryRunner(DBUtil.getDataSource());
            return  r.query(sql,new BeanListHandler<Goods>(Goods.class),(pageNumber-1)*pageSize,pageSize);
        }
        else
        {
            String sql="select * from goods where type_id=? limit ? , ?";
            QueryRunner r=new QueryRunner(DBUtil.getDataSource());
            return  r.query(sql,new BeanListHandler<Goods>(Goods.class),typeID,(pageNumber-1)*pageSize,pageSize);
        }
    }

    @Override
    public int getCountOfGoodsByTypeID(int typeID) throws SQLException {
        String sql="";
        QueryRunner r=new QueryRunner(DBUtil.getDataSource());
        if(typeID==0)
        {
            sql="select count(*) from goods";
            return r.query(sql,new ScalarHandler<Long>()).intValue();
        }
        else
        {
            sql="select count(*) from goods where type_id=?";
            return r.query(sql,new ScalarHandler<Long>(),typeID).intValue();
        }
    }

    @Override
    public List<Goods> selectGoodsbyRecommend(int type, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        if(type==0) {
            //当不添加推荐类型限制的时候
            String sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,type t where g.type_id=t.id order by g.id limit ?,?";
            return r.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNumber-1)*pageSize,pageSize);

        }

        String sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g,recommend r,type t where g.id=r.goods_id and g.type_id=t.id and r.type=? order by g.id limit ?,?";
        return r.query(sql, new BeanListHandler<Goods>(Goods.class),type,(pageNumber-1)*pageSize,pageSize);
    }
    public List<Goods> selectGoods(int typeId,int pageNo,int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        if(typeId==0) {
            String sql = "select * from goods limit ?,?";
            return r.query(sql, new BeanListHandler<Goods>(Goods.class), (pageNo-1)*pageSize,pageSize );
        }else {
            String sql = "select * from goods where type_id=? limit ?,?";
            return r.query(sql, new BeanListHandler<Goods>(Goods.class),typeId, (pageNo-1)*pageSize,pageSize );
        }
    }
    @Override
    public int getRecommendCountOfGoodsByTypeID(int type) throws SQLException {
        if(type==0)return getCountOfGoodsByTypeID(0);
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select count(*) from recommend where type=?";
        return r.query(sql, new ScalarHandler<Long>(),type).intValue();
    }

    @Override
    public Goods getGoodsById(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = ? and g.type_id=t.id";
        return r.query(sql, new BeanHandler<Goods>(Goods.class),id);
    }

    @Override
    public int getSearchCount(String keyword) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select count(*) from goods where name like ?";
        return r.query(sql, new ScalarHandler<Long>(),"%"+keyword+"%").intValue();
    }

    @Override
    public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from goods where name like ? limit ?,?";
        return r.query(sql, new BeanListHandler<Goods>(Goods.class),"%"+keyword+"%",(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public boolean isScroll(Goods g) throws SQLException {
        return isRecommend(g, 1);
    }

    @Override
    public boolean isHot(Goods g) throws SQLException {
        return isRecommend(g, 1);
    }

    @Override
    public boolean isNew(Goods g) throws SQLException {
        return isRecommend(g, 3);
    }

    @Override
    public boolean isRecommend(Goods g, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from recommend where type=? and goods_id=?";
        Recommend recommend = r.query(sql, new BeanHandler<Recommend>(Recommend.class),type,g.getId());
        if(recommend==null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void addRecommend(int id, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into recommend(type,goods_id) values(?,?)";
        r.update(sql,type,id);
    }

    @Override
    public void removeRecommend(int id, int type) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "delete from recommend where type=? and goods_id=?";
        r.update(sql,type,id);
    }

    @Override
    public void insert(Goods g) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(?,?,?,?,?,?,?,?)";
        r.update(sql,g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId());
    }

    @Override
    public void update(Goods g) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "update goods set name=?,cover=?,image1=?,image2=?,price=?,intro=?,stock=?,type_id=? where id=?";
        r.update(sql,g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId(),g.getId());
    }

    @Override
    public void delete(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DBUtil.getDataSource());
        String sql = "delete from goods where id = ?";
        r.update(sql,id);
    }

}
