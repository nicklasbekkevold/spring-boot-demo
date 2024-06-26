package com.example.springboot.controller;


import com.example.springboot.model.Unit;
import com.example.springboot.service.UnitService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService service;
    private final UnitModelAssembler assembler;

    public UnitController(UnitService service, UnitModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping
    public CollectionModel<EntityModel<Unit>> getAll() {
        List<EntityModel<Unit>> units = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(units, linkTo(methodOn(UnitController.class).getAll()).withSelfRel());
    }
    // end::get-aggregate-root[]

    // Single item
    // tag::get-single-item[]
    @GetMapping("/{id}")
    public ResponseEntity<?> getUnit(@PathVariable int id) {
        Optional<Unit> unit = service.findById(id);

        if (unit.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Not found")
                            .withDetail("Could not find unit with id %d".formatted(id)));

        }
        return ResponseEntity.ok(assembler.toModel(unit.get()));
    }
    // end::get-single-item[]

}
