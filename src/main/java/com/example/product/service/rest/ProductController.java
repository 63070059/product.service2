package com.example.product.service.rest;

import com.example.product.service.command.CreateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;
//test
@RestController
@RequestMapping("/products")
public class ProductController {
    private final CommandGateway commandGateway;
    @Autowired
    public ProductController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }
    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel model){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(model.getTitle())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @DeleteMapping
    public String deleteProduct(){
        return "product deleted";
    }
}
