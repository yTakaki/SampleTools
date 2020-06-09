package com.example.demo.datelog.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.master.domain.model.Product;
import com.example.demo.master.domain.service.ProductService;

@Service
public class DatelogService {

	@Autowired
	private ProductService service;

	public Product selectOneProduct(String productId) {
		return service.selectOneProduct(productId);
	}

	public List<String> selectCompIdList(String productId) {
		List<String> list = new ArrayList<>();
		Product p = service.selectOneProduct(productId);
		if (p.getComp1().length()>0) { list.add(p.getComp1());}
		if (p.getComp2().length()>0) { list.add(p.getComp2());}
		if (p.getComp3().length()>0) { list.add(p.getComp3());}
		if (p.getComp4().length()>0) { list.add(p.getComp4());}
		if (p.getComp5().length()>0) { list.add(p.getComp5());}
		if (p.getComp6().length()>0) { list.add(p.getComp6());}
		if (p.getComp7().length()>0) { list.add(p.getComp7());}
		if (p.getComp8().length()>0) { list.add(p.getComp8());}
		if (p.getComp9().length()>0) { list.add(p.getComp9());}
		if (p.getComp10().length()>0) { list.add(p.getComp10());}
		return list;
	}

}
