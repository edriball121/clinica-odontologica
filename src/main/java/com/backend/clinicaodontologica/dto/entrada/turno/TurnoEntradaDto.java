package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoEntradaDto {

    @NotNull(message = "La fecha y hora no puede ser nula")
    @NotBlank(message = "Debe especificarse la fecha y hora")
    @FutureOrPresent(message = "La fecha y hora debe ser actual o futura")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El id de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del odontólogo")
    private OdontologoEntradaDto odontologoEntradaDto;

    @NotNull(message = "El id de paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del paciente")
    private PacienteEntradaDto pacienteEntradaDto;

    public TurnoEntradaDto(){}

    public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto){
        this.fechaYHora = fechaYHora;
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public LocalDateTime getFechaYHora(){return fechaYHora;}
    public void setFechaYHora(LocalDateTime fechaYHora){this.fechaYHora = fechaYHora;}

    public OdontologoEntradaDto getOdontologoEntradaDto() {
        return odontologoEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoEntradaDto = odontologoEntradaDto;
    }

    public PacienteEntradaDto getPacienteEntradaDto() {
        return pacienteEntradaDto;
    }

    public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
        this.pacienteEntradaDto = pacienteEntradaDto;
    }
}
