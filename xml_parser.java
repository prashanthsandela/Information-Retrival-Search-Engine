import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;


public class xml_parser {
	public static String filePath = "/media/prashanth/Windows8_OS/Users/Prashanth/Desktop/SJSU/Classes/SearchEngines/Assignments/P3/Input/enwiki-latest-pages-articles.xml_2";
//	public static String filePath_sample = "/media/prashanth/Windows8_OS/Users/Prashanth/Desktop/SJSU/Classes/SearchEngines/Assignments/P3/input/sample1.xml";
	public static String filePath1 = "/home/prashanth/Documents/SE/P3/Output/1_XML_TO_ID_Title_URL_temp/id_url.txt";
	public static String filePath2 = "/home/prashanth/Documents/SE/P3/Output/1_XML_TO_ID_Title_URL_temp/id_title.txt";
	public static String outputFolderPath = "/home/prashanth/Documents/SE/P3/Output/Tokens/AllTerms.txt";
	
	public static void main(String args[]) throws XMLStreamException, FactoryConfigurationError, IOException, InterruptedException{
		parseXML();
	}
	
	private static void parseXML() throws IOException, InterruptedException{
		
		BufferedReader br = general.readStream(filePath);
		String startTime = general.getTime();
		
		String line, title, ns;
		String id = null;
		boolean redirect = false, ns0 = false, startpage = false, foundID = false;
		StringBuilder pageContent = new StringBuilder();
		StringBuilder urls = new StringBuilder();
		StringBuilder idTitle = new StringBuilder();
		int j = 1;
		Pattern pattern = Pattern
				.compile("\\b((http\\:\\/\\/|~\\/|\\/))+(en.wikipedia.org)+"
						+ "(:[\\d]{1,5})?"
						+ "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?"
						+ "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
						+ "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)"
						+ "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
						+ "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*"
						+ "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");
		
		tokenizer.getStopWords();
		List<String> lPage = new ArrayList<String>();
		
		while((line = br.readLine()) != null)
		{
			if(line.indexOf("<page>") != -1)
			{
				line = line.trim();
				title = "";
				id = "";
				startpage = false;
				redirect = false;
				while((line = br.readLine()) != null)
				{
					line = line.trim();
					ns0 = true;
					redirect = false;
					if(line.indexOf("</text>") != -1 )
					{
						pageContent.append(line.substring(0, line.indexOf("</text>")).trim() + "\n");
						startpage = false;
					}
					else if(startpage){
						pageContent.append(line.trim() + "\n");
					}
					
					else if( line.indexOf("</page>") != -1 )
					{
						break;
					}
					
					else if(line.indexOf("<title>") != -1 && line.indexOf("</title>") != -1)
					{
						title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>")).trim();
					}
					
					else if(line.indexOf("<ns>") != -1 && line.indexOf("</ns>") != -1)
					{
						ns = line.substring(line.indexOf("<ns>") + 4, line.indexOf("</ns>")).trim();
						if ( !ns.equals( "0"))
						{
							ns0 = true;
							break;
						}
					}
					else if(line.indexOf("<redirect") != -1 && line.indexOf("/>") != -1)
					{
						redirect = true;
						break;
						
					}
					else if(line.indexOf("<id>") != -1 && line.indexOf("</id>") != -1 && !foundID)
					{
						id = line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")).trim();
						foundID = true;
					}
					else if( line.indexOf("<text") != -1 &&  line.indexOf("</text>") != -1 )
					{
						pageContent.append(line.substring(line.indexOf("<text"), line.indexOf("</text>")).trim() + "\n");
					}
					else if( line.indexOf("<text") != -1 )
					{
						startpage = true;
						pageContent.append(line.substring(line.indexOf(">") + 1) );
					}
				}
				
				if(!redirect && ns0)
				{
					//System.out.println(id);
					lPage.add(id);
					lPage.add(pageContent.toString());
					
					idTitle.append(id + " " + title + "\n");
					
					Matcher matcher = pattern.matcher(pageContent.toString());
					while (matcher.find()) {
						
						urls.append(id + "\t"
								+ matcher.group().replaceAll("http\\:\\/\\/en\\.wikipedia\\."
								+ "|\\/\\/en\\.wikipedia\\."
								+ "|en\\.wikipedia\\.", "wikipedia.").replaceAll("#.*", "") + "\n"
								);
						
//						urls.append(id + "\t" + "wikipedia.org/wiki/"
//								+ title.replaceAll(" ", "_") + "\t"
//								+ matcher.group().replaceAll("http\\:\\/\\/en\\.wikipedia\\."
//								+ "|\\/\\/en\\.wikipedia\\."
//								+ "|en\\.wikipedia\\.", "wikipedia.").replaceAll("#.*", "") + "\n");
					}
					
					if(j % 8 == 0){
						tokenizer.splitTok(lPage);
						lPage = new ArrayList<String>();
					}
						
					pageContent = new StringBuilder();
					j++;
					if( j % 10000 == 0)
						System.out.println(j + " " + startTime + " " + general.getTime());
					if(j % 30000 == 0){
						general.createFile(filePath1, removeDuplicates(urls), true);
						general.createFile(filePath2, idTitle.toString(), true);
						urls 	= new StringBuilder();
						idTitle = new StringBuilder();
						//System.exit(0);
					}
				}
				foundID = false;
			}
		}
		
		myThread1.writefn();
		myThread2.writefn();
		myThread3.writefn();
		myThread4.writefn();
		myThread5.writefn();
		myThread6.writefn();
		myThread7.writefn();
		myThread8.writefn();
		
		myThread1.writeAll();
		  myThread2.writeAll();
		  myThread3.writeAll();
		  myThread4.writeAll();
		  myThread5.writeAll();
		  myThread6.writeAll();
		  myThread7.writeAll();
		  myThread8.writeAll();
		
		general.createFile(filePath1, urls.toString(), true);
		general.createFile(filePath2, idTitle.toString(), true);
		
		System.out.println(j + " " + startTime + " " + general.getTime());
		System.out.println("Start Time = " + startTime + " EndTime: " + general.getTime());
		
	}
	
	private static String removeDuplicates(StringBuilder urls) {
		String[] strArray;
		StringBuilder sb = new StringBuilder();
		strArray = urls.toString().split("\n");
		Arrays.sort(strArray);
		String prev = " ";
		String next = "";

		int len = strArray.length;
		for (int i = 0; i < len; i++) {
			next = strArray[i].trim();
			if (!prev.equalsIgnoreCase(next) && !next.isEmpty() && next != "\n") {
				sb.append(next + "\n");
			}
			prev = strArray[i];
		}
		return sb.toString();

	}
	
}
