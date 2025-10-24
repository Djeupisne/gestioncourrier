package tg.inseed.gestioncourrier.gestioncourrier.typeCourrier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les types de courrier.
 * Ce service interagit avec {@link TypeCourrierRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class TypeCourrierService {

    @Autowired
    private final TypeCourrierRepository typeCourrierRepository;

    public TypeCourrierService(TypeCourrierRepository typeCourrierRepository) {
        this.typeCourrierRepository = typeCourrierRepository;
    }

    public TypeCourrier createTypeCourrier(TypeCourrier typeCourrier) {
        return typeCourrierRepository.save(typeCourrier);
    }

    public List<TypeCourrier> getAllTypeCourriers() {
        return typeCourrierRepository.findAll();
    }

    public TypeCourrier getTypeCourrierById(Long id) {
        return typeCourrierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TypeCourrier introuvable avec l'id: " + id));
    }

    public TypeCourrier updateTypeCourrier(Long id, TypeCourrier newTypeCourrier) {
        TypeCourrier typeCourrier = getTypeCourrierById(id);
        typeCourrier.setLibelle(newTypeCourrier.getLibelle());
        typeCourrier.setDescription(newTypeCourrier.getDescription());
        return typeCourrierRepository.save(typeCourrier);
    }

    public void deleteTypeCourrier(Long id) {
        if (!typeCourrierRepository.existsById(id)) {
            throw new RuntimeException("Le type de courrier avec l'id " + id + " n'existe pas.");
        }
        typeCourrierRepository.deleteById(id);
    }
}