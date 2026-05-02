package com.fst.elearning.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "inscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {  // ← Changé : 'inscription' → 'Inscription' (majuscule)
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Utilisateur apprenant;  // ← Changé : 'utilisateur' → 'Utilisateur'
    
    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;  // ← Changé : 'cours' → 'Cours'
    
    private LocalDate dateInscription = LocalDate.now();
    
    @Enumerated(EnumType.STRING)
    private StatutInscription statut = StatutInscription.EN_COURS;

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}
}

enum StatutInscription {
    EN_COURS, TERMINE, ABANDONNE
}