import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class webgraph {
	public static String home = "/home/prashanth/Documents/SE/P3/Output/";
	public static String filePath1 = "/home/prashanth/Documents/SE/P3/Output/1_XML_TO_ID_Title_URL/id_url1.txt";
	public static String filePath2 = "/home/prashanth/Documents/SE/P3/Output/1_XML_TO_ID_Title_URL/id_title_new.txt";
	public static String OutputPath = "/home/prashanth/Documents/SE/P3/Output/4_WebGraph/";
//	public static String[] titles = new String[10771634];
//	public static int[] ids = new int[10771634];
	public static SortedMap<String, Integer> urlTitle = new TreeMap<String, Integer>();
	public static SortedMap<Integer, SortedSet<Integer>> inLinks = new TreeMap<Integer, SortedSet<Integer>>();
	//public static int[][] inLinks = new int[10771634][];
	//public static InLinks il = new InLinks();
	public static void main(String args[]) throws IOException
	{
		String startTime = general.getTime();
		getIdTitle();
		populateinlinksGraph();
		System.out.println("Writing to File System: ");
		writeToFile();
		System.out.println("Start Time: " + startTime + " End Time: " + general.getTime());
	}
	private static void writeToFile() 
	{
		System.out.println("Writing idTitle File");
		StringBuilder sb = new StringBuilder();
		List <String> list = new ArrayList<String>(urlTitle.keySet());
		int count = 0;
		for(String i: list)
		{
			sb.append(i + "=" + urlTitle.get(i).toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", "") + "\n");
			count++;
			
			if(count % 1000000 == 0)
			{
				System.out.println("Written WebGraph File: Count= " + count);
				general.createFile(OutputPath + "idTitle.txt", sb.toString(), true);
				sb = new StringBuilder();
			}
		}
		general.createFile(OutputPath + "idTitle.txt", sb.toString(), true);
		urlTitle = new TreeMap<String, Integer>();
		list = new ArrayList<String>();
		
		System.out.println("Writing WebGraph File");
		List <Integer> list1 = new ArrayList<Integer>(inLinks.keySet());
		sb = new StringBuilder();
		count = 0;
		for(Integer i: list1)
		{
			sb.append(i + "=" + inLinks.get(i).toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", "") + "\n");
			count++;
			
			if(count % 1000000 == 0)
			{
				System.out.println("Written WebGraph File: Count= " + count);
				general.createFile(OutputPath + "webgraph.txt", sb.toString(), true);
				sb = new StringBuilder();
			}
		}
		general.createFile(OutputPath + "webgraph.txt", sb.toString(), true);
		
	}
	
	private static void populateinlinksGraph() throws IOException 
	{
		BufferedReader br = general.readStream(filePath1);
		String line;
		int index;
		int lineCount = 0;
		while( (line = br.readLine()) != null)
		{
			line = line.trim();
			index = line.indexOf("\t");
			System.out.println(index);
			if(index != -1)
			{
				int Id = Integer.parseInt(line.substring(0,index));
				
				String OTtitle = line.substring(index + 1).trim();
				System.out.println(OTtitle);
				if(urlTitle.containsKey(OTtitle))
				{
					Integer key = urlTitle.get(OTtitle);
					if(inLinks.get(key) != null)
					{
						inLinks.get(key).add(Id);
					}
					else
					{
						SortedSet<Integer> ss = new TreeSet<Integer>();
						ss.add(Id);
						inLinks.put(key, ss);
					}
					
				}
			}
			
			if(lineCount % 100 == 0)
			{
				System.out.println("InLinks Count" + lineCount);
			}
			
			lineCount++;
		}
	}
	
	private static void getIdTitle() throws IOException 
	{
		BufferedReader br = general.readStream(filePath2);
		String line, word;
		int prevId = 0;
		int index, id, count = 0;
		
		while( (line = br.readLine()) != null)
		{
			if(count % 10000 == 0)
				System.out.println("Count: " + count);
			
			line = line.trim();
			index = line.indexOf(" ");
			try
			{
				id = Integer.parseInt(line.substring(0,index));
				
				if(prevId != id)
				{
					word = line.substring(index + 1);
					urlTitle.put(word, id);
//					titles[count] = word;
//					ids[count] = id;
					count++;
					prevId = id;
				}
				
			}
			catch(Exception e)
			{
				//System.out.println("Error: " + e);
				continue;
			} 
			
		}	
		System.out.println("Count =" + count);
	}
	
	
}