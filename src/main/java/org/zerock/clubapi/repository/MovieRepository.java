package org.zerock.clubapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.clubapi.entity.BiMovie;

public interface MovieRepository extends JpaRepository<BiMovie, Long> {

    @EntityGraph(attributePaths = "posterList", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from BiMovie m")
    Page<BiMovie> findAll2(Pageable pageable);

    @Query("select m, p, count(p) from BiMovie m left join Poster p on p.bimovie = m group by p.bimovie")
    Page<Object[]> findAll3(Pageable pageable);

    @Query("select m, p, count(p) from BiMovie m left join Poster p on p.bimovie = m where p.idx = 1 group by p.bimovie")
    Page<Object[]> findAll4(Pageable pageable);
}
