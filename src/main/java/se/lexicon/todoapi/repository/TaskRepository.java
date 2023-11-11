package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainsIgnoreCase(String title);

    List<Task> findTasksByPerson_Id(Long id);

    List<Task> findByDone(boolean done);

    List<Task> findByDeadlineBetween(LocalDate from, LocalDate to);

    List<Task> findByDeadline(LocalDate deadline);

    List<Task> findByPersonNull();

    List<Task> findByPersonNotNull();

    @Query("select t from Task t where t.done = false")
    List<Task> selectUnFinishedTasks();

    List<Task> findByDoneFalse();

//    @Query("select t from Task t where t.done = false and current_date > t.deadline")
//    List<Task> selectTasksByNotDoneAndOverdue();

    List<Task> findByDoneFalseAndDeadlineBefore(LocalDate date);

    @Query("select t from Task t")
    List<Task> getAll();

}
