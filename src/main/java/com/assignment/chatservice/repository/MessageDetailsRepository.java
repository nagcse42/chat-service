package com.assignment.chatservice.repository;

import java.util.List;

import com.assignment.chatservice.models.MessageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Nagarjuna Paritala
 */
@Repository
public interface MessageDetailsRepository extends JpaRepository<MessageDetails, Long> {
    List<MessageDetails> findByUserName(String userName);
    int deleteByUserName(String userName);
}