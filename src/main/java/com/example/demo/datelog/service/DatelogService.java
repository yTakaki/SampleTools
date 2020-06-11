package com.example.demo.datelog.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.datelog.model.CompDatelog;
import com.example.demo.master.model.Product;
import com.example.demo.master.service.ProductService;

@Service
@Transactional
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

	public List<CompDatelog> selectCompList(String productId,LocalDate shipmentDate) {
		List<String> list = selectCompIdList(productId);
		List<CompDatelog> compList = new ArrayList<>();
		for (String str:list) {
			Product p = selectOneProduct(str);
			CompDatelog cp = new CompDatelog(p.getProductId(),p.getProductCd(),
					p.getProductName(),p.isFood(),shipmentDate.plusDays(p.getPermitPeriod()));
			compList.add(cp);
		}
		return compList;
	}

}
