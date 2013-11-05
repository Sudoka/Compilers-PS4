import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.*;


public class Helper {
	//eg. aaa, integer
	protected static HashMap<String, String> cVarType = new HashMap<String, String>();
	protected static HashMap<String, String> iterType = new HashMap<String, String>();
	protected static HashMap<String, String> cFunType = new HashMap<String, String>();
	protected static HashMap<String, List<String>> funGenType = new HashMap<String, List<String>>();
	protected static ArrayList<String>		 funArgList=new ArrayList<String>();
	protected static HashMap<String, String> cClassSuper=new HashMap<String, String>();
	protected static HashMap<String, String> cStr = new HashMap<String, String>();
	
	static int one = 97;
	static int two = 97;
	static int three = 97;
    protected static String getVarName() {
    	String first = Character.toString((char)one);
    	String second = Character.toString((char)two);
    	String third = Character.toString((char)three);
    	String out = first+second+third;
    	three = three + 1;
    	if (three==123) {
    		three = 97;
    		two = two + 1;
    		if (two==123) {
    			two = 97;
    			one = one + 1;
    			if (one==123) 
    				System.out.println("run out of names");
    		}
    	}
    	return "aaa"+out;
    }
    
	protected static <T> String printList(String l, List<T> es, String r, String i) {
		if (es == null) return " "+l + " " + r+" ";
		String s = " "+l ;
		for (T e : es) {
			s += e.toString() + i + " ";
		}
		int j = s.lastIndexOf(i+" ");
		if (j > 1) s = s.substring(0, j);
		s +=r;
		return s;
	}
	
	protected static <T, K> String printMap(String l, Map<T,K> es, String r, String i) {
		if (es == null) return " "+l + " " + r+" ";
		String s = " "+l + " ";
		for (Entry<T, K> e : es.entrySet()) {
			s += e.getKey().toString()+" : "+e.getValue().toString() + " "+i + " ";
		}
		int j = s.lastIndexOf(i+" ");
		if (j > 1) s = s.substring(0, j);
		s +=( r+" " );
		return s;
	}
	
	protected static String listFlatten(List<CuStat> cs) {
		if(cs == null) return " ";
		String s=" ";
		if (cs == null || cs.size() == 0) return s;
		for (CuStat t : cs) {
			if (t instanceof Stats) s+=listFlatten((ArrayList<CuStat>) ((Stats)t).al);
			else s+= t.toString() + " ";
		}
		return s;
	}
	protected static <T> boolean equals (Collection<T> x, Collection<T> y) {
		for (T t1 : x) {
			boolean t1E = false;
			for (T t2 : y) {
				if (!t1E && t1.equals(t2))  t1E = true;
			}
			if (!t1E) return false;
		}
		return x.size()==y.size();
	}
	
	protected static void P(Object s) {
		//System.out.println(s.toString());
	}
	protected static void P(String s, Object...args) {
		//System.out.println(String.format(s, args));
	}
	/* thoughts coming, to implement later on */
	protected static void ToDo(String comment){
	}
	
	/** Get the current line number.
	 * @return int - Current line number.
	 */
	public static String getLineInfo() {
        String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();            
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
	    return fullClassName + "." + methodName + "(): line " + lineNumber;
	}
	
	protected static CuType getTypeForIterable(String s){
		CuType type;
		Pattern p = Pattern.compile("[A-Za-z ]*< ?([A-Za-z0-9]*)");		//Iterable  < Integer  < >  >
		Matcher m = p.matcher(s);
		m.find();
		//System.out.println(m.group(1));
		if (m.group(1).length() == 1)
			type = new VTypePara(m.group(1));	
		else
			type = new VClass(m.group(1), new ArrayList<CuType>());
		return type;
	}
}
