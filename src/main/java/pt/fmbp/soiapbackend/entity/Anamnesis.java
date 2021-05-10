package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Anamnesis")
public class Anamnesis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anamnesis", nullable = false, updatable = false)
    private Long idAnamnesis;

    @Column(name = "fecha_anamnesis")
    private LocalDate fechaAnamnesis;

    @Column(name = "motivo_consulta_paciente")
    private String motivoConsultaPaciente;

    @Column(name = "antecedentes_paciente")
    private String antecedentesPaciente;

    @Column(name = "antecedentes_familiares")
    private String antecedentesFamiliares;

    private String observaciones;

    @Column(nullable = false)
    private String estado;

    @OneToOne(mappedBy = "anamnesis")
    private Paciente paciente;

    public Anamnesis() { }

    public Anamnesis(Long idAnamnesis, LocalDate fechaAnamnesis, String motivoConsultaPaciente, String antecedentesPaciente,
                     String antecedentesFamiliares, String observaciones) {
        this.idAnamnesis = idAnamnesis;
        this.fechaAnamnesis = fechaAnamnesis;
        this.motivoConsultaPaciente = motivoConsultaPaciente;
        this.antecedentesPaciente = antecedentesPaciente;
        this.antecedentesFamiliares = antecedentesFamiliares;
        this.observaciones = observaciones;
        this.estado = "Activo";
    }

    @PrePersist
    private void prePersist() {
        this.estado = "Activo";
        this.fechaAnamnesis = LocalDate.now();
    }

    public Long getIdAnamnesis() {
        return idAnamnesis;
    }

    public LocalDate getFechaAnamnesis() {
        return fechaAnamnesis;
    }

    public void setFechaAnamnesis(LocalDate fechaAnamnesis) {
        this.fechaAnamnesis = fechaAnamnesis;
    }

    public String getMotivoConsultaPaciente() {
        return motivoConsultaPaciente;
    }

    public void setMotivoConsultaPaciente(String motivoConsultaPaciente) {
        this.motivoConsultaPaciente = motivoConsultaPaciente;
    }

    public String getAntecedentesPaciente() {
        return antecedentesPaciente;
    }

    public void setAntecedentesPaciente(String antecedentesPaciente) {
        this.antecedentesPaciente = antecedentesPaciente;
    }

    public String getAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anamnesis)) return false;
        Anamnesis anamnesis = (Anamnesis) o;
        return Objects.equals(idAnamnesis, anamnesis.idAnamnesis) && Objects.equals(fechaAnamnesis, anamnesis.fechaAnamnesis) && Objects.equals(motivoConsultaPaciente, anamnesis.motivoConsultaPaciente) && Objects.equals(antecedentesPaciente, anamnesis.antecedentesPaciente) && Objects.equals(antecedentesFamiliares, anamnesis.antecedentesFamiliares) && Objects.equals(observaciones, anamnesis.observaciones) && Objects.equals(estado, anamnesis.estado) && Objects.equals(paciente, anamnesis.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnamnesis, fechaAnamnesis, motivoConsultaPaciente, antecedentesPaciente, antecedentesFamiliares, observaciones, estado, paciente);
    }

    @Override
    public String toString() {
        return "Anamnesis{" +
                "idAnamnesis=" + idAnamnesis +
                ", fechaAnamnesis=" + fechaAnamnesis +
                ", motivoConsultaPaciente='" + motivoConsultaPaciente + '\'' +
                ", antecedentesPaciente='" + antecedentesPaciente + '\'' +
                ", antecedentesFamiliares='" + antecedentesFamiliares + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}
