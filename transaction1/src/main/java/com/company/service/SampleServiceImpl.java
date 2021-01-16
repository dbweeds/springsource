package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.mapper.SampleMapper1;
import com.company.mapper.SampleMapper2;

@Service("sample")
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMapper1 mapper1;

	@Autowired
	private SampleMapper2 mapper2;

	@Transactional
	@Override
	public void addData(String value) {
		mapper1.insertCal(value);// 컬럼 500
		mapper2.insertCal(value);// 컬럼 50
	}

}
