package com.mall.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.mall.pojo.Category;
import com.mall.pojo.PageBean;
import com.mall.pojo.Product;
import com.mall.service.CategoryService;
import com.mall.service.ProductService;
import com.mall.service.impl.CategoryServiceImpl;
import com.mall.service.impl.ProductServiceImpl;
import com.mall.util.FileUploadUtils;
import com.mall.util.StringUtils;
import com.mall.util.UUIDUtils;
import com.mall.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final int PAGESIZE = 10;
	
	private ProductService productService = new ProductServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private static final String SAVE_DIR_PATH = "/products/1/";
	private static final String PRODUCT_PREFIX = "products/1/";
	
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:新增商品
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		
		@SuppressWarnings("unchecked")
		List<FileItem> items = upload.parseRequest(request);
		Map<String,Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : items) {
			String fieldName = fileItem.getFieldName();
			if (fileItem.isFormField()) {
				String value = fileItem.getString("UTF-8");
				map.put(fieldName, value);
			} else {
				String name = fileItem.getName();
				String uniqueName = FileUploadUtils.getUniqueName(name);
				String saveDir = getServletContext().getRealPath(SAVE_DIR_PATH);
				map.put(fieldName, PRODUCT_PREFIX + uniqueName);
				
				File file = new File(saveDir, uniqueName);
				InputStream inputStream = fileItem.getInputStream();
				OutputStream outputStream = new FileOutputStream(file);
				
				//输出
				IOUtils.copy(inputStream, outputStream);
				IOUtils.closeQuietly(inputStream);
				IOUtils.closeQuietly(outputStream);
			}
		}
		
		//保存
		Product product = new Product();
		BeanUtils.populate(product, map);
		
		//设置表单没有提交的数据
		product.setPid(UUIDUtils.ranUUID());
		product.setPdate(new Date());
		product.setPflag(0);
		productService.add(product);
		return "adminProductServlet?method=pageProduct";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:分页查询商品
	 */
	public String pageProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageStr = request.getParameter("page");
		int page = 1;
		if (!StringUtils.isEmpty(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		
		PageBean<Product> pageBean = productService.adminPageProduct(page, PAGESIZE);
		request.setAttribute("page", pageBean);
		return "/admin/product/list.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:编辑商品
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pid = request.getParameter("pid");
		if (StringUtils.isEmpty(pid)) {
			return null;
		}
		
		Product product = productService.get(pid);
		request.setAttribute("product", product);
		//获取分类列表
		List<Category> findAllCategory = categoryService.findAllCategory();
		request.setAttribute("categorys", findAllCategory);
		return "/admin/product/edit.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:更新商品
	 */
	@SuppressWarnings("unchecked")
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		
		List<FileItem> items = upload.parseRequest(request);
		Map<String, Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : items) {
			String fieldName = fileItem.getFieldName();
			if (fileItem.isFormField()) {
				String value = fileItem.getString("UTF-8");
				map.put(fieldName, value);
			} else {
				String fileName = fileItem.getName();
				//没有选择文件，那么不更新图片
				if (!StringUtils.isEmpty(fileName)) {
					//上传图片
					//生成唯一文件名
					String uniqueName = FileUploadUtils.getUniqueName(fileName);
					//存放目录
					String saveDir = getServletContext().getRealPath(SAVE_DIR_PATH);
					String pimage = PRODUCT_PREFIX + uniqueName;
					map.put(fieldName, pimage);
					
					//输出文件
					File file = new File(saveDir, uniqueName);
					InputStream inputStream = fileItem.getInputStream();
					OutputStream outputStream = new FileOutputStream(file);
					
					//输出
					IOUtils.copy(inputStream, outputStream);
					IOUtils.closeQuietly(inputStream);
					IOUtils.closeQuietly(outputStream);
				}
			}
		}
		
		//更新数据库
		Product product = productService.get((String) map.get("pid"));
		BeanUtils.populate(product, map);
		productService.update(product);
		return "adminProductServlet?method=pageProduct";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:上下架
	 */
	public String changeShelve(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pid = request.getParameter("pid");
		String pflag = request.getParameter("pflag");
		if (!StringUtils.isEmpty(pid) || !StringUtils.isEmpty(pflag)) {
			productService.changeShelve(pid, Integer.parseInt(pflag));
		}
		
		if ("1".equals(pflag)) {
			return "adminProductServlet?method=pageProduct";
		} else {
			return "adminProductServlet?method=findDownProducts";
		}
		
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月18日
	 * @version 1.0
	 * desc:查询下架商品
	 */
	public String findDownProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Product> products = productService.findDownProducts();
		request.setAttribute("products", products);
		return "/admin/product/pushDown_list.jsp";
	}
	
}
