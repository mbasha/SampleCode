package cu.cs.cpsc215.project1;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;

public class DownloadRepository //implements Repository
{
	private HashSet<String> currentFiles = new HashSet<String>();
	private HashSet<String> visitedFiles = new HashSet<String>();
	private HashSet<String> visitedImages = new HashSet<String>();
	String directory = "";
	private static final DownloadRepository downloadRepInstance = new DownloadRepository();
	
	private DownloadRepository(){
		//singleton class
		//should contain nothing
	}

	public static DownloadRepository getRepository()
	{
		return downloadRepInstance;
	}
	
	public void setDirectory(String d)
	{
		directory = d;
	}
	
	public String getDirectory()
	{
		return directory;
	}
	
	public void storeImages(WebImage a){
		try {
			a.store(directory);
		} catch (Exception e1) {
			System.out.println("Failed to store image.");
		}
	}
	
	public void storeFiles(WebFile a)
	{
		try {
			a.store(directory);
		} catch (Exception e1) {
			System.out.println("Failed to store file.");
		}
	}
	
	public boolean newImage(String str) {
		if(!visitedImages.contains(str)) {
			visitedImages.add(str);
			return true;
		}
		return false;
	}
	public boolean newFile(String str) {
		if(!visitedFiles.contains(str)) {
			visitedFiles.add(str);
			return true;
		}
		return false;
	}

	public String check(String name) {
		if(!currentFiles.contains(name)) {
			currentFiles.add(name);
			return name;
		}
		
		String temp;
		String filetype;
		if(name.contains(".")) {
			filetype = name.substring(name.lastIndexOf('.'));
		}
		else {
			currentFiles.add(name);
			return name;
		}
		
		int i = 2;
		while(currentFiles.contains(name)) {
			temp = name.substring(0,  name.lastIndexOf('.'));
			temp += i;
			i++;
			if(!currentFiles.contains(temp+filetype)) {
				name = temp + filetype;
			}
		}
		
		currentFiles.add(name);		
		return name;
	}
}