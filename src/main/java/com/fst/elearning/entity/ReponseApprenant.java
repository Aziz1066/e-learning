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
@Table(name = "reponse_apprenant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReponseApprenant {  // ← Changé : 'reponseapprenant' → 'ReponseApprenant'
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Utilisateur apprenant;  // ← Changé : 'utilisateur' → 'Utilisateur'
    
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;  // ← Changé : 'question' → 'Question'
    
    private Integer reponseDonnee;
    
    private Boolean estCorrecte;
    
    private LocalDateTime dateSoumission = LocalDateTime.now();

	public Boolean getEstCorrecte() {
		return estCorrecte;
	}

	public void setEstCorrecte(Boolean estCorrecte) {
		this.estCorrecte = estCorrecte;
	}

	public LocalDateTime getDateSoumission() {
		return dateSoumission;
	}

	public void setDateSoumission(LocalDateTime dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	public Integer getReponseDonnee() {
		return reponseDonnee;
	}

	public void setReponseDonnee(Integer reponseDonnee) {
		this.reponseDonnee = reponseDonnee;
	}
}