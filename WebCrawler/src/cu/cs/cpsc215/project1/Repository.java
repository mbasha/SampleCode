package cu.cs.cpsc215.project1;

import java.awt.Image;
import java.io.File;

//This interface is not used. Please ignore

public interface Repository {
	//static?
	//private void DownloadRepository();
	public DownloadRepository getRepository();
	public void setDirectory(String d);
	public void saveImages(Image inImage);
	public void saveFiles(File inFile);	
}
