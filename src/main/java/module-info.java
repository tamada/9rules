import com.github.ninerules.rules.Validator;

module ninerules {
    requires java.logging;
    requires org.eclipse.jdt.core;
    requires org.eclipse.equinox.common;
    requires org.checkerframework.checker;

    exports com.github.ninerules;
    exports com.github.ninerules.annotation;
    exports com.github.ninerules.entities;
    exports com.github.ninerules.parameters;

    uses Validator;
    provides Validator with com.github.ninerules.rules.accessor.NoAccessorValidator,
com.github.ninerules.rules.elsestatement.NoElseStatementValidator,
        com.github.ninerules.rules.fieldcount.FieldCountValidator,
        com.github.ninerules.rules.firstclasscollection.FirstClassCollectionValidator,
        com.github.ninerules.rules.indentlevel.IndentLevelValidator,
        com.github.ninerules.rules.onedot.OneDotPerLineValidator,
        com.github.ninerules.rules.primitive.NoPrimitivesValidator,
        com.github.ninerules.rules.smallobject.MethodLengthValidator,
        com.github.ninerules.rules.smallobject.SourceLengthValidator;
}