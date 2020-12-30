package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;

@Repository("categoryDao")
@Mapper
public interface CategoryDao {
	void addCategory(Category category);
	void deleteFromProducts(Integer id);
	void deleteFromCategory(Integer id);
	List<Category> allcategorys();
	void updatecategory(Category category);
	Category findcategory(String name);
	Integer findcategoryIdByName(String name);
}
