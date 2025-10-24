package tg.inseed.gestioncourrier.gestioncourrier.typeCourrier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur les types de courrier.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link TypeCourrierService}.
 * 
 * Permet de catégoriser les courriers selon leur nature ou leur usage.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-typecourriers")
public class TypeCourrierController {

    @Autowired
    private final TypeCourrierService typeCourrierService;

    public TypeCourrierController(TypeCourrierService typeCourrierService) {
        this.typeCourrierService = typeCourrierService;
    }

    @PostMapping("/ajouter")
    public TypeCourrier createTypeCourrier(@RequestBody TypeCourrier typeCourrier) {
        return typeCourrierService.createTypeCourrier(typeCourrier);
    }

    @GetMapping("/list")
    public List<TypeCourrier> getAllTypeCourriers() {
        return typeCourrierService.getAllTypeCourriers();
    }

    @GetMapping("/{id}")
    public TypeCourrier getTypeCourrierById(@PathVariable Long id) {
        return typeCourrierService.getTypeCourrierById(id);
    }

    @PutMapping("/update/{id}")
    public TypeCourrier updateTypeCourrier(@PathVariable Long id, @RequestBody TypeCourrier typeCourrier) {
        return typeCourrierService.updateTypeCourrier(id, typeCourrier);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTypeCourrier(@PathVariable Long id) {
        typeCourrierService.deleteTypeCourrier(id);
    }
}