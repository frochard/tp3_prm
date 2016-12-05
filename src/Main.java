/**
 * Created by fred on 05/12/16.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    final static String repATrier = "Stages_old/";

    public static void main(String[] args) throws FileNotFoundException {

        //Results arrays
        //List<String> doublons = new ArrayList<>();

        HashSet doublons = new HashSet();

        //Vocabulary definition
        List<String> vocabulaire = new ArrayList<>();
        vocabulaire.add("Linux");
        vocabulaire.add("linux");
        vocabulaire.add("Vuforia");
        vocabulaire.add("Android");
        vocabulaire.add("IOS");
        vocabulaire.add("Business");
        vocabulaire.add("Objects");
        vocabulaire.add("Hyperion");
        vocabulaire.add("Planning");
        vocabulaire.add("Talend");
        vocabulaire.add("Cognos");
        vocabulaire.add("QlikView");
        vocabulaire.add("SAP");
        vocabulaire.add("BPC");
        vocabulaire.add("Python");
        vocabulaire.add("Matlab");
        vocabulaire.add("Entropy");
        vocabulaire.add("beamforming");
        vocabulaire.add("BeezUp");
        vocabulaire.add("Doxygen");
        vocabulaire.add("informatique");
        vocabulaire.add("Informatique");
        vocabulaire.add("Junit");
        vocabulaire.add("TOGAF");
        vocabulaire.add("Microsoft");
        vocabulaire.add("Windows");
        vocabulaire.add("(Neurosciences");
        vocabulaire.add("Java");
        vocabulaire.add("Anglais");
        vocabulaire.add("JBoss");
        vocabulaire.add("Tomcat");
        vocabulaire.add("Websphere");
        vocabulaire.add("SQL");
        vocabulaire.add("Oracle");
        vocabulaire.add("ORACLE");
        vocabulaire.add("MYSQL");
        vocabulaire.add("Hibernate");
        vocabulaire.add("Spring");
        vocabulaire.add("J2EE");
        vocabulaire.add("Struts");
        vocabulaire.add("SGBDR");
        vocabulaire.add("ERP");
        vocabulaire.add("Intelligence");
        vocabulaire.add("E-Business");
        vocabulaire.add("PHP");
        vocabulaire.add("XML");
        vocabulaire.add("PRM");
        vocabulaire.add("NTIC");
        vocabulaire.add("SI");
        vocabulaire.add("TMA");
        vocabulaire.add("IT");
        vocabulaire.add("JAVA");
        vocabulaire.add("RSI");
        vocabulaire.add("Ingenieur");
        vocabulaire.add("Symfony");
        vocabulaire.add("Drupal");
        vocabulaire.add("Big");
        vocabulaire.add("Data");
        vocabulaire.add("Sharepoint");
        vocabulaire.add("CRM");
        vocabulaire.add("Eclipse");
        vocabulaire.add("Benchmark");
        vocabulaire.add("Fonctionnel");
        vocabulaire.add("fonctionnel");

        //Gets all pdf files in an array
        File folder = new File(repATrier);

        List<String> files = new ArrayList();
        List<Vector> filesVector = new ArrayList();

        for (File pdfFile : folder.listFiles()){
            //Get PDF content int text
            String text = "";
            try {
                text = getPdfContent(pdfFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            files.add(pdfFile.getName());
            filesVector.add(getPdfVector(text, vocabulaire));
        }

        //Files to check
        int nbFile = files.size();

        int currentFile=0;

        //Duplication search
        for(int i=0;i<nbFile;i++){
            Vector v = filesVector.get(i);
            for(int j=i+1;j<nbFile;j++){
                if(v.equals(filesVector.get(j))){
                    doublons.add(files.get(i));
                    doublons.add(files.get(j));
                }
            }
        }

        //Results display
        Iterator it = doublons.iterator();
        while(it.hasNext())
            System.out.println(it.next());

        System.out.println(doublons.size() + " duplicated files.");

    }

    public static String getPdfContent(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return content;
    }

    public static Vector getPdfVector(String content, List<String> vocabulaire){
        Vector vecteur = new Vector();
        for(String voc : vocabulaire){
            int nb = 0;
            for(String word : content.split("[^éèôûîïüàêa-zA-Z0-9]")){
                if (word.equals(voc)){
                    nb++;
                }
            }
            vecteur.add(nb);
        }
        return vecteur;
    }

}
