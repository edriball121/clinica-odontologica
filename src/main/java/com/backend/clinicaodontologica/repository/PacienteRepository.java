package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p")
    List<Paciente> findAllPacientes();

    @Query("SELECT p FROM Paciente p WHERE p.dni = :dni")
    Optional<Paciente> findByDni(@Param("dni") int dni);

    @Query("SELECT p FROM Paciente p WHERE p.id = :id")
    Optional<Paciente> findPacienteById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Paciente p WHERE p.id = :id")
    void deletePacienteById(@Param("id") Long id);
}
