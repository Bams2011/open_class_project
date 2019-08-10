import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.*;

public class DeflaterImp  {

    /**
   * 
   * @param inputByte
   *       Array of bytes to be extracted 
   * @return  Byte array after decompression 
   * @throws IOException
   */
  public static byte[] uncompress(byte[] inputByte,Boolean verbosite) throws IOException {
    int len = 0,i=1;
    Inflater infl = new Inflater();
    int datalength = inputByte.length;
    infl.setInput(inputByte);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] outByte = new byte[1024];
    try {
      while (!infl.finished()) {
        //  Decompresser le contenu du tableau d'entrée  et mettre ça dans un tableau de sortie à renvoyer  
        len = infl.inflate(outByte);
        if (len == 0) {
          break;
        }
        bos.write(outByte, 0, len);
        

          

      }
      if(verbosite){
          System.out.println("Decompression finie");
          i++;
        }
      infl.end();
    } catch (Exception e) {
      //
    } finally {
      bos.close();
    }
    return bos.toByteArray();
  }

  /**
   *  compress .
   * 
   * @param inputByte
   *       Array of bytes to be compressed 
   * @return  Compressed data 
   * @throws IOException
   */
  public static byte[] compress(byte[] inputByte,Boolean verbosite) throws IOException {
    int len = 0,i=1;
    Deflater defl = new Deflater();
    int datalength = inputByte.length;
    defl.setInput(inputByte);
    defl.finish();
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] outputByte = new byte[1024];
    try {
      while (!defl.finished()) {
        //  Compress and compress the content to the byte output stream bos in 
        len = defl.deflate(outputByte);
       /* if(verbosite) {
          System.out.println("Compression faite à "+(i++*len*100)/datalength+" %");
         
        }*/
        bos.write(outputByte, 0, len);
        
      }
      defl.end();
      if(verbosite) {
          System.out.println("Compression finie");
        }
    } finally {
      bos.close();
    }
    return bos.toByteArray();
  }
}