package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.converter.ObjectConverter;
import com.uet.recipeweb.dto.ReportCommentDTO;
import com.uet.recipeweb.dto.ReportRecipeDTO;
import com.uet.recipeweb.dto.ReportUserDTO;
import com.uet.recipeweb.service.IReportService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReportAPI {
	@Autowired
	IReportService reportService;
	
	@PostMapping("/user/reports/recipes")
	@PreAuthorize("hasAuthority('user')")
	public ReportRecipeDTO createReportRecipe(@RequestBody ReportRecipeDTO reportRecipeDTO) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		reportRecipeDTO.setUserId(userId);
		return reportService.saveReportRecipe(reportRecipeDTO);
	}
	
	@PutMapping("/admin/reports/recipes/{reportId}")
	@PreAuthorize("hasAuthority('admin')")
	public ReportRecipeDTO updateReportRecipe(@RequestBody ReportRecipeDTO reportRecipeDTO, @PathVariable("reportId") Integer reportId) {
		reportRecipeDTO.setId(reportId);
		return reportService.saveReportRecipe(reportRecipeDTO);
	}
	
	@GetMapping("/admin/reports/recipes")
	@PreAuthorize("hasAuthority('admin')")
	public List<ReportRecipeDTO> getAllReportRecipes() {
		return reportService.findAllReportRecipeByStatusFalse();
	}
	
	@GetMapping("/user/reports/recipes")
	@PreAuthorize("hasAuthority('user')")
	public List<ReportRecipeDTO> getMyReportRecipes() {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return reportService.findAllReportRecipeByUserId(userId);
	}
	
	@PostMapping("/user/reports/comments")
	@PreAuthorize("hasAuthority('user')")
	public ReportCommentDTO createReportComment(@RequestBody ReportCommentDTO reportCommentDTO) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		reportCommentDTO.setUserId(userId);
		return reportService.saveReportComment(reportCommentDTO);
	}
	
	@PutMapping("/admin/reports/comments/{reportId}")
	@PreAuthorize("hasAuthority('admin')")
	public ReportCommentDTO updateReportComment(@RequestBody ReportCommentDTO reportCommentDTO, @PathVariable("reportId") Integer reportId) {
		reportCommentDTO.setId(reportId);
		return reportService.saveReportComment(reportCommentDTO);
	}
	
	@GetMapping("/admin/reports/comments")
	@PreAuthorize("hasAuthority('admin')")
	public List<ReportCommentDTO> getAllReportComments() {
		return reportService.findAllReportCommentByStatusFalse();
	}
	
	@GetMapping("/user/reports/comments")
	@PreAuthorize("hasAuthority('user')")
	public List<ReportCommentDTO> getMyReportComments() {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return reportService.findAllReportCommentByUserId(userId);
	}
	
	@PostMapping("/user/reports/users")
	@PreAuthorize("hasAuthority('user')")
	public ReportUserDTO createReportUser(@RequestBody ReportUserDTO reportUserDTO) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		reportUserDTO.setReportUserId(userId);
		return reportService.saveReportUser(reportUserDTO);
	}
	
	@PutMapping("/admin/reports/users/{reportId}")
	@PreAuthorize("hasAuthority('admin')")
	public ReportUserDTO updateReportUser(@RequestBody ReportUserDTO reportUserDTO, @PathVariable("reportId") Integer reportId) {
		reportUserDTO.setId(reportId);
		return reportService.saveReportUser(reportUserDTO);
	}
	
	@GetMapping("/admin/reports/users")
	@PreAuthorize("hasAuthority('admin')")
	public List<ReportUserDTO> getAllReportUsers() {
		return reportService.findAllReportUserByStatusFalse();
	}
	
	@GetMapping("/user/reports/users")
	@PreAuthorize("hasAuthority('user')")
	public List<ReportUserDTO> getMyReportUsers() {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return reportService.findAllReportUserByUserId(userId);
	}
}
