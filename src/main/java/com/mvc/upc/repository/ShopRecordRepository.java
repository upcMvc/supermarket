package com.mvc.upc.repository;

import com.mvc.upc.model.ShopRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShopRecordRepository extends CrudRepository<ShopRecord, Integer> {

    /*
     * 注意findBy的顺序，若写成findAllByUserIdAndStatusIsLessThanOrderByCreateTime的形式
     * 则有可能导致歧义（userId and status both less than the param you give）
     */


    /**
     * @param min    订单状态的最小值
     * @param max    订单状态的最大值
     * @param userId 用户id
     * @return 指定用户的所有状态值在min与max之间的订单
     */
    Iterable<ShopRecord> findAllByStatusBetweenAndUserIdOrderByCreateTime(int min, int max, int userId);

    Iterable<ShopRecord> findAllByWareHouseIdAndStatusOrderByCreateTime(int wareHouseId, int status);

    Iterable<ShopRecord> findAllByUserIdAndStatusOrderByCreateTime(int userId, int status);

    ShopRecord findFirstById(int id);
}
