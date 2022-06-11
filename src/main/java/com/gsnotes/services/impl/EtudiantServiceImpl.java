package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.services.IEtudiantService;
import com.gsnotes.web.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

    @Autowired
    private IEtudiantDao etudiantDao;

    @Override
    public List<Etudiant> getAllEtudiant() {
        return etudiantDao.findAll();
    }

    @Override
    public List<Etudiant> getEtudiantByNiveau() {
        return null;
    }

    @Override
    public Etudiant getEtudiantById(Long idEtudaint) {

        return etudiantDao.getById(idEtudaint);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudents(List<PersonModel> list) {
        for(PersonModel model : list)
        {
            Optional<Etudiant> etudiantOptional = etudiantDao.findById(model.getIdUtilisateur());
            if(etudiantOptional.isPresent())
            {
                Etudiant etudiant = etudiantOptional.get();
                etudiant.setPrenom(model.getPrenom());
                etudiant.setNom(model.getNom());
                etudiant.setCne(model.getCne());
                etudiantDao.save(etudiant);
            }
        }
    }
}
