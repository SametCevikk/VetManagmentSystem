package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVaccineService {

    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);
    VaccineResponse get(Long id);
    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);
    Page<VaccineResponse> cursor(int page, int pageSize);
    void delete(Long id);


}
