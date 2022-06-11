package com.gsnotes.web.controllers;
import com.gsnotes.exceptionhandlers.exception.IntegrityException;
import com.gsnotes.services.IInscriptionAnnuelleService;

import com.gsnotes.services.impl.EtudiantServiceImpl;
import com.gsnotes.utils.importing.ImportUtil;
import com.gsnotes.utils.importing.model.ImportModel;
import com.gsnotes.web.models.PersonModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/cadreadmin")
public class EtudentsControler {


    @Autowired
    private IInscriptionAnnuelleService inscriptionAnnuelleService;
    @Autowired
    private EtudiantServiceImpl etudiantService;



    @GetMapping("/inscription")
    public String InscriptionView()
    {
        return "/cadreadmin/inscription";
    }


    @PostMapping("/inscription")
    public String addOrUpdateData(Model model, @RequestParam(value ="excel",required = false) MultipartFile excel, HttpServletRequest request)

    {
        if(request.getParameter("_method")!=null&&request.getParameter("_method").equals("update"))
            return this.updateData(model, request);
        int sheet = 1;

        List<ImportModel> data = new ArrayList<>();
        String error=null;
        try {
            data= ImportUtil.getFromCsvUsingModel(excel.getInputStream(),sheet);
        } catch (IntegrityException e) {
            error=e.getMessage()+"\nCause:"+e.getCause().getMessage();
        } catch (IOException e) {
            error = "Cause:" + e.getCause().getMessage();
        }
        List<ImportModel> toChange = new ArrayList<>();
        if(error==null) {
            try {
                toChange = inscriptionAnnuelleService.inscription(data);
            } catch (IntegrityException e) {
                error = e.getMessage();
            }
            catch (Exception e)
            {
                error=e.getMessage();
                e.printStackTrace();
            }
        }
        String success=null;
        if(error==null)
            success="Updated successfully";

        model.addAttribute("error",error);
        model.addAttribute("success",success);
        model.addAttribute("change",toChange);
        return "/cadreadmin/inscription";
    }







    private String updateData(Model model, HttpServletRequest request)
    {
        String data[] = request.getParameterValues("data");
        List<PersonModel> models = new ArrayList<>();
        for(String d: data)
        {
            String[] datos = d.split("-");
            if(datos.length==4)
            {
                PersonModel personModel = new PersonModel();
                personModel.setIdUtilisateur(Long.valueOf(datos[0]));
                personModel.setNom(datos[1]);
                personModel.setPrenom(datos[2]);
                personModel.setCne(datos[3]);
                models.add(personModel);
            }
        }
        String error=null;
        try
        {
            etudiantService.updateStudents(models);
        }catch (Exception e)
        {
            error=e.getMessage();
        }
        String success=null;
        if(error==null)
            success="Students updated";
        model.addAttribute("error",error);
        model.addAttribute("success",success);
        return "/cadreadmin/inscription";
    }





}