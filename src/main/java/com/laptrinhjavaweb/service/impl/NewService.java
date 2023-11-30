package com.laptrinhjavaweb.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.INewService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository  newRepository;

	@Autowired
	private NewConverter newConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for(NewEntity item : entities){
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models;
	}


	@Override
	public List<NewDTO> findAllByCategoryCode(String categoryCode,Pageable pageable) {

		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAllByCategoryCode(categoryCode,pageable);
		for(NewEntity item : entities){
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models;
	}


	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}


	@Override
	public NewDTO findById(Long id) {
		NewEntity newEntity = newRepository.findOne(id);
		return newConverter.toDTO(newEntity);
	}

	@Override
	@Transactional
	public NewDTO update(NewDTO newDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		System.out.println(newDTO.getTitle());
		NewEntity oldNew = newRepository.findOne(newDTO.getId());
		oldNew.setCategory(categoryEntity);
		newEntity = newConverter.toEntity(oldNew,newDTO);

		return newConverter.toDTO(newRepository.save(newEntity));
	}


	@Override
	@Transactional
	public NewDTO save(NewDTO newDTO, MultipartFile file) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity newEntity = new NewEntity();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")){
			System.out.println("not a valid file");
		}
		try {
			newDTO.setThumbnail(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(newDTO.getId() != null){
			NewEntity oldNew = newRepository.findOne(newDTO.getId());
			oldNew.setCategory(categoryEntity);
			newEntity = newConverter.toEntity(oldNew,newDTO);
		}else{
			newEntity = newConverter.toEntity(newDTO);
			newEntity.setCategory(categoryEntity);
		}
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	@Override
	public List<NewDTO> listSearch(String keyword,Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = new ArrayList<>();
		if(keyword != null){
			entities = newRepository.findByTitleLike("%"+keyword+"%",pageable);
		}else{
			entities = newRepository.findAll(pageable).getContent();
		}
		for(NewEntity item : entities){
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models;
	}


	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(long id: ids){
			newRepository.delete(id);
		}
	}
}
