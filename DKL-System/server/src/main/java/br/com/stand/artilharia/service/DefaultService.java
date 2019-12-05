package br.com.stand.artilharia.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stand.artilharia.exception.NotFoundException;
import br.com.stand.artilharia.model.Modelo;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class DefaultService<R extends JpaRepository<C, Long>, C extends Modelo> {
    @Autowired
    protected R repo;

    public C findOne(Long id) {
        Optional<C> obj = repo.findById(id);
        ResolvableType resolvableType = ResolvableType.forClass(repo.getClass()).as(JpaRepository.class);
        return obj.orElseThrow(() -> new NotFoundException("Não encontrado",
                String.format("Não foi encontrado, id:%s ", id), String.format("error.%s.notfound",
                        resolvableType.getGeneric(0).getRawClass().getSimpleName().toLowerCase())));
    }

    public abstract Page<C> getAll(String busca, PageRequest pageRequest);

    public C save(C obj) {
        try {
            return repo.save(obj);
        } catch (Exception e) {
            log.info(e);
            return null;
        }
    }

    public C update(Long id, C obj) {
        obj.setId(id);
        return save(obj);
    }

    public Boolean delete(Long id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.info(e);
            return false;
        }
    }
}