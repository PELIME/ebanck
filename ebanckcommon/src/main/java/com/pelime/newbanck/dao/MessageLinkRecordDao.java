package com.pelime.newbanck.dao;

import com.pelime.newbanck.domain.MessageLinkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLinkRecordDao extends JpaRepository<MessageLinkRecord,Long> {
}
