package easymall.service;

import java.util.List;

import easymall.po.Category;

public interface CategoryService {
	public void addCategory(Category category);
	public void deleteCategory(Integer id);
	public List<Category> findAllCategorys();
	public void updateCategory(Category category);
	public Category findcategory(String name);
	public Integer findcategoryIdByName(String name);
}
