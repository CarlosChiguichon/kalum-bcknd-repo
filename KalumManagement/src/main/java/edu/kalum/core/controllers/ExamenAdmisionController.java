package edu.kalum.core.controllers;

import edu.kalum.core.model.dao.services.IExamenAdmisionService;
import edu.kalum.core.model.entities.ExamenAdmision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "kalum-management/v1")
public class ExamenAdmisionController {
    private Logger logger = LoggerFactory.getLogger(ExamenAdmisionController.class);
    @Autowired
    private IExamenAdmisionService examenAdmisionService;
    @GetMapping("examen-admision")
    public ResponseEntity<?> listarExamenesAdmision(){
        Map<String,Object> response = new HashMap();
        logger.debug("Iniciando proceso de consulta de examenes de admision");
        try {
            List<ExamenAdmision> examenesAdmision = examenAdmisionService.findAll();
            if (examenesAdmision == null && examenesAdmision.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Se consultaron los examenes de admision");
                return new ResponseEntity<List<ExamenAdmision>>(examenesAdmision, HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de ejecutar la consulta a la base de datos");
            response.put("Mensaje","Error al momento de ejecutar la consulta a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/examen-admision/{examenId}")
    public ResponseEntity<?> showExamenAdmision(@PathVariable String examenId){
        Map<String,Object> response = new HashMap();
        logger.debug("Iniciando busqueda de examen con ID ".concat(examenId));
        try {
            ExamenAdmision examenAdmision = examenAdmisionService.findById(examenId);
            if (examenAdmision == null) {
                logger.warn("No existe el examen con ID ".concat(examenId));
                response.put("Mensaje","No existe el examen con ID #".concat(examenId));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            } else {
                logger.info("Se proceso la busqueda de examen de admision de forma exitosa");
                return new ResponseEntity<ExamenAdmision>(examenAdmision,HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de ejecutar la consulta a la base de datos");
            response.put("Mensaje","Error al momento de ejecutar la consulta a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}


