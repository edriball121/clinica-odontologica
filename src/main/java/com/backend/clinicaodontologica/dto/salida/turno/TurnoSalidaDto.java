package com.backend.clinicaodontologica.dto.salida.turno;

import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

public class TurnoSalidaDto {
    private int id;
    private OdontologoSalidaDto odontologoSalidaDto;
    private PacienteSalidaDto pacienteSalidaDto;

    public TurnoSalidaDto(){
    }

    public TurnoSalidaDto(int id, OdontologoSalidaDto odontologoSalidaDto, PacienteSalidaDto pacienteSalidaDto) {
        this.id = id;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.pacienteSalidaDto = pacienteSalidaDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OdontologoSalidaDto getOdontologoSalidaDto() {
        return odontologoSalidaDto;
    }

    public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoSalidaDto) {
        this.odontologoSalidaDto = odontologoSalidaDto;
    }

    public PacienteSalidaDto getPacienteSalidaDto() {
        return pacienteSalidaDto;
    }

    public void setPacienteSalidaDto(PacienteSalidaDto pacienteSalidaDto) {
        this.pacienteSalidaDto = pacienteSalidaDto;
    }
}
