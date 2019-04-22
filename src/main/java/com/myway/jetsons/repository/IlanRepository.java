package com.myway.jetsons.repository;

import com.myway.jetsons.model.Ilan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Ebru Göksal
 */
public interface IlanRepository extends JpaRepository<Ilan,Long> {

    List<Ilan> findAllByOrderByVitrinHakki();

    @Query("SELECT ilan FROM Ilan ilan join Ofis ofis on ofis=ilan.ofis WHERE ofis.id = :id AND ilan.vitrinHakki = true")
    List<Ilan> findByOfisId(Long id);
}
