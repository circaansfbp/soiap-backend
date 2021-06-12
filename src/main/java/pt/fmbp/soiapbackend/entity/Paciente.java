package pt.fmbp.soiapbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente", nullable = false, updatable = false)
    private Long idPaciente;

    @NotEmpty(message = "El nombre del paciente no puede estar vacío.")
    @Size(min = 3, max = 30, message = "El nombre debe contener entre 3 y 30 caracteres.")
    @Pattern(regexp = "^(?!\\s)^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+$",
            message = "El nombre del paciente no debe comenzar con un espacio en blanco, ni contener caracteres especiales o números.")
    private String nombre;

    @NotEmpty(message = "El apellido del paciente no puede estar vacío")
    @Size(min = 3, max = 30, message = "El apellido debe contener entre 3 y 30 caracteres.")
    @Pattern(regexp = "^(?!\\s)^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+$",
            message = "El apellido del paciente no debe comenzar con un espacio en blanco, ni contener caracteres especiales o números.")
    private String apellido;

    @NotEmpty(message = "El número de teléfono del paciente no puede estar vacío.")
    @Size(min = 8, max = 12, message = "El teléfono debe contener un total de 8 caracteres.")
    private String telefono;

    @Email(message = "Debe ingresar un e-mail válido.")
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Size(max = 100, message = "La ocupación/profesión del paciente debe tener un máximo de 100 caracteres.")
    private String ocupacion;

    @Size(max = 100, message = "La institución a la que pertenece el paciente debe tener un máximo de 100 caracteres.")
    private String institucion;

    @NotEmpty(message = "La afiliación de salud del paciente no puede estar vacía.")
    @Column(name = "afiliacion_salud")
    @Size(max = 100, message = "El nombre de la afiliación de salud del paciente debe contener un máximo de 100 caracteres.")
    @Pattern(regexp = "^(?!\\s)^[0-9a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]+$",
            message = "Afiliación de salud del paciente: no se permite el ingreso de un espacio en blanco al comienzo, ni de dígitos o caracteres especiales.")
    private String afiliacionSalud;


    @Column(name = "estado_civil")
    @Size(max = 100, message = "El estado civil del paciente debe contener un máximo de 100 caracteres.")
    private String estadoCivil;


    @Column(name = "familia_nuclear")
    @Size(max = 1000, message = "Solo se permite un máximo de 1000 caracteres para la descripción de la familia nuclear del paciente")
    private String familiaNuclear;

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

    // Ficha de tratamiento del paciente
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ficha")
    private FichaTratamiento fichaTratamiento;


    public Paciente() {
        this.atenciones = new ArrayList<>();
    }

    public Paciente(Long idPaciente, String nombre, String apellido, String telefono, String email, LocalDate fechaNacimiento,
                    String ocupacion, String institucion, String afiliacionSalud, String estadoCivil, String familiaNuclear,
                    String estado, List<HoraAtencion> atenciones, FichaTratamiento fichaTratamiento, Anamnesis anamnesis) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
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
    private void PrePersist() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return Objects.equals(idPaciente, paciente.idPaciente) && Objects.equals(nombre, paciente.nombre) && Objects.equals(apellido, paciente.apellido) && Objects.equals(telefono, paciente.telefono) && Objects.equals(email, paciente.email) && Objects.equals(fechaNacimiento, paciente.fechaNacimiento) && Objects.equals(ocupacion, paciente.ocupacion) && Objects.equals(institucion, paciente.institucion) && Objects.equals(afiliacionSalud, paciente.afiliacionSalud) && Objects.equals(estadoCivil, paciente.estadoCivil) && Objects.equals(familiaNuclear, paciente.familiaNuclear) && Objects.equals(estado, paciente.estado) && Objects.equals(atenciones, paciente.atenciones) && Objects.equals(anamnesis, paciente.anamnesis) && Objects.equals(fichaTratamiento, paciente.fichaTratamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, nombre, apellido, telefono, email, fechaNacimiento, ocupacion, institucion, afiliacionSalud, estadoCivil, familiaNuclear, estado, atenciones, anamnesis, fichaTratamiento);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", ocupacion='" + ocupacion + '\'' +
                ", institucion='" + institucion + '\'' +
                ", afiliacionSalud='" + afiliacionSalud + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", familiaNuclear='" + familiaNuclear + '\'' +
                ", estado='" + estado + '\'' +
                ", atenciones=" + atenciones +
                ", anamnesis=" + anamnesis +
                ", fichaTratamiento=" + fichaTratamiento +
                '}';
    }
}
