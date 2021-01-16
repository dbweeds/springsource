package com.company.service;

import java.util.List;

import com.company.domain.PersonVO;

public interface PersonService {
	public int insertPerson(String id, String name);

	public int updatePerson(String id, String name);

	public int deltePerson(String id);

	public String getPerson(String id);

	public List<PersonVO> selectAll();
}
