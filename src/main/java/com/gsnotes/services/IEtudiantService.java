package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.web.models.PersonModel;

import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> getAllEtudiant();
    public List<Etudiant> getEtudiantByNiveau();
    public Etudiant getEtudiantById(Long idEtudiant);
    public void updateStudents(List<PersonModel> list);
}
