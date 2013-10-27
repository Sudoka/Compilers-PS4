import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*************** TO DO check related operators against figures 9************************


public abstract class CuExpr {
	protected String text = "";
	protected String methodId = null;
	protected String cText = "";
	protected String name = "";
	protected String castType = "";
	private CuType type = null;
	public void add(List<CuType> pt, List<CuExpr> es) {}
	public final CuType getType(CuContext context) throws NoSuchTypeException {
		if(type == null) { type = calculateType(context); }
Helper.P("return expression type " + type);
		return type;
	}
	protected CuType calculateType(CuContext context) throws NoSuchTypeException { return null;};
	@Override public String toString() {return text;}
	
	public String toC() {
		return cText;
	}
	
	public String construct(){
		return name;
	}
	
	public String getCastType(){
		return castType;
	}
	protected CuType binaryExprType(CuContext context, String leftId, String methodId, CuType rightType) throws NoSuchTypeException {
		//System.out.println("in binaryExprType, begin");
		//System.out.println("leftid is " + leftId + ", methodid is " + methodId + ",right type is " + rightType.id);
		// get the functions of left class
		CuClass cur_class = context.mClasses.get(leftId);
		if (cur_class == null) {
			//System.out.println("didn't find this class in class context");
			throw new NoSuchTypeException();
		}
		Map<String, CuTypeScheme> funcs =  cur_class.mFunctions;
		// check the method typescheme
		CuTypeScheme ts = funcs.get(methodId);
		if (ts == null ) {
			//System.out.println("didn't find this method in current class");
			throw new NoSuchTypeException();
		}
		Helper.ToDo("we know there is only one parameter for now");
		CuType tR = null;
		for (String mystr : ts.data_tc.keySet()) {
			tR = ts.data_tc.get(mystr);
		}
		/** if this method exists, kindcontext is <>, and type scheme matches with input */
		if (!rightType.isSubtypeOf(tR)) {
			throw new NoSuchTypeException();
		}
		//System.out.println("in binaryExprType, end");
		return ts.data_t;
	}

	protected CuType unaryExprType(CuContext context, String id, String methodId) throws NoSuchTypeException {
		// get the functions of left class
		CuClass cur_class = context.mClasses.get(id);
		if (cur_class == null) {
			throw new NoSuchTypeException();
		}
		Map<String, CuTypeScheme> funcs = cur_class.mFunctions;
		// check the method typescheme
		CuTypeScheme ts = funcs.get(methodId);
		if (ts==null) {
			throw new NoSuchTypeException();
		}
		return ts.data_t;
	}
	
	protected Boolean isTypeOf(CuContext context, CuType t) {
		return this.getType(context).isSubtypeOf(t);
	}
    protected Boolean isTypeOf(CuContext context, CuType t, Map<String, CuType> map) {
        CuType type = this.getType(context);
        //System.out.println("in isType of, before type check, type is " + type.toString());
        if (type == null)
        	type.calculateType(context);
        //System.out.println("in isType of, after type check, type is " + type.toString());
        
        t.plugIn(map);
        //System.out.println("t type is " + t.type.toString());
Helper.P(String.format("this=%s<%s> parent %s, that=%s<%s>, map=%s", type,type.map,type.parentType, t,t.map, map));
        return type.isSubtypeOf(t);
    }
}

class AndExpr extends CuExpr{
	private CuExpr left, right;
	public AndExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
//		super.desiredType = CuType.bool;
		super.methodId = "add";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
		
		name += left.construct() + ";\n";
		name += right.construct() + ";\n";
		
		super.cText = String.format("(%s*)%s->value && (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());		
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		//right should pass in a type
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
}

class AppExpr extends CuExpr {
	CuExpr left;
	CuExpr right;
	public AppExpr(CuExpr e1, CuExpr e2) {
		this.left = e1;
		this.right = e2;
		super.text = e1.toString() + " ++ " + e2.toString();
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		CuType t1 = left.calculateType(context);
		CuType t2 = right.calculateType(context);
		if (!t1.isIterable() || !t2.isIterable()) {
			throw new NoSuchTypeException();
		}
		CuType type = CuType.commonParent(t1.type, t2.type);
		return new Iter(type);
		/*CuType type = CuType.commonParent(left.getType(context), right.getType(context));
		if (type.isIterable()) return type;
		if (type.isBottom()) return new Iter(CuType.bottom);
		Helper.ToDo("Bottom <: Iterable<Bot>?"); */
	}
}

class BrkExpr extends CuExpr {
	private List<CuExpr> val;
	public BrkExpr(List<CuExpr> es){
		this.val = es;
		super.text=Helper.printList("[", val, "]", ",");
		
		String iter = Helper.getVarName();
		String temp = "";
		for (CuExpr e : val)
		{
			temp += "*%s.value[j++] = " + e.toString() + ";\n";
		}
		super.name = String.format(
				"int j=0;\nIterable %s;\n"
				+ "%s.size = %d\n",				
				iter, iter, val.size());
		super.name += temp;
		super.castType = "Iterable";
	
	}
	@Override protected CuType calculateType(CuContext context) {
		//System.out.println("in bracket expression, start");
		if (val == null || val.isEmpty()) return new Iter(CuType.bottom);
		CuType t = val.get(0).getType(context);
		//System.out.println("type id is " + t.id);
		for (int i = 0; i+1 < val.size(); i++) {
			t = CuType.commonParent(val.get(i).getType(context), val.get(i+1).getType(context));
		} // find the common parent type of all expressions here
		
		//System.out.println("in bracket expression end");
		
		return new Iter(t);
	}
}

class CBoolean extends CuExpr{
	Boolean val;
	public CBoolean(Boolean b){
		val=b;
		super.text=b.toString();
		if (val)
			super.cText = "1";
		else
			super.cText = "0";
	}
	@Override protected CuType calculateType(CuContext context) {
		if (val == null) { throw new NoSuchTypeException();}
		return CuType.bool;
	}
}

class CInteger extends CuExpr {
	Integer val;
	public CInteger(Integer i){
		val=i;
		super.text=i.toString();
		
		String temp = Helper.getVarName();
		super.name = String.format("Integer %s;\n%s.value = %d;\n", temp, temp, i);		
		super.cText = temp;
		super.castType = "Integer";
	}
	@Override protected CuType calculateType(CuContext context) {
		if (val == null) { throw new NoSuchTypeException();}
		return CuType.integer;
	}
}

class CString extends CuExpr {
	String val;
	public CString(String s){
		val=s;
		super.text=s;
		
		String temp = Helper.getVarName();
		super.name = String.format("String %s;\n%s.value = %s;\n", temp, temp, "\"" + s + "\"");
		
		super.cText = temp;
		super.castType = "String";
	}
	@Override protected CuType calculateType(CuContext context) {
		if (val == null) { throw new NoSuchTypeException();}
		return CuType.string;
	}
}

class DivideExpr extends CuExpr{
	private CuExpr left, right;
	public DivideExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
		super.methodId = "divide";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";
		
		super.cText = String.format("(%s*)%s->value / (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!left.getType(context).isInteger() || !right.getType(context).isInteger())
			throw new NoSuchTypeException();
		return CuType.integer;
	}
	 */
}

class EqualExpr extends CuExpr{
	private CuExpr left, right;
	private String method2= null;
	public EqualExpr(CuExpr e1, CuExpr e2, Boolean eq) {
		left = e1;
		right = e2;
		super.methodId = "equals";
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";
		
		if (eq) {
			super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
			super.cText = String.format("(%s*)%s->value == (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
		}
		else {
			method2 = "negate";
			super.text = String.format("%s . %s < > ( %s ) . negate ( )", left.toString(), super.methodId, right.toString());
			super.cText = String.format("(%s*)%s->value != (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
		}
		System.out.println(super.cText);
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		CuType t = binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
		if (method2 != null) {
			CuClass cur_class = context.mClasses.get(t.id);
			return cur_class.mFunctions.get(method2).data_t;
		}
		return t;
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!left.equals(right))
			throw new NoSuchTypeException();
		return CuType.bool;
	} */
}

class GreaterThanExpr extends CuExpr{
	private CuExpr left, right;
	public GreaterThanExpr(CuExpr e1, CuExpr e2, Boolean strict) {
		left = e1;
		right = e2;
		super.methodId = "greaterThan";
		Helper.ToDo("strict boolean??");
		super.text = String.format("%s . %s < > ( %s , %s )", left.toString(), super.methodId, right.toString(), strict);
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";
		if(strict)
			super.cText = String.format("(%s*)%s->value > (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
		else
			super.cText = String.format("(%s*)%s->value >= (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
	}

	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		boolean b1 = left.isTypeOf(context, CuType.integer) && right.isTypeOf(context, CuType.integer);
		boolean b2 = left.isTypeOf(context, CuType.bool) && right.isTypeOf(context, CuType.bool);
		if ((!b1) && (!b2))
			throw new NoSuchTypeException();
		return CuType.bool;
	}
}

class LessThanExpr extends CuExpr{
	private CuExpr left, right;
	public LessThanExpr(CuExpr e1, CuExpr e2, Boolean strict) {
		left = e1;
		right = e2;
		super.methodId = "lessThan";
		super.text = String.format("%s . %s < > ( %s, %s )", left.toString(), super.methodId, right.toString(), strict);
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";
		if(strict)
			super.cText = String.format("(%s*)%s->value < (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
		else
			super.cText = String.format("(%s*)%s->value <= (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		boolean b1 = left.isTypeOf(context, CuType.integer) && right.isTypeOf(context, CuType.integer);
		boolean b2 = left.isTypeOf(context, CuType.bool) && right.isTypeOf(context, CuType.bool);
		if ((!b1) && (!b2))
			throw new NoSuchTypeException();
		return CuType.bool;
	}
}

class MinusExpr extends CuExpr{
	private CuExpr left, right;
	public MinusExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
		super.methodId = "minus";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";

		super.cText = String.format("(%s*)%s->value - (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
		
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!left.getType(context).isInteger() || !right.getType(context).isInteger())
			throw new NoSuchTypeException();
		return CuType.integer;
	}*/
}

class ModuloExpr extends CuExpr{
	private CuExpr left, right;
	public ModuloExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
		super.methodId = "modulo";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
		
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";

		super.cText = String.format("(%s*)%s->value % (%s*)%s->value", left.getCastType(), left.toC(), right.getCastType(), right.toC());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!left.getType(context).isInteger() || !right.getType(context).isInteger())
			throw new NoSuchTypeException();
		return CuType.integer;
	}*/
}

class NegateExpr extends CuExpr{
	private CuExpr val;
	public NegateExpr(CuExpr e) {
		val = e;
		super.methodId = "negate";
		super.text = String.format("%s . %s < > ( )", val.toString(), super.methodId);

		super.name += e.construct() + ";\n";

		super.cText = String.format("!((%s*)%s->value)", e.getCastType(), e.toC());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return unaryExprType(context, val.getType(context).id, super.methodId);
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!val.getType(context).isBoolean())
			throw new NoSuchTypeException();
		return CuType.bool;
	}*/
}

class NegativeExpr extends CuExpr{
	private CuExpr val;
	public NegativeExpr(CuExpr e) {
		val = e;
		super.methodId = "negative";
		super.text = String.format("%s . %s < > ( )", val.toString(), super.methodId);

		super.name += e.construct() + ";\n";

		super.cText = String.format("-((%s*)%s->value)", e.getCastType(), e.toC());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return unaryExprType(context, val.getType(context).id, super.methodId);
	}
	/*
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!val.getType(context).isInteger())
			throw new NoSuchTypeException();
		return CuType.integer;
	}*/
}

class OnwardsExpr extends CuExpr{
	private CuExpr val;
	public OnwardsExpr(CuExpr e, Boolean inclusiveness) {
		val = e;
		super.methodId = "onwards";
		super.text = String.format("%s . %s < > ( %s )", val.toString(), super.methodId, inclusiveness);
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, val.getType(context).id, super.methodId, CuType.bool);
	}
}

class OrExpr extends CuExpr{
	private CuExpr left, right;
	public OrExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
		super.methodId = "or";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());

		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";

		super.cText = String.format("(%s*)%s->value || (%s*)%s->value", left.getCastType(), left.toString(), right.getCastType(), right.toString());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
}

class PlusExpr extends CuExpr{
	private CuExpr left, right;
	public PlusExpr(CuExpr e1, CuExpr e2) {
		left = e1;
		right = e2;
		super.methodId = "plus";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());

		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";

		super.cText = String.format("(%s*)%s->value + (%s*)%s->value", left.getCastType(), left.toString(), right.getCastType(), right.toString());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		//System.out.println("in plus expr begin");
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
}

class ThroughExpr extends CuExpr{
	private CuExpr left, right;
	public ThroughExpr(CuExpr e1, CuExpr e2, Boolean low, Boolean up) {
		left = e1;
		right = e2;
		super.methodId = "through";
		super.text = String.format("%s . %s < > ( %s , %s , %s )", left.toString(), methodId, right.toString(), low, up);
		
		String temp = Helper.getVarName();
		if (low && up)
			super.name = String.format(
				"int i, j=0;\nIterable %s/*iterable name*/;\n"
				+ "%s/*iterable name*/.size = %d"
				+ "for(i=%s/*start val*/; i<=%s/*end val*/; i++)\n"
				+ "*%s.value[j++] = i"
				, temp, temp, Integer.parseInt(e2.toString())-Integer.parseInt(e1.toString())+1, e1.toString(), e2.toString(), "array");
		else if (low)
			super.name = String.format(
					"int i, j=0;\nIterable %s/*iterable name*/;\n"
					+ "%s/*iterable name*/.size = %d"
					+ "for(i=%s/*start val*/; i<%s/*end val*/; i++)\n"
					+ "*%s.value[j++] = i"
					,temp, temp, Integer.parseInt(e2.toString())-Integer.parseInt(e1.toString()), e1.toString(), e2.toString(), "array");
		else if (up)
			super.name = String.format(
					"int i, j=0;\nIterable %s/*iterable name*/;\n"
					+ "%s/*iterable name*/.size = %d"
					+ "for(i=%s+1/*start val*/; i<=%s/*end val*/; i++)\n"
					+ "*%s.value[j++] = i"
					,temp, temp, Integer.parseInt(e2.toString())-Integer.parseInt(e1.toString()), e1.toString(), e2.toString(), "array");
		else
			super.name = String.format(
					"int i, j=0;\nIterable %s/*iterable name*/;\n"
					+ "%s/*iterable name*/.size = %d"
					+ "for(i=%s+1/*start val*/; i<%s/*end val*/; i++)\n"
					+ "*%s.value[j++] = i"
					,temp, temp, Integer.parseInt(e2.toString())-Integer.parseInt(e1.toString())-1, e1.toString(), e2.toString(), "array");
		
		super.castType = "Iterable";
		super.cText = temp;
	}

	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		boolean b1 = left.isTypeOf(context, CuType.integer) && right.isTypeOf(context, CuType.integer);
		boolean b2 = left.isTypeOf(context, CuType.bool) && right.isTypeOf(context, CuType.bool);
		if ((!b1) && (!b2))
			throw new NoSuchTypeException();
		if (b1)
			return new Iter(CuType.integer);
		else
			return new Iter(CuType.bool);
	}
}

class TimesExpr extends CuExpr{
	private CuExpr left, right;
	public TimesExpr(CuExpr e1, CuExpr e2) {
		this.left = e1;
		this.right = e2;
		super.methodId = "times";
		super.text = String.format("%s . %s < > ( %s )", left.toString(), super.methodId, right.toString());
	
		super.name += left.construct() + ";\n";
		super.name += right.construct() + ";\n";

		super.cText = String.format("(%s*)%s->value * (%s*)%s->value", left.getCastType(), left.toString(), right.getCastType(), right.toString());
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
		return binaryExprType(context, left.getType(context).id, super.methodId, right.getType(context));
	}
}

class VarExpr extends CuExpr{// e.vv<tao1...>(e1,...)
	private CuExpr val;
	private String method;
	private List<CuType> types;
	List<CuExpr> es;
	public VarExpr(CuExpr e, String var, List<CuType> pt, List<CuExpr> es) {		
		this.val = e;
		this.method = var;
		this.types = pt;
		this.es = es;
		super.text = String.format("%s . %s %s %s", this.val.toString(), this.method, 
				Helper.printList("<", this.types, ">", ","), Helper.printList("(", this.es, ")", ","));
		
		//to be modified when class definition becomes clearer
		int offset = 0;
		String temp = "";
		if (es == null)
			temp = "(&this)";
		temp += "(&this, ";
		for (CuExpr exp : es) {
			super.name += exp.construct() + ";\n";
			temp += "&" + exp.toC() + ", ";
		}
		int j = temp.lastIndexOf(", ");
		if (j > 1) temp = temp.substring(0, j);
		temp += ")";
		
		super.cText = String.format("%s . *(vtable+%d) %s", val.toString(), offset, temp);
		
	}
	@Override protected CuType calculateType(CuContext context) throws NoSuchTypeException {
//System.out.println("in VarExp, begin");
        CuType tHat = val.getType(context); // 1st line in Figure 5 exp
//System.out.println("t_hat is " + tHat.id);
        CuClass cur_class = context.mClasses.get(tHat.id);
        if (cur_class == null) {
        	throw new NoSuchTypeException();
        }
        CuTypeScheme ts = cur_class.mFunctions.get(method);
        if (ts == null) {
        	throw new NoSuchTypeException();
        }
        //System.out.println("got this function");
        
        if (ts.data_kc.size() != types.size()) throw new NoSuchTypeException();
        Map<String, CuType> mapping = new HashMap<String, CuType>();
Helper.P(String.format("kc=%s. types=%s. eMap=%s",ts.data_kc, types,val.calculateType(context).map));
        for (int i = 0; i < types.size(); i++) {
        	mapping.put(ts.data_kc.get(i), types.get(i));
        }
        mapping.putAll(val.getType(context).map); // add mapping from the expression that owns this method
        //added by Yinglei
        if (ts.data_tc.size() != es.size()) throw new NoSuchTypeException();
        List<CuType> tList = new ArrayList<CuType>();
        for (CuType ct : ts.data_tc.values()) {
        	tList.add(ct);
        }   
        for (int i = 0; i < es.size(); i++) {
        	if (!es.get(i).isTypeOf(context, tList.get(i), mapping)) {
        		//System.out.println(es.get(i).toString() + " doesnt match " + tList.get(i).toString() );
        		throw new NoSuchTypeException();
        	}
        }        	
        //System.out.println("in VarExp, end");
        ts.data_t.plugIn(mapping);
Helper.P(String.format("%s returns %s<%s>, mapping %s", this, ts.data_t,ts.data_t.map, mapping));
        return ts.data_t;
	}

}
class VcExp extends CuExpr {
	private String val; 
	private List<CuType> types;
	private List<CuExpr> es;
	public VcExp(String v, List<CuType> pt, List<CuExpr> e){
		//System.out.println("in VcExp constructor, begin");
		this.val=v;
		this.types=pt;
		this.es=e;
		
		super.text=val.toString()+Helper.printList("<", types, ">", ",")+Helper.printList("(", es, ")", ",");
Helper.P("VcExp= "+text);
		//System.out.println("in VcExp constructor, end");
		
		String temp = "";
		if (es == null)
			temp = "()";
		temp += "(";
		for (CuExpr exp : es) {
			super.name += exp.construct() + ";\n";
			temp += "&" + exp.toC() + ", ";
		}
		int j = temp.lastIndexOf(", ");
		if (j > 1) temp = temp.substring(0, j);
		temp += ")";
		
		super.cText=val.toString()+temp;
		
		
	}
	@Override protected CuType calculateType(CuContext context)  throws NoSuchTypeException{
		//System.out.println("in VcExp, begin val is " + val);
		//type check each tao_i // check tao in scope
		for (CuType ct : types) {
			ct.calculateType(context);
		}      
		
        if (context.getFunction(val) == null) throw new NoSuchTypeException();
        // check each es 
        TypeScheme cur_ts = (TypeScheme) context.getFunction(val);
        List<CuType> tList = new ArrayList<CuType>();
        for (CuType cur_type : cur_ts.data_tc.values()) {
            tList.add(cur_type);
        }
        Map<String, CuType> mapping = new HashMap<String, CuType>();
        for (int i = 0; i < types.size(); i++) {
        	mapping.put(cur_ts.data_kc.get(i), types.get(i));
        }
  Helper.P(String.format("mapping=%s. types=%s. data_kc=%s ", mapping, types, cur_ts.data_kc));
  		//added by Yinglei
        if (es.size() != cur_ts.data_tc.size()) throw new NoSuchTypeException();
        for (int i = 0; i < es.size(); i++) {
            if (!es.get(i).isTypeOf(context, tList.get(i), mapping)) {
            	//System.out.println(es.get(i).toString() + " doesnt match " + tList.get(i).toString() );
            	throw new NoSuchTypeException();
            }	
        }
        //System.out.println("in VcExp, end");
        cur_ts.data_t.plugIn(mapping);
 Helper.P(String.format("VcExp return %s<%s>", cur_ts.data_t, cur_ts.data_t.map));
        return cur_ts.data_t;
	}
}

class VvExp extends CuExpr{
	private String val;
	private List<CuType> types = new ArrayList<CuType>();
	private List<CuExpr> es;
	
	public VvExp(String str){
		val = str;
		super.text=str;
		super.cText =str;
	}
	
	@Override public void add(List<CuType> pt, List<CuExpr> e){
		types = pt;
		es = e;
		super.text += Helper.printList("<", pt, ">", ",")+Helper.printList("(", es, ")", ",");
	}

	@Override protected CuType calculateType(CuContext context) {
		//System.out.println(String.format("in VvExp %s, begin %s", text, val));
		if (es == null) return context.getVariable(val);
		//else, it will be the same as in VcExp
        // check tao in scope
		//System.out.println("not a variable, checking function context");
        if (context.getFunction(val) == null) throw new NoSuchTypeException();
        //System.out.println("got this function from function context");
		//type check each tao_i // check tao in scope
		for (CuType ct : types) {
			ct.calculateType(context);
		}     
        // check each es 
        TypeScheme cur_ts = (TypeScheme) context.getFunction(val);
        List<CuType> tList = new ArrayList<CuType>();
        for (CuType cur_type : cur_ts.data_tc.values()) {
        	if(cur_type.id.equals("Iterable"))
        		cur_type.type = Helper.getTypeForIterable(cur_type.text);
            tList.add(cur_type);
        }
        Map<String, CuType> mapping = new HashMap<String, CuType>();
        for (int i = 0; i < types.size(); i++) {
        	mapping.put(cur_ts.data_kc.get(i), types.get(i));
        }
Helper.P("VvExp MAPPING "+mapping);
		//added by Yinglei
		if (cur_ts.data_tc.size() != es.size()) throw new NoSuchTypeException();
        for (int i = 0; i < es.size(); i++) {
        	//System.out.println(es.get(i).toString());
            if (!es.get(i).isTypeOf(context, tList.get(i), mapping)) {
            	//System.out.println("type mismatch, " + "es is " + es.get(i).toString() + "tListgeti is " + tList.get(i).toString() );
                throw new NoSuchTypeException();
            }
Helper.P(String.format("calculated %s", es.get(i)));
        }
        cur_ts.data_t.plugIn(mapping);
Helper.P(String.format("VvExp returns %s<%s>", cur_ts.data_t, cur_ts.data_t.map));
        return cur_ts.data_t;
	}
	
}
