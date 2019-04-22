package com.myway.jetsons.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Ebru Göksal
 */
@Entity
@Table(name = "emlakci")
public class Emlakci {
    @ApiModelProperty(value = "The ID for Emlakci")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emlakci")
    @SequenceGenerator(name = "seq_emlakci", sequenceName = "seq_emlakci", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "vitrin_hakki")
    private int vitrinHakki;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVitrinHakki() {
        return vitrinHakki;
    }

    public void setVitrinHakki(int vitrinHakki) {
        this.vitrinHakki = vitrinHakki;
    }
}
