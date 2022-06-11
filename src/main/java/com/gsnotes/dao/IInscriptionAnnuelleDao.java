package com.gsnotes.dao;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IInscriptionAnnuelleDao extends JpaRepository<InscriptionAnnuelle, Long> {
    List<InscriptionAnnuelle> getInscriptionAnnuelleByAnneeAndNiveau(int annee, Niveau niveau);
    List<InscriptionAnnuelle> findByEtudiantAndAnnee(Etudiant etudiant,int annee);
}
