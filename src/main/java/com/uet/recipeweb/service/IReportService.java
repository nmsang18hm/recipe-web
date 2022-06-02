package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.ReportCommentDTO;
import com.uet.recipeweb.dto.ReportRecipeDTO;
import com.uet.recipeweb.dto.ReportUserDTO;

public interface IReportService {
	public ReportRecipeDTO saveReportRecipe(ReportRecipeDTO reportRecipeDTO);
	public List<ReportRecipeDTO> findAllReportRecipeByStatusFalse();
	public List<ReportRecipeDTO> findAllReportRecipeByUserId(Long userId);
	
	public ReportCommentDTO saveReportComment(ReportCommentDTO reportCommentDTO);
	public List<ReportCommentDTO> findAllReportCommentByStatusFalse();
	public List<ReportCommentDTO> findAllReportCommentByUserId(Long userId);
	
	public ReportUserDTO saveReportUser(ReportUserDTO reportUserDTO);
	public List<ReportUserDTO> findAllReportUserByStatusFalse();
	public List<ReportUserDTO> findAllReportUserByUserId(Long userId);
}
