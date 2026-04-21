package com.fst.elearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecon {  // ← Changé : 'lecon' → 'Lecon' (majuscule)
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(columnDefinition = "TEXT")
    private String contenu;
    
    private Integer ordre;
    
    private Integer dureeMin;
    
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CoursModule module;  // ← Changé : 'module' → 'CoursModule'

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getDureeMin() {
		return dureeMin;
	}

	public void setDureeMin(Integer dureeMin) {
		this.dureeMin = dureeMin;
	}
}