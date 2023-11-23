package com.backend.clinicaodontologica.service.impl;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        //configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);

        Odontologo odontologoPersistir = odontologoRepository.save(odontologoEntidad);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoPersistir, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto" + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoRepository.findAll().stream().map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologoSalidaDtos));
        return odontologoSalidaDtos;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odotologoEncontrado = null;

        if (odontologoBuscado != null) {
            odotologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: " + JsonPrinter.toString(odotologoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");
        return odotologoEncontrado;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo) {
        Odontologo odontologoRecibido = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoActualizar = odontologoRepository.findById(odontologoRecibido.getId()).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoActualizar != null) {
            odontologoActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoActualizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}" + JsonPrinter.toString(odontologoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.findById(id).orElse(null) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            //excepcion a lanzar aqui
        }
    }

    /*private void configureMapping() {
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class).addMappings(mapper -> mapper.map(OdontologoEntradaDto::getNombre, Odontologo::setNombre));
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class).addMappings(modelMapper -> modelMapper.map(Odontologo::getNombre, OdontologoSalidaDto::setNombre));
    }*/

}
