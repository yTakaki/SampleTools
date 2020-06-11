package com.example.demo.master.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductRadio {

	private Map<String,Boolean> radioSetup;

	private Map<String,Boolean> radioFood;

	private Map<String,Integer> radioProductStatus;

	public Map<String,Boolean> initSetup() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("セットアップ品", true);
		radio.put("既製品", false);
		return radio;
	}

	public Map<String,Boolean> initFood() {
		Map<String,Boolean> radio = new LinkedHashMap<>();
		radio.put("食品", true);
		radio.put("食品以外", false);
		return radio;
	}

	public Map<String,Integer> initProductStatus() {
		Map<String,Integer> radio = new LinkedHashMap<>();
		radio.put("取扱可", 0);
		radio.put("終売予定", 1);
		radio.put("終了", 2);
		return radio;
	}

}
