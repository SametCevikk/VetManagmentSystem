package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VetManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VetManagementSystem.service.concretes.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest){

        AppointmentResponse appointmentResponse = appointmentService.save(appointmentSaveRequest);

        return ResultHelper.success(appointmentResponse);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id){
        AppointmentResponse appointmentResponse = appointmentService.get(id);

        return ResultHelper.success(appointmentResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest ){
        AppointmentResponse appointmentResponse = appointmentService.update(appointmentUpdateRequest);

        return ResultHelper.success(appointmentResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AppointmentResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return appointmentService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        appointmentService.delete(id);
        return ResultHelper.success();

    }

    @GetMapping("/search/by-doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> searchAppointmentByDoctorId(
            @RequestParam Long doctorId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finishDate){

        return appointmentService.searchAppointmentByDoctorId(doctorId,startDate,finishDate);
    }

    @GetMapping("/search/by-animal")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> searchAppointmentByAnimalId(
            @RequestParam Long animalId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finishDate){

        return appointmentService.searchAppointmentByAnimalId(animalId,startDate,finishDate);
    }
}
