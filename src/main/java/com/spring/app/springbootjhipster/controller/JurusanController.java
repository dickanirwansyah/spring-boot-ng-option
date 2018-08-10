package com.spring.app.springbootjhipster.controller;

import com.spring.app.springbootjhipster.entity.Jurusan;
import com.spring.app.springbootjhipster.exception.NotFoundException;
import com.spring.app.springbootjhipster.repository.JurusanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/jurusan")
public class JurusanController {

    @Autowired
    private JurusanRepository jurusanRepository;

    @PostMapping
    public Jurusan newJurusan(@RequestBody Jurusan jurusan){
        return jurusanRepository.save(jurusan);
    }

    @GetMapping(value = "/{idjurusan}")
    public Optional<Jurusan> getId(@PathVariable Long idjurusan){
        Optional<Jurusan> jurusan = jurusanRepository.findById(idjurusan);
        if(!jurusan.isPresent()){
            throw new NotFoundException("jurusan tidak ada");
        }
        return jurusan;
    }

    @GetMapping
    public List<Jurusan> listJurusan(){
        List<Jurusan> jurusans = new ArrayList<>();
        for (Jurusan jurusan : jurusanRepository.findAll()){
            jurusans.add(jurusan);
        }
        return jurusans;
    }

    @PutMapping(value = "/{idjurusan}")
    public Jurusan updateJurusan(@PathVariable Long idjurusan,
                                 @RequestBody Jurusan jurusan){
        return jurusanRepository.findById(idjurusan)
                .map(currentJurusan -> {
                    currentJurusan.setName(jurusan.getName());
                    currentJurusan.setActive(jurusan.isActive());
                    return jurusanRepository.save(currentJurusan);
                }).orElseThrow(() -> new NotFoundException("idjurusan : "+idjurusan+" not found"));
    }

    @PutMapping
    public ResponseEntity<Jurusan> jurusanUpdate(@RequestBody Jurusan jurusan){
        if (jurusan.getIdjurusan() == 0){
            return new ResponseEntity<Jurusan>(HttpStatus.NOT_FOUND);
        }
        return Optional.ofNullable(jurusanRepository.save(jurusan))
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.OK))
                .orElse(new ResponseEntity<Jurusan>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/deactive/{idjurusan}")
    public Jurusan deactiveJurusan(@PathVariable Long idjurusan){
        return jurusanRepository.findById(idjurusan)
                .map(currentJurusan -> {
                    currentJurusan.setActive(false);
                    return jurusanRepository.save(currentJurusan);
                }).orElseThrow(() -> new NotFoundException("idjurusan : "+idjurusan+" not found"));
    }

    @PostMapping(value = "/active/{idjurusan}")
    public Jurusan activeJurusan(@PathVariable Long idjurusan){
        return jurusanRepository.findById(idjurusan)
                .map(currentJurusan -> {
                    currentJurusan.setActive(true);
                    return jurusanRepository.save(currentJurusan);
                }).orElseThrow(() -> new NotFoundException("idjurusan : "+idjurusan+" not found"));
    }
}
