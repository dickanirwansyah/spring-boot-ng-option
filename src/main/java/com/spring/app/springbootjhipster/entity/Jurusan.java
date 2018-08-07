package com.spring.app.springbootjhipster.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jurusan")
public class Jurusan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idjurusan;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "jurusan", cascade = CascadeType.ALL)
    private Set<Mahasiswa> mahasiswas = new HashSet<>();

    public Jurusan(){}

    public Long getIdjurusan(){
        return idjurusan;
    }

    public void setIdjurusan(Long idjurusan){
        this.idjurusan = idjurusan;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
