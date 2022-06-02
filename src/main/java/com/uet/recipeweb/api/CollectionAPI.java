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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.converter.ObjectConverter;
import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.service.ICollectionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CollectionAPI {
	@Autowired
	ICollectionService collectionService;
	
	@PostMapping(value = "/collections")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public CollectionDTO createCollection(@RequestBody CollectionDTO model) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		model.setUserId(userId);
		return collectionService.save(model);
	}
	
	@GetMapping(value = "/collections")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public List<CollectionDTO> getMyCollections() {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return collectionService.findAllCollectionByUserId(userId, true);
	}
	
	@GetMapping(value = "/collections/users/{userName}")
	public List<CollectionDTO> getCollectionsOfUser(@PathVariable("userName") String userName) {
		return collectionService.findAllCollectionByUserName(userName);
	}
	
	@PutMapping(value = "/collections/{collectionId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public CollectionDTO updateCollection(@RequestBody CollectionDTO model, @PathVariable("collectionId") Long collectionId) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority().equals("admin")) {
			model.setId(collectionId);
			return collectionService.save(model);
		} else {
			Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if (collectionService.findUserIdByCollectionId(collectionId).equals(userId)) {
				model.setId(collectionId);
				return collectionService.save(model);
			} else {
				return null;
			}
		}
		
	}
	
	@DeleteMapping(value = "/collections/{collectionId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public String deleteCollection(@PathVariable("collectionId") Long collectionId) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority().equals("admin")) {
			collectionService.delete(collectionId);
			return "Success";
		} else {
			Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if (collectionService.findUserIdByCollectionId(collectionId).equals(userId)) {
				collectionService.delete(collectionId);
				return "Success";
			} else {
				return "Not allowed";
			}
		}
	}
	
	@GetMapping(value = "/collections/{collectionId}")
	public CollectionDTO getCollection(@PathVariable("collectionId") Long collectionId) {
		return collectionService.findOneById(collectionId);
	}
	
	@PostMapping(value = "/collections/{collectionId}/{recipeId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public String addRecipeToCollection(@PathVariable("collectionId") Long collectionId, @PathVariable("recipeId") Long recipeId) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (collectionService.findUserIdByCollectionId(collectionId).equals(userId)) {
			return collectionService.addRecipeToCollection(collectionId, recipeId);
		}
		return "Not allowed";
	}
	
	@DeleteMapping(value = "/collections/{collectionId}/{recipeId}")
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public String deleteRecipeFromCollection(@PathVariable("collectionId") Long collectionId, @PathVariable("recipeId") Long recipeId) {
		Long userId = ObjectConverter.toLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (collectionService.findUserIdByCollectionId(collectionId).equals(userId)) {
			return collectionService.deleteRecipeFromCollection(collectionId, recipeId);
		}
		return "Not allowed";
	}
}
