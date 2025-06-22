package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VetManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.animal.AnimalResponse;
import org.springframework.data.domain.Page;

public interface IAnimalService {
    AnimalResponse save(AnimalSaveRequest animalSaveRequest);
    AnimalResponse get(Long id);
    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);
    Page<AnimalResponse> cursor(int page, int pageSize);
    void delete(Long id);
}
