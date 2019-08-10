import java.io.*;
import java.util.Base64;

public class SenFileCompressor {

	
	public static File[] getFiles(String[] files) {
		int i=1,j=0;
		while(i<files.length ) {
			if(files[i].equals("-r"))
				break;
			i++;
		}
		File[] Tabfiles = new File[i-1];
		for(j=0;j<i-1;j++) {
			Tabfiles[j] = new File(files [j+1]);
		}
		return Tabfiles;
	}
	
	public static void main(String[] args) {
		FileInputStream source = null;
		FileOutputStream destination = null;
		FileOutputStream resultfile = null;
		byte[] reader = null;
		byte[] result = null;
		File[] tabfiles = null;
		String destinationpath =  "/home/abdoulaye/Bureau/TP_JAVA/Projet_Compression/test/";
		Boolean verbosite = false;
		tabfiles = getFiles(args);
		File dest = null ;
		int nbre = tabfiles.length;
		int nbreargs = args.length;
		if (args[nbreargs-1].equals("-v")) 
			verbosite = true;

		switch(args[0]) {
			case "-h": {
				System.out.println("fichier aide");
				SenFileHelp.OurMan();
				break;
			}
			case "-c": {
				System.out.println("Bienvenue dans l'onglet de Compression de fichiers");
				
				try {
					
					//Verification de la donnée d'un chemin destinataire
					if( nbre + 1 < nbreargs &&  args[nbre + 1].equals("-r")) {
						if(nbre + 2 < nbreargs) {
							destinationpath = args[nbre + 2];
							dest = new File(destinationpath);
							if(!dest.exists() && nbre + 3 == nbreargs ) {
								System.out.println("Le chemin destination n'existe pas");
								return ;
							}
							if(!dest.exists() && nbre + 3 < nbreargs && !args[nbre +3].equals("-f")) {
								System.out.println("Le chemin destination n'existe pas"); 
									return ;
							}
							else if(!dest.exists() && nbre + 3 < nbreargs && args[nbre +3].equals("-f")) {
								dest.mkdir();
							}
						}
						else  {
							System.out.println("Vous n'avez pas  précisé de chemin ,le resultat sera mis dans le répertoire de test");
						}
							
					}
					
					//archivage
					if(verbosite)
						System.out.println("Archivage des fichiers");
					File archive = Archivage.packFile(tabfiles,destinationpath,verbosite);
					//System.out.println(archive.getAbsolutePath());
					//compression 
					FileInputStream archivereader = new FileInputStream(archive);
					int length = archivereader.available();
					reader = new byte[length];
					result = new byte[length];
					//System.out.println(destinationpath);
					File resultcompress = new File(destinationpath,"testcompress.sfc");
					resultfile = new FileOutputStream(resultcompress);

					archivereader.read(reader);
					result = DeflaterImp.compress(reader,verbosite);
					resultfile.write(result);
					if(verbosite)
						System.out.println("Les fichiers compressés sont dans le dossier "+resultcompress.getAbsolutePath());
					resultfile.flush();
					resultfile.close();
					archivereader.close();
				} catch(IOException ex) {
					System.out.println("Erreur lors de la compression");
				}
				


				//	if (args[1]== "-r") System.out.println("Compression avec cchemin absolu");
			  //	if (args[1]=="-v")System.out.println("Compression avec détails d'exécution");
				break;
			}
			case "-d": {

				System.out.println("Bienvenue dans l'onglet de décompression de Fichier");
				try {
					//Verification de la donnée d'un chemin destinataire
					source = new FileInputStream(tabfiles[0]);
					if( nbre + 1 < nbreargs &&  args[nbre + 1].equals("-r")) {
						if(nbre + 2 < nbreargs) {
							destinationpath = args[nbre + 2];
							dest = new File(destinationpath);
							if(!dest.exists() && nbre + 3 == nbreargs ) {
								System.out.println("Le chemin destination n'existe pas");
								return ;
							}
							if(!dest.exists() && nbre + 3 < nbreargs && !args[nbre +3].equals("-f")) {
								System.out.println("Le chemin destination n'existe pas"); 
									return ;
							}
							else if(!dest.exists() && nbre + 3 < nbreargs && args[nbre +3].equals("-f")) {
								dest.mkdir();
							}
						}
						else  {
							System.out.println("Vous n'avez pas  précisé de chemin ,le resultat sera mis dans le répertoire de test ");
						}
							
					}

					if(verbosite)
						System.out.println("Décompression du fichier");

					File unpackfile = new File(destinationpath,"decompression.tmp");
					resultfile = new FileOutputStream(unpackfile);
					//Decompression
					int length = source.available();
					reader= new byte[length];
					source.read(reader);
					String encodestr = Base64.getEncoder().encodeToString(reader);
					result = DeflaterImp.uncompress(Base64.getDecoder().decode(encodestr),verbosite);
					resultfile.write(result);
					resultfile.flush();
					resultfile.close();

					//Desarchivage
					Desarchivage.unpackFile(unpackfile,destinationpath,verbosite);
					if(verbosite) {
						System.out.println("Desarchivage finie");
						System.out.println("Les fichiers décompressés sont dans le dossier "+destinationpath);
					}
						
				} catch(IOException ex) {
					System.out.println("Erreur lors de la décompression");
				}

				
				
				
				//Desarchivage.unpackFile(temp);
				//	if (args[1]== "-r") System.out.println("Compression avec cchemin absolu");
			  //	if (args[1]=="-v")System.out.println("Compression avec détails d'exécution");
				break;
			}

			default : {
				SenFileHelp.OurMan();
				break;
			}

			
		}
	}


		
	/*	try {
			source = new FileInputStream(args[0]);
			destination =  new FileOutputStream(args[1]);
			decompressfile = new FileOutputStream("testdecompression.java");
			int length = source.available();
			//compression
			compressreader = new byte[length];
			source.read(compressreader);
			compressresult = DeflaterImp.compress(compressreader);

			//decompression
			String encodestr = Base64.getEncoder().encodeToString(compressresult);
			decompressresult = DeflaterImp.uncompress(Base64.getDecoder().decode(encodestr));

			//Ecriture des resultat
			decompressfile.write(decompressresult);
			decompressfile.flush();
			decompressfile.close(); 
			destination.write(compressresult);
			destination.flush();
			destination.close();
			source.close();
		} catch(IOException ex) {
			System.out.println("Erreur lors de la compression du fichier");
			
		}
		
	}*/
			
		 
		
		
		
		
}

