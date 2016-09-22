package morzan.botafogo.test;

import com.basistech.rosette.api.RosetteAPIException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.io.Files;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import java.util.Scanner;
import morzan.botafogo.models.ProcesingText;

/** A simple corenlp example ripped directly from the Stanford CoreNLP website using text from wikinews. */
public class SimpleExample {

  public static void main(String[] args) throws IOException, RosetteAPIException {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Hola, ¿qué tal?");
    
    String text = sc.nextLine();
    
    ProcesingText nlp = new ProcesingText();
    nlp.procesarTexto(text);
    nlp.probandoRosette(text);
  }

}
