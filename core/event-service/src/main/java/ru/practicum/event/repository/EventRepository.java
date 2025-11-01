package ru.practicum.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.event.eventDto.State;
import ru.practicum.event.model.Event;



import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByInitiator(Long initiatorId, Pageable page);

    Optional<Event> findByIdAndState(Long id, State state);

    Optional<Event> findByIdAndInitiator(Long id, Long initiatorId);

    List<Event> findAll(Specification<Event> spec, Pageable pageable);

    @Modifying
    @Query("UPDATE Event e SET e.confirmedRequests = e.confirmedRequests + :increment WHERE e.id = :eventId")
    void incrementConfirmedRequests(@Param("eventId") Long eventId,
                                    @Param("increment") int increment);

    boolean existsByCategoryId(Long categoryId);
}

