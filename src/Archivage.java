import java.io.*;
import java.nio.ByteBuffer;

public  class Archivage {
    
public static void writefilemetada(File[] files,BufferedOutputStream writemeta) throws IOException{
  
  ByteBuffer buffer = ByteBuffer.allocate(8);
  //BufferedOutputStream writemeta = null;
  int i;
  //File outputfile = new File("output.tmp");
  try {
    
    for(i=0;i<files.length;i++) {
      File fichier = files[i];
      //writemeta = new BufferedOutputStream(new FileOutputStream(outputfile));
      if( fichier!=null &&  fichier.isFile()) {
        //On recupere le nom + la longueur de chaque fichier
        byte[] name = (fichier.getName()+":").getBytes();
        String temp = Long.toHexString(fichier.length());
        byte[] length = (temp+";").getBytes();
        //System.out.println(fichier.getName());

        //On ecrit alors ces informations
        writemeta.write(name,0,name.length);
        writemeta.write(length,0,length.length);
        }
    }
     //Retour à la ligne après avoir écrit toutes les informations des fichiers à archiver  
     byte[] endmeta = ("\n").getBytes();
      writemeta.write(endmeta);
  } catch(IOException ex) {
      System.out.println("Erreur lors de l'écriture des métadonnées pour l'archivage");
  } 
}

    public static File packFile(File[] files,String path,Boolean verbosite) {
        
      File outputfile = new File(path,"output.tmp");
      
      if(!outputfile.exists()) {
        try {
          outputfile.createNewFile();
          //output = new File("ouput.tmp");
        } catch(IOException ex) {
          System.out.println("Impossible de créerle fichier d'archive");
        }
      }
      BufferedInputStream reader = null;
      BufferedOutputStream writer = null;
      int i;
      try{
        writer =  new BufferedOutputStream(new FileOutputStream(outputfile));
        writefilemetada(files,writer);
        //Enregitrement des informations de chaque fichier
        for(i=0; i<files.length ;i++) {
          File fichier = files[i];
          //System.out.println("writing filedata");
          //System.out.println(fichier.getAbsolutePath());
          
          if(fichier != null && fichier.isFile()){
            //on copie le contenu de chaque fichier dans le fichier d'archive
            //writter = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputfile)));
            reader = new BufferedInputStream(new FileInputStream(fichier));
            final byte[] buffer = new byte[1024]; //tampon de lecture
            int dataCount;
            while((dataCount = reader.read(buffer, 0, buffer.length)) != -1 ) {
              writer.write(buffer, 0, dataCount);
            }
            //On ferme le flux de lecture.
            reader.close();
          }
          if(verbosite) {
            System.out.println("Le Fichier "+fichier.getName()+" a été ajouté à l'archive");
            System.out.println("Archivage fait  à "+((i+1)*100)/files.length); 
          }
            
           
        }
        //Fin de parcours des fichiers
        
      
      } catch(IOException e) {
        System.out.println("Erreur lors de l'archivage du fichier : ");
        e.printStackTrace();
        } finally {
            try {
              if(writer != null) {
                writer.flush();
                writer.close();
              }
              if(reader != null) {
                reader.close();
              }
              
              
            } catch (IOException e) {
              System.out.println("Impossible de fermer correctement les flux ");
              }
          
          }
        
          return outputfile;
        }

}