package com.uet.recipeweb.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uet.recipeweb.dto.CollectionDTO;
import com.uet.recipeweb.entity.CollectionEntity;
import com.uet.recipeweb.entity.LoginInfoEntity;
import com.uet.recipeweb.entity.RecipeEntity;
import com.uet.recipeweb.entity.UserEntity;
import com.uet.recipeweb.repository.CollectionRepository;
import com.uet.recipeweb.repository.LoginInfoRepository;
import com.uet.recipeweb.repository.RecipeRepository;
import com.uet.recipeweb.repository.UserRepository;
import com.uet.recipeweb.service.ICollectionService;
import com.uet.recipeweb.util.AwsUtils;

@Service
public class CollectionService implements ICollectionService {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AwsUtils awsUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CollectionRepository collectionRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	LoginInfoRepository loginInfoRepository;

	@Override
	public CollectionDTO save(CollectionDTO collectionDTO) {
		CollectionEntity collectionEntity = new CollectionEntity();
		if (collectionDTO.getId() != null) {
			collectionEntity = collectionRepository.getById(collectionDTO.getId());
			modelMapper.map(collectionDTO, collectionEntity);
		} else {
			collectionEntity = modelMapper.map(collectionDTO, CollectionEntity.class);
		}
		if (collectionDTO.getUserId() != null) {
			UserEntity userEntity = userRepository.getById(collectionDTO.getUserId());
			collectionEntity.setOwner(userEntity);
		}
		collectionEntity = collectionRepository.save(collectionEntity);
		CollectionDTO result = modelMapper.map(collectionEntity, CollectionDTO.class);
		result.setUserId(collectionEntity.getOwner().getId());
		result.setTotalLikes(collectionEntity.getUserSaveEntities().size());
		result.setUserName(loginInfoRepository.getById(result.getUserId()).getUserName());
		if (collectionEntity.getRecipeEntities().size() > 0) {
			result.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
		}
		return result;
	}

	@Override
	public void delete(Long id) {
		collectionRepository.deleteById(id);
	}

	@Override
	public Long findUserIdByCollectionId(Long collectionId) {
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		return collectionEntity.getOwner().getId();
	}

	@Override
	public List<CollectionDTO> findAllCollectionByUserId(Long userId, boolean isOwner) {
		List<CollectionEntity> collectionEntities = userRepository.getById(userId).getMyCollections();
		if (!isOwner) {
			Iterator<CollectionEntity> iterator = collectionEntities.iterator();
			while (iterator.hasNext()) {
				CollectionEntity collectionEntity = iterator.next();
				if (collectionEntity.getIsPublic() == 0) {
					iterator.remove();
				}
			}
		}
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(userId);
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public CollectionDTO findOneById(Long collectionId) {
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
		List<RecipeEntity> recipeEntities = collectionEntity.getRecipeEntities();
		for (RecipeEntity recipeEntity : recipeEntities) {
			collectionDTO.getRecipeIds().add(recipeEntity.getId());
		}
		collectionDTO.setUserId(collectionEntity.getOwner().getId());
		collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
		collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
		if (collectionEntity.getRecipeEntities().size() > 0) {
			collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
		}
		return collectionDTO;
	}

	@Override
	public String addRecipeToCollection(Long collectionId, Long recipeId) {
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		RecipeEntity recipeEntity = recipeRepository.getById(recipeId);
		if (recipeEntity.getCollectionEntities().contains(collectionEntity)) {
			return "The recipe has already been added!";
		}
		collectionEntity.getRecipeEntities().add(recipeEntity);
		collectionRepository.save(collectionEntity);
		recipeEntity.getCollectionEntities().add(collectionEntity);
		recipeRepository.save(recipeEntity);
		return "Success";
	}

	@Override
	public String deleteRecipeFromCollection(Long collectionId, Long recipeId) {
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		RecipeEntity recipeEntity = recipeRepository.getById(recipeId);
		if (!recipeEntity.getCollectionEntities().contains(collectionEntity)) {
			return "The recipe hasn't been added!";
		}
		collectionEntity.getRecipeEntities().removeIf(recipe -> recipe.getId().equals(recipeEntity.getId()));
		collectionRepository.save(collectionEntity);
		recipeEntity.getCollectionEntities().removeIf(collection -> collection.getId().equals(collectionEntity.getId()));
		recipeRepository.save(recipeEntity);
		return "Success";
	}

	@Override
	public String saveCollection(Long userId, Long collectionId) {
		UserEntity userEntity = userRepository.getById(userId);
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		if (userEntity.getSavedCollections().contains(collectionEntity)) {
			return "The collection has already been saved!";
		}
		userEntity.getSavedCollections().add(collectionEntity);
		userRepository.save(userEntity);
		collectionEntity.getUserSaveEntities().add(userEntity);
		collectionRepository.save(collectionEntity);
		return "Success";
	}

	@Override
	public String unsaveCollection(Long userId, Long collectionId) {
		UserEntity userEntity = userRepository.getById(userId);
		CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
		if (!userEntity.getSavedCollections().contains(collectionEntity)) {
			return "The collection hasn't been saved!";
		}
		userEntity.getSavedCollections().removeIf(collection -> collection.getId().equals(collectionId));
		userRepository.save(userEntity);
		collectionEntity.getUserSaveEntities().removeIf(user -> user.getId().equals(userId));
		collectionRepository.save(collectionEntity);
		return "Success";
	}

	@Override
	public List<CollectionDTO> findAllSavedCollection(Long userId) {
		List<CollectionEntity> collectionEntities = userRepository.getById(userId).getSavedCollections();
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public List<String> suggestCollectionName(String keyword) {
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		return collectionRepository.suggestCollectionName(keyword);
	}

	@Override
	public List<CollectionDTO> search(String keyword) {
		keyword = keyword.replaceAll("\\s","*");
		keyword = "*" + keyword + "*";
		List<CollectionEntity> collectionEntities = collectionRepository.search(keyword);
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public List<CollectionDTO> findTop10ByLikeMonth() {
		List<CollectionEntity> collectionEntities = collectionRepository.findTop10ByLikeMonth();
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public List<CollectionDTO> findTop10New() {
		List<CollectionEntity> collectionEntities = collectionRepository.findTop10New();
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : collectionEntities) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public List<CollectionDTO> findAllCollectionByUserName(String userName) {
		LoginInfoEntity loginInfoEntity = loginInfoRepository.findOneByUserName(userName);
		return findAllCollectionByUserId(loginInfoEntity.getId(), false);
	}

	@Override
	public List<CollectionDTO> findAllOrderByLike(Long limit) {
		List<CollectionEntity> collectionEntities = collectionRepository.findAll();
		collectionEntities.sort(new Comparator<CollectionEntity>() {

			@Override
			public int compare(CollectionEntity o1, CollectionEntity o2) {
				return  o2.getUserSaveEntities().size() - o1.getUserSaveEntities().size();
			}
			
		});
		List<CollectionEntity> result = new ArrayList<>();
		if (limit < collectionEntities.size()) {
			for (int i = 0; i < limit; i++) {
				result.add(collectionEntities.get(i));
			}
		} else {
			result = collectionEntities;
		}
		
		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		for (CollectionEntity collectionEntity : result) {
			CollectionDTO collectionDTO = modelMapper.map(collectionEntity, CollectionDTO.class);
			collectionDTO.setUserId(collectionEntity.getOwner().getId());
			collectionEntity.getRecipeEntities().forEach(recipe -> {
				collectionDTO.getRecipeIds().add(recipe.getId());
			});
			collectionDTO.setTotalLikes(collectionEntity.getUserSaveEntities().size());
			collectionDTO.setUserName(loginInfoRepository.getById(collectionDTO.getUserId()).getUserName());
			if (collectionEntity.getRecipeEntities().size() > 0) {
				collectionDTO.setImageUrl(awsUtils.generateLink(collectionEntity.getRecipeEntities().get(0).getMain_image_url()));
			}
			collectionDTOs.add(collectionDTO);
		}
		return collectionDTOs;
	}

	@Override
	public boolean checkSaveCollection(Long userId, Long collectionId) {
		UserEntity userEntity = userRepository.getById(userId);
		for (CollectionEntity collectionEntity : userEntity.getSavedCollections()) {
			if (collectionEntity.getId() == collectionId) {
				return true;
			}
		}
		return false;
	}
}
