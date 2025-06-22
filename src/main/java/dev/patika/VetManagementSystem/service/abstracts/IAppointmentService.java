package dev.patika.VetManagementSystem.service.abstracts;

import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.appointment.AppointmentResponse;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest);
    AppointmentResponse get(Long id);
    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);
    Page<AppointmentResponse> cursor(int page, int pageSize);
    void delete(Long id);
}
