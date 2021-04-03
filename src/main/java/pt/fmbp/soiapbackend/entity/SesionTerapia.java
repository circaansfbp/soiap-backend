package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Sesion_terapia")
public class SesionTerapia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion", nullable = false, updatable = false)
    private Long idSesion;

    @Column(name = "nro_sesion")
    private int nroSesion;

    @Column(name = "fecha_sesion")
    @Temporal(value = TemporalType.DATE)
    private Date fechaSesion;

    private String observaciones;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ficha")
    private FichaTratamiento fichaTratamiento;

    public SesionTerapia() { }

    public SesionTerapia(Long idSesion, int nroSesion, Date fechaSesion, String observaciones, FichaTratamiento fichaTratamiento) {
        this.idSesion = idSesion;
        this.nroSesion = nroSesion;
        this.fechaSesion = fechaSesion;
        this.observaciones = observaciones;
        this.estado = "Activo";
        this.fichaTratamiento = fichaTratamiento;
    }

    public Long getIdSesion() {
        return idSesion;
    }

    public int getNroSesion() {
        return nroSesion;
    }

    public void setNroSesion(int nroSesion) {
        this.nroSesion = nroSesion;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
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

    public FichaTratamiento getFichaTratamiento() {
        return fichaTratamiento;
    }

    public void setFichaTratamiento(FichaTratamiento fichaTratamiento) {
        this.fichaTratamiento = fichaTratamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SesionTerapia)) return false;
        SesionTerapia that = (SesionTerapia) o;
        return nroSesion == that.nroSesion && Objects.equals(idSesion, that.idSesion) && Objects.equals(fechaSesion, that.fechaSesion) && Objects.equals(observaciones, that.observaciones) && Objects.equals(estado, that.estado) && Objects.equals(fichaTratamiento, that.fichaTratamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSesion, nroSesion, fechaSesion, observaciones, estado, fichaTratamiento);
    }

    @Override
    public String toString() {
        return "SesionTerapia{" +
                "idSesion=" + idSesion +
                ", nroSesion=" + nroSesion +
                ", fechaSesion=" + fechaSesion +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", fichaTratamiento=" + fichaTratamiento +
                '}';
    }
}
