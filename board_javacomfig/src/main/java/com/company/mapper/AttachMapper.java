package com.company.mapper;

import java.util.List;

import com.company.domain.FileAttach;

public interface AttachMapper {
	public int insert(FileAttach attach);

	public int remove(int bno);

	public List<FileAttach> getOldFiles();
}
