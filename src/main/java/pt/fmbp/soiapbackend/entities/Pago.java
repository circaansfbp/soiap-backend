package pt.fmbp.soiapbackend.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Pago")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pago", nullable = false, updatable = false)
    private int idPago;

    @Column(name = "estado_pago", nullable = false)
    private boolean estadoPago;

    @Column(name = "fecha_pago")
    @Temporal(value = TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "medio_pago")
    private String medioPago;

    @Column(name = "monto_pago")
    private String montoPago;

    @Column(name = "cantidad_horas_pagadas")
    private int cantidadHorasPagadas;

    @Column(nullable = false)
    private String estado;

    public Pago() { }

    public Pago(int idPago, boolean estadoPago, Date fechaPago, String medioPago, String montoPago, int cantidadHorasPagadas, String estado) {
        this.idPago = idPago;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
        this.montoPago = montoPago;
        this.cantidadHorasPagadas = cantidadHorasPagadas;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(String montoPago) {
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
        return idPago == pago.idPago && estadoPago == pago.estadoPago && cantidadHorasPagadas == pago.cantidadHorasPagadas && Objects.equals(fechaPago, pago.fechaPago) && Objects.equals(medioPago, pago.medioPago) && Objects.equals(montoPago, pago.montoPago) && Objects.equals(estado, pago.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago, estadoPago, fechaPago, medioPago, montoPago, cantidadHorasPagadas, estado);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", estadoPago=" + estadoPago +
                ", fechaPago=" + fechaPago +
                ", medioPago='" + medioPago + '\'' +
                ", montoPago='" + montoPago + '\'' +
                ", cantidadHorasPagadas=" + cantidadHorasPagadas +
                ", estado='" + estado + '\'' +
                '}';
    }
}
