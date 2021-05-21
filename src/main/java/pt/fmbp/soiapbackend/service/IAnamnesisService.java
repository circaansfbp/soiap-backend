package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.Anamnesis;

public interface IAnamnesisService {

    // Obtener una anamnesis específica a través de su ID
    Anamnesis getAnamnesisById(Long idAnamnesis);

    // Actualizar una anamnesis
    Anamnesis updateAnamnesis(Anamnesis anamnesis, Long idAnamnesis);

    // Eliminación lógica de una anamnesis
    Anamnesis deleteAnamnesis(Long idAnamnesis);

    // Recuperación lógica de una anamnesis
    Anamnesis reintegrarAnamnesis(Long idAnamnesis);
}
