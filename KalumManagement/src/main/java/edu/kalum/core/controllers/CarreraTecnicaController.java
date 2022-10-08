package edu.kalum.core.controllers;

import edu.kalum.core.model.dao.services.ICarreraTecnicaService;
import edu.kalum.core.model.entities.CarreraTecnica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/kalum-management/v1")
public class CarreraTecnicaController {
    @Value("${edu.kalum.core.configuration.page.site}")
    private Integer pagesize;

    private Logger logger = LoggerFactory.getLogger(CarreraTecnicaController.class);
    @Autowired
    private ICarreraTecnicaService carreraTecnicaService;

    @GetMapping("/carreras-tecnicas/page/{page}")
    public ResponseEntity<?> index(@PathVariable Integer page){
        Map<String,Object> response = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page,pagesize);
            Page<CarreraTecnica> carreraTecnicaPage = carreraTecnicaService.findAll(pageable);
            if (carreraTecnicaPage == null && carreraTecnicaPage.getSize() == 0){
                logger.warn("No existen registros de carreras tecnicas");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Se proceso consulta de carreras tecnicas por paginas");
                return new ResponseEntity<Page<CarreraTecnica>>(carreraTecnicaPage,HttpStatus.OK);
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

    @GetMapping("/carreras-tecnicas")
    public ResponseEntity<?> listarCarrerasTecnicas(){
        Map<String, Object> response = new HashMap<>();
        logger.debug("Iniciando proceso de consulta de carreras tecnicas");
        try {
            List<CarreraTecnica> carrerasTecnicas = carreraTecnicaService.findAll();
            if (carrerasTecnicas == null && carrerasTecnicas.isEmpty()) {
                logger.warn("No existen registros de carreras tecnicas");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Se consulto la informacion de las carreras tecnicas");
                return new ResponseEntity<List<CarreraTecnica>>(carrerasTecnicas, HttpStatus.OK);
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

    @GetMapping("/carreras-tecnicas/{carreraId}")
    public ResponseEntity<?> showCarreraTecnica(@PathVariable String carreraId){
        Map<String,Object> response = new HashMap();
        logger.debug("Iniciando proceso de busqueda de la carrera tecnica con ID ".concat(carreraId));
        try {
            CarreraTecnica carreraTecnica = carreraTecnicaService.findById(carreraId);
            if (carreraTecnica == null) {
                logger.warn("No existe la carrera tecnica con el ID ".concat(carreraId));
                response.put("Mensaje", "No existe la carrera tecnica con el ID ".concat(carreraId));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            } else {
                logger.info("Se proceso la busqueda de la carrera tecnica de forma exitosa");
                return new ResponseEntity<CarreraTecnica>(carreraTecnica, HttpStatus.OK);
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
    @PostMapping("/carreras-tecnicas")
    public ResponseEntity<?> create(@Valid @RequestBody CarreraTecnica value, BindingResult result){
        Map<String,Object> response = new HashMap<>();
        if (result.hasErrors() == true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores",errores);
            logger.info("Se encontraron errores de validaciones en la peticion");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            value.setCarreraId(UUID.randomUUID().toString());
            CarreraTecnica carreraTecnica = carreraTecnicaService.save(value);
            response.put("Mensaje","La carrera tecnica ha sido creada exitosamente");
            response.put("CarreraTecnica", carreraTecnica);
            logger.info("Se creo una carrera tecnica de forma exitosa");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de crear el nuevo registro");
            response.put("Mensaje","Error al momento de crear el nuevo registro");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/carreras-tecnicas/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CarreraTecnica value, BindingResult result, @PathVariable String id){
        Map<String,Object> response = new HashMap();
        if (result.hasErrors() == true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores",errores);
            logger.info("Se encontraron errores de validacion en la peticion");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            CarreraTecnica carreraTecnica = carreraTecnicaService.findById(id);
            if (carreraTecnica == null) {
                response.put("Mensaje","La carrera tecnica con el ID".concat(id).concat(" no existe"));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            } else {
                carreraTecnica.setCarreraTecnica(value.getCarreraTecnica());
                carreraTecnicaService.save(carreraTecnica);
                response.put("Mensaje","La carrera tecnica fue actualizada con exito");
                response.put("CarreraTecnica",carreraTecnica);
                logger.info("Se actualizo la informacion de forma correcta");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de actualizar registro");
            response.put("Mensaje","Error al momento de actualizar registro");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @DeleteMapping("/carreras-tecnicas/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String,Object> response = new HashMap<>();
        try {
            CarreraTecnica carreraTecnica = carreraTecnicaService.findById(id);
            if (carreraTecnica == null) {
                response.put("Mensaje","La carrera tecnica con el ID".concat(id).concat(" no existe"));
                logger.warn("No existen registros de carreras tecnicas");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            } else {
                carreraTecnicaService.delete(carreraTecnica);
                response.put("Mensaje","La carrera tecnica fue eliminada exitosamente");
                response.put("CarreraTecnica", carreraTecnica);
                logger.info("Se elimino la carrera de forma exitosa");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de eliminar registro");
            response.put("Mensaje","Error al momento de eliminar registro");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
