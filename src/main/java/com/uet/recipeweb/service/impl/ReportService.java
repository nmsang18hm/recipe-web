package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.ReportCommentDTO;
import com.uet.recipeweb.dto.ReportRecipeDTO;
import com.uet.recipeweb.dto.ReportUserDTO;
import com.uet.recipeweb.entity.CommentEntity;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.entity.ReportCommentEntity;
import com.uet.recipeweb.entity.ReportRecipeEntity;
import com.uet.recipeweb.entity.ReportUserEntity;
import com.uet.recipeweb.entity.UserEntity;
import com.uet.recipeweb.repository.CommentRepository;
import com.uet.recipeweb.repository.RecipeRepository;
import com.uet.recipeweb.repository.ReportCommentRepository;
import com.uet.recipeweb.repository.ReportRecipeRepository;
import com.uet.recipeweb.repository.ReportUserRepository;
import com.uet.recipeweb.repository.UserRepository;
import com.uet.recipeweb.service.IReportService;

@Service
public class ReportService implements IReportService {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ReportRecipeRepository reportRecipeRepository;
	
	@Autowired
	ReportCommentRepository reportCommentRepository;
	
	@Autowired
	ReportUserRepository reportUserRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public ReportRecipeDTO saveReportRecipe(ReportRecipeDTO reportRecipeDTO) {
		ReportRecipeEntity reportRecipeEntity = new ReportRecipeEntity();
		if (reportRecipeDTO.getId() != null) {
			reportRecipeEntity = reportRecipeRepository.getById(reportRecipeDTO.getId());
			modelMapper.map(reportRecipeDTO, reportRecipeEntity);
		} else {
			reportRecipeEntity = modelMapper.map(reportRecipeDTO, ReportRecipeEntity.class);
			reportRecipeEntity.setStatus((short) 0);
		}
		if (reportRecipeDTO.getUserId() != null) {
			UserEntity userEntity = userRepository.getById(reportRecipeDTO.getUserId());
			reportRecipeEntity.setUserReportRecipe(userEntity);
		}
		if (reportRecipeDTO.getRecipeId() != null) {
			RecipeEntity recipeEntity = recipeRepository.getById(reportRecipeDTO.getRecipeId());
			reportRecipeEntity.setReportedRecipe(recipeEntity);
		}
		reportRecipeEntity = reportRecipeRepository.save(reportRecipeEntity);
		ReportRecipeDTO result = modelMapper.map(reportRecipeEntity, ReportRecipeDTO.class);
		result.setUserId(reportRecipeEntity.getUserReportRecipe().getId());
		result.setRecipeId(reportRecipeEntity.getReportedRecipe().getId());
		return result;
	}

	@Override
	public List<ReportRecipeDTO> findAllReportRecipeByStatusFalse() {
		List<ReportRecipeEntity> reportRecipeEntities = reportRecipeRepository.findAllByStatus((short) 0);
		return mapRecipeReportEntityListToDTO(reportRecipeEntities);
	}

	@Override
	public List<ReportRecipeDTO> findAllReportRecipeByUserId(Long userId) {
		List<ReportRecipeEntity> reportRecipeEntities  = userRepository.getById(userId).getReportRecipeEntities();
		return mapRecipeReportEntityListToDTO(reportRecipeEntities);
	}

	@Override
	public ReportCommentDTO saveReportComment(ReportCommentDTO reportCommentDTO) {
		ReportCommentEntity reportCommentEntity = new ReportCommentEntity();
		if (reportCommentDTO.getId() != null) {
			reportCommentEntity = reportCommentRepository.getById(reportCommentDTO.getId());
			modelMapper.map(reportCommentDTO, reportCommentEntity);
		} else {
			reportCommentEntity = modelMapper.map(reportCommentDTO, ReportCommentEntity.class);
			reportCommentEntity.setStatus((short) 0);
		}
		if (reportCommentDTO.getUserId() != null) {
			UserEntity userEntity = userRepository.getById(reportCommentDTO.getUserId());
			reportCommentEntity.setUserReportComment(userEntity);
		}
		reportCommentEntity = reportCommentRepository.save(reportCommentEntity);
		ReportCommentDTO result = modelMapper.map(reportCommentEntity, ReportCommentDTO.class);
		result.setUserId(reportCommentEntity.getUserReportComment().getId());
		CommentEntity commentEntity = commentRepository.getById(reportCommentEntity.getCommentId());
		result.setRecipeId(commentEntity.getRecipeId());
		result.setCommentContent(commentEntity.getContent());
		return result;
	}

	@Override
	public List<ReportCommentDTO> findAllReportCommentByStatusFalse() {
		List<ReportCommentEntity> reportCommentEntities = reportCommentRepository.findAllByStatus((short) 0);
		return mapCommentReportEntityListToDTO(reportCommentEntities);
	}

	@Override
	public List<ReportCommentDTO> findAllReportCommentByUserId(Long userId) {
		List<ReportCommentEntity> reportCommentEntities = userRepository.getById(userId).getReportCommentEntities();
		return mapCommentReportEntityListToDTO(reportCommentEntities);
	}

	@Override
	public ReportUserDTO saveReportUser(ReportUserDTO reportUserDTO) {
		ReportUserEntity reportUserEntity = new ReportUserEntity();
		if (reportUserDTO.getId() != null) {
			reportUserEntity = reportUserRepository.getById(reportUserDTO.getId());
			modelMapper.map(reportUserDTO, reportUserEntity);
		} else {
			reportUserEntity = modelMapper.map(reportUserDTO, ReportUserEntity.class);
			reportUserEntity.setStatus((short) 0);
		}
		if (reportUserDTO.getReportUserId() != null) {
			UserEntity userEntity = userRepository.getById(reportUserDTO.getReportUserId());
			reportUserEntity.setReportUser(userEntity);
		}
		if (reportUserDTO.getReportedUserId() != null) {
			UserEntity userEntity = userRepository.getById(reportUserDTO.getReportedUserId());
			reportUserEntity.setReportedUser(userEntity);
		}
		reportUserEntity = reportUserRepository.save(reportUserEntity);
		ReportUserDTO result = modelMapper.map(reportUserEntity, ReportUserDTO.class);
		result.setReportUserId(reportUserEntity.getReportUser().getId());
		result.setReportedUserId(reportUserEntity.getReportedUser().getId());
		return result;
	}

	@Override
	public List<ReportUserDTO> findAllReportUserByStatusFalse() {
		List<ReportUserEntity> reportUserEntities = reportUserRepository.findAllByStatus((short) 0);
		return mapUserReportEntityListToDTO(reportUserEntities);
	}

	@Override
	public List<ReportUserDTO> findAllReportUserByUserId(Long userId) {
		List<ReportUserEntity> reportUserEntities = userRepository.getById(userId).getReportUserEntities();
		return mapUserReportEntityListToDTO(reportUserEntities);
	}
	
	private List<ReportUserDTO> mapUserReportEntityListToDTO(List<ReportUserEntity> reportUserEntities) {
		List<ReportUserDTO> reportUserDTOs = new ArrayList<>();
		for (ReportUserEntity reportUserEntity : reportUserEntities) {
			ReportUserDTO reportUserDTO = modelMapper.map(reportUserEntity, ReportUserDTO.class);
			reportUserDTO.setReportUserId(reportUserEntity.getReportUser().getId());
			reportUserDTO.setReportedUserId(reportUserEntity.getReportedUser().getId());
			reportUserDTOs.add(reportUserDTO);
		}
		return reportUserDTOs;
	}
	
	private List<ReportCommentDTO> mapCommentReportEntityListToDTO(List<ReportCommentEntity> reportCommentEntities) {
		List<ReportCommentDTO> reportCommentDTOs = new ArrayList<>();
		for (ReportCommentEntity reportCommentEntity : reportCommentEntities) {
			ReportCommentDTO reportCommentDTO = modelMapper.map(reportCommentEntity, ReportCommentDTO.class);
			reportCommentDTO.setUserId(reportCommentEntity.getUserReportComment().getId());
			CommentEntity commentEntity = commentRepository.getById(reportCommentEntity.getCommentId());
			reportCommentDTO.setRecipeId(commentEntity.getRecipeId());
			reportCommentDTO.setCommentContent(commentEntity.getContent());
			reportCommentDTOs.add(reportCommentDTO);
		}
		return reportCommentDTOs;
	}
	
	private List<ReportRecipeDTO> mapRecipeReportEntityListToDTO(List<ReportRecipeEntity> reportRecipeEntities) {
		List<ReportRecipeDTO> reportRecipeDTOs = new ArrayList<>();
		for (ReportRecipeEntity reportRecipeEntity : reportRecipeEntities) {
			ReportRecipeDTO reportRecipeDTO = modelMapper.map(reportRecipeEntity, ReportRecipeDTO.class);
			reportRecipeDTO.setUserId(reportRecipeEntity.getUserReportRecipe().getId());
			reportRecipeDTO.setRecipeId(reportRecipeEntity.getReportedRecipe().getId());
			reportRecipeDTOs.add(reportRecipeDTO);
		}
		return reportRecipeDTOs;
	}
}
