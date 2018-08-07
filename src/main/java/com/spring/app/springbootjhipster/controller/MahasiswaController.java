package com.spring.app.springbootjhipster.controller;

import com.spring.app.springbootjhipster.entity.Mahasiswa;
import com.spring.app.springbootjhipster.exception.NotFoundException;
import com.spring.app.springbootjhipster.repository.JurusanRepository;
import com.spring.app.springbootjhipster.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private JurusanRepository jurusanRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @PostMapping(value = "/{idjurusan}/create")
    public Mahasiswa createMahasiswa(@PathVariable Long idjurusan,
                                     @RequestBody Mahasiswa mahasiswa){
        return jurusanRepository.findById(idjurusan)
                .map(currentJurusan -> {
                    mahasiswa.setJurusan(currentJurusan);
                    return mahasiswaRepository.save(mahasiswa);
                }).orElseThrow(() -> new NotFoundException("idjurusan : "+idjurusan+" not found"));
    }

    @GetMapping(value = "/{idmahasiswa}")
    public Optional<Mahasiswa> getMahasiswaId(@PathVariable Long idmahasiswa){
        Optional<Mahasiswa> mahasiswaId = mahasiswaRepository.findById(idmahasiswa);
        if (!mahasiswaId.isPresent()){
            throw new NotFoundException("Mahasiswa tidak ada");
        }
        return mahasiswaId;
    }

    @DeleteMapping(value = "/{idmahasiswa}")
    public ResponseEntity<Void> deleteMahasiswa(@PathVariable Long idmahasiswa){
        return mahasiswaRepository.findById(idmahasiswa)
                .map(mahasiswa -> {
                    mahasiswaRepository.delete(mahasiswa);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Mahasiswa> list(){
        List<Mahasiswa> mahasiswas = new ArrayList<>();
        for (Mahasiswa mahasiswa : mahasiswaRepository.findAll()){
            mahasiswas.add(mahasiswa);
        }
        return mahasiswas;
    }

    @GetMapping(value = "/name")
    public Mahasiswa byName(@RequestParam(value = "name") String name){
        return mahasiswaRepository.findByName(name);
    }

    @PutMapping(value = "/{idjurusan}/update/{idmahasiswa}")
    public Mahasiswa updateMahasiswa(@PathVariable("idjurusan") Long idjurusan,
                                     @PathVariable("idmahasiswa") Long idmahasiswa,
                                     @RequestBody Mahasiswa mahasiswa){

        return jurusanRepository.findById(idjurusan)
                .map(currentJurusan -> {
                    return mahasiswaRepository.findById(idmahasiswa)
                            .map(currentMahasiswa -> {
                                currentMahasiswa.setJurusan(currentJurusan);
                                currentMahasiswa.setName(mahasiswa.getName());
                                return mahasiswaRepository.save(currentMahasiswa);
                            }).orElseThrow(() -> new NotFoundException("idmahasiswa : "+idmahasiswa+" not found"));
                }).orElseThrow(() -> new NotFoundException("idjurusan : "+idjurusan+" not found"));
    }
}
