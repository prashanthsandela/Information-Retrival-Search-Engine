import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class search {
	public static String pageRank 	= "~/prashanth/Documents/SE/src/P3/Output/5_pageRank/pageRank.txt";
	public static String idUrls 	= "~/prashanth/Documents/SE/src/P3/Output/1_XML_TO_ID_Title_URL/id_title.txt";
	public static String searchString = "zzpa or zznb";
	public static int blockSize = Compression.blockSize;
	
	public static void main(String args[]) throws IOException
	{
		String startTime = general.getTime();
		
		//Get all compressed Tokens and ID's
		System.out.println("Compressing Documents");
		Compression.compress();
		
		//Search for String
		search1();
		System.out.println("Done. Start Time: " + startTime + " End Time: " + general.getTime());
	}

	private static void search1() throws IOException {
		String query[] = searchString.split(" ");
		
		StringBuilder newQuery = new StringBuilder();
		
		int boolCount = 0;
		for(String s: query)
		{
			if( !s.equalsIgnoreCase("and") || !s.equalsIgnoreCase("or") || !s.equalsIgnoreCase("not"))
			{
				newQuery.append(s + ",");
				boolCount++;
			}
		}
		
		//Converts all tokens into of arrays but with compression
		String tokens[] = Compression.tokens.split("[0-9]+");
		byte[][] TokenSets = new byte[boolCount][];
		
		int currCount = 0;
		String[] query1 = newQuery.toString().split(",");
		for(String s: query1)
		{
			int len = tokens.length;
			String commonText = "";
			String word = "";
			boolean found = false;
			int index = 0;
			
			//Search for Token
			for(int i = 0; i < len; i++)
			{
				if( i % (blockSize + 1) == 0)
				{
					commonText = tokens[i];
				}
				else
				{
					word = commonText + tokens[i];
					if(word.equalsIgnoreCase(s))
					{
						index = (int) (i - Math.floor(i/blockSize));
						break;
					}
				}				
			}
			
			if(found)
			{
				TokenSets[currCount] = Compression.tokenSet[index];
			}
		}
		
		int[] indexes = getCombinations(query, decode(TokenSets));
		indexes = loadPageRanks(indexes);
		
		//Get page titles
		getTitles(indexes);
	}

	private static void getTitles(int[] indexes) throws IOException {
		
		SortedSet<Integer> ss = new TreeSet<Integer>();
		String[] sets = new String[10];
		
		for(int i: indexes)
		{
			ss.add(i);
		}
		
		BufferedReader br = general.readStream(idUrls);
		String line;
		int i =0; 
		while( (line = br.readLine()) != null)
		{
			int index = line.indexOf(",");
			int id = Integer.parseInt(line.substring(0, index));
			if( ss.contains(id))
			{
				sets[i] = line.substring(0, index + 1);
			}
			i++;
		}
		
		System.out.println("Top 10 URL's");
		for(String s: sets)
		{
			System.out.println("http://en.wikipedia.com/wiki?title=" + s.replace(" ", "_"));
		}
		
	}

	private static int[] loadPageRanks(int[] indexes) throws IOException {
		
		SortedMap<Double, String> sm = new TreeMap<Double, String>();
		BufferedReader br = general.readStream(pageRank);
		String line;
		int i = 0;
		while( (line = br.readLine()) != null)
		{
			int comindex = line.indexOf(",");
			String id = line.substring(0, comindex);
			double pageRank = Double.parseDouble(line.substring(comindex + 1));
			
			if(indexes[i] == Integer.parseInt(id))
			{
				if(sm.get(pageRank) == null)
				{
					sm.put(pageRank, id);
				}
				else
				{
					sm.put(pageRank, sm.get(pageRank) + "," +id);
				}
				i++;
			}
		}	
		
		//Returning top 10 pages.
		String[] pageRanks = sm.toString().replaceAll("], ", "\n").replaceAll("\\[|\\]|\\{|\\}| ", "").split("\n");
		int[] indexess = new int[10];
		for(int i1 = 0, j = 0; i1 < 10; i1++, j++)
		{
			if( pageRanks[i1].indexOf(",") != -1)
			{
				String[] commPages = pageRanks[i1].split(",");
				for(String s: commPages)
				{
					indexess[i1] = Integer.parseInt(s);
					i1++;
				}
			}
			else
			{
				indexess[i1] = Integer.parseInt(pageRanks[j]);
			}
		}
		
		return indexess;
	}


	private static int[] getCombinations(String[] query, int[][] Ids) {
		StringBuilder sb = new StringBuilder();
		int j = 1;
		for(String s: query)
		{
			if(j % 2 == 0)
			{
				switch(s.toLowerCase())
				{
					case "or" : sb.append("or" + ",");  break;
					case "not": sb.append("not" + ","); break;
					default	  : sb.append("and" + ","); break;
				}
			}
			else
			{
				sb.append(s + ",");
			}
			j++;
		}
		
		query = sb.toString().split(",");
		String nextOperator = "";
		int[] index = null;
		for(int i = 0, j1 = 0; i < query.length - 1; i += 2, j1++)
		{
			if(nextOperator == "")
			{
				index = Ids[j1];
			}
			else
			{
				switch(nextOperator)
				{
					case "or" : index = OR(index, Ids[j1]);  break;
					case "not": index = NOT(index, Ids[j1]); break;
					case "and": index = AND(index, Ids[j1]); break;
				}
			}
			nextOperator = query[i + 1];
		}
		
		return index;
	}

	private static int[] AND(int[] index, int[] is) {
		HashSet<Integer> hs = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		
		for(int i: is)
		{
			hs.add(i);
		}
		
		for(int i: index)
		{
			if(hs.contains(i))
			{
				sb.append(i + ",");
			}
		}
		String[] str = sb.toString().split(",");
		
		int[] returnIndex = new int[str.length];
		int i = 0;
		for(String s: str)
		{
			returnIndex[i] = Integer.parseInt(s);
			i++;
		}
		return returnIndex;
	}

	private static int[] NOT(int[] index, int[] is) {
		HashSet<Integer> hs = new HashSet<Integer>();
		
		for(int i: is)
		{
			hs.add(i);
		}
		
		for(int i: index)
		{
			hs.add(i);
		}
		String[] str = (String[]) hs.toArray();
		
		int[] returnIndex = new int[str.length];
		int i = 0;
		for(String s: str)
		{
			returnIndex[i] = Integer.parseInt(s);
			i++;
		}
		return returnIndex;
	}

	private static int[] OR(int[] index, int[] is) {
		HashSet<Integer> hs = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		
		for(int i: is)
		{
			hs.add(i);
		}
		
		for(int i: index)
		{
			if(!hs.contains(i))
			{
				sb.append(i + ",");
			}
		}
		String[] str = sb.toString().split(",");
		
		int[] returnIndex = new int[str.length];
		int i = 0;
		for(String s: str)
		{
			returnIndex[i] = Integer.parseInt(s);
			i++;
		}
		return returnIndex;
	}

	private static int[][] decode(byte[][] tokenSets) {
		
		int size = tokenSets.length;
		int[][] sout = new int[size][];
		int count = 0;
		
		for(byte[] s: tokenSets)
		{
			StringBuilder sb = new StringBuilder();
			for(byte s1: s)
			{
				if( s1 < 0 )
				{
					sb.append((s1) + ";");
				}
				else
				{
					sb.append(s1 + ",");
				}
			}

			sout[count] = decodeIntegers(sb.toString().split(";"));
			
			int[] ids = new int[sout.length];
			int idCount = 0, prevId = 0;
			//Count the id to get original id
			for(int sbs: sout[count])
			{
				ids[idCount] = prevId + sbs;
				prevId = ids[idCount];
				idCount++;
			}
			
			sout[count] = ids;
			count++;
		}
		
		return sout;
	}

	private static int[] decodeIntegers(String[] split) {
		int[] values = new int[split.length];
		int count = 0;
		for(String s: split)
		{
			int size = s.split(",").length;
			String[] sp = s.split(",");
			int value = 0;
			if(size == 4)
			{
				 value = ( Integer.parseInt(sp[0]) << 21 ) 
							+ ( Integer.parseInt(sp[1]) << 14 ) 
							+ ( Integer.parseInt(sp[2]) << 7 ) 
							+ ( Integer.parseInt(sp[3]) + 128 ); 
			}
			else if(size == 3)
			{
				 value = ( Integer.parseInt(sp[0]) << 14 ) 
						+ ( Integer.parseInt(sp[1]) << 7 ) 
						+ ( Integer.parseInt(sp[2]) + 128 ); 
			}
			else if(size == 2 )
			{
				 value = ( Integer.parseInt(sp[0]) << 7 ) 
						+ ( Integer.parseInt(sp[1]) + 128 ); 
			}
			else
			{
				 value = ( Integer.parseInt(sp[0]) + 128 );
			}
			
			values[count] = value;
			
			count++;
		}
		return values;
	}
	
}
