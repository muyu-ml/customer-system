package com.lcl.galaxy.microservice.middleground.customer.controller.hateoas.assembler;

import com.lcl.galaxy.microservice.middleground.customer.controller.hateoas.HypermediaCustomerStaffController;
import com.lcl.galaxy.microservice.middleground.customer.entity.staff.CustomerStaff;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HypermediaCustomerStaffAssembler implements SimpleRepresentationModelAssembler<CustomerStaff> {


    @Override
    public void addLinks(EntityModel<CustomerStaff> resource) {
        Long id = resource.getContent().getId();

        resource.add(linkTo(methodOn(HypermediaCustomerStaffController.class).single(id)).withSelfRel());

        resource.add(linkTo(methodOn(HypermediaCustomerStaffController.class).all()).withRel("customerStaffs"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<CustomerStaff>> resources) {
        resources.add(linkTo(methodOn(HypermediaCustomerStaffController.class).all()).withSelfRel());
    }
}
