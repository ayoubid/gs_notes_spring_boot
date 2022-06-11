package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.exceptionhandlers.exception.IntegrityException;
import com.gsnotes.utils.importing.model.ImportModel;

import java.util.List;

public interface IInscriptionAnnuelleService {


    public List<InscriptionAnnuelle> getInscriptionAnnuelleByAnneeAndNiveau(int annee, Niveau niveau);
    public List<ImportModel> inscription(List<ImportModel> data) throws IntegrityException;
}
