package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.VetManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineResponse;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);
    DoctorResponse get(Long id);
    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest);
    Page<DoctorResponse> cursor(int page, int pageSize);
    void delete(Long id);
}
