package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.VetManagementSystem.service.concretes.AvailableDateService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available-dates")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){

        AvailableDateResponse availableDateResponse = availableDateService.save(availableDateSaveRequest);

        return ResultHelper.success(availableDateResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id){
        AvailableDateResponse availableDateResponse = availableDateService.get(id);

        return ResultHelper.success(availableDateResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest ){
        AvailableDateResponse availableDateResponse = availableDateService.update(availableDateUpdateRequest);

        return ResultHelper.success(availableDateResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AvailableDateResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return availableDateService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        availableDateService.delete(id);
        return ResultHelper.success();

    }
}
