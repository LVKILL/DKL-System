package br.com.stand.artilharia.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public abstract class DefaultController<T> {

    protected abstract ResponseEntity<Page<T>> getAll(int pageIndex, int pageSize, String busca);

    protected abstract ResponseEntity<T> get(Long id);

    protected abstract ResponseEntity<T> post(T obj);

    protected abstract ResponseEntity<T> put(Long id, T obj);

    protected abstract ResponseEntity<Boolean> delete(Long id);
}
