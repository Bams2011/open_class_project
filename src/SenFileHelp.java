//==================================================================================
// Nom         : help.java
// Auteur      : Mame Astou GASSAMA
// Description : Mini projet java: Le fichier aide
//==================================================================================
import java.util.*;
    
public  class SenFileHelp{
	public static void  OurMan(){
        String line  = "    SenFileCompressor est un logiciel d’archivage et de compression de fichiers\n" +
                        " L’archivage est l’utilisation d'un seul fichier pour stocker plusieurs fichiers\n"+
                        "et la compression de données est la diminution de l'espace occupé sur le support numérique,sans perte de qualité.\n" +
                        "On peut donc le comparer à la combinaison de tar (archivage) et gzip (compression) dans le cadre d'une archive compressée tgz\n" + "\n*        -----------------------------        \n" +
                        "\nEXECUTION: java SenFileCompessor [option] [Fichier]\n" +
                        "java SenFileCompressor -h \n" +
                        "java SenFileCompressor -d [Fichier] -r [chemin] -f -v \n" +
                        "java SenFileCompressor -c [Fichier] -r [chemin] -f -v \n" + "\n        -----------------------------        \n" +
                        "\nDescription: \n"+
                        "On peut exécuter le programme en choisissant une option selon le besoin \n"+
                        "-h (help)  permet d'afficher l’aide du programme qui donne les instructions d’utilisation du programme.\n" +
                        "-c (compression) permet de compresser les fichiers. Il fournit un fichier  d’extension « .sfc » qui regroupe,\n" + "sous forme compressé, les  différents fichiers fournis en paramètre. Notons qu'une compression entraine implicitement un archivage.\n"+
                        "-d (decompression) permet de décompresser un fichier compressé. Il fournit l’intégralité des fichiers contenus dans l’archive donné en paramètre. \n" +
                        "-r cette option peut être combinée avec les options -c et -d. Elle permet de spécifier le chemin (absolu ou relatif) vers le répertoire où seront stockés les fichiers (ou le fichier) produits par le programme.\n" +
                        "-f permet de créer automatiquement le chemin s'il n'existe pas. Cette  option ne doit donc être donnée que si l’option « -r » est précisée. 	-v (verbosité) 	permet de lister les details sur l'exécution du programme.\n" +
                        "-v concerne la verbosité du programme. Le programme donnera des détails sur son exécution en fonction de la présence ou non de ce paramètre\n";
        
        System.out.println("                    AIDE                     ");
        System.out.println("        -----------------------------        ") ;    
        System.out.println(line);
    }
}
    
