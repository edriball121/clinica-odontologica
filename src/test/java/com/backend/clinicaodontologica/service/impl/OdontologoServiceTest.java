package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoConMatriculaYRetornaId(){
        // Arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("11111111111","Pablo","Porto");

        // Act
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        // Assert
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("11111111111", odontologoSalidaDto.getMatricula());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnOdontologoPorIdYEncontrarlo() throws ResourceNotFoundException {
        //Arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("11111111111","Pablo","Porto");

        //Act
        OdontologoSalidaDto odontologoSalidaDtoAdd = odontologoService.registrarOdontologo(odontologoEntradaDto);
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(1L);

        //Asert
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals(1L, odontologoSalidaDto.getId());
    }

    @Test
    @Order(3)
    void deberiaPoderEliminarUnOdontologoRegistradoEnElSistema() throws ResourceNotFoundException {
        //Arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("11111111111","Pablo","Porto");

        //Act
        OdontologoSalidaDto odontologoSalidaDtoAdd = odontologoService.registrarOdontologo(odontologoEntradaDto);
        OdontologoSalidaDto odontologoSalidaDtoDelete = odontologoService.eliminarOdontologo(1L);
        List <OdontologoSalidaDto> odontologoSalidaDtoSarchById = (List<OdontologoSalidaDto>) odontologoService.listarOdontologos();
        //Asert
        assertTrue(odontologoSalidaDtoSarchById.isEmpty());
    }

}