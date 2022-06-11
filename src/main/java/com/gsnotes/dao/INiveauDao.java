package com.gsnotes.dao;

import com.gsnotes.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INiveauDao extends JpaRepository<Niveau, Long> {

}
