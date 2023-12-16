package com.store.management.tool;

import com.store.management.tool.util.ParameterizedReturnTypeCondition;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;


public class ArchitectureTest {

    private final JavaClasses allProjectClasses = new ClassFileImporter().importPackages("com.store.management.tool");

    @Test
    public void testDomainModelNotLeakedViaControllerMethods() {
        methods().that().areMetaAnnotatedWith(RequestMapping.class)
                .and().arePublic()
                .should().haveRawReturnType(not(resideInAPackage("..model..")))
                .andShould(new ParameterizedReturnTypeCondition(not(resideInAPackage("..model.."))))
                .check(allProjectClasses);
    }
}
