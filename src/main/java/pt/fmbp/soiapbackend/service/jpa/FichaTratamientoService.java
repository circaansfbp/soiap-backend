package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.FichaTratamiento;
import pt.fmbp.soiapbackend.repository.IFichaTratamientoRepository;
import pt.fmbp.soiapbackend.service.IFichaTratamientoService;

@Service
public class FichaTratamientoService implements IFichaTratamientoService {

    @Autowired
    private IFichaTratamientoRepository fichaTratamientoRepository;

    // Para obtener una ficha de tratamiento específica
    @Override
    @Transactional(readOnly = true)
    public FichaTratamiento getFichaTratamientoById(Long idFichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            return fichaTratamientoRepository.findById(idFichaTratamiento).orElse(null);
        } else
            return null;
    }

    // Para actualizar una ficha de tratamiento
    @Override
    public FichaTratamiento updateFichaTratamiento(FichaTratamiento fichaTratamiento, Long idFichaTratamiento) {
        if (fichaTratamiento != null && idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaToUpdate = getFichaTratamientoById(idFichaTratamiento);

            // Actualiza el motivo de la consulta
            if (!fichaToUpdate.getMotivoConsultaProfesional().equalsIgnoreCase(fichaTratamiento.getMotivoConsultaProfesional())) {
                fichaToUpdate.setMotivoConsultaProfesional(fichaTratamiento.getMotivoConsultaProfesional());
            }

            // Actualiza el resultado del diagnóstico
            if (!fichaToUpdate.getResultadoDiagnostico().equalsIgnoreCase(fichaTratamiento.getResultadoDiagnostico())) {
                fichaToUpdate.setResultadoDiagnostico(fichaTratamiento.getResultadoDiagnostico());
            }

            // Actualiza la sugerencia del tratamiento
            if (!fichaToUpdate.getSugerenciaTratamiento().equalsIgnoreCase(fichaTratamiento.getSugerenciaTratamiento())) {
                fichaToUpdate.setSugerenciaTratamiento(fichaTratamiento.getSugerenciaTratamiento());
            }

            // Actualiza los objetivos de la terapia
            if (!fichaToUpdate.getObjetivosTerapia().equalsIgnoreCase(fichaTratamiento.getObjetivosTerapia())) {
                fichaToUpdate.setObjetivosTerapia(fichaTratamiento.getObjetivosTerapia());
            }

            return fichaTratamientoRepository.save(fichaToUpdate);
        }
        else
            return null;
    }

    // Eliminación lógica de una ficha de tratamiento
    @Override
    @Transactional
    public FichaTratamiento deleteFichaTratamiento(Long idFichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaToDelete = getFichaTratamientoById(idFichaTratamiento);

            if (fichaToDelete != null) {
                fichaToDelete.setEstado("Inactivo");
                return fichaTratamientoRepository.save(fichaToDelete);
            }
            else
                return null;
        }
        else
            return null;
    }

    // Reintegración lógica de una ficha de tratamiento
    @Override
    @Transactional
    public FichaTratamiento reintegrarFichaTratamiento(Long idFichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaToIntegrate = getFichaTratamientoById(idFichaTratamiento);

            if (fichaToIntegrate != null) {
                fichaToIntegrate.setEstado("Activo");
                return fichaTratamientoRepository.save(fichaToIntegrate);
            }
            else
                return null;
        }
        else
            return null;
    }
}
