import java.io.BufferedReader;
import java.io.IOException;


public class Compression 
{

	public static String home = "/home/prashanth/Documents/SE/P3/Output/";
	public static String tokens = home + "Tokens/";
	public static String compress = home + "Compression/";
	public static String[] files = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	public static int totTokens = 0;
	public static StringBuilder token = new StringBuilder();
	public static StringBuilder Indexes = new StringBuilder();
	public static short blockSize = 6;
	public static int[] wordPointer = new int[(int)Math.ceil( 11865654 / blockSize )];
	public static byte[] postingList;
	public static int wordPointerInc = 0;
	public static StringBuilder sb_PostingList = new StringBuilder();
	public static int wordCount;
	public static String[] binValues = new String[11865654];
	//public static HashMap<Integer, byte[]> binValues = new HashMap<Integer, byte[]>();
	public static byte[][] tokenSet = new byte[11865654][];
	public static Runtime runtime = Runtime.getRuntime(); 
	
	public static void main(String args[]) throws IOException
	{
		String startTime = general.getTime();
		
		System.out.println("Started");
		compress();
		
		System.out.println("Done");
		System.out.println("Total Tokens: " + totTokens);
		System.out.println("Done. Start Time: " + startTime + " End Time: " + general.getTime());
	}
	
	public static void compress() throws IOException
	{		
		
		for(String file: files)
		{
			BufferedReader br = general.readStream(tokens + file +".txt_sorted.txt");
			
			
			String line;
			String word = "";
			
			short EQIndex = 0;
			int count = 1;
			String[] tempWords = new String[blockSize];
			int curBlock = 0;
			while( (line = br.readLine()) != null)
			{
				count++;
				line = line.trim();
				
				EQIndex = (short) line.indexOf("=");
				
				word = line.substring(0, EQIndex);
				if( curBlock == blockSize)
				{
					dictionaryCompression(tempWords);		
					curBlock = 0;
										
				}
				//System.out.println(word);
				tempWords[curBlock] = word;
				
				curBlock++;
				
				line = line.substring(EQIndex + 1).trim();
				//String[] indexArray = line.split(",");
					
				postingListCompression(line);
				
				if(wordCount % 10000 == 0){
					System.out.println(wordCount + " " + word + " " + file + " " + general.getTime());
					System.out.println("max memory: " + runtime.maxMemory() / (1024 * 1024)); 	
					System.out.println("allocated memory: " + runtime.totalMemory() / (1024 * 1024)); 
					System.out.println("free memory: " + runtime.freeMemory() / (1024 * 1024)); 
				}
				
			}
			
			totTokens += count; 
		}
		
		general.createFile(compress + "tokensAsLine_4.txt", token.toString(), true);
	}
	
	//Dictionary Compression take place here
	public static void dictionaryCompression(String[] tempWords)	
	{
		short len = (short) tempWords[0].length();
		for(int j = 0; j < len; j++)
		{
			boolean found = true;
			
			//Implementing blocks to accomidate n words in a single block.
			for(int i = 1; i < blockSize; i++)
			{
				if(  !tempWords[i].substring(0,j + 1).equals(tempWords[0].substring(0,j + 1)) )
				{
					found = false;
					//System.out.println(i);
					break;
				}
			}
			
			if(!found || j == len - 1)
			{
				//Implementing Front Coding
				wordPointer[wordPointerInc] = token.length();
				
				//Extracting common phrased
				String comm = j + tempWords[0].substring(0, j);
				
				for(int i = 0; i < blockSize; i++)
				{
				//	System.out.println(( tempWords[i].length() - j ) + tempWords[i].substring(j));
					comm += ( tempWords[i].length() - j ) + tempWords[i].substring(j);
				}
				//System.out.println("Term Doc= " + comm);
				
				//Appending after front Coding
				token.append(comm);
				wordPointerInc++;
				break;
			}
		}
	}
	
	//Posting list compression takes place here
	public static void  postingListCompression(String line)
	{
		wordCount++;
		int prevDocId = 0;
		int curDocId = 0;
		StringBuilder sb = new StringBuilder();
		String str[] = line.split(",");
		
		//Difference b/w previous id to current id
		for(String s: str)
		{
			if( s != null && !s.isEmpty())
			{
				curDocId = Integer.parseInt(s);
				
				// Difference
				int docIdDiff = curDocId - prevDocId;
				sb.append(docIdDiff + ",");
				prevDocId = curDocId;
			}
			
		}
		tokenSet[wordCount] = encode(sb.toString());
	}
	
	//Byte Encoding
	public static byte[] encode(String string)
	{
		String[] sb = string.split(",");
		
		byte[] btr = new byte[sb.length * 4];
		int byteCount = 0;
		//System.out.println(sb.length);
		for(String s: sb)
		{
			byte[] tempBytes = intToByteArray(Integer.parseInt(s));
			for(byte t: tempBytes)
			{
				if( t != 0)
				{
					btr[byteCount] = t;
					byteCount++;
				}
			}
		}
		
		byte[] btr1 = new byte[byteCount];
		for(int i = 0; i < byteCount; i++)
		{
			btr1[i] = btr[i];
		}
		
		return btr1;
		
	}
	
	//Identify front digit for least significant bit and return value
	public static final byte[] intToByteArray(int value) {
		if(value < 128)
		{
			return new byte[] { (byte)(128 + value) };
		}
		else if(value >= 128 && value < 16384 )
		{
			return new byte[] { (byte)(value >>> 7), (byte)(128 + value) };
		}
		else if(value >= 16384 && value < 2097152 )
		{
			return new byte[] { (byte)(value >>> 14) ,(byte)(value >>> 7), (byte)(128 + value) };
		}
		else if(value >= 2097152)
		{
			return new byte[] { (byte)(value >>> 21) ,(byte)(value >>> 14) ,(byte)(value >>> 7), (byte)(128 + value) };
		}
		return null;
	}
	
}
