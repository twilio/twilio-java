package com.twilio.compliance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import static com.tngtech.archunit.library.GeneralCodingRules.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ComplianceTest {
    static final private ImportOptions importOpts = new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS);
    static final private JavaClasses twilioClasses = new ClassFileImporter(importOpts).importPackages("com.twilio");

    @Before
    public void setUp() {
        assertTrue(twilioClasses.size() > 0);
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
            .check(twilioClasses);
    }
}
