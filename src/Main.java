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

    final static String repATrier = "Stages_old/";//"test/";

    public static void main(String[] args) throws FileNotFoundException {

        //Results arrays
        List<String> doublons = new ArrayList<>();

        //Vocabulary definition
        List<String> vocabulaire = new ArrayList<>();
        vocabulaire.add("Linux");
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

        //Gets all pdf files in an array
        File folder = new File(repATrier);

        Map files = new HashMap();

        for (File pdfFile : folder.listFiles()){
            //Get PDF content int text
            String text = "";
            try {
                text = getPdfContent(pdfFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            files.put(pdfFile.getName(),getPdfVector(text, vocabulaire));
        }

        //Files to check
        int nbFile = files.size();

        int currentFile=0;

        //Duplication search
        Iterator it1 = files.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair = (Map.Entry)it1.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it1.remove(); // avoids a ConcurrentModificationException
        }

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
