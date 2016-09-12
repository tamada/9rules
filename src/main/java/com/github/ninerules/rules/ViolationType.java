package com.github.ninerules.rules;

import java.io.Serializable;
import java.util.Objects;

import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;

public class ViolationType implements Serializable{
    private static final long serialVersionUID = -846665473550557939L;

    private Message message;
    private Parameter parameter = NullParameter.parameter();

    public ViolationType(Message message){
        this.message = message;
    }

    public ViolationType(Message message, Parameter parameter){
        this.message = message;
        this.parameter = parameter;
    }

    @Override
    public String toString(){
        if(parameter == null) return String.valueOf(message);
        return parameter.format(message);
    }

    public boolean equals(Object object){
        return object instanceof ViolationType
            && equals((ViolationType)object);
    }

    private boolean equals(ViolationType type){
        return equals(message, type.message)
                && equals(parameter, type.parameter);
    }

    private boolean equals(Message message1, Message message2){
        return Objects.equals(message1, message2); 
    }

    private boolean equals(Parameter parameter1, Parameter parameter2){
        return Objects.equals(parameter1, parameter2); 
    }
}
