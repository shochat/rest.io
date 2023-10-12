package io.repository;

import io.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findAllByAutherId(Long autherId);
    int deleteMessageById(Long id);
    @Modifying
    @Query("update Message m set m.voteRate = ?1 where m.id = ?2")
    int updateVoteRate(@Param(value = "vote_rate") int rate, @Param(value = "id") int messageId);
}
