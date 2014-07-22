import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class tokenizer {
  public static String outPath = "/home/prashanth/Documents/SE/P3/Output/Tokens/AllTerms_4.txt";
  public static StringBuilder finalBuilder = new StringBuilder();
  public static int count = 0;
  public static int noOfThreads;
  public static HashSet<String> wordSet = new HashSet<String>();
  public static HashSet<String> specialChars = new HashSet<String>();
  static String content = null;
  static String docID = null;
  static StringBuilder BlockContent = new StringBuilder();
  static String docId;
  
  public static void splitTok(List<String> lPage) throws IOException, InterruptedException{
	  outPath = xml_parser.outputFolderPath;
	  count++;
	  
	  //System.out.println("Here " + count);
	  
	  myThread1 mt1 = new myThread1();
	  myThread2 mt2 = new myThread2();
	  myThread3 mt3 = new myThread3();
	  myThread4 mt4 = new myThread4();
	  myThread5 mt5 = new myThread5();
	  myThread6 mt6 = new myThread6();
	  myThread7 mt7 = new myThread7();
	  myThread8 mt8 = new myThread8();
	  
	  for(int i = 0; i < lPage.size(); i += 2 ){
		  if(i % 8 == 0){
			  myThread8.content = lPage.get(i + 1);
			  myThread8.docID = lPage.get(i);
			  mt8.run();
		  } 
		  else if(i % 7 == 0){
			  myThread7.content = lPage.get(i + 1);
			  myThread7.docID = lPage.get(i);
			  mt7.run();
		  }
		  else if(i % 6 == 0){
			  myThread6.content = lPage.get(i + 1);
			  myThread6.docID = lPage.get(i);
			  mt6.run();
		  }
		  else if(i % 5 == 0){
			  myThread5.content = lPage.get(i + 1);
			  myThread5.docID = lPage.get(i);
			  mt5.run();
		  }
		  else if(i % 4 == 0){
			  myThread2.content = lPage.get(i + 1);
			  myThread2.docID = lPage.get(i);
			  mt2.run();
		  } 
		  else if(i % 3 == 0){
			  myThread3.content = lPage.get(i + 1);
			  myThread3.docID = lPage.get(i);
			  mt3.run();
		  }
		  else if(i % 2 == 0){
			  myThread4.content = lPage.get(i + 1);
			  myThread4.docID = lPage.get(i);
			  mt4.run();
		  }
		  else{
			  myThread1.content = lPage.get(i + 1);
			  myThread1.docID = lPage.get(i);
			  mt1.run();
		  }
	  }
	  
	  if(count % 1000 == 0)
		  System.out.println("Count:"+count+" "+ " Time: " + getTime());
  }

  public static void process(String str, String docid) throws IOException{
	  str = str.toLowerCase();
	  str = tokens(str);
	  str = sort(str,  false);
	  BlockContent.append(docid+','+str+'\n');
	  if(count % 1000 == 0)
		  writefn();
//	  System.out.println(str);
//	  System.exit(0);
  }
  
  public static String sort(String str, boolean isFinal) throws IOException{
	String[] strArray ;
	StringBuilder sb = new StringBuilder();
	strArray = str.split(" ");
	Arrays.sort(strArray);
	String prev = " ";
	String next = "";
	
	int len = strArray.length;
	for(int i = 0; i < len; i++)
	{
		next = strArray[i].trim();
		if(!wordSet.contains(next))
		if(!prev.equalsIgnoreCase(next) && !next.isEmpty() && next != "\n" ){
			sb.append(next+',');
		}
		prev = strArray[i];
	}	
	return sb.toString();
}
  
  static void getStopWords() throws IOException {
	
	
	  BufferedReader br = null;
	  String line;
	try {
		br = new BufferedReader(new FileReader("/media/prashanth/LENOVO/SearchEngines/P1/Data/Outputs/stopwords.txt"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	  try {
		while((line = br.readLine()) != null )
		  {
			  wordSet.add(line.trim());
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
}

  public static String tokens(String str){
	  StringBuilder sb = new StringBuilder();
	  sb.append("\n");
	  str = str.replaceAll("[\\W]|\t|[0-9]|\\_", " ").replaceAll("\\s+", " ");
	  return str;
  }
  
  public static void writefn() throws IOException{
	  BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath, true), "utf-8"));
			bw.write(BlockContent.toString());
			System.out.println("All Files Written: " + count);
		} catch (Exception e) {
			System.out.println("Error in 3: " + e + "Filename" + outPath);
		}
		finally
		{
		    try
		    {
		        if ( bw != null)
		        	bw.close( );
		    }
		    catch ( IOException e)
		    {
		    	System.out.println("Error in 4: " + e);
		    }
		}
		BlockContent = new StringBuilder();
  }	
 
  public static String getTime(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss SSS");
		return ft.format(dNow);
	}

}

