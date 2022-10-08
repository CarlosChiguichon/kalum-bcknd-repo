package edu.kalum.enrollment.models.dao;

import edu.kalum.enrollment.models.domain.Aspirante;

public interface IAspiranteDao {
    public Aspirante findbyId(String noExpediente);
}
