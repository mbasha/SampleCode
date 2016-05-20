package cu.cs.cpsc215.project1;

import org.jsoup.nodes.Element;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.select.Elements;

public class WebFile implements WebElement{	
	private Elements files;
		
	public WebFile()
	{
		files = null;
	}
	public WebFile(Elements element)
	{
		files = element;
	}
	public Elements getFiles()
	{
		return files;
	}
	public void setFiles(Elements element)
	{
		files = element;
	}
	@Override
	public void store(String dir) {
		String str;
		for(Element link : files) {
			String name;
			str = link.attr("abs:href");
			if(str.lastIndexOf('?') > str.lastIndexOf('.')) {
				name = str.substring(str.lastIndexOf('/')+1, str.lastIndexOf('?'));
			}
			else {
				name = str.substring(str.lastIndexOf('/')+1);	
			}
			
			name = DownloadRepository.getRepository().check(name);
		
			try {
				if(!DownloadRepository.getRepository().newFile(str)) {
					throw new Exception();
				}
				
				URL url = new URL(str);
				System.out.println("Storing file from " + str + " as " + name);
				InputStream is = url.openStream();
				OutputStream os = new FileOutputStream(dir + "/" + name);
				byte[] b = new byte[2048];
				int length;
				
				while((length = is.read(b)) != -1) {
					os.write(b, 0, length);
				}
				
				is.close();
				os.close();
			
			} catch (StringIndexOutOfBoundsException e1) {
				System.out.println("Invalid url name: " + str);
			} catch (MalformedURLException e2) {
				System.out.println("Invalid url: " + str);
			} catch (FileNotFoundException e3) {
				System.out.println("Invalid file name: " + str);
			} catch (IOException e4) {
				System.out.println("Invalid url name: " + str);
			} catch (Exception e) {
				System.out.println("This url has already been stored.");
			}
		}
	}
}