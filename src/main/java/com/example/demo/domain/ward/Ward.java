package com.example.demo.domain.ward;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.tavern.Tavern;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ward")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ward {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tavern> taverns = Collections.emptyList();
}
