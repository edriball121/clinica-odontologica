package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoModificacionEntradaDto {

    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;
    @NotNull(message = "La fecha y hora no puede ser nula")
    @NotBlank(message = "Debe especificarse la fecha y hora")
    @FutureOrPresent(message = "La fecha y hora debe ser actual o futura")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El id de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del odontólogo")
    private Odontologo odontologo;

    @NotNull(message = "El id de paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del paciente")
    private Paciente paciente;

    public TurnoModificacionEntradaDto() {
    }

    public TurnoModificacionEntradaDto(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
