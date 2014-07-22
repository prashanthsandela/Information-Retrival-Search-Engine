import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class myThread4 extends Thread {
	static String content, docID;
	static StringBuilder BlockContent = new StringBuilder();
	public static int count = 0;
	
	public static StringBuilder SBa = new StringBuilder();
	public static StringBuilder SBb = new StringBuilder();
	public static StringBuilder SBc = new StringBuilder();
	public static StringBuilder SBd = new StringBuilder();
	public static StringBuilder SBe = new StringBuilder();
	public static StringBuilder SBf = new StringBuilder();
	public static StringBuilder SBg = new StringBuilder();
	public static StringBuilder SBh = new StringBuilder();
	public static StringBuilder SBi = new StringBuilder();
	public static StringBuilder SBj = new StringBuilder();
	public static StringBuilder SBk = new StringBuilder();
	public static StringBuilder SBl = new StringBuilder();
	public static StringBuilder SBm = new StringBuilder();
	public static StringBuilder SBn = new StringBuilder();
	public static StringBuilder SBo = new StringBuilder();
	public static StringBuilder SBp = new StringBuilder();
	public static StringBuilder SBq = new StringBuilder();
	public static StringBuilder SBr = new StringBuilder();
	public static StringBuilder SBs = new StringBuilder();
	public static StringBuilder SBt = new StringBuilder();
	public static StringBuilder SBu = new StringBuilder();
	public static StringBuilder SBv = new StringBuilder();
	public static StringBuilder SBw = new StringBuilder();
	public static StringBuilder SBx = new StringBuilder();
	public static StringBuilder SBy = new StringBuilder();
	public static StringBuilder SBz = new StringBuilder();
	public static StringBuilder SBOthers = new StringBuilder();
	public static int counta = 1, countb = 1, countc = 1, countd = 1, counte = 1, countf = 1, countg = 1, counth = 1, counti = 1, countj = 1, countk = 1;
	public static int countl = 1, countm = 1, countn = 1, counto = 1, countp = 1, countq = 1, countr = 1, counts = 1, countt = 1, countu = 1, countv = 1;
	public static int countw = 1, countx = 1, county = 1, countz = 1, countoo = 1;
	
	public void run(){
		try {
			process(content, docID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  public static void process(String str, String docid) throws IOException{ 
		  str = str.toLowerCase();
		  str = tokens(str);
		  str = sort(docid, str,  false);
		  count++;
		  BlockContent.append(str);
		  if(count % 1000 == 0)
			  writefn();
	  }
	  
	  public static String sort(String docid, String str, boolean isFinal) throws IOException{
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
				if(!tokenizer.wordSet.contains(next))
				if(!prev.equalsIgnoreCase(next) && !next.isEmpty() && next != "\n" ){
					sb.append(next+","+docid+"\n");
					fillSBs(next + ' ' + docid);
				}
				prev = strArray[i];
			}	
			return sb.toString();
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
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tokenizer.outPath, true), "utf-8"));
					bw.write(BlockContent.toString());
					System.out.println("All Files Written: " + count);
				} catch (Exception e) {
					System.out.println("Error in 3: " + e + "Filename" + tokenizer.outPath);
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
		  
		  synchronized public static void fillSBs(String str) throws IOException
		  {
			  switch(str.substring(0,1))
			  {
				  case "a": SBa.append(str).append('\n');counta++;break;
				  case "b": SBb.append(str).append('\n');countb++;break;
				  case "c": SBc.append(str).append('\n');countc++;break;
				  case "d": SBd.append(str).append('\n');countd++;break;
				  case "e": SBe.append(str).append('\n');counte++;break;
				  case "f": SBf.append(str).append('\n');countf++;break;
				  case "g": SBg.append(str).append('\n');countg++;break;
				  case "h": SBh.append(str).append('\n');counth++;break;
				  case "i": SBi.append(str).append('\n');counti++;break;
				  case "j": SBj.append(str).append('\n');countj++;break;
				  case "k": SBk.append(str).append('\n');countk++;break;
				  case "l": SBl.append(str).append('\n');countl++;break;
				  case "m": SBm.append(str).append('\n');countm++;break;
				  case "n": SBn.append(str).append('\n');countn++;break;
				  case "o": SBo.append(str).append('\n');counto++;break;
				  case "p": SBp.append(str).append('\n');countp++;break;
				  case "q": SBq.append(str).append('\n');countq++;break;
				  case "r": SBr.append(str).append('\n');countr++;break;
				  case "s": SBs.append(str).append('\n');counts++;break;
				  case "t": SBt.append(str).append('\n');countt++;break;
				  case "u": SBu.append(str).append('\n');countu++;break;
				  case "v": SBv.append(str).append('\n');countv++;break;
				  case "w": SBw.append(str).append('\n');countw++;break;
				  case "x": SBx.append(str).append('\n');countx++;break;
				  case "y": SBy.append(str).append('\n');county++;break;
				  case "z": SBz.append(str).append('\n');countz++;break;
				  default : SBOthers.append(str).append('\n');countoo++;break;
			  }
			  
			  if(counta > 300000) { general.createFile(createPages.tokens + "Tokens/a.txt", sortString(SBa.toString()), true); counta = 1; SBa = new StringBuilder();}
			  if(countb > 300000) { general.createFile(createPages.tokens + "Tokens/b.txt", sortString(SBb.toString()), true); countb = 1; SBb = new StringBuilder(); }
			  if(countc > 300000) { general.createFile(createPages.tokens + "Tokens/c.txt", sortString(SBc.toString()), true); countc = 1; SBc = new StringBuilder(); }
			  if(countd > 300000) { general.createFile(createPages.tokens + "Tokens/d.txt", sortString(SBd.toString()), true); countd = 1; SBd = new StringBuilder(); }
			  if(counte > 300000) { general.createFile(createPages.tokens + "Tokens/e.txt", sortString(SBe.toString()), true); counte = 1; SBe = new StringBuilder(); }
			  if(countf > 300000) { general.createFile(createPages.tokens + "Tokens/f.txt", sortString(SBf.toString()), true); countf = 1; SBf = new StringBuilder(); }
			  if(countg > 300000) { general.createFile(createPages.tokens + "Tokens/g.txt", sortString(SBg.toString()), true); countg = 1; SBg = new StringBuilder(); }
			  if(counth > 300000) { general.createFile(createPages.tokens + "Tokens/h.txt", sortString(SBh.toString()), true); counth = 1; SBh = new StringBuilder(); }
			  if(counti > 300000) { general.createFile(createPages.tokens + "Tokens/i.txt", sortString(SBi.toString()), true); counti = 1; SBi = new StringBuilder(); }
			  if(countj > 300000) { general.createFile(createPages.tokens + "Tokens/j.txt", sortString(SBj.toString()), true); countj = 1; SBj = new StringBuilder(); }
			  if(countk > 300000) { general.createFile(createPages.tokens + "Tokens/k.txt", sortString(SBk.toString()), true); countk = 1; SBk = new StringBuilder(); }
			  if(countl > 300000) { general.createFile(createPages.tokens + "Tokens/l.txt", sortString(SBl.toString()), true); countl = 1; SBl = new StringBuilder(); }
			  if(countm > 300000) { general.createFile(createPages.tokens + "Tokens/m.txt", sortString(SBm.toString()), true); countm = 1; SBm = new StringBuilder(); }
			  if(countn > 300000) { general.createFile(createPages.tokens + "Tokens/n.txt", sortString(SBn.toString()), true); countn = 1; SBn = new StringBuilder(); }
			  if(counto > 300000) { general.createFile(createPages.tokens + "Tokens/o.txt", sortString(SBo.toString()), true); counto = 1; SBo = new StringBuilder(); }
			  if(countp > 300000) { general.createFile(createPages.tokens + "Tokens/p.txt", sortString(SBp.toString()), true); countp = 1; SBp = new StringBuilder(); }
			  if(countq > 300000) { general.createFile(createPages.tokens + "Tokens/q.txt", sortString(SBq.toString()), true); countq = 1; SBq = new StringBuilder(); }
			  if(countr > 300000) { general.createFile(createPages.tokens + "Tokens/r.txt", sortString(SBr.toString()), true); countr = 1; SBr = new StringBuilder(); }
			  if(counts > 300000) { general.createFile(createPages.tokens + "Tokens/s.txt", sortString(SBs.toString()), true); counts = 1; SBs = new StringBuilder(); }
			  if(countt > 300000) { general.createFile(createPages.tokens + "Tokens/t.txt", sortString(SBt.toString()), true); countt = 1; SBt = new StringBuilder(); }
			  if(countu > 300000) { general.createFile(createPages.tokens + "Tokens/u.txt", sortString(SBu.toString()), true); countu = 1; SBu = new StringBuilder(); }
			  if(countv > 300000) { general.createFile(createPages.tokens + "Tokens/v.txt", sortString(SBv.toString()), true); countv = 1; SBv = new StringBuilder(); }
			  if(countw > 300000) { general.createFile(createPages.tokens + "Tokens/w.txt", sortString(SBw.toString()), true); countw = 1; SBw = new StringBuilder(); }
			  if(countx > 300000) { general.createFile(createPages.tokens + "Tokens/x.txt", sortString(SBx.toString()), true); countx = 1; SBx = new StringBuilder(); }
			  if(county > 300000) { general.createFile(createPages.tokens + "Tokens/y.txt", sortString(SBy.toString()), true); county = 1; SBy = new StringBuilder(); }
			  if(countz > 300000) { general.createFile(createPages.tokens + "Tokens/z.txt", sortString(SBz.toString()), true); countz = 1; SBz= new StringBuilder(); }
			  if(countoo > 300000) { general.createFile(createPages.tokens + "Tokens/oo.txt", sortString(SBOthers.toString()), true); countoo = 1; SBOthers = new StringBuilder(); }
		  }
		
		public static void writeAll(){
			general.createFile(createPages.tokens + "Tokens/a.txt", sortString(SBa.toString()), true);
			general.createFile(createPages.tokens + "Tokens/b.txt", sortString(SBb.toString()), true);
			general.createFile(createPages.tokens + "Tokens/c.txt", sortString(SBc.toString()), true);
			general.createFile(createPages.tokens + "Tokens/d.txt", sortString(SBd.toString()), true);
			general.createFile(createPages.tokens + "Tokens/e.txt", sortString(SBe.toString()), true);
			general.createFile(createPages.tokens + "Tokens/f.txt", sortString(SBf.toString()), true);
			general.createFile(createPages.tokens + "Tokens/g.txt", sortString(SBg.toString()), true);
			general.createFile(createPages.tokens + "Tokens/h.txt", sortString(SBh.toString()), true);
			general.createFile(createPages.tokens + "Tokens/i.txt", sortString(SBi.toString()), true);
			general.createFile(createPages.tokens + "Tokens/h.txt", sortString(SBh.toString()), true);
			general.createFile(createPages.tokens + "Tokens/j.txt", sortString(SBj.toString()), true);
			general.createFile(createPages.tokens + "Tokens/k.txt", sortString(SBk.toString()), true);
			general.createFile(createPages.tokens + "Tokens/l.txt", sortString(SBl.toString()), true);
			general.createFile(createPages.tokens + "Tokens/m.txt", sortString(SBm.toString()), true);
			general.createFile(createPages.tokens + "Tokens/n.txt", sortString(SBn.toString()), true);
			general.createFile(createPages.tokens + "Tokens/o.txt", sortString(SBo.toString()), true);
			general.createFile(createPages.tokens + "Tokens/p.txt", sortString(SBp.toString()), true);
			general.createFile(createPages.tokens + "Tokens/q.txt", sortString(SBq.toString()), true);
			general.createFile(createPages.tokens + "Tokens/r.txt", sortString(SBr.toString()), true);
			general.createFile(createPages.tokens + "Tokens/s.txt", sortString(SBs.toString()), true);
			general.createFile(createPages.tokens + "Tokens/t.txt", sortString(SBt.toString()), true);
			general.createFile(createPages.tokens + "Tokens/u.txt", sortString(SBu.toString()), true);
			general.createFile(createPages.tokens + "Tokens/v.txt", sortString(SBv.toString()), true);
			general.createFile(createPages.tokens + "Tokens/w.txt", sortString(SBw.toString()), true);
			general.createFile(createPages.tokens + "Tokens/x.txt", sortString(SBx.toString()), true);
			general.createFile(createPages.tokens + "Tokens/y.txt", sortString(SBy.toString()), true);
			general.createFile(createPages.tokens + "Tokens/z.txt", sortString(SBz.toString()), true);
			general.createFile(createPages.tokens + "Tokens/oo.txt", sortString(SBOthers.toString()), true);

		}
		
		public static String sortString(String str)
		{
			String[] strArray ;
			StringBuilder sb = new StringBuilder();
			strArray = str.split("\n");
			Arrays.sort(strArray);
			String prev = " ";
			String next = "";
			
			int len = strArray.length;
			for(int i = 0; i < len; i++)
			{
				next = strArray[i].trim();
				if(!prev.equalsIgnoreCase(next) && !next.isEmpty() && next != "\n"){
					sb.append(next + "\n");
				}
				prev = strArray[i];
			}	
			return sb.toString();
		}
	
}
