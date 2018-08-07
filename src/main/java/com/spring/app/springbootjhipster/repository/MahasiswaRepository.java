package com.spring.app.springbootjhipster.repository;

import com.spring.app.springbootjhipster.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {

    Mahasiswa findByName(String name);
}
