package edu.kalum.enrollment.models.dao;

import edu.kalum.enrollment.models.domain.Aspirante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AspiranteDaoImpl implements IAspiranteDao{

    @PersistenceContext(unitName = "KALUM-DEV-PU")
    private EntityManager entityManager;

    @Override
    public Aspirante findbyId(String noExpediente) {
        return entityManager.find(Aspirante.class,noExpediente);
    }
}