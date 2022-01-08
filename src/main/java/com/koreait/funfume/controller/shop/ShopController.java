/**
 * 
 */
package com.koreait.funfume.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.funfume.domain.Product;
import com.koreait.funfume.model.product.ProductService;

@Controller
public class ShopController {
	//공통 로직인 서비스 보유
	@Autowired
	private ProductService productService;	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getMain() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/index");
		
		return mav;
	}
	
	@RequestMapping(value="/product-detail")
	public ModelAndView getDetail(HttpServletRequest request, int product_id) {
		 Product product = productService.select(product_id);
		ModelAndView mav = new ModelAndView("shop/product-detail");
		mav.addObject("product",product);
		return mav;
		
	}
}
