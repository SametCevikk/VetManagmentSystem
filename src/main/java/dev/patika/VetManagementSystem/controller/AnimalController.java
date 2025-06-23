package dev.patika.VetManagementSystem.controller;

import dev.patika.VetManagementSystem.core.result.Result;
import dev.patika.VetManagementSystem.core.result.ResultData;
import dev.patika.VetManagementSystem.core.result.ResultHelper;
import dev.patika.VetManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VetManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VetManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VetManagementSystem.entities.Animal;
import dev.patika.VetManagementSystem.service.concretes.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){

        AnimalResponse animalResponse = animalService.save(animalSaveRequest);

        return ResultHelper.success(animalResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id){
        AnimalResponse animalResponse = animalService.get(id);

        return ResultHelper.success(animalResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){
        AnimalResponse animalResponse = animalService.update(animalUpdateRequest);

        return ResultHelper.success(animalResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AnimalResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        return animalService.cursor(page,pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        animalService.delete(id);
        return ResultHelper.success();

    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<AnimalResponse> getCustomersByName(
            @RequestParam String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize){
        return animalService.getAnimalByName(name,page,pageSize);
    }

    @GetMapping("/by-customer/{customerId}")
    public Page<AnimalResponse> getAnimalsByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        return animalService.getAnimalsByCustomerId(customerId,page,pageSize);
    }

}
