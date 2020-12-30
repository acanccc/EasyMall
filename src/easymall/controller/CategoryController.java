package easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Category;
import easymall.pojo.Kind;
import easymall.service.CategoryService;

@Controller("categoryController")
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("/addcategory")
	public String addCategory(Category category,HttpSession session,Model model) {
		Category _category=categoryService.findcategory(category.getName());
		if(_category==null) {
			categoryService.addCategory(category);
		}
		else {
			model.addAttribute("message","该账号已存在");
		}
		return "redirect:/admin/category/showcategory";
	}
	@RequestMapping("/deletecategory")
	public String deletecategory(Integer id,HttpSession session){
		System.out.println("成功运行");
		categoryService.deleteCategory(id);
		return "redirect:/admin/category/showcategory";
	}
	@RequestMapping("/updatecategory")
	public String updatecategory(Integer id,String name,String description) {
		Category category=new Category();
		category.setId(id);
		category.setDescription(description);
		category.setName(name);
		System.out.println(name);
		System.out.println(description);
		categoryService.updateCategory(category);
		return "redirect:/admin/category/showcategory";
	}
	@RequestMapping("/showcategory")
	public String showcategory(HttpSession session,Model model){
		List<Category> categorys=categoryService.findAllCategorys();
		List<Kind> kinds=categoryService.findKind();
		model.addAttribute("categorys",categorys);
		model.addAttribute("kinds", kinds);
		return "category";
	}
}
