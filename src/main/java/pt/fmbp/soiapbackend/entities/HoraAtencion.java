package pt.fmbp.soiapbackend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Hora_Atencion")
public class HoraAtencion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_atencion", nullable = false, updatable = false)
    private int idAtencion;

    private boolean asistencia;

    @Column(name = "confirma_asistencia")
    private boolean confirmaAsistencia;

    @Column(name = "hora_atencion", nullable = false)
    //@Temporal(value = TemporalType.TIME)
    private LocalTime horaAtencion;

    @Column(name = "fecha_atencion", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date fechaAtencion;

    @Column(name = "nro_consulta")
    private int nroConsulta;

    @Column(nullable = false)
    private boolean disponible;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telefono_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago")
    private Pago pago;

    public HoraAtencion() { }

    public HoraAtencion(int idAtencion, boolean asistencia, boolean confirmaAsistencia, LocalTime horaAtencion, Date fechaAtencion,
                        int nroConsulta, boolean disponible, String estado, Paciente paciente, Pago pago) {
        this.idAtencion = idAtencion;
        this.asistencia = asistencia;
        this.confirmaAsistencia = confirmaAsistencia;
        this.horaAtencion = horaAtencion;
        this.fechaAtencion = fechaAtencion;
        this.nroConsulta = nroConsulta;
        this.disponible = disponible;
        this.estado = estado;
        this.paciente = paciente;
        this.pago = pago;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public boolean isConfirmaAsistencia() {
        return confirmaAsistencia;
    }

    public void setConfirmaAsistencia(boolean confirmaAsistencia) {
        this.confirmaAsistencia = confirmaAsistencia;
    }

    public LocalTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getNroConsulta() {
        return nroConsulta;
    }

    public void setNroConsulta(int nroConsulta) {
        this.nroConsulta = nroConsulta;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
        return idAtencion == that.idAtencion && asistencia == that.asistencia && confirmaAsistencia == that.confirmaAsistencia && nroConsulta == that.nroConsulta && disponible == that.disponible && Objects.equals(horaAtencion, that.horaAtencion) && Objects.equals(fechaAtencion, that.fechaAtencion) && Objects.equals(estado, that.estado) && Objects.equals(paciente, that.paciente) && Objects.equals(pago, that.pago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtencion, asistencia, confirmaAsistencia, horaAtencion, fechaAtencion, nroConsulta, disponible, estado, paciente, pago);
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
                ", disponible=" + disponible +
                ", estado='" + estado + '\'' +
                ", paciente=" + paciente +
                ", pago=" + pago +
                '}';
    }
}
