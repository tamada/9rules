package com.github.ninerules.rules.violations;

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

    @Override
    public int hashCode(){
        return Objects.hashCode(this);
    }

    public boolean equals(Object object){
        return object instanceof ViolationType
            && equals((ViolationType)object);
    }

    private boolean equals(ViolationType type){
        return equals(type.message,
                      type.parameter);
    }

    private boolean equals(Message message1, Parameter parameter1){
        return Objects.equals(message, message1) &&
            Objects.equals(parameter, parameter1); 
    }
}
