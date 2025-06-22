package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.VetManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VetManagementSystem.service.concretes.DoctorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){

        DoctorResponse doctorResponse = doctorService.save(doctorSaveRequest);

        return ResultHelper.success(doctorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id){
        DoctorResponse doctorResponse = doctorService.get(id);

        return ResultHelper.success(doctorResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest ){
        DoctorResponse doctorResponse = doctorService.update(doctorUpdateRequest);

        return ResultHelper.success(doctorResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DoctorResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return doctorService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        doctorService.delete(id);
        return ResultHelper.success();

    }
}
