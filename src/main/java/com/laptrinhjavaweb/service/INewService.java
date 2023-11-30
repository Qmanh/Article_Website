package com.laptrinhjavaweb.service;

import java.sql.Timestamp;
import java.util.List;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	List<NewDTO> findAllByCategoryCode(String categoryCode,Pageable pageable);
	int getTotalItem();
	NewDTO findById(Long id);
	NewDTO update(NewDTO newDTO);
	NewDTO save(NewDTO newDTO,MultipartFile file);
	List<NewDTO> listSearch(String keyword,Pageable pageable);
	void delete(Long[] ids);
}
