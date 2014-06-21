package kb.html_function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kb.bookmarkUnits.Directory;
import kb.bookmarkUnits.Item;
import kb.bookmarkUnits.Link;

/**
 * Diese Klasse generiert aus einer vorhandenen HTML Bookmark einen übersichtlichen
 * Baum der die Namen der Ordner und die darin beinahltenden Links aufzeigt.
 * 
 * @author Martin
 *
 */

public class Decoder {

	public static final String path = "G:\\Test\\bookmarks.html";
	
	public Decoder(){
		
	}
	
	public void genereateFileList(){
		try {
			
			BufferedReader 	reader 	= new BufferedReader(new FileReader(new File(path)));
			String 			zeile 	= null;
			
			Link link 		= null;
			Directory dir 	= null;
			
			while((zeile = reader.readLine()) != null){
				System.out.println(zeile);
				
				//Zeile beinhaltet <DT> oder <DL> ??
				 if(isContent(zeile)){
					//Tag beinhaltet "HREF" ??
					if(isLink(zeile)){
						
						link = new Link();
						fillData(zeile, link);
						link.setHref("");
						dir.addLinkToList(link);
						
					}else{
						zeile = reader.readLine();
						dir = new Directory();
						fillData(zeile, dir);
					}
				 }
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void fillData(String zeile, Item item) {
		
		Document doc = Jsoup.parse(zeile);

		
		String name 			= doc.body().text();
		String lastModified 	= doc.getElementsByAttribute("LAST_MODIFIED").attr("LAST_MODIFIED").toString();
		String addData 			= doc.getElementsByAttribute("ADD_DATE").attr("ADD_DATE").toString();
		
	}

	
	
	
	
	
	
	
	
	
	
	private boolean isContent(String zeile){
		return isDL(zeile) || isDT(zeile);
	}
	
	private boolean isLink(String zeile) throws IOException{
		return zeile.matches(".*(HREF=).*") && isDT(zeile);
	}
	
	private boolean isDL(String s){
		return s.matches(".*(<DL>).*");
	}
	
	private boolean isDT(String s){
		return s.matches(".*(<DT>).*");
	}
}
