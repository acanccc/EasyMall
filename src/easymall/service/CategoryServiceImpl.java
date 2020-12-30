package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.CategoryDao;
import easymall.po.Category;
import easymall.pojo.Kind;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryDao.deleteFromProducts(id);
		categoryDao.deleteFromCategory(id);
	}

	@Override
	public List<Category> findAllCategorys() {
		return categoryDao.allcategorys();
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updatecategory(category);
	}

	@Override
	public Category findcategory(String name) {
		// TODO Auto-generated method stub
		return categoryDao.findcategory(name);
	}

	@Override
	public Integer findcategoryIdByName(String name) {
		return categoryDao.findcategoryIdByName(name);
	}

	@Override
	public List<Kind> findKind() {
		List<Kind> kinds=categoryDao.findKind();
		return kinds;
	}

}
