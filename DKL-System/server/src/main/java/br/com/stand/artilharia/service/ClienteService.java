package br.com.stand.artilharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.stand.artilharia.model.Cliente;
import br.com.stand.artilharia.repository.ClienteRepository;

@Service
public class ClienteService extends DefaultService<ClienteRepository, Cliente> {

    @Override
    public Page<Cliente> getAll(String busca, PageRequest pageRequest) {
        return repo.getAll(busca, pageRequest);
    }

}