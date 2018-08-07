package com.spring.app.springbootjhipster.entity;

import javax.persistence.*;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmahasiswa;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idjurusan", nullable = false)
    private Jurusan jurusan;

    public Mahasiswa(){}

    public Long getIdmahasiswa(){
        return idmahasiswa;
    }

    public void setIdmahasiswa(Long idmahasiswa){
        this.idmahasiswa = idmahasiswa;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Jurusan getJurusan(){
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan){
        this.jurusan = jurusan;
    }
}
