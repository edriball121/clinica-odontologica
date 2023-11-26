package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("SELECT o FROM Odontologo o")
    List<Odontologo> findAllOdontologos();

    @Query("SELECT o FROM Odontologo o WHERE o.id = :id")
    Optional<Odontologo> findOdontologoById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Odontologo o WHERE o.id = :id")
    void deleteOdontologoById(@Param("id") Long id);
}
