package com.store.management.tool.util;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaParameterizedType;
import com.tngtech.archunit.core.domain.JavaType;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.Collection;
import java.util.List;

public class ParameterizedReturnTypeCondition extends ArchCondition<JavaMethod> {

    private final DescribedPredicate<JavaClass> returnPredicate;

    public ParameterizedReturnTypeCondition(DescribedPredicate<JavaClass> returnPredicate) {
        super("Return types is " + returnPredicate.getDescription());
        this.returnPredicate = returnPredicate;
    }

    @Override
    public void init(Collection<JavaMethod> allObjectsToTest) {
        super.init(allObjectsToTest);
    }

    @Override
    public void check(JavaMethod method, ConditionEvents events) {
        JavaType returnType = method.getReturnType();

        if (returnType == null) {
            return;
        }

        if (!(returnType instanceof JavaParameterizedType parameterizedType)) {
            return;
        }

        List<JavaType> actualTypeArguments = parameterizedType.getActualTypeArguments();
        boolean match = actualTypeArguments.stream().anyMatch(c -> returnPredicate.test(c.toErasure()));
        events.add(new SimpleConditionEvent(method, match, method.getDescription() + " has return type " + returnType.getName() + " that should " + returnPredicate.getDescription()));
    }
}
