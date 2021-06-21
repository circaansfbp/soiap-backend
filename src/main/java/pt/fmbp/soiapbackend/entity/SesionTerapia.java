package pt.fmbp.soiapbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
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

    @NotNull
    @Column(name = "fecha_sesion")
    private LocalDate fechaSesion;

    @NotEmpty(message = "Las observaciones no pueden estar vacías")
    @Size(max = 3000)
    private String observaciones;

    @NotEmpty
    @Column(nullable = false)
    private String estado;

    @JsonIgnoreProperties({"sesionesDeTerapia", "hibernateLazyInitializer", "handler"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ficha")
    private FichaTratamiento fichaTratamiento;

    public SesionTerapia() { }

    public SesionTerapia(Long idSesion, int nroSesion, LocalDate fechaSesion, String observaciones, FichaTratamiento fichaTratamiento) {
        this.idSesion = idSesion;
        this.nroSesion = nroSesion;
        this.fechaSesion = fechaSesion;
        this.observaciones = observaciones;
        this.estado = "Activo";
        this.fichaTratamiento = fichaTratamiento;
    }

    @PrePersist
    private void prePersist() {
        this.estado = "Activo";
        this.fechaSesion = LocalDate.now();
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

    public LocalDate getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDate fechaSesion) {
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
