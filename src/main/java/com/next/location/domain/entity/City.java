package com.next.location.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 254)
    private String name;

    @Column(name = "population", nullable = false)
    private Integer population;
    
}
