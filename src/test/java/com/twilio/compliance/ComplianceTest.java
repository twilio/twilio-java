package com.twilio.compliance;

import com.google.common.collect.ImmutableList;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;

import nl.jqno.equalsverifier.EqualsVerifier;

import static com.tngtech.archunit.library.GeneralCodingRules.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComplianceTest {
    static final private ImportOptions importOpts = new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS);
    static final private JavaClasses twilioClasses = new ClassFileImporter(importOpts).importPackages("com.twilio");
    static final private List<Class> resourceClasses = getResourceClasses(twilioClasses);

    @Before
    public void setUp() {
        assertTrue(twilioClasses.size() > 0);
        assertTrue(resourceClasses.size() > 1);
    }

    @Test
    public void testEqualsMethods() {
        for (Class clazz : resourceClasses) {
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
    public void resourceClassSanityCheck() {
        GivenClassesConjunction resourceClasses = classes().that().areAssignableTo(com.twilio.base.Resource.class).and().doNotHaveFullyQualifiedName(com.twilio.base.Resource.class.getName());

        resourceClasses.should()
            .beAnnotatedWith(com.fasterxml.jackson.annotation.JsonIgnoreProperties.class)
            .andShould()
            .haveOnlyFinalFields()
            .andShould()
            .haveOnlyPrivateConstructors()
            .check(twilioClasses);
    }

    private static List<Class> getResourceClasses(final JavaClasses jclasses) {
        ImmutableList.Builder<Class> builder = ImmutableList.builder();
        for (JavaClass jclass : jclasses) {
          if (jclass.isAssignableTo(com.twilio.base.Resource.class)
              && (!jclass.getModifiers().contains(JavaModifier.ABSTRACT))) {
              builder.add(jclass.reflect());
            }
        }
        return builder.build();
    }
}
