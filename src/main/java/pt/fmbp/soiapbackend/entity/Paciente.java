package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable {

    @Id
    @Column(nullable = false)
    private String telefono;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date fechaNacimiento;

    private String ocupacion;

    private String institucion;

    @Column(name = "afiliacion_salud", nullable = false)
    private String afiliacionSalud;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "familia_nuclear")
    private String familiaNuclear;

    @Column(nullable = false)
    private String estado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<HoraAtencion> atenciones;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ficha")
    private FichaTratamiento fichaTratamiento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anamnesis")
    private Anamnesis anamnesis;

    public Paciente() { }

    public Paciente(String telefono, String nombreCompleto, Date fechaNacimiento, String ocupacion, String institucion,
                    String afiliacionSalud, String estadoCivil, String familiaNuclear,
                    FichaTratamiento fichaTratamiento, Anamnesis anamnesis) {
        this.telefono = telefono;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.institucion = institucion;
        this.afiliacionSalud = afiliacionSalud;
        this.estadoCivil = estadoCivil;
        this.familiaNuclear = familiaNuclear;
        this.estado = "Activo";
        this.atenciones = new ArrayList<>();
        this.fichaTratamiento = fichaTratamiento;
        this.anamnesis = anamnesis;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getAfiliacionSalud() {
        return afiliacionSalud;
    }

    public void setAfiliacionSalud(String afiliacionSalud) {
        this.afiliacionSalud = afiliacionSalud;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFamiliaNuclear() {
        return familiaNuclear;
    }

    public void setFamiliaNuclear(String familiaNuclear) {
        this.familiaNuclear = familiaNuclear;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<HoraAtencion> getAtenciones() {
        return atenciones;
    }

    public void setAtenciones(List<HoraAtencion> atenciones) {
        this.atenciones = atenciones;
    }

    public FichaTratamiento getFichaTratamiento() {
        return fichaTratamiento;
    }

    public void setFichaTratamiento(FichaTratamiento fichaTratamiento) {
        this.fichaTratamiento = fichaTratamiento;
    }

    public Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(telefono, paciente.telefono) && Objects.equals(nombreCompleto, paciente.nombreCompleto) && Objects.equals(fechaNacimiento, paciente.fechaNacimiento) && Objects.equals(ocupacion, paciente.ocupacion) && Objects.equals(institucion, paciente.institucion) && Objects.equals(afiliacionSalud, paciente.afiliacionSalud) && Objects.equals(estadoCivil, paciente.estadoCivil) && Objects.equals(familiaNuclear, paciente.familiaNuclear) && Objects.equals(estado, paciente.estado) && Objects.equals(atenciones, paciente.atenciones) && Objects.equals(fichaTratamiento, paciente.fichaTratamiento) && Objects.equals(anamnesis, paciente.anamnesis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono, nombreCompleto, fechaNacimiento, ocupacion, institucion, afiliacionSalud, estadoCivil, familiaNuclear, estado, atenciones, fichaTratamiento, anamnesis);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "telefono='" + telefono + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", ocupacion='" + ocupacion + '\'' +
                ", institucion='" + institucion + '\'' +
                ", afiliacionSalud='" + afiliacionSalud + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", familiaNuclear='" + familiaNuclear + '\'' +
                ", estado='" + estado + '\'' +
                ", atenciones=" + atenciones +
                ", fichaTratamiento=" + fichaTratamiento +
                ", anamnesis=" + anamnesis +
                '}';
    }
}
