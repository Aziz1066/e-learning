package com.fst.elearning.entity;

import java.time.LocalDateTime;

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
@Table(name = "progression_lecon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressionLecon {  // ← Changé : 'progressionlecon' → 'ProgressionLecon'
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Utilisateur apprenant;  // ← Changé : 'utilisateur' → 'Utilisateur'
    
    @ManyToOne
    @JoinColumn(name = "lecon_id", nullable = false)
    private Lecon lecon;  // ← Changé : 'lecon' → 'Lecon'
    
    private boolean completee = false;
    
    private LocalDateTime dateCompletion;

	public LocalDateTime getDateCompletion() {
		return dateCompletion;
	}

	public void setDateCompletion(LocalDateTime dateCompletion) {
		this.dateCompletion = dateCompletion;
	}

	public boolean isCompletee() {
		return completee;
	}

	public void setCompletee(boolean completee) {
		this.completee = completee;
	}
}