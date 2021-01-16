package com.company.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper2 {
	@Insert("insert into tbl_sample2 values(#{value})")
	public int insertCal(String value);
}
