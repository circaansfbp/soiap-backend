package pt.fmbp.soiapbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Pago")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false, updatable = false)
    private Long idPago;

    /*@Column(name = "estado_pago", nullable = false)
    private boolean estadoPago;*/

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "medio_pago")
    private String medioPago;

    @Column(name = "monto_pago")
    private Long montoPago;

    @Column(name = "cantidad_horas_pagadas")
    private int cantidadHorasPagadas;

    @Column(nullable = false)
    private String estado;

    /*@JsonIgnoreProperties(value = {"id_pago", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pago")
    private List<HoraAtencion> pagosPorHoraAtencion;*/

    public Pago() { }

    public Pago(Long idPago, /*boolean estadoPago*/ LocalDate fechaPago, String medioPago, Long montoPago, int cantidadHorasPagadas) {
        this.idPago = idPago;
        //this.estadoPago = estadoPago;
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

    /*public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }*/

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
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

    /*public List<HoraAtencion> getPagosPorHoraAtencion() {
        return pagosPorHoraAtencion;
    }

    public void setPagosPorHoraAtencion(List<HoraAtencion> pagosPorHoraAtencion) {
        this.pagosPorHoraAtencion = pagosPorHoraAtencion;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return /*estadoPago == pago.estadoPago*/ cantidadHorasPagadas == pago.cantidadHorasPagadas && Objects.equals(idPago, pago.idPago) && Objects.equals(fechaPago, pago.fechaPago) && Objects.equals(medioPago, pago.medioPago) && Objects.equals(montoPago, pago.montoPago) && Objects.equals(estado, pago.estado); /*&& Objects.equals(pagosPorHoraAtencion, pago.pagosPorHoraAtencion*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago, /*estadoPago,*/ fechaPago, medioPago, montoPago, cantidadHorasPagadas, estado /*pagosPorHoraAtencion*/);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", fechaPago=" + fechaPago +
                ", medioPago='" + medioPago + '\'' +
                ", montoPago=" + montoPago +
                ", cantidadHorasPagadas=" + cantidadHorasPagadas +
                ", estado='" + estado + '\'' +
                '}';
    }
}
