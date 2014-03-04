package cu.cs.cpsc215.project1;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.*;
import java.net.*;
//imported external jar jsoup
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class WebPage implements WebElement{
	private Elements Pages;
	//verified pages
	private Elements pages;
	private Elements Files;
	//verified files
	private WebFile files;
	private WebImage images;
	private String url;
	private Document doc;
	private static ArrayList<String> urlListVisit = new ArrayList <String>();
		
	public WebPage(String URLIn) throws IOException
	{
		url = URLIn;
		try{
			doc = Jsoup.connect(url).get();
		} catch(SocketTimeoutException e1){
			System.out.println("It took too long to connect to " +url+ ". Disconnecting.");
		} catch(UnknownHostException e2) {
			System.out.println(url + " has an unknown host.");
		} catch(HttpStatusException e3){
			System.out.println(url+ " is invalid");
		} catch (IOException e4){
			System.out.println(url+ " is invalid");
		}
		
		crawl();
		DownloadRepository.getRepository().storeFiles(files);
		DownloadRepository.getRepository().storeImages(images);
		
	}
	public void crawl()
	{
		try {
			Pages = doc.select("a[href]");
		} catch (NullPointerException e) {
			images = new WebImage(new Elements());
			files = new WebFile(new Elements());
			return;
		}
		
		pages = new Elements();
		Files = new Elements();
		for(Element link : Pages) {
			if(link.toString().lastIndexOf("tel:") == -1 && link.toString().lastIndexOf("mailto:") == -1) {
				String temp = link.attr("abs:href");
				if(temp.endsWith(".html") || temp.endsWith(".edu") || temp.contains(".htm") 
						|| temp.contains(".aspx") || temp.endsWith(".com") || temp.contains(".php") 
						|| temp.endsWith(".org") || temp.endsWith(".gov") || temp.endsWith(".net")
						|| temp.contains(".php") || temp.lastIndexOf("/") > temp.lastIndexOf(".")
						|| temp.lastIndexOf("#") > temp.lastIndexOf(".") || temp.contains(".asp")) {
					if(temp.contains("#") && !urlListVisit.contains(temp)) {
						pages.add(link);
						urlListVisit.add(temp);
					}
					else if(temp.lastIndexOf("#") > temp.lastIndexOf(".") && !urlListVisit.contains(temp.substring(0,  temp.lastIndexOf(".")))) {
						pages.add(link);
						urlListVisit.add(temp);
					}
				}
				else {
					Files.add(link);
				}	
			}
		}
		
		images = new WebImage(doc.select("img"));
		files = new WebFile(Files);
	}
	
	public WebImage getImages()
	{
		return images;
	}
	public WebFile getFiles()
	{
		return files;
	}
	public Elements getWebPages()
	{
		return pages;
	}
	public static ArrayList<String> getVisitedURLs() {
		return urlListVisit;
	}

	@Override
	public void store(String dir) {
		System.out.println("You do not store URL to disk");
		
	}
}
