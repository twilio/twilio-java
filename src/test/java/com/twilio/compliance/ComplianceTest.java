package com.twilio.compliance;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.GivenClasses;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;

import com.twilio.base.Resource;
import nl.jqno.equalsverifier.EqualsVerifier;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.conditions.ArchConditions.dependOnClassesThat;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComplianceTest {
    static final private ImportOptions importOpts = new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS);
    static final private JavaClasses twilioClasses = new ClassFileImporter(importOpts).importPackages("com.twilio");
    static final private List<Class> resourceClasses = getResourceClasses(twilioClasses);
    static final private List<Class> variantClasses = new ArrayList<Class>(); // classes that do not follow the generic template

    private static DescribedPredicate<JavaClass> areNotInVariantList() {
        return new DescribedPredicate<JavaClass>("classes that follow the same template pattern") {
            @Override
            public boolean apply(final JavaClass input) {
                return !variantClasses.contains(input.getName());
            }
        };
    }

    @Before
    public void setUp() {
        assertTrue(twilioClasses.size() > 0);
        assertTrue(resourceClasses.size() > 1);
        variantClasses.add(com.twilio.rest.voice.v1.ArchivedCall.class);
    }

    @Test
    public void testEqualsMethods() {
        List <Class> eligibleResourceClasses = resourceClasses.stream().filter( c -> !variantClasses.contains(c)).collect(Collectors.toList());
        for (Class clazz : eligibleResourceClasses) {
            EqualsVerifier.forClass(clazz)
                    .usingGetClass()
                    .verify();
        }
    }
    @Test
    public void noClassesShouldUseJavaUtilLogging() {
        NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.check(twilioClasses);
    }

    @Test
    public void noClassesShouldUseLog4j() {
        // disallow Log4j version 1.x
        disallowPackage("org.apache.log4j").check(twilioClasses);
        // disallow Log4j version 2.x
        disallowPackage("org.apache.logging.log4j").check(twilioClasses);
    }

    @Test
    public void noClassesShouldUseJodaTime() {
        NO_CLASSES_SHOULD_USE_JODATIME.check(twilioClasses);
    }

    @Test
    public void resourceClassSanityCheck() {

        GivenClasses filteredClasses = (GivenClasses) classes().that(areNotInVariantList());
        GivenClassesConjunction resourceClasses = filteredClasses.that().areAssignableTo(Resource.class).and()
                .doNotHaveFullyQualifiedName(Resource.class.getName()).and().
                doNotBelongToAnyOf(variantClasses.get(0));

        resourceClasses.should()
                .beAnnotatedWith(com.fasterxml.jackson.annotation.JsonIgnoreProperties.class)
                .andShould()
                .haveOnlyFinalFields()
                .andShould()
                .haveOnlyPrivateConstructors()
                .check(twilioClasses);
    }

    private static List<Class> getResourceClasses(final JavaClasses jclasses) {
        List<Class> builder = new ArrayList<>();
        for (JavaClass jclass : jclasses) {
            if (jclass.isAssignableTo(com.twilio.base.Resource.class)
                    && (!jclass.getModifiers().contains(JavaModifier.ABSTRACT))) {
                builder.add(jclass.reflect());
            }
        }
        return Collections.unmodifiableList(builder);
    }

    private static ArchRule disallowPackage(final String packageIdentifier) {
        return noClasses()
                .should(dependOnClassesThat(resideInAPackage(packageIdentifier)));
    }
}