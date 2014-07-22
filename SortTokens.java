import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class SortTokens 
{
	public static String home = "/home/prashanth/Documents/SE/P3/Output/";
	public static String tokens = home + "Tokens/";
	public static String tokensFile = home + "Tokens/All_Tokens.txt";
	public static SortedMap<String, SortedSet<Integer>> SM = new TreeMap<String, SortedSet<Integer>>();
	
	public static void main(String args[]) throws IOException
	{		
		String startTime = general.getTime();
		System.out.println("Start");
		generateTokens();
		
		
		System.out.println("Done. Start Time: " + startTime + " End Time: " + general.getTime());
	}
	
	public static void generateTokens() throws IOException
	{
		String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		for(String fp: a)
		{
			fp = fp + ".txt";
			BufferedReader br = general.readStream(tokens + fp);
			String line = null;
			int count = 1;
			short tempFiles = 1;
			
			SM = new TreeMap<String, SortedSet<Integer>>();
			
			while((line = br.readLine()) != null){
				
				String[] temp = line.trim().split(" ");
				if(SM.get(temp[0]) == null)
				{
					SortedSet<Integer> ss = new TreeSet<Integer>();
					ss.add(Integer.parseInt(temp[1]));
					SM.put(temp[0], ss);
				}
				else
				{
					SM.get(temp[0]).add(Integer.parseInt(temp[1]));
				}
				
				count++;
				if(count % 20000 == 0)
				{ 
					System.out.println(count  + " " + " Size:" + SM.size() + " " + fp + ' ' + "Time: " + general.getTime());
				}
				if(count % 5000000 == 0){
					System.out.println("Memory Cleared");
					general.createFile(tokens + "tmp_"+tempFiles+".txt", SM.toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", ""), false);
					SM = new TreeMap<String, SortedSet<Integer>>();
					tempFiles++;
				}
			}
			
			System.out.println("Generated for " + fp);
			general.createFile(tokens + "tmp_"+tempFiles+".txt", SM.toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", ""), true);
			
			general.createFile(tokens + "InterFile.txt", " ", false);
			
			for(short j = 1; j <= tempFiles; j++){
				externalSort(tempFiles, (short) j);
				
				File oldfile =new File(tokens + "tmp.txt");
				File newfile =new File(tokens + "InterFile.txt");
				File delete = new File(tokens + "tmp_" + j + ".txt");
				
				newfile.delete();
				oldfile.renameTo(newfile);
				oldfile.delete();
				delete.delete();
				
			}
			
			File oldfile =new File(tokens + "InterFile.txt");
			File newfile =new File(tokens + fp +"_sorted.txt");
			
			oldfile.renameTo(newfile);
		}
	}

	private static void externalSort(short tempFiles, short currFile) throws IOException 
	{
		
		System.out.println("Sorted File: " + currFile + " of " + tempFiles);
		
		BufferedReader br1 = general.readStream(tokens + "InterFile.txt");
		BufferedReader br2 = general.readStream(tokens + "tmp_" + currFile + ".txt");

		String line1, line2;
		String word1, word2;
		StringBuilder sb = new StringBuilder();
		
		long count = 0;
		line1 = br1.readLine();
		line2 = br2.readLine();
		while( true )
		{
			
			if( line1 == null  || line2 == null )
			{
				System.out.println("exiting one file ");
				break;
			}
			
			if(count % 20000 == 0)
				System.out.println("ExternalSort: " + count);
			
			short sr1 = (short) line1.indexOf("=");
			short sr2 = (short) line1.indexOf("=");

			if(sr1 == -1 || sr2 == -1)
				break;
			
			word1 = line1.substring(0, ( sr1 == -1 ) ? 0 : sr1 ).trim();
			word2 = line2.substring(0, line2.indexOf("=")).trim();
			
			if(! ( word1.isEmpty() || word2.isEmpty() )) 
			{
				if(word1.compareTo(word2) < 0)
				{
					sb.append( line1.trim() + "\n"); 
					line1 = br1.readLine();
				}
				else if (word1.compareTo(word2) == 0)
				{
					int index1 = line1.indexOf("=");
					int index2 = line2.indexOf("=");
					sb.append(word1 + "=" + sortInt(line1.substring(index1 + 1 ).trim() + "," + line2.substring(index2 + 1 ).trim()) + "\n");
					if(count > 50000)
					{
						count = 1;
						general.createFile(tokens + "tmp.txt", sb.toString(), true);
						sb = new StringBuilder();
					}
					line2 = br2.readLine();
					line1 = br1.readLine();
				}
				else if (word1.compareTo(word2) > 0)
				{
					sb.append(line2.trim() + "\n");
					line2 = br2.readLine();			
				}
				}
					
				count++;
		}
		
		System.out.println("Writing remaining files");
		
		if(line1 == null || line1.trim().isEmpty())
		{
			while( line2  != null && !line2.trim().isEmpty())
			{
				if(count > 150000)
				{
					count = 1;
					general.createFile(tokens + "tmp.txt", sb.toString(), true);
					sb = new StringBuilder();
				}
				sb.append(line2 + "\n");
				count++;
				line2 = br2.readLine();
			}
		}
		else 
		{
			while( line1 != null && !line1.trim().isEmpty())
			{
				if(count > 150000)
				{
					count = 1;
					general.createFile(tokens + "tmp.txt", sb.toString(), true);
					sb = new StringBuilder();
				}
				sb.append(line1 + "\n");
				count++;
				
				line1 = br1.readLine();
			}
		}
		
		general.createFile(tokens + "tmp.txt", sb.toString(), true);

	}
	
	public static String sortInt(String string)
	{
		
		String[] str = string.trim().split(",");
		
		if(str.length == 1)
			return string;
		
		SortedSet<Integer> ss = new TreeSet<Integer>();
		
		for(String s: str)
		{
			if(s != null)
				ss.add(Integer.parseInt(s));
		}
		
		return ss.toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", "");
	}
	
}
