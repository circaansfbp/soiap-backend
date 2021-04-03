package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Anamnesis")
public class Anamnesis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anamnesis", nullable = false, updatable = false)
    private Long idAnamnesis;

    @Temporal(value = TemporalType.DATE)
    private Date fecha;

    @Column(name = "motivo_consulta_paciente",nullable = false)
    private String motivoConsultaPaciente;

    @Column(name = "antecedentes_paciente")
    private String antecedentesPaciente;

    @Column(name = "antecedentes_familiares")
    private String antecedentesFamiliares;

    private String observaciones;

    @Column(nullable = false)
    private String estado;

    public Anamnesis() { }

    public Anamnesis(Long idAnamnesis, Date fecha, String motivoConsultaPaciente, String antecedentesPaciente,
                     String antecedentesFamiliares, String observaciones) {
        this.idAnamnesis = idAnamnesis;
        this.fecha = fecha;
        this.motivoConsultaPaciente = motivoConsultaPaciente;
        this.antecedentesPaciente = antecedentesPaciente;
        this.antecedentesFamiliares = antecedentesFamiliares;
        this.observaciones = observaciones;
        this.estado = "Activo";
    }

    public Long getIdAnamnesis() {
        return idAnamnesis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anamnesis)) return false;
        Anamnesis anamnesis = (Anamnesis) o;
        return Objects.equals(idAnamnesis, anamnesis.idAnamnesis) && Objects.equals(fecha, anamnesis.fecha) && Objects.equals(motivoConsultaPaciente, anamnesis.motivoConsultaPaciente) && Objects.equals(antecedentesPaciente, anamnesis.antecedentesPaciente) && Objects.equals(antecedentesFamiliares, anamnesis.antecedentesFamiliares) && Objects.equals(observaciones, anamnesis.observaciones) && Objects.equals(estado, anamnesis.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnamnesis, fecha, motivoConsultaPaciente, antecedentesPaciente, antecedentesFamiliares, observaciones, estado);
    }

    @Override
    public String toString() {
        return "Anamnesis{" +
                "idAnamnesis=" + idAnamnesis +
                ", fecha=" + fecha +
                ", motivoConsultaPaciente='" + motivoConsultaPaciente + '\'' +
                ", antecedentesPaciente='" + antecedentesPaciente + '\'' +
                ", antecedentesFamiliares='" + antecedentesFamiliares + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
