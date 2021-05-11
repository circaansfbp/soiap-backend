package pt.fmbp.soiapbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable {

    // REALIZAR VALIDACIONES DE CAMPOS QUE NO DEBEN SER NULOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente", nullable = false, updatable = false)
    private Long idPaciente;

    @Size(min = 3, max = 30)
    private String nombre;

    @Size(min = 3, max = 30)
    private String apellido;

    @Size(min = 12, max = 12)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Size(max = 30)
    private String ocupacion;

    @Size(max = 30)
    private String institucion;

    @Column(name = "afiliacion_salud")
    @Size(max = 30)
    private String afiliacionSalud;


    @Column(name = "estado_civil")
    @Size(max = 30)
    private String estadoCivil;


    @Column(name = "familia_nuclear")
    @Size(max = 255)
    private String familiaNuclear;

    @Column(nullable = false)
    @Size(max = 15)
    private String estado;

    // Atenciones del paciente
    @JsonIgnoreProperties(value = {"paciente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<HoraAtencion> atenciones;

    // Anamnesis del paciente
    @JsonIgnoreProperties(value = {"paciente", "hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_anamnesis")
    private Anamnesis anamnesis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ficha")
    @NotFound(action = NotFoundAction.IGNORE)
    private FichaTratamiento fichaTratamiento;


    public Paciente () { this.atenciones = new ArrayList<>(); }

    public Paciente(Long idPaciente, String nombre, String apellido, String telefono, LocalDate fechaNacimiento,
                    String ocupacion, String institucion, String afiliacionSalud, String estadoCivil, String familiaNuclear,
                    String estado, List<HoraAtencion> atenciones, FichaTratamiento fichaTratamiento, Anamnesis anamnesis) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.institucion = institucion;
        this.afiliacionSalud = afiliacionSalud;
        this.estadoCivil = estadoCivil;
        this.familiaNuclear = familiaNuclear;
        this.estado = estado;
        this.atenciones = new ArrayList<>();
        this.fichaTratamiento = fichaTratamiento;
        this.anamnesis = anamnesis;
    }

    @PrePersist
    private void PrePersist () {
        this.estado = "Activo";
        this.telefono = "+569" + this.telefono;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
        return Objects.equals(idPaciente, paciente.idPaciente) && Objects.equals(nombre, paciente.nombre) && Objects.equals(apellido, paciente.apellido) && Objects.equals(telefono, paciente.telefono) && Objects.equals(fechaNacimiento, paciente.fechaNacimiento) && Objects.equals(ocupacion, paciente.ocupacion) && Objects.equals(institucion, paciente.institucion) && Objects.equals(afiliacionSalud, paciente.afiliacionSalud) && Objects.equals(estadoCivil, paciente.estadoCivil) && Objects.equals(familiaNuclear, paciente.familiaNuclear) && Objects.equals(estado, paciente.estado) && Objects.equals(atenciones, paciente.atenciones) && Objects.equals(fichaTratamiento, paciente.fichaTratamiento) && Objects.equals(anamnesis, paciente.anamnesis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, nombre, apellido, telefono, fechaNacimiento, ocupacion, institucion, afiliacionSalud, estadoCivil, familiaNuclear, estado, atenciones, fichaTratamiento, anamnesis);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
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
