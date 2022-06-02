package com.uet.recipeweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.converter.ObjectConverter;
import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.service.ICollectionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class InteractionAPI {
	@Autowired
	ICollectionService collectionService;
	
	@PostMapping("/interaction/collections/{collectionId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public String saveCollection(@PathVariable("collectionId") Long collectionId) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return collectionService.saveCollection(userId, collectionId);
	}

	@DeleteMapping("/interaction/collections/{collectionId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public String unsaveCollection(@PathVariable("collectionId") Long collectionId) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return collectionService.unsaveCollection(userId, collectionId);
	}
	
	@GetMapping("/interaction/collections")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public List<CollectionDTO> getSavedCollection() {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return collectionService.findAllSavedCollection(userId);
	}
	
	@GetMapping(value = "/interaction/collections/check/{collectionId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public boolean checkSaveCollection(@PathVariable("collectionId") Long collectionId) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return collectionService.checkSaveCollection(userId, collectionId);
	}
}
