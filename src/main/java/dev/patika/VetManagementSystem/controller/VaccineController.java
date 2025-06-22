package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VetManagementSystem.dto.response.vaccine.VaccineWithAnimalResponse;
import dev.patika.VetManagementSystem.service.concretes.VaccineService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){

         VaccineResponse vaccineResponse = vaccineService.save(vaccineSaveRequest);

         return ResultHelper.success(vaccineResponse);

    }
    @GetMapping("/expiring")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineWithAnimalResponse> getUnexpiredVaccines(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
            @RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finishDate){
        return vaccineService.getUnexpiredVaccines(startDate,finishDate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> getVaccine(@PathVariable("id") Long id){
        VaccineResponse vaccineResponse = vaccineService.get(id);

        return ResultHelper.success(vaccineResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> updateVaccine(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        VaccineResponse vaccineResponse = vaccineService.update(vaccineUpdateRequest);

        return ResultHelper.success(vaccineResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VaccineResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return vaccineService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteById(@PathVariable("id") Long id){
        vaccineService.delete(id);
        return ResultHelper.success();

    }


    @GetMapping("/by-animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Page<VaccineResponse> getVaccineByAnimalId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize){

        return vaccineService.getVaccineByAnimalId(id,page,pageSize);
    }





}
