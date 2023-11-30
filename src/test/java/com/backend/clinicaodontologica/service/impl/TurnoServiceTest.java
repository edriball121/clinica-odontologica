package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Service
class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void crearUnTurnoYValidarSusIDs(){
        // Arrange
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456789, LocalDate.of(2023, 12, 24), new DomicilioEntradaDto("calle", 1234, "Localidad", "Provincia"));
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("11111111111", "Pablo", "Porto");

        // Act
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDate.of(2023, 11, 30), odontologoSalidaDto.getId(), pacienteSalidaDto.getId());
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        // Assert
        assertNotNull(pacienteSalidaDto.getId());
        assertNotNull(odontologoSalidaDto.getId());
        assertNotNull(turnoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaVaciaDeTurno(){
        List<TurnoSalidaDto> turnoSalidaDtos = turnoService.listarTurnos();

        assertTrue(turnoSalidaDtos.isEmpty());
    }

    @Test
    @Order(3)
    void alIntentarEliminarUnTurnoConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException(){

        try{
            turnoService.eliminarTurno(1L);
        } catch (Exception exception){
            exception.printStackTrace();
        }

        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }
}