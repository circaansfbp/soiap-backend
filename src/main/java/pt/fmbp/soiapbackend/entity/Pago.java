package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Pago")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false, updatable = false)
    private Long idPago;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "afiliacion_paciente")
    @Size(max = 30)
    private String afiliacionPaciente;

    @Column(name = "medio_pago")
    @Size(max = 30)
    private String medioPago;

    @Column(name = "monto_pago")
    private Long montoPago;

    @Column(name = "cantidad_horas_pagadas")
    private int cantidadHorasPagadas;

    @Column(nullable = false)
    @Size(max = 15)
    private String estado;

    public Pago() { }

    public Pago(Long idPago, LocalDate fechaPago, String medioPago, Long montoPago, int cantidadHorasPagadas) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
        this.montoPago = montoPago;
        this.cantidadHorasPagadas = cantidadHorasPagadas;
        this.estado = "Activo";
    }

    @PrePersist
    private void prePersist() {
        this.estado = "Activo";
        this.fechaPago = LocalDate.now();
    }

    public Long getIdPago() {
        return idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getAfiliacionPaciente() {
        return afiliacionPaciente;
    }

    public void setAfiliacionPaciente(String afiliacionPaciente) {
        this.afiliacionPaciente = afiliacionPaciente;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Long getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Long montoPago) {
        this.montoPago = montoPago;
    }

    public int getCantidadHorasPagadas() {
        return cantidadHorasPagadas;
    }

    public void setCantidadHorasPagadas(int cantidadHorasPagadas) {
        this.cantidadHorasPagadas = cantidadHorasPagadas;
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
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return cantidadHorasPagadas == pago.cantidadHorasPagadas && Objects.equals(idPago, pago.idPago) && Objects.equals(fechaPago, pago.fechaPago) && Objects.equals(afiliacionPaciente, pago.afiliacionPaciente) && Objects.equals(medioPago, pago.medioPago) && Objects.equals(montoPago, pago.montoPago) && Objects.equals(estado, pago.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago, fechaPago, afiliacionPaciente, medioPago, montoPago, cantidadHorasPagadas, estado);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", fechaPago=" + fechaPago +
                ", afiliacionPaciente='" + afiliacionPaciente + '\'' +
                ", medioPago='" + medioPago + '\'' +
                ", montoPago=" + montoPago +
                ", cantidadHorasPagadas=" + cantidadHorasPagadas +
                ", estado='" + estado + '\'' +
                '}';
    }
}
