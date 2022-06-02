package com.uet.recipeweb.service;

import java.util.List;

import com.uet.recipeweb.dto.CollectionDTO;;

public interface ICollectionService {
	public CollectionDTO save(CollectionDTO collectionDTO);
	public void delete(Long id);
	public Long findUserIdByCollectionId(Long collectionId);
	public List<CollectionDTO> findAllCollectionByUserId(Long userId, boolean isOwner);
	public List<CollectionDTO> findAllCollectionByUserName(String userName);
	public CollectionDTO findOneById(Long collectionId);
	public String addRecipeToCollection(Long collectionId, Long recipeId);
	public String deleteRecipeFromCollection(Long collectionId, Long recipeId);
	public String saveCollection(Long userId, Long collectionId);
	public String unsaveCollection(Long userId, Long collectionId);
	public List<CollectionDTO> findAllSavedCollection(Long userId);
	public List<String> suggestCollectionName(String keyword);
	public List<CollectionDTO> search(String keyword);
	public List<CollectionDTO> findTop10ByLikeMonth();
	public List<CollectionDTO> findTop10New();
	public List<CollectionDTO> findAllOrderByLike(Long limit);
	public boolean checkSaveCollection(Long userId, Long collectionId);
}
