package edu.kalum.enrollment.models.dao;

import edu.kalum.enrollment.models.domain.CarreraTecnica;

import java.util.List;

public interface ICarreraTecnicaDao {
    public List<CarreraTecnica> findAll();
    public CarreraTecnica findById(String carreraTecnicaId);
    public void save(CarreraTecnica carreraTecnica);
    public void update(CarreraTecnica carreraTecnica);
    public void delete(CarreraTecnica carreraTecnica);
}
