package cu.cs.cpsc215.project1;

/*
 * Abrar Basha - mbasha
 * Partner: Sarah Malick
 * project1 - WebCrawler
 *  cpsc2510-001
 * 
 * This program is a WebCrawler and it will perform a basic parse of the web page specified at the command-line.
 * The goal of the parse is to identify the images used within the page, the other files linked
 * from within the page, and the web pages linked from within the page. The first two element
 * sets will be saved to disk in the directory specified at the command-line. The third set of elements
 * will be used as input to the next step in the page crawl.
 */

import java.io.*;

public class WebCrawler {
	public static void main(String[] args) throws Exception {
		DownloadRepository.getRepository().setDirectory(args[2]);
		
		String startURL = args[0];
		int depth = Integer.parseInt(args[1]);
		int min = 0;
		int max = 0;
		int index = 0;
		
		WebPage page = new WebPage(startURL);
		
		while(index < depth)
		{
			max = WebPage.getVisitedURLs().size();
			for(int i = min; i < max; i++) {
				new WebPage(WebPage.getVisitedURLs().get(i));
			}
			min = max;
			index++;
		}
			System.out.println("Webcrawl complete");
	}
}