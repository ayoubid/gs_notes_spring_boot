package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEtudiantDao;
import com.gsnotes.dao.IInscriptionAnnuelleDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.exceptionhandlers.exception.IntegrityException;
import com.gsnotes.services.IInscriptionAnnuelleService;
import com.gsnotes.utils.enumeration.ActionType;
import com.gsnotes.utils.importing.model.ImportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InscriptionAnnuelleServiceImpl implements IInscriptionAnnuelleService {



    @Autowired
    private INiveauDao niveauDao;
    @Autowired
    private IInscriptionAnnuelleDao inscriptionAnnuelleDao;
    @Autowired
    private IEtudiantDao etudiantDao;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ImportModel>  inscription(List<ImportModel> data) throws IntegrityException {
        List<ImportModel> out = new ArrayList<>();
        for(ImportModel model : data)
        {
            Optional<Etudiant> etudiantOptional = etudiantDao.findById(model.getId());
            if(model.getType()== ActionType.REINSCRIPTION&&etudiantOptional.isEmpty())
                    throw new IntegrityException("Unable to find student with id :"+model.getId());
            else if(etudiantOptional.isPresent()&&model.getType()== ActionType.INSCRIPTION)
                throw new IntegrityException("Student with id :"+model.getId()+" already exist");
            Etudiant etudiant = null;
            if(etudiantOptional.isPresent())
                etudiant=etudiantOptional.get();
            Optional<Niveau> niveauOptional = niveauDao.findById(model.getIdNiveau());
            if(niveauOptional.isEmpty())
            {
                throw new IntegrityException("Unable to find level with id : "+model.getIdNiveau());
            }
            int thisyear = Calendar.getInstance().get(Calendar.YEAR);
            Niveau niveau = niveauOptional.get();
            if(model.getType()==ActionType.INSCRIPTION)
            {
                etudiant = new Etudiant();
                etudiant.setCne(model.getCne());
                etudiant.setNom(model.getNom());
                etudiant.setPrenom(model.getPrenom());
                etudiant = etudiantDao.save(etudiant);
            }
            else
            {
                if(inscriptionAnnuelleDao.findByEtudiantAndAnnee(etudiant,thisyear).size()!=0)
                    throw new IntegrityException("Student with id "+etudiant.getIdUtilisateur()+" has already a subscription");
                Niveau nextLevel = niveau.getNiveauSuivant();
                if(nextLevel==null)
                    throw new IntegrityException("No next level for level id : "+niveau.getIdNiveau());
                niveau = nextLevel;
            }
            if(!etudiant.getNom().equals(model.getNom())
            ||!etudiant.getCne().equals(model.getCne())
            ||!etudiant.getPrenom().equals(model.getPrenom()))
                out.add(model);

            InscriptionAnnuelle inscriptionAnnuelle = new InscriptionAnnuelle();
            inscriptionAnnuelle.setAnnee(thisyear);
            inscriptionAnnuelle.setEtudiant(etudiant);
            inscriptionAnnuelle.setNiveau(niveau);

            inscriptionAnnuelleDao.save(inscriptionAnnuelle);
        }
        return out;
    }


    @Override
    public List<InscriptionAnnuelle> getInscriptionAnnuelleByAnneeAndNiveau(int annee, Niveau niveau){
        return  inscriptionAnnuelleDao.getInscriptionAnnuelleByAnneeAndNiveau(annee, niveau);
    }

}
