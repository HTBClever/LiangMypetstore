package org.csu.mypetstore.persistence;

import java.util.List;
import org.csu.mypetstore.domain.Category;

public interface CategoryDAO {
    //获取所有的大类
    List<Category> getCategoryList();
    //获取某一个类
    Category getCategory(String categoryId);
}
