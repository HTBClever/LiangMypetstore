package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.persistence.CategoryDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_CATEGORY_LIST="select CATID as categoryId, NAME, DESCN as description from CATEGORY";
    private static final String GET_CATEGORY="select CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY  WHERE CATID =?";
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList=new ArrayList<>();
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_CATEGORY_LIST);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setCategoryId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setDescription(rs.getString(3));
                categoryList.add(category);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category=null;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_CATEGORY);
            ps.setString(1,categoryId);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                category=new Category();
                category.setCategoryId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setDescription(rs.getString(3));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
