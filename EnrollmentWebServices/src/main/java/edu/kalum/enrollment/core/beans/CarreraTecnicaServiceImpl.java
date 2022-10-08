package edu.kalum.enrollment.core.beans;

import edu.kalum.enrollment.models.dao.ICarreraTecnicaDao;
import edu.kalum.enrollment.models.domain.CarreraTecnica;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(endpointInterface = "edu.kalum.enrollment.core.beans.ICarreraTecnicaService")
public class CarreraTecnicaServiceImpl implements ICarreraTecnicaService{

    //Logger log = LogManager.getRootLogger();
    @Inject
    private ICarreraTecnicaDao carreraTecnicaDao;
    @Override
    public List<CarreraTecnica> listarCarrerasTecnicas() {
        //log.info("Iniciando proceso de consulta de carreras tecnicas");
        return carreraTecnicaDao.findAll();
    }

    @Override
    public CarreraTecnica listarCarreraTecnica(String carreraTecnicaId) {
        //log.info("Iniciando el proceso de busqueda de la carrera tecnica con ID " + carreraTecnicaId);
        return carreraTecnicaDao.findById(carreraTecnicaId);
    }

    @Override
    public void insertarCarreraTecnica(CarreraTecnica carreraTecnica) {
        //log.info("Iniciando el proceso de almacenamiento de la carrera tecnica");
        carreraTecnicaDao.save(carreraTecnica);
    }

    @Override
    public void actualizarCarreraTecnica(CarreraTecnica carreraTecnica) {
        //log.info("Actualizando la informacion de la carrera tecnica con ID " + carreraTecnica.getCarreraId());
        carreraTecnicaDao.update(carreraTecnica);
    }

    @Override
    public void eliminarCarreraTecnica(CarreraTecnica carreraTecnica) {
        //log.info("Iniciando el proceso de eliminacion de la carrera tecnica con el ID " + carreraTecnica.getCarreraId());
        carreraTecnicaDao.delete(carreraTecnica);
    }

}
