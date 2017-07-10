package com.mvc.upc.service;

import com.mvc.upc.model.Collection;
import com.mvc.upc.repository.CollectionRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;
    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @param userId
     * @param goodId
     * @return an Collection
     */
    public Collection createCollection(int userId, int goodId) {
        Collection collection = new Collection(userId, goodId);
        return collectionRepository.save(collection);
    }

    /**
     * @param userId
     * @return an Iterator
     */
    public Iterable<Collection> findAllByUserIdOrderByCreateTime(int userId) {
        Iterable<Collection> collections = collectionRepository.findAllByUserIdOrderByCreateTime(userId);
        if (collections == null) {
            log.info("没有收藏的商品");
        }
        return collections;
    }

    /**
     * @param id
     */
    public boolean deleteCollection(int id) {
        Collection collection = collectionRepository.findOne(id);
        if (collection != null) {
            collectionRepository.delete(id);
            return true;
        } else {
            log.info("没有找到该收藏商品，无法删除");
            return false;
        }
    }


}

