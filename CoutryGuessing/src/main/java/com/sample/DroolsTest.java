package com.sample;

import javax.swing.JOptionPane;
import java.awt.List;
import java.lang.reflect.Array;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import java.net.URL;

import pl.put.poznan.KoniecException;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            JOptionPane.showMessageDialog(null, "Pomyśl o pewnym państwie w Europie, program\nprzy pomocy dialogu postara się je odgadnać.", "CountryGuessing – A. Juskowiak, M. Rybarski @ PUT 2013", JOptionPane.INFORMATION_MESSAGE);

            // go !
            ksession.fireAllRules();
            logger.close();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, "Koniec");
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newUrlResource(DroolsTest.class.getClassLoader().getResource("pytania.drl")), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newUrlResource(DroolsTest.class.getClassLoader().getResource("wiedza.drl")), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

}

/*
testowy komentarz
*/
