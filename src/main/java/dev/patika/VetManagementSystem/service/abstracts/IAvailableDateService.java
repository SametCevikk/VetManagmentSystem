package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.availableDate.AvailableDateResponse;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDateResponse get(Long id);
    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);
    Page<AvailableDateResponse> cursor(int page, int pageSize);
    void delete(Long id);
}
