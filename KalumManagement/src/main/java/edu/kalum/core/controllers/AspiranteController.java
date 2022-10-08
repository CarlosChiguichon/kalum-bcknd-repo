package edu.kalum.core.controllers;

import edu.kalum.core.model.dao.services.IAspiranteService;
import edu.kalum.core.model.dao.services.ICarreraTecnicaService;
import edu.kalum.core.model.dao.services.IExamenAdmisionService;
import edu.kalum.core.model.dao.services.IJornadaService;
import edu.kalum.core.model.entities.Aspirante;
import edu.kalum.core.model.entities.CarreraTecnica;
import edu.kalum.core.model.entities.ExamenAdmision;
import edu.kalum.core.model.entities.Jornada;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/kalum-management/v1")
public class AspiranteController {
    @Value("${edu.kalum.core.configuration.page.site}")
    private Integer pagesize;
    private Logger logger = LoggerFactory.getLogger(AspiranteController.class);
    @Autowired
    private IAspiranteService aspiranteService;
    @Autowired
    private ICarreraTecnicaService carreraTecnicaService;
    @Autowired
    private IExamenAdmisionService examenAdmisionService;
    @Autowired
    private IJornadaService jornadaService;

    @GetMapping("/aspirantes/page/{page}")
    public ResponseEntity<?> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pageable pageable = PageRequest.of(page, pagesize);
            Page<Aspirante> aspirantePage = aspiranteService.findAll(pageable);
            if (aspirantePage == null && aspirantePage.getSize() == 0) {
                logger.warn("No existen registros de aspirantes");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Se proceso consulta de aspirantes por paginas");
                return new ResponseEntity<Page<Aspirante>>(aspirantePage, HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje", "Error al momento de conectarse a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de ejecutar la consulta a la base de datos");
            response.put("Mensaje", "Error al momento de ejecutar la consulta a la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/aspirantes")
    public ResponseEntity<?> listarAspirantes(){
        Map<String, Object> response = new HashMap<>();
        logger.debug("Iniciando proceso de consulta de aspirantes");
        try {
            List<Aspirante> aspirantes = aspiranteService.findAll();
            if (aspirantes == null && aspirantes.isEmpty()) {
                logger.warn("No existen registros de aspirantes");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("se consulto la informacion de aspirantes");
                return new ResponseEntity<List<Aspirante>>(aspirantes, HttpStatus.OK);
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

    @GetMapping("/aspirantes/{noExpediente}")
    public ResponseEntity<?> showAspirante(@PathVariable String noExpediente){
        Map<String,Object> response = new HashMap();
        logger.debug("Iniciando proceso de busqueda de aspirante con expediente #".concat(noExpediente));
        try {
            Aspirante aspirante = aspiranteService.findById(noExpediente);
            if (aspirante == null) {
                logger.warn("No existe el aspirante con expediente #".concat(noExpediente));
                response.put("Mensaje","No existe el aspirante con expediente #".concat(noExpediente));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            } else {
                logger.info("Se proceso la busqueda del aspirante de forma exitosa");
                return new ResponseEntity<Aspirante>(aspirante,HttpStatus.OK);
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
    @PostMapping("/aspirantes")
    public ResponseEntity<?> create(@Valid @RequestBody Aspirante value, BindingResult result){
        Map<String,Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                            .stream()
                            .map(error-> error.getDefaultMessage())
                            .collect(Collectors.toList());
            response.put("errores",errores);
            logger.info("Se encontraron errores de validacion en la peticion");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            Aspirante aspirante = aspiranteService.findById(value.getNoExpediente());
            if (aspirante != null){
                response.put("Mensaje","Ya existe un registro con el numero de expediente ".concat(value.getNoExpediente()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            }
            Jornada jornada = jornadaService.findById(value.getJornada().getJornadaId());
            if (jornada == null) {
                response.put("Mensaje","No existe una jornada con #".concat(value.getJornada().getJornadaId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setJornada(jornada);
            }
            ExamenAdmision examenAdmision = examenAdmisionService.findById(value.getExamenAdmision().getExamenId());
            if (examenAdmision == null) {
                response.put("Mensaje","No existe un examen de admision con el codigo ".concat(value.getExamenAdmision().getExamenId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setExamenAdmision(examenAdmision);
            }
            CarreraTecnica carreraTecnica = carreraTecnicaService.findById(value.getCarreraTecnica().getCarreraId());
            if (carreraTecnica == null) {
                response.put("Mensaje","No existe carrera tecnica con ID #".concat(value.getCarreraTecnica().getCarreraId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setCarreraTecnica(carreraTecnica);
            }
            aspiranteService.save(value);
            response.put("Mensaje","Aspirante fue creado con exito");
            response.put("Aspirante",value);
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
    @PutMapping("/aspirantes/{noExpediente}")
    public ResponseEntity<?> update(@Valid @RequestBody Aspirante value, BindingResult result, @PathVariable String noExpediente){
        Map<String,Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(error-> error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores",errores);
            logger.info("Se encontraron errores de validacion en la peticion");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        try {
            Aspirante aspirante = aspiranteService.findById(noExpediente);
            if (aspirante == null){
                response.put("Mensaje","No existe un aspirante con expediente #".concat(value.getNoExpediente()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            }
            Jornada jornada = jornadaService.findById(value.getJornada().getJornadaId());
            if (jornada == null) {
                response.put("Mensaje","No existe una jornada con #".concat(value.getJornada().getJornadaId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setJornada(jornada);
            }
            ExamenAdmision examenAdmision = examenAdmisionService.findById(value.getExamenAdmision().getExamenId());
            if (examenAdmision == null) {
                response.put("Mensaje","No existe un examen de admision con el codigo ".concat(value.getExamenAdmision().getExamenId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setExamenAdmision(examenAdmision);
            }
            CarreraTecnica carreraTecnica = carreraTecnicaService.findById(value.getCarreraTecnica().getCarreraId());
            if (carreraTecnica == null) {
                response.put("Mensaje","No existe carrera tecnica con ID #".concat(value.getCarreraTecnica().getCarreraId()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
            } else {
                value.setCarreraTecnica(carreraTecnica);
            }
            aspiranteService.save(value);
            response.put("Mensaje","Aspirante fue actualizado con exito");
            response.put("Aspirante",value);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("Mensaje","Error al momento de conectarse a la base de datos");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("Error al momento de crear el nuevo registro");
            response.put("Mensaje","Error al momento de crear el nuevo registro2");
            response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @DeleteMapping("/aspirantes/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String,Object> response = new HashMap<>();
        try {
            Aspirante aspirante = aspiranteService.findById(id);
            if (aspirante == null){
                response.put("Mensaje","El aspirante con el expediente #".concat(id).concat(" no existe"));
                logger.warn("No existe expediente de aspirante");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
            } else {
                aspiranteService.delete(aspirante);
                response.put("Mensaje","El aspirante fue eliminado exitosamente");
                response.put("Aspirante",aspirante);
                logger.info("Se elimino el aspirante de forma exitosa");
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
