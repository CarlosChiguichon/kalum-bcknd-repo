package edu.kalum.enrollment.core.beans;

import edu.kalum.enrollment.models.dao.IAspiranteDao;
import edu.kalum.enrollment.models.dao.ICarreraTecnicaDao;
import edu.kalum.enrollment.models.dao.IEnrollmentProcessDao;
import edu.kalum.enrollment.models.domain.Aspirante;
import edu.kalum.enrollment.models.domain.CarreraTecnica;
import edu.kalum.enrollment.models.entities.EnrollmentRequest;
import edu.kalum.enrollment.models.entities.EnrollmentResponse;
import edu.kalum.enrollment.models.entities.StatusEnrollmentProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

@Stateless
@WebService(endpointInterface = "edu.kalum.enrollment.core.beans.IEnrollmentService")
public class EnrollmentServiceImpl implements IEnrollmentService{
    //private Logger logger = LogManager.getRootLogger();
    @Inject
    private IEnrollmentProcessDao enrollmentProcessDao;
    @Inject
    private ICarreraTecnicaDao carreraTecnicaDao;
    @Inject
    private IAspiranteDao aspiranteDao;
    @Override
    public EnrollmentResponse enrollmentProcess(EnrollmentRequest request) {
        //logger.debug("Iniciando proceso de inscripcion");
        EnrollmentResponse enrollmentResponse = null;
        //logger.debug("Iniciando el proceso de busqueda de la carrera tecnica con el ID #" + request.getCarreraID());
        CarreraTecnica carreraTecnica = carreraTecnicaDao.findById(request.getCarreraID());
        if(carreraTecnica != null){
            //logger.debug("Iniciando proceso de busqueda de aspirante con expediente #" + request.getNoExpediente());
            Aspirante aspirante = aspiranteDao.findbyId(request.getNoExpediente());
            if (aspirante!=null) {
                //logger.info("Iniciando el proceso de inscripcion");
                StatusEnrollmentProcess respuesta = null;
                try {
                     respuesta = enrollmentProcessDao.executeEnrollmentProcess(request);
                } catch (Exception e) {
                    enrollmentResponse = new EnrollmentResponse(503, "Error ".concat(e.getCause().getMessage()));
                    //logger.error("Ocurrio un error al momento de llamar al procedimiento {" + e.getMessage() + "}");
                    //logger.fatal(e.getMessage());
                }
                if (respuesta != null) {

                    if (respuesta.getStatus().equalsIgnoreCase("TRANSACTION SUCCESS")){
                        //logger.info("Se finalizo el proceso de inscripcion de forma exitosa");
                        enrollmentResponse = new EnrollmentResponse(201, respuesta.getStatus());
                    } else {
                        enrollmentResponse = new EnrollmentResponse(503, respuesta.getStatus());
                    }
                }
            } else {
                //logger.warn("No se encontraron registros del aspirante con expediente #" + request.getNoExpediente());
                enrollmentResponse = new EnrollmentResponse(400,"El aspirante con el ID "+ request.getNoExpediente() + " no existe");
            }
        } else {
            //logger.warn("No se encontro registro de la carrera tecnica con ID #" + request.getCarreraID());
            enrollmentResponse = new EnrollmentResponse(400,"La carrera con el ID " + request.getCarreraID() + " no existe");
        }
        return enrollmentResponse;
    }
}