package pt.fmbp.soiapbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ficha_tratamiento")
public class FichaTratamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha", nullable = false, updatable = false)
    private Long idFichaTratamiento;

    @Column(name = "motivo_consulta_profesional", nullable = false)
    private String motivoConsultaProfesional;

    @Column(name = "fecha_diagnostico", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date fechaDiagnostico;

    @Column(name = "resultado_diagnostico", nullable = false)
    private String resultadoDiagnostico;

    @Column(name = "sugerencia_tratamiento", nullable = false)
    private String sugerenciaTratamiento;

    @Column(name = "objetivos_terapia", nullable = false)
    private String objetivosTerapia;

    @Column(nullable = false)
    private String estado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fichaTratamiento", cascade = CascadeType.ALL)
    private List<SesionTerapia> sesionesDeTerapia;

    public FichaTratamiento() { }

    public FichaTratamiento(Long idFichaTratamiento, String motivoConsultaProfesional, Date fechaDiagnostico,
                            String resultadoDiagnostico, String sugerenciaTratamiento, String objetivosTerapia) {
        this.idFichaTratamiento = idFichaTratamiento;
        this.motivoConsultaProfesional = motivoConsultaProfesional;
        this.fechaDiagnostico = fechaDiagnostico;
        this.resultadoDiagnostico = resultadoDiagnostico;
        this.sugerenciaTratamiento = sugerenciaTratamiento;
        this.objetivosTerapia = objetivosTerapia;
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

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
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
