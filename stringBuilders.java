import java.io.IOException;
import java.util.Arrays;

class StringBuilders{
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
		  
		  if(counta > 50000) { general.createFile(createPages.tokens + "Tokens/a.txt", sortString(SBa.toString()), true); counta = 1; }
		  if(countb > 50000) { general.createFile(createPages.tokens + "Tokens/b.txt", sortString(SBb.toString()), true); countb = 1; }
		  if(countc > 50000) { general.createFile(createPages.tokens + "Tokens/c.txt", sortString(SBc.toString()), true); countc = 1; }
		  if(countd > 50000) { general.createFile(createPages.tokens + "Tokens/d.txt", sortString(SBd.toString()), true); countd = 1; }
		  if(counte > 50000) { general.createFile(createPages.tokens + "Tokens/e.txt", sortString(SBe.toString()), true); counte = 1; }
		  if(countf > 50000) { general.createFile(createPages.tokens + "Tokens/f.txt", sortString(SBf.toString()), true); countf = 1; }
		  if(countg > 50000) { general.createFile(createPages.tokens + "Tokens/g.txt", sortString(SBg.toString()), true); countg = 1; }
		  if(counth > 50000) { general.createFile(createPages.tokens + "Tokens/h.txt", sortString(SBh.toString()), true); counth = 1; }
		  if(counti > 50000) { general.createFile(createPages.tokens + "Tokens/i.txt", sortString(SBi.toString()), true); counti = 1; }
		  if(counth > 50000) { general.createFile(createPages.tokens + "Tokens/h.txt", sortString(SBh.toString()), true); counth = 1; }
		  if(countj > 50000) { general.createFile(createPages.tokens + "Tokens/j.txt", sortString(SBj.toString()), true); countj = 1; }
		  if(countk > 50000) { general.createFile(createPages.tokens + "Tokens/k.txt", sortString(SBk.toString()), true); countk = 1; }
		  if(countl > 50000) { general.createFile(createPages.tokens + "Tokens/l.txt", sortString(SBl.toString()), true); countl = 1; }
		  if(countm > 50000) { general.createFile(createPages.tokens + "Tokens/m.txt", sortString(SBm.toString()), true); countm = 1; }
		  if(countn > 50000) { general.createFile(createPages.tokens + "Tokens/n.txt", sortString(SBn.toString()), true); countn = 1; }
		  if(counto > 50000) { general.createFile(createPages.tokens + "Tokens/o.txt", sortString(SBo.toString()), true); counto = 1; }
		  if(countp > 50000) { general.createFile(createPages.tokens + "Tokens/p.txt", sortString(SBp.toString()), true); countp = 1; }
		  if(countq > 50000) { general.createFile(createPages.tokens + "Tokens/q.txt", sortString(SBq.toString()), true); countq = 1; }
		  if(countr > 50000) { general.createFile(createPages.tokens + "Tokens/r.txt", sortString(SBr.toString()), true); countr = 1; }
		  if(counts > 50000) { general.createFile(createPages.tokens + "Tokens/s.txt", sortString(SBs.toString()), true); counts = 1; }
		  if(countt > 50000) { general.createFile(createPages.tokens + "Tokens/t.txt", sortString(SBt.toString()), true); countt = 1; }
		  if(countu > 50000) { general.createFile(createPages.tokens + "Tokens/u.txt", sortString(SBu.toString()), true); countu = 1; }
		  if(countv > 50000) { general.createFile(createPages.tokens + "Tokens/v.txt", sortString(SBv.toString()), true); countv = 1; }
		  if(countw > 50000) { general.createFile(createPages.tokens + "Tokens/w.txt", sortString(SBw.toString()), true); countw = 1; }
		  if(countx > 50000) { general.createFile(createPages.tokens + "Tokens/x.txt", sortString(SBx.toString()), true); countx = 1; }
		  if(county > 50000) { general.createFile(createPages.tokens + "Tokens/y.txt", sortString(SBy.toString()), true); county = 1; }
		  if(countz > 50000) { general.createFile(createPages.tokens + "Tokens/z.txt", sortString(SBz.toString()), true); countz = 1; }
		  if(countoo > 50000) { general.createFile(createPages.tokens + "Tokens/oo.txt", sortString(SBOthers.toString()), true); countoo = 1; }
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