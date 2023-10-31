package com.iken.Labo.repository;

        import com.iken.Labo.model.Project;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.time.LocalDate;
        import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByEndDateAfter(LocalDate now);

    @Query("SELECT p FROM Project p WHERE p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE")
    List<Project> findOngoingProjects();
}
