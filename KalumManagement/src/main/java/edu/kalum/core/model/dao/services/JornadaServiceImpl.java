package edu.kalum.core.model.dao.services;

import edu.kalum.core.model.dao.IJornadaDao;
import edu.kalum.core.model.entities.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaServiceImpl  implements IJornadaService{
    @Autowired
    private IJornadaDao jornadaDao;

    @Override
    public List<Jornada> findAll() {
        return jornadaDao.findAll();
    }

    @Override
    public Jornada findById(String jornadaId) {
        return jornadaDao.findById(jornadaId).orElse(null);
    }

    @Override
    public Jornada save(Jornada jornada) {
        return jornadaDao.save(jornada);
    }
}
