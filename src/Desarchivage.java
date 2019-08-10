import java.io.*;
public class Desarchivage {
     
  public static String findfilemetadata(File file) throws IOException  {
    BufferedReader metareader = null;
    String entete =null;
    //System.out.println(file.getName());
    try {

      FileReader inputmeta = new FileReader(file.getAbsolutePath());
      metareader = new BufferedReader(inputmeta);
      entete = metareader.readLine();
      
    } catch(IOException ex) {
      System.out.println("Erreur lors de la lecture des métadonnées du fichier d'archive");
    }finally {
      metareader.close();
      return entete;
    }
    
    
    
  }

    public static void writedatafile(String filemetadata,String path,BufferedInputStream reader,Boolean verbosite) throws IOException{
      //System.out.println(filemetadata[i]);
      //On initialise notre Writer à null
      BufferedOutputStream writer = null;            
      String[] filedata = filemetadata.split(":");
      String filename = filedata[0];
      try {
        File outputfile = new File(path,filename);
        writer = new BufferedOutputStream(new FileOutputStream(outputfile));
        long length = Long.valueOf(filedata[1],16);
        //System.out.println(length);
        int nbretours = (int)(length/1024);
        int reste = (int)(length%1024);
        //System.out.println(nbretours);
        //System.out.println(reste);
        final byte[]  buffer = new byte[1024]; // allocation du tampon 
        final byte[] bufferreste = new byte[reste]; //tampon pour le reste
        int dataCount,j;
        for(j=0;j<nbretours;j++) {
          
          //System.out.println("Tour n°"+j);
             reader.read(buffer,0,buffer.length);
              writer.write(buffer, 0, buffer.length);
            
        }
        // Ecriture du reste du fichier          
        reader.read(bufferreste, 0, bufferreste.length);
        writer.write(bufferreste, 0, bufferreste.length);
        if(verbosite) {
          System.out.println("le fichier "+filename+" a bien été extrait de l'archive");
        }
        
        //System.out.println(reste);
     
      }catch(IOException ex){
        System.out.println("Impossible de fermer correctement les flux ");
      }
      finally {
        writer.close();
      }
    }
     public static void unpackFile(File file,String path,Boolean verbosite) {
            //On initialise notre Writer et notre Reader à null
            
            BufferedInputStream reader = null;
            try {
                //File inputfile = new File(file);
                reader = new BufferedInputStream(new FileInputStream(file));
                String entete = findfilemetadata(file);
                //System.out.println(entete);
                String[] filemetadata = entete.split(";");
                int i;
                int numberfiles = filemetadata.length;
                int saut = (entete+"\n").length();
                reader.skip(saut);
                for(i=0;i<filemetadata.length;i++) {
                  writedatafile(filemetadata[i],path,reader,verbosite);
                  if(verbosite) {
                    System.out.println("Desarchivage faite à "+((i+1)*100)/numberfiles+" %");
                  }
                }
                  
                  
            }catch(IOException e){
                System.out.println("Erreur lors du desarchivage du fichier : ");
                e.printStackTrace();
              }
            finally {
                try {
                    if(reader != null)
                    {
                      reader.close();
                    }
                    
                    
                  } catch (IOException e) {
                    System.out.println("Impossible de fermer correctement les flux ");
                  }
            }
            

        }

}