package com.dzb.console.service;

import org.springframework.stereotype.Service;

import com.github.promeg.pinyinhelper.Pinyin;

@Service
public class PyService {

	public String toPinyinFirstLetter(String str) {
		
		char[] chars = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i< chars.length; i++) {
			String py = Pinyin.toPinyin(chars[i]);
			sb.append(py.substring(0, 1));
		}
		return sb.toString();
	}
}
