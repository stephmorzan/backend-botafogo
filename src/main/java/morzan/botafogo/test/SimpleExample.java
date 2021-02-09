package morzan.botafogo.test;

import com.basistech.rosette.api.RosetteAPIException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.util.Scanner;
import morzan.botafogo.models.ProcesingText;

/** A simple corenlp example ripped directly from the Stanford CoreNLP website using text from wikinews. */
public class SimpleExample {

  public static void main(String[] args) throws IOException, RosetteAPIException {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Hola, ¿cómo te va hasta ahora?");
    
    String text = sc.nextLine();
    
    //System.out.println(System.getProperties());
    
    ProcesingText nlp = new ProcesingText();
    nlp.master(text);   
    
  }

}
