package com.mvc.upc.service;

import com.mvc.upc.model.Collection;
import com.mvc.upc.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    public Collection createCollection(int userId, int goodId, String createTime) {
        Collection collection = new Collection(userId, goodId, createTime);
        return collectionRepository.save(collection);
    }

    public Iterable<Collection> findAllByUserIdOrderByCreateTime(int userId) {
        return collectionRepository.findAllByUserIdOrderByCreateTime(userId);
    }

    public void deleteCollection(int id) {
        collectionRepository.delete(id);
    }

    public CollectionService() {
    }
}

