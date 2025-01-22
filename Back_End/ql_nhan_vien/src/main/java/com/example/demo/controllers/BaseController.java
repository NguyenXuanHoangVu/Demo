package com.example.demo.controllers;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repositories.BaseRepository;
import com.example.demo.service.QlNhanVienServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T, ID extends Serializable> {
    @Autowired
    private BaseRepository<T, ID> repository;

    // Endpoint to get all entities
    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Endpoint to get an entity by ID
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        T entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return ResponseEntity.ok(entity);
    }

    // Endpoint to add a new entity
    @PostMapping
    public ResponseEntity<T> add(@Valid @RequestBody T entity) {
        T savedEntity = repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    // Endpoint to update an entity by ID
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @Valid @RequestBody T entityDetails) {
        T updatedEntity = repository.save(entityDetails);
        return ResponseEntity.ok(updatedEntity);
    }

    // Endpoint to delete an entity by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

