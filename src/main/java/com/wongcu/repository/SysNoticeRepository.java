package com.wongcu.repository;

import com.wongcu.model.ChatLog;
import com.wongcu.model.SysNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author WongCU
 * @date 2018/6/26
 */
public interface SysNoticeRepository extends MongoRepository<SysNotice, String> {

    @Query("{\"to\": ?0}")
    Page<SysNotice> findAndPages(String to, Pageable pages);
}
