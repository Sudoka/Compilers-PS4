import java.util.ArrayList;
import java.util.List;


public abstract class CuProgr {
	protected String text = "";
	protected String ctext = "";
	@Override public String toString() {
		return text;
	}
	//added for project 4
	protected List<String> newVars = new ArrayList<String>();
	//new functions
	public void add_prg(CuProgr p){}
	public void add_lastStat(CuStat s) {}
	
	public void calculateType(CuContext context) throws NoSuchTypeException {}
	
	public String toC(ArrayList<String> localVars) {
		return ctext;
	}
}

class FullPrg extends CuProgr {
	List<CuProgr> elements = new ArrayList<CuProgr>();
	CuStat s;
	public void add_prg(CuProgr p){
		elements.add(p);
	}
	public void add_lastStat(CuStat s) {
		this.s = s;
	}
	@Override public String toC(ArrayList<String> localVars) {
		String temp_str = "";
		for (CuProgr cp : elements) {
			temp_str += cp.toC(localVars);
			for(String str : cp.newVars) {
				if (!super.newVars.contains(str)) {
					super.newVars.add(str);
				}
			}
		}
		//need to call toC first before using newVars		
		temp_str += s.toC(localVars);
		for(String str : s.newVars) {
			if (!super.newVars.contains(str)) {
				super.newVars.add(str);
			}
		}
    	for (String str : super.newVars) {
    		super.ctext += "void * " + str + " = NULL;\n";
    		temp_str = temp_str.replaceAll("void \\* " + str + " = NULL;\n", "");
    	}
    	super.ctext += temp_str;
		return super.ctext;
	}
	public void calculateType(CuContext context) throws NoSuchTypeException {
		int i = 0;
		for (CuProgr prg : elements) { 
			//System.out.println(prg.toString());
		}
		for (CuProgr prg1 : elements) {
			//System.out.println(i + ":" + prg1.toString());
			i++;
			prg1.calculateType(context);
			//System.out.println("finished one");
		}
		HReturn re = this.s.calculateType(context);
Helper.P("program return, type is " + re.tau.toString());
		if (!re.tau.isSubtypeOf(new Iter(CuType.string)) || (re.b== false)) {
			throw new NoSuchTypeException();
		}
	}
}

class ClassPrg extends CuProgr {
	CuClass c; 
	public ClassPrg (CuClass c) {
		//System.out.println("in class program constructor");
		this.c = c;
		super.text = c.toString();
		//System.out.println("exit class program constructor");
	}
	@Override public void calculateType(CuContext context) throws NoSuchTypeException {
		//Yinglei's guess it that depends on its detailed type (class/interface),
		//it will use the correct function and change the context properly
		//System.out.println("in class program");
		context.mergeVariable();
		c.calculateType(context);
		//System.out.println("in class program, end");
	}
}

class FunPrg extends CuProgr {
	//used for printing in project 2
	List<String> fun; // need to change
	//used for type checking in project 3
	String name;
	CuTypeScheme typeScheme;
	CuStat statement;

	public FunPrg(CuVvc var, CuTypeScheme ts, CuStat s) {
		//System.out.println("in fun program constructor, begin");
		//String t = String.format("fun %s %s %s", var.toString(), ts.toString(), s.toString());
		//fun.add(t);
		this.name = var.toString();
		this.typeScheme = ts;
		this.statement = s;
		//System.out.println("in fun program constructor, end");
	}
	
	@Override public String toString() {
		return Helper.printList("", fun, "", "");
	}
	@Override  public void calculateType(CuContext context) throws NoSuchTypeException {
Helper.P("in func program " + name);
		//System.out.println(this.statement.toString());
		//update the function context
		context.mergeVariable();
		if (context.mFunctions.containsKey(this.name)) {
			throw new NoSuchTypeException();
		}
		context.updateFunction(this.name, this.typeScheme);
		//type check typeschemes and statements
		this.typeScheme.calculateType(context);
		CuContext temp_context = new CuContext (context);
		temp_context.updateKc(this.typeScheme.data_kc);
		for(String cur_str : this.typeScheme.data_tc.keySet())
		{
			if(context.mVariables.containsKey(cur_str)){
				throw new NoSuchTypeException();
			}
		}
		temp_context.updateMutType(this.typeScheme.data_tc);
		HReturn re = this.statement.calculateType(temp_context);
		if (re.b == false) {
			throw new NoSuchTypeException();
		}
		if (!re.tau.isSubtypeOf(this.typeScheme.data_t)) {
			throw new NoSuchTypeException();
		}
	}
}

class StatPrg extends CuProgr {
	CuStat stat;
	public StatPrg(CuStat s) {
		//System.out.println("in statement program constructor");
		this.stat = s;
		super.text = s.toString();
	}
	@Override public String toC(ArrayList<String> localVars) {
		super.ctext = stat.toC(localVars);
		super.newVars = stat.newVars;
		return super.ctext;
	}
	@Override public void calculateType(CuContext context) throws NoSuchTypeException {
		//System.out.println("in statement program");
		HReturn re = stat.calculateType(context);
		//System.out.println("before checking return type, type is "+re.tau.id);
		CuType dtype= new Iter(CuType.string);
		//System.out.println("created type iterable of string ");
		//final tau should be a subtype of iterable string
		if (!re.tau.isSubtypeOf(dtype)) {
			//System.out.println("type is not iterable of string");
			throw new NoSuchTypeException();
		}
	}
	
}