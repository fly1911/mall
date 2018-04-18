package com.mall.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestParam {
	
	@Test
	public void testParam() {
		add();
	}

	public void add(String...strings){
		if (strings.length == 0) {
			System.out.println(0);
		}
		
	}
	

}
