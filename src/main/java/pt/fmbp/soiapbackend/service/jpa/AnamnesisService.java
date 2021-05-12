package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.Anamnesis;
import pt.fmbp.soiapbackend.repository.IAnamnesisRepository;
import pt.fmbp.soiapbackend.service.IAnamnesisService;

@Service
public class AnamnesisService implements IAnamnesisService {

    @Autowired
    private IAnamnesisRepository anamnesisRepository;

    // Obtener una anamnesis por su ID
    public Anamnesis getAnamnesisById(Long idAnamnesis) {
        if (idAnamnesis != null && idAnamnesis != 0) {
            return anamnesisRepository.findById(idAnamnesis).orElse(null);
        } else
            return null;
    }

    // Actualizar una anamnesis
    @Override
    @Transactional
    public Anamnesis updateAnamnesis(Anamnesis anamnesis, Long idAnamnesis) {
        if (idAnamnesis != null && idAnamnesis != 0) {
            Anamnesis anamnesisToUpdate = getAnamnesisById(idAnamnesis);

            if (anamnesisToUpdate != null) {

                // Actualiza el motivo de la consulta del paciente
                if (!anamnesisToUpdate.getMotivoConsultaPaciente().equalsIgnoreCase(anamnesis.getMotivoConsultaPaciente())) {
                    anamnesisToUpdate.setMotivoConsultaPaciente(anamnesis.getMotivoConsultaPaciente());
                }

                // Actualiza los antecedentes del paciente
                if (!anamnesisToUpdate.getAntecedentesPaciente().equalsIgnoreCase(anamnesis.getAntecedentesPaciente())) {
                    anamnesisToUpdate.setAntecedentesPaciente(anamnesis.getAntecedentesPaciente());
                }

                // Actualiza los antecedentes familiares
                if (!anamnesisToUpdate.getAntecedentesFamiliares().equalsIgnoreCase(anamnesis.getAntecedentesFamiliares())) {
                    anamnesisToUpdate.setAntecedentesFamiliares(anamnesis.getAntecedentesFamiliares());
                }

                // Actualiza las observaciones
                if (!anamnesisToUpdate.getObservaciones().equalsIgnoreCase(anamnesis.getObservaciones())) {
                    anamnesisToUpdate.setObservaciones(anamnesis.getObservaciones());
                }

                return anamnesisRepository.save(anamnesisToUpdate);
            }
            else return null;
        }
        else return null;
    }
}
