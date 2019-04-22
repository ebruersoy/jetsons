package com.myway.jetsons.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Ebru Göksal
 */
@Entity
@Table(name = "ilan")
public class Ilan {

    @ApiModelProperty(value = "The ID for Ilan")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ilan")
    @SequenceGenerator(name = "seq_ilan", sequenceName = "seq_ilan", allocationSize = 1)
    private Long id;

    @Column(name = "detail")
    private String detail;

    @Column(name = "vitrin_hakki")
    private boolean vitrinHakki;

    @OneToOne
    @JoinColumn(name = "danisman_id")
    private Danisman danisman;

    @ManyToOne
    @JoinColumn(name = "ofis_id")
    private Ofis ofis;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Danisman getDanisman() {
        return danisman;
    }

    public void setDanisman(Danisman danisman) {
        this.danisman = danisman;
    }

    public boolean isVitrinHakki() {
        return vitrinHakki;
    }

    public void setVitrinHakki(boolean vitrinHakki) {
        this.vitrinHakki = vitrinHakki;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Ofis getOfis() {
        return ofis;
    }

    public void setOfis(Ofis ofis) {
        this.ofis = ofis;
    }
}
