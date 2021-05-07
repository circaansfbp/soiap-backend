package pt.fmbp.soiapbackend.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Hora_Atencion")
public class HoraAtencion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atencion", nullable = false, updatable = false)
    private Long idAtencion;

    //0 = PENDIENTE
    //1 = ASISTE
    //-1 = NO ASISTE
    private int asistencia;

    //0 = PENDIENTE
    //1 = ASISTE
    //-1 = NO ASISTE
    @Column(name = "confirma_asistencia")
    private int confirmaAsistencia;

    @Column(name = "hora_atencion")
    @NotNull
    private LocalTime horaAtencion;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDate fechaAtencion;

    @Column(name = "nro_consulta")
    private int nroConsulta;

    @JsonIgnoreProperties({"atenciones", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pago")
    private Pago pago;

    public HoraAtencion() { }

    public HoraAtencion(Long idAtencion, int asistencia, int confirmaAsistencia, LocalTime horaAtencion, LocalDate fechaAtencion, int nroConsulta, Paciente paciente, Pago pago) {
        this.idAtencion = idAtencion;
        this.asistencia = asistencia;
        this.confirmaAsistencia = confirmaAsistencia;
        this.horaAtencion = horaAtencion;
        this.fechaAtencion = fechaAtencion;
        this.nroConsulta = nroConsulta;
        this.paciente = paciente;
        this.pago = pago;
    }

    @PrePersist
    private void prePersist() {
        this.asistencia = 0;
        this.confirmaAsistencia = 0;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public int getConfirmaAsistencia() {
        return confirmaAsistencia;
    }

    public void setConfirmaAsistencia(int confirmaAsistencia) {
        this.confirmaAsistencia = confirmaAsistencia;
    }

    public LocalTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public LocalDate getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDate fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getNroConsulta() {
        return nroConsulta;
    }

    public void setNroConsulta(int nroConsulta) {
        this.nroConsulta = nroConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoraAtencion)) return false;
        HoraAtencion that = (HoraAtencion) o;
        return asistencia == that.asistencia && confirmaAsistencia == that.confirmaAsistencia && nroConsulta == that.nroConsulta && Objects.equals(idAtencion, that.idAtencion) && Objects.equals(horaAtencion, that.horaAtencion) && Objects.equals(fechaAtencion, that.fechaAtencion) && Objects.equals(paciente, that.paciente) && Objects.equals(pago, that.pago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtencion, asistencia, confirmaAsistencia, horaAtencion, fechaAtencion, nroConsulta, paciente, pago);
    }

    @Override
    public String toString() {
        return "HoraAtencion{" +
                "idAtencion=" + idAtencion +
                ", asistencia=" + asistencia +
                ", confirmaAsistencia=" + confirmaAsistencia +
                ", horaAtencion=" + horaAtencion +
                ", fechaAtencion=" + fechaAtencion +
                ", nroConsulta=" + nroConsulta +
                ", paciente=" + paciente +
                ", pago=" + pago +
                '}';
    }
}
