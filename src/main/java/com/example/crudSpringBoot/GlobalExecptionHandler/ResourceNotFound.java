package com.example.crudSpringBoot.GlobalExecptionHandler;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message){
        super(message);
    }

}
