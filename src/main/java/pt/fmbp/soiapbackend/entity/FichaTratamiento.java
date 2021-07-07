package pt.fmbp.soiapbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ficha_tratamiento")
public class FichaTratamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha", nullable = false, updatable = false)
    private Long idFichaTratamiento;

    @NotEmpty
    @Size(max = 2000)
    @Column(name = "motivo_consulta_profesional")
    private String motivoConsultaProfesional;

    @NotNull
    @Column(name = "fecha_diagnostico")
    private LocalDate fechaDiagnostico;

    @NotEmpty
    @Size(max = 2000)
    @Column(name = "resultado_diagnostico")
    private String resultadoDiagnostico;

    @NotEmpty
    @Size(max = 2000)
    @Column(name = "sugerencia_tratamiento")
    private String sugerenciaTratamiento;

    @NotEmpty
    @Size(max = 2000)
    @Column(name = "objetivos_terapia")
    private String objetivosTerapia;

    @NotEmpty
    @Size(max = 15)
    private String estado;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fichaTratamiento", cascade = CascadeType.ALL)
    private List<SesionTerapia> sesionesDeTerapia;

    public FichaTratamiento() { }

    public FichaTratamiento(Long idFichaTratamiento, String motivoConsultaProfesional, LocalDate fechaDiagnostico,
                            String resultadoDiagnostico, String sugerenciaTratamiento, String objetivosTerapia) {
        this.idFichaTratamiento = idFichaTratamiento;
        this.motivoConsultaProfesional = motivoConsultaProfesional;
        this.fechaDiagnostico = fechaDiagnostico;
        this.resultadoDiagnostico = resultadoDiagnostico;
        this.sugerenciaTratamiento = sugerenciaTratamiento;
        this.objetivosTerapia = objetivosTerapia;
        this.estado = "Activo";
    }

    @PrePersist
    private void prePersist() {
        this.fechaDiagnostico = LocalDate.now();
        this.estado = "Activo";
    }

    public Long getIdFichaTratamiento() {
        return idFichaTratamiento;
    }

    public String getMotivoConsultaProfesional() {
        return motivoConsultaProfesional;
    }

    public void setMotivoConsultaProfesional(String motivoConsultaProfesional) {
        this.motivoConsultaProfesional = motivoConsultaProfesional;
    }

    public LocalDate getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getResultadoDiagnostico() {
        return resultadoDiagnostico;
    }

    public void setResultadoDiagnostico(String resultadoDiagnostico) {
        this.resultadoDiagnostico = resultadoDiagnostico;
    }

    public String getSugerenciaTratamiento() {
        return sugerenciaTratamiento;
    }

    public void setSugerenciaTratamiento(String sugerenciaTratamiento) {
        this.sugerenciaTratamiento = sugerenciaTratamiento;
    }

    public String getObjetivosTerapia() {
        return objetivosTerapia;
    }

    public void setObjetivosTerapia(String objetivosTerapia) {
        this.objetivosTerapia = objetivosTerapia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SesionTerapia> getSesionesDeTerapia() {
        return sesionesDeTerapia;
    }

    public void setSesionesDeTerapia(List<SesionTerapia> sesionesDeTerapia) {
        this.sesionesDeTerapia = sesionesDeTerapia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FichaTratamiento)) return false;
        FichaTratamiento that = (FichaTratamiento) o;
        return Objects.equals(idFichaTratamiento, that.idFichaTratamiento) && Objects.equals(motivoConsultaProfesional, that.motivoConsultaProfesional) && Objects.equals(fechaDiagnostico, that.fechaDiagnostico) && Objects.equals(resultadoDiagnostico, that.resultadoDiagnostico) && Objects.equals(sugerenciaTratamiento, that.sugerenciaTratamiento) && Objects.equals(objetivosTerapia, that.objetivosTerapia) && Objects.equals(estado, that.estado) && Objects.equals(sesionesDeTerapia, that.sesionesDeTerapia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFichaTratamiento, motivoConsultaProfesional, fechaDiagnostico, resultadoDiagnostico, sugerenciaTratamiento, objetivosTerapia, estado, sesionesDeTerapia);
    }

    @Override
    public String toString() {
        return "FichaTratamiento{" +
                "idFichaTratamiento=" + idFichaTratamiento +
                ", motivoConsultaProfesional='" + motivoConsultaProfesional + '\'' +
                ", fechaDiagnostico=" + fechaDiagnostico +
                ", resultadoDiagnostico='" + resultadoDiagnostico + '\'' +
                ", sugerenciaTratamiento='" + sugerenciaTratamiento + '\'' +
                ", objetivosTerapia='" + objetivosTerapia + '\'' +
                ", estado='" + estado + '\'' +
                ", sesionesDeTerapia=" + sesionesDeTerapia +
                '}';
    }
}
