package com.grpc.grpcpraxis.grpcserver.validators;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.grpc.grpcpraxis.grpcserver.HelloWorld;
import com.grpc.grpcpraxis.grpcserver.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public void validateFields(HelloWorld.HelloRequest helloRequest) throws ValidationException {
        // Get the descriptor for HelloRequest
        Descriptors.Descriptor descriptor = helloRequest.getDescriptorForType();

        // Extract the field options
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName("name");

        // Get the field options
        DescriptorProtos.FieldOptions fieldOptions = fieldDescriptor.getOptions();

        // Get the validation rule from the field options
        String validationRule = fieldOptions.getExtension(HelloWorld.validationRule);

        // Perform custom validation based on the validation rule
        if (validationRule.equals("name_length")) {
            String name = (String) helloRequest.getField(fieldDescriptor);
            // Perform your custom validation logic for the 'name' field
            if (name.isEmpty() || name.length() > 50) {
                throw new ValidationException("Name field is required");
            }
        }
    }
}

