package com.clinic.medinstitute.controllers;

import java.util.List;

import com.clinic.medinstitute.entities.ClientEntity;
import com.clinic.medinstitute.repositories.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinic.medinstitute.entities.DoctorEntity;
import com.clinic.medinstitute.services.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v4/test/doctors")
public class DoctorController {
    
    /*
     * Dependency injection
     */
    @Autowired
    private DoctorService service;
    @Autowired
    private MedicRepository mRap;
    
    private DoctorController(DoctorService service) {
        this.service = service;
    }


    /*
     * Find all Clients
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DoctorEntity> findAllDoctors() {
        return service.findAll();
    }


    /*
     * Find Client by it's ID
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorEntity findDoctorById(@PathVariable @NotBlank @Positive Long id) {
        return service.findById(id);
    }

    /*
     * Find Doctor by it's Name
     */
    @GetMapping(value = "searchDoctorName")
    @ResponseBody
    public ResponseEntity<List<DoctorEntity>> searchDoctorName(@RequestParam(name = "name")String name){
        List<DoctorEntity> doctor = mRap.searchDoctorName(name.trim().toUpperCase());
        return new ResponseEntity<List<DoctorEntity>>(doctor, HttpStatus.OK);
    }

    /*
     * Insert new Client
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DoctorEntity insertNewDoctor(@Valid @RequestBody DoctorEntity medic) {
        return service.insert(medic);
    }


    /*
     * Update an existing Client
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorEntity updateDoctor(@Valid @RequestBody DoctorEntity medic, @PathVariable @NotBlank @Positive Long id) {
        return service.update(medic, id);
    }


    /*
     * Delete an existing Client
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable @NotBlank @Positive Long id) {
        service.delete(id);
    }

}
