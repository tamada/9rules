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
        if(parameter == null)
            return String.valueOf(message);
        return parameter.format(message);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object object){
        return object instanceof ViolationType
            && checkEquals(((ViolationType)object).message, 
                    ((ViolationType)object).parameter);
    }

    private boolean checkEquals(Message otherMessage, Parameter otherParameter){
        return Objects.equals(message,  otherMessage)
                && Objects.equals(parameter, otherParameter);
    }
}
