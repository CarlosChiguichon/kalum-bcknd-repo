package edu.kalum.enrollment.models.dao;

import edu.kalum.enrollment.models.domain.CarreraTecnica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarreraTecnicaDaoImpl implements ICarreraTecnicaDao{
    @PersistenceContext(name = "KALUM-DEV-PU")
    private EntityManager entityManager;

    @Override
    public List<CarreraTecnica> findAll() {
        return entityManager.createNamedQuery("carreraTecnica.findAll").getResultList();
    }

    @Override
    public CarreraTecnica findById(String carreraTecnicaId) {
        return entityManager.find(CarreraTecnica.class,carreraTecnicaId);
    }

    @Override
    public void save(CarreraTecnica carreraTecnica) {
        entityManager.persist(carreraTecnica);
    }

    @Override
    public void update(CarreraTecnica carreraTecnica) {
        entityManager.merge(carreraTecnica);
    }

    @Override
    public void delete(CarreraTecnica carreraTecnica) {
        entityManager.remove(entityManager.merge(carreraTecnica));
    }
}
