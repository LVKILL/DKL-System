package br.com.stand.artilharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.stand.artilharia.model.Ambiente;
import br.com.stand.artilharia.repository.AmbienteRepository;

@Service
public class AmbienteService extends DefaultService<AmbienteRepository, Ambiente> {

    @Override
    public Page<Ambiente> getAll(String busca, PageRequest pageRequest) {
        return repo.getAll(busca, pageRequest);
    }

}