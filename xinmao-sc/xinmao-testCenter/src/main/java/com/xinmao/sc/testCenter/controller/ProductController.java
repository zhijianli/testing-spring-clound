package com.xinmao.sc.testCenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.sc.testCenter.domain.Product;

@RestController
public class ProductController {
	
	@RequestMapping(value="list",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<Product> listProduct(){
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1,"产品1"));
		list.add(new Product(2,"产品2"));
		list.add(new Product(3,"产品3"));
		return list;
	}

}
