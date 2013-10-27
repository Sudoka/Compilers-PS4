import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class CuStat {
	protected String text = "";
	protected String ctext = "";
	//added for project 4
	protected List<String> newVars = new ArrayList<String>();
	@Override public String toString() {
		return text;
	}
	public String toC() {
		return ctext;
	}
	public void add (CuStat st){}
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		
		HReturn re = new HReturn();
		return re;
	}
}

class AssignStat extends CuStat{
	private CuVvc var;
	private CuExpr ee;
	public AssignStat (CuVvc t, CuExpr e) {
		var = t;
		ee = e;
		super.text = var.toString() + " := " + ee.toString() + " ;";
		super.ctext = ee.construct();
		super.ctext += "void * " + var.toString() +";\n";
		super.ctext += var.toString() + " = &" + ee.toC() + ";\n";
		super.newVars.add(var.toString());
	}
	
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//System.out.println("In assig start");
		//System.out.println("var="+var.toString() + " expr="+ ee.toString());
		//check var is in immutable, type check fails
		if (context.inVar(var.toString())) {
			throw new NoSuchTypeException();
		}
		//whenever we calculate expr type, we use a temporary context with merged mutable and
		//immutable variables
		CuContext tcontext = new CuContext (context);
		tcontext.mergeVariable();
		//System.out.println("In assig stat, before expr check");
		CuType exprType = ee.calculateType(tcontext);
		//System.out.println("In assig stat, after expr check");
		context.updateMutType(var.toString(), exprType);
		HReturn re = new HReturn();
		re.b = false;
		re.tau = CuType.bottom;
		//System.out.println("In assignment statement end");
		return re;
	}
}

class ForStat extends CuStat{
	private CuVvc var;
	private CuExpr e;
	private CuStat s1;
	public ForStat(CuVvc v, CuExpr ee, CuStat ss) {
		var = v;
		e = ee;
		s1 = ss;
		super.text = "for ( " + var + " in " + e.toString() + " ) " + s1.toString();
		super.ctext = e.construct();
		String iter_name = Helper.getVarName();
		super.ctext += "int " + iter_name + ";\n";
		super.ctext += "for (" + iter_name + "=0; " + iter_name + "<" + e.toC() + ".size;" + iter_name + "++) {\n";
		super.ctext += "void * " + var.toString() + "=" + e.toC() + ".value[" + iter_name + "];\n";
		super.ctext += s1.toC() + "}\n";
	}
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//whenever we calculate expr type, we use a temporary context with merged mutable and
		//immutable variables
		//System.out.println("in for stat, begin");
		CuContext tcontext = new CuContext (context);
		tcontext.mergeVariable();		
    	//check whether e is an iterable of tau
    	CuType eType = e.calculateType(tcontext);
Helper.P(String.format("FOR %s is %s<%s>", e, eType, eType.map));

 		Boolean flag = eType.isIterable();
    	if (flag != true) {
    		throw new NoSuchTypeException();
    	}
    	//eType.type = Helper.getTypeForIterable(eType.toString());
    	//var can't appear in mutable or immutable variables
    	if (context.inMutVar(this.var.toString()) || context.inVar(this.var.toString())) {
    		throw new NoSuchTypeException();
    	}
    	//System.out.println("etype is " + eType.toString());
    	CuType iter_type = eType.type;
    	//System.out.println("variable type is " + iter_type.id);
    	CuContext s_context = new CuContext(context);
    	s_context.updateMutType(this.var.toString(), iter_type);
    	HReturn re = s1.calculateType(s_context);
    	//System.out.println("return type is " + re.tau.id);
    	
    	//type weakening to make it type check
    	context.weakenMutType(s_context);
		
		re.b = false;
		//System.out.println("in for stat, end");
		return re;
	}
}

class IfStat extends CuStat{
	private CuExpr e;
	private CuStat s1=null;
	private CuStat s2=null;
	public IfStat (CuExpr ex, CuStat st) {
		this.e = ex;
		this.s1 = st;
		super.text = "if ( " + e.toString() + " ) " + s1.toString();
	}

    @Override public void add (CuStat st) {
    	s2 = st;
    	super.text += " else " + s2.toString();
    	//if we don't have s2, there won't be new vars
		for (String str : s1.newVars) {
			if (s2.newVars.contains(str)) {
				super.newVars.add(str);
			}
		}
    }
    
    //for if statement, ctext is build here
    @Override public String toC() {
    	super.ctext += this.e.construct();
    	String temp_s1 = s1.toC();
    	String temp_s2 = "";
    	if (s2 != null)
    		temp_s2 = s2.toC();
    	//dealing with scoping
    	for (String str : super.newVars) {
    		temp_s1.replaceAll("void * " + str + ";\n", "");
    		temp_s2.replaceAll("void * " + str + ";\n", "");
    		super.ctext += "void * " + str + ";\n";
    	}
    	super.ctext += "if (" + e.toC() + ") {\n";
    	super.ctext += temp_s1 + "}\n";
    	if (s2 != null) {
    		super.ctext += "else {\n";
    		super.ctext += temp_s2 + "}\n";
    	}
    	return super.ctext;
    }
    
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//whenever we calculate expr type, we use a temporary context with merged mutable and
		//immutable variables
		CuContext tcontext = new CuContext (context);
		tcontext.mergeVariable();		
    	//check whether e is boolean
    	CuType eType = e.calculateType(tcontext);
    	if (!eType.isBoolean()) {
    		throw new NoSuchTypeException();
    	}
    	CuContext temp_context1 = new CuContext (context);
    	
		HReturn re1 = s1.calculateType(temp_context1);
		//if we don't have s2, temp_context2 will simply be context
		CuContext temp_context2 = new CuContext (context);
		//default is false and bottom
		HReturn re2 = new HReturn();
		if (s2 != null) {
			re2 = s2.calculateType(temp_context2);		
		}
		
		//System.out.println("temp_c1: " + temp_context1.mMutVariables.toString());
		//System.out.println("temp_c2: " + temp_context2.mMutVariables.toString());
				
		temp_context1.weakenMutType(temp_context2);
		//we are passing reference, this is suppose to change
		context.mMutVariables = temp_context1.mMutVariables;
		
		//System.out.println("after weakining " + context.mMutVariables.toString());
		HReturn re_out = new HReturn();
		if (re1.b==false || re2.b==false) {
			re_out.b = false;
		}
		else {
			re_out.b = true;
		}
		re_out.tau = CuType.commonParent(re1.tau, re2.tau);
		return re_out;
	}

}

class ReturnStat extends CuStat{
	private CuExpr e;
	public ReturnStat (CuExpr ee) {
		e = ee;
		super.text = "return " + e.toString() + " ;";
		super.ctext += e.construct();
		super.ctext += "return &" + e.toC() + ";\n";
	}
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//System.out.println("in return stat, begin");
		HReturn re = new HReturn();
		re.b = true;
		//whenever we calculate expr type, we use a temporary context with merged mutable and
		//immutable variables
		CuContext tcontext = new CuContext (context);
		tcontext.mergeVariable();	
		re.tau = e.calculateType(tcontext);
		//System.out.println("in return stat, exp is " + e.toString() + " end");
		return re;
	}
}

class Stats extends CuStat{
	public ArrayList<CuStat> al = new ArrayList<CuStat>();
	public Stats (List<CuStat> cu) {
		al = (ArrayList<CuStat>) cu;
		text = "{ " + Helper.listFlatten(al) + " }";
		ArrayList<String> c_stats = new ArrayList<String>();
		for(CuStat cs : al) {
			for (String str: cs.newVars) {
				if (!super.newVars.contains(str)) {
					super.newVars.add(str);
				}
			}
			//to be safe, we need to call toC
			c_stats.add(cs.toC());
		}
		
		for(String str : super.newVars) {
			super.ctext += "void * " + str + ";\n";
			for (String cstr : c_stats) {
				cstr.replaceAll("void * " + str + ";\n", "");
			}
		}
		
		for (String cstr : c_stats) {
			super.ctext += cstr;
		}
	}
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//System.out.println("in stats statement, begin");
		//System.out.println("number of elements: "+al.size());
		//default is false, bottom
		HReturn re = new HReturn();
		for (CuStat cs : al) {
			//System.out.println(cs.toString());
			HReturn temp = cs.calculateType(context);
			if (temp.b==true) {
				re.b = true;
			}
			//System.out.println("finished " + cs.toString() + "before common parrent");
			//System.out.println("re tau is " + re.tau.id + ", temp tau is " + temp.tau.id);
			re.tau = CuType.commonParent(re.tau, temp.tau);
			//System.out.println("finished " + cs.toString() + "after common parrent, common type is " + re.tau.id);
		}
		//System.out.println("in stats statement, end");
		return re;
	}
}

class WhileStat extends CuStat{
	private CuExpr e;
	private CuStat s1;
	public WhileStat (CuExpr ex, CuStat st){
		e = ex;
		s1 = st;
		text = "while ( " + e.toString() + " ) " + s1.toString();
		super.ctext = e.construct();
		super.ctext += "while (" + e.toC() + ") {\n";
		super.ctext += s1.toC();
		super.ctext += "}\n";
	}
    public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//whenever we calculate expr type, we use a temporary context with merged mutable and
		//immutable variables
		CuContext tcontext = new CuContext (context);
		tcontext.mergeVariable();		
    	//check whether e is boolean
		//System.out.println("in while, before checking expr");
		//System.out.println(this.e.toString());
    	CuType eType = e.calculateType(tcontext);
    	//System.out.println("in while, after checking expr");
    	if (!eType.isBoolean()) {
    		//System.out.println("in while, expr is not boolean");
    		//System.out.println("in while, expr type is " + eType.id);
    		throw new NoSuchTypeException();
    	} 
    	CuContext s_context = new CuContext(context);
    	HReturn re = s1.calculateType(s_context);   	
    	//type weakening to make it type check
    	context.weakenMutType(s_context);
    	re.b = false;
    	return re;
    }
}

class EmptyBody extends CuStat {
	public EmptyBody(){
		text=" ;";
	}
	public HReturn calculateType(CuContext context) throws NoSuchTypeException {
		//default is false and bottom
		return new HReturn();
	}
} 
