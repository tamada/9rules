package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.accessor.NoAccessorValidator;
import com.github.ninerules.rules.elsestatement.NoElseStatementValidator;
import com.github.ninerules.rules.fieldcount.FieldCountValidator;
import com.github.ninerules.rules.firstclasscollection.FirstClassCollectionValidator;
import com.github.ninerules.rules.indentlevel.IndentLevelValidator;
import com.github.ninerules.rules.onedot.OneDotPerLineValidator;
import com.github.ninerules.rules.primitive.NoPrimitivesValidator;
import com.github.ninerules.rules.smallobject.MethodLengthValidator;
import com.github.ninerules.rules.smallobject.SourceLengthValidator;

@SuppressWarnings("unchecked")
public class ServiceLoaderTest {
    @Test
    public void testBasic(){
        ServiceLoader<Validator> loader = new ServiceLoaderBuilder<Validator>().load(Validator.class);

        List<Class<Validator>> validators = loader.stream()
                .collect(Collectors.toList());

        assertThat(validators.size(), is(9));
        assertThat(validators.get(0).getName(), is(NoAccessorValidator.class.getName()));
        assertThat(validators.get(1).getName(), is(NoElseStatementValidator.class.getName()));
        assertThat(validators.get(2).getName(), is(FieldCountValidator.class.getName()));
        assertThat(validators.get(3).getName(), is(FirstClassCollectionValidator.class.getName()));
        assertThat(validators.get(4).getName(), is(IndentLevelValidator.class.getName()));
        assertThat(validators.get(5).getName(), is(OneDotPerLineValidator.class.getName()));
        assertThat(validators.get(6).getName(), is(NoPrimitivesValidator.class.getName()));
        assertThat(validators.get(7).getName(), is(MethodLengthValidator.class.getName()));
        assertThat(validators.get(8).getName(), is(SourceLengthValidator.class.getName()));
    }

    @Test
    public void testNotExistsType(){
        ServiceLoader<LineCount> loader = new ServiceLoaderBuilder<LineCount>().load(LineCount.class);

        Class<LineCount>[] counts = (Class<LineCount>[]) loader.stream().toArray(Class<?>[]::new);
        assertThat(counts.length, is(0));
    }

    @Ignore
    @Test
    public void testDummyClassName(){
        ServiceLoader<Streams> loader = new ServiceLoaderBuilder<Streams>().load(Streams.class);

        Class<Streams>[] streams = (Class<Streams>[]) loader.stream().toArray(Class<?>[]::new);
        assertThat(streams.length, is(1));
        assertThat(streams[0], is(nullValue()));
    }
}
