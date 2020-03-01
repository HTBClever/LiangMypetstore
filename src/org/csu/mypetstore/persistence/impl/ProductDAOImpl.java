package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT_LIST_BY_ID="select PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String GET_PRODUCT="SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID =?";
    private static final String GET_PRODUCT_SEARCH="select PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList=new ArrayList<>();
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_PRODUCT_LIST_BY_ID);
            ps.setString(1,categoryId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setProductId(rs.getString(1));
                product.setName(rs.getString(2));
                product.setCategoryId(rs.getString(4));
                product.setDescription(rs.getString(3));
                productList.add(product);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String product) {
        Product product1=null;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_PRODUCT);
            ps.setString(1,product);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                product1=new Product();
                product1.setProductId(rs.getString(1));
                product1.setCategoryId(rs.getString(4));
                product1.setName(rs.getString(2));
                product1.setDescription(rs.getString(3));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product1;
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        List<Product> productList=new ArrayList<>();
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_PRODUCT_SEARCH);
            ps.setString(1,"%"+keyword+"%");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Product product=new Product();
                product.setProductId(rs.getString(1));
                product.setName(rs.getString(2));
                product.setCategoryId(rs.getString(4));
                product.setDescription(rs.getString(3));
                productList.add(product);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
