package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.FichaTratamiento;

public interface IFichaTratamientoService {

    // Obtener una ficha de tratamiento por su ID
    FichaTratamiento getFichaTratamientoById(Long idFichaTratamiento);

    // Actualizar una ficha de tratamiento
    FichaTratamiento updateFichaTratamiento(FichaTratamiento fichaTratamiento, Long idFichaTratamiento);

    // Eliminación lógica de una ficha de tratamiento
    FichaTratamiento deleteFichaTratamiento(Long idFichaTratamiento);

    // Reintegración lógica de una ficha de tratamiento
    FichaTratamiento reintegrarFichaTratamiento(Long idFichaTratamiento);
}
