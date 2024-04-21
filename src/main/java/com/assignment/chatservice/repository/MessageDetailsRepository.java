package com.assignment.chatservice.repository;

import java.util.List;

import com.assignment.chatservice.models.MessageDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface MessageDetailsRepository extends JpaRepository<MessageDetails, Long> {
    List<MessageDetails> findByUserName(String userName);
    @Transactional
    int deleteByUserName(String userName);
}