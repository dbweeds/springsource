package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.PersonVO;

public interface PersonMapper {
	// sql 구문을 인라인 방식
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insert(@Param("id") String id, @Param("name") String name);
//
//	@Update("update person set name=#{name} where id=#{id}")
//	public int update(@Param("id") String id, @Param("name") String name);
//
//	@Delete("delete from person where id=#{id}")
//	public int delete(@Param("id") String id);

	// sql 구문은 xml방식
	public int insert(@Param("id") String id, @Param("name") String name);

	public int update(@Param("id") String id, @Param("name") String name);

	public int delete(@Param("id") String id);

	public String getPerson(@Param("id") String id);

	public List<PersonVO> selectAll();
}
