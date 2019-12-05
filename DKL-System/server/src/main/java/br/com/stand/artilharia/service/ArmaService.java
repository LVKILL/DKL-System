package br.com.stand.artilharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.stand.artilharia.model.Arma;
import br.com.stand.artilharia.repository.ArmaRepository;

@Service
public class ArmaService extends DefaultService<ArmaRepository, Arma> {

    @Override
    public Page<Arma> getAll(String busca, PageRequest pageRequest) {
        return repo.getAll(busca, pageRequest);
    }

}