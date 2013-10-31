import java.util.*;
import java.util.Map.Entry;

/** class declaration add type */
public abstract class CuType {
	protected static CuType top = new Top();
	protected static CuType bottom = new Bottom();
	protected static CuType bool = new VClass("Boolean", new ArrayList<CuType>());
	protected static CuType integer = new VClass("Integer", new ArrayList<CuType>());
	protected static CuType character = new VClass("Character", new ArrayList<CuType>());
	protected static CuType string = new VClass("String", new ArrayList<CuType>());
	protected static CuType iterable(ArrayList<CuType> arg) {return new Iter(arg);}
	protected List<CuType> parentType = new ArrayList<CuType>();
	List<CuType> iniArgs = new ArrayList<CuType>();
	protected String id;
	protected String text = "";
	protected Map<String, CuType> map = new LinkedHashMap<String, CuType>();// typeParameter->non-generic type arguments
	protected CuType type = CuType.bottom; // for Iterable<E>
	CuType(){ changeParent(top); }
	//copy Constructor, added by Yinglei
	CuType getcopy() {
		return this;
	}

	/** methods in its subtypes */
	public void changeParents(List<CuType> t) {parentType = t;}
	public void changeParent(CuType t) {
		parentType = new ArrayList<CuType>();
		parentType.add(t);
	} // append
	public boolean isTop() {return false;}
	public boolean isBottom() {return false;}
	public boolean isTypePara() {return false;}
	public boolean isClassOrInterface() {return false;}
	public boolean isIntersect() { return false;}
	public boolean isIterable() {return false;}
	public boolean isString() {return false;}
	public boolean isCharacter() {return false;}
	public boolean isInteger() {return false;}
	public boolean isBoolean() {return false;}

	public void add(CuType t) {}
	public Map<String, CuType> plugIn(Map<String, CuType> t) {return map;}

	// Hierarchy of types
	public boolean equals(Object that) { return equals((CuType)that); }
	abstract public boolean equals(CuType that);
	public boolean isSubtypeOf(CuType that) {
		if (this.equals(that) || this.isBottom() || that.isTop()) return true;
		for (CuType p : this.parentType) {
			if (p.isSubtypeOf(that)) return true;
		}
		return false;
	}
	public static CuType commonParent(CuType t1, CuType t2) {
		if (t1 == null || t1.isBottom()) return t2;
		if (t2 == null || t2.isBottom() ) return t1;
		//System.out.println("t1 is " + t1.toString());
		//System.out.println("t2 is " + t2.toString());
		if(t1.isIterable() && t2.isIterable())
		{
			//System.out.println("common parent of " + t1.type + " " + t2.type);
			return new Iter(CuType.commonParent(t1.type, t2.type));
		}
		List<CuType> parent1 = superTypeList(t1);
		List<CuType> parent2 = superTypeList(t2);
		//System.out.println("parent 1 is ");
		for (CuType p : parent1) { 
			//System.out.println(p.toString());
		}
		//System.out.println("parent 2 is ");
		for (CuType p : parent2) { 
			//System.out.println(p.toString());
		}
		for (CuType p : parent1) {
			//System.out.println("p is " + p.toString());
			if (parent2.contains(p)) { //System.out.println(	"returned p which is " + p.toString()); 
			return p;}
		}
		//System.out.println("returned top");
		return top;
	}
	public CuType calculateType(CuContext context) throws NoSuchTypeException { return null;}
	// find all the super types of n, including itself
	public static List<CuType> superTypeList(CuType n) {
		Queue<CuType> l = new LinkedList<CuType>();
		l.add(n);
		List<CuType> p = new ArrayList<CuType>();
		while (!l.isEmpty()) {
			CuType t = l.poll();
			p.add(t);
			if (!(t.parentType.size() == 1 && t.parentType.get(0) == null))
			for (CuType x : t.parentType) {
				if (!x.isTop() && !p.contains(x)) {
					l.add(x);
				}
			}
		}
		if (n.id.equals("String"))
			p.add(new Iter(character));
		if (!p.contains(top)) p.add(top);
		return p;
	}

	@Override public String toString() { return text;}
}
/** determine whether an interface: isInterface() == true
 * determine is a class but not interface isClassOrInterface() && !isInterface()
 * determine is a class or an interface: isClassOrInterface()
 */
class VClass extends CuType {
	public VClass(String s, List<CuType> args){
		super.id = s;
		iniArgs = args; // the <tao1, tao2 ...> when it is constructed
//Helper.P(String.format("VClass %s<%s>", s, args.toString()));
/*		for (CuType t : args) {
			map.put(t, CuType.bottom); // type parameter is mapped to bottom initially
		}
*/
		if (s.equals("String")) parentType.add(new Iter(CuType.character)); // String<> extends Iterable<Character<>>
		super.text=super.id+ " "+ Helper.printList("<", args, ">", ",");
		// TODO: merge to Iter(), no need anymore
		if (super.id.equals("Iterable")) {
			if (args.size()>1) throw new NoSuchTypeException(); // Iterable<E>, E cannot have 2 or more elements
		}
	}
	//added by Yinglei
	//copy Constructor, added by Yinglei
	@Override CuType getcopy() {
		CuType re = new VClass(super.id, iniArgs);
		re.parentType = new ArrayList<CuType>(this.parentType);
		re.iniArgs = new ArrayList<CuType> (this.iniArgs);
		re.id = new String (this.id);
		re.text = new String(this.text);
		re.map = new LinkedHashMap<String, CuType>(this.map);
		//change of reference should not change the value it is pointing to, ie re.type = Bottom doesn't make the 
		//this.type to be bottom
		re.type = this.type;
		return re;
	}

	@Override public CuType calculateType(CuContext context) {
		//System.out.println("in Vclass type check " + this.toString());
		// check if class or interface
		CuClass c = context.mClasses.get(id);
		if (c == null) throw new NoSuchTypeException(); 
		// get its parent types, for isSubtypeOf()
		super.changeParent(context.mClasses.get(id).superType);
		// get the generic types when declared
		List<String> typePara = c.kindCtxt;
		if (typePara.size() != iniArgs.size()) throw new NoSuchTypeException();
		for (int i = 0; i < iniArgs.size(); i++) {
			iniArgs.get(i).calculateType(context);
			map.put(typePara.get(i), iniArgs.get(i));
		}
		
		//special process for iterable TODO: delete this
		if(id.equals("Iterable")) {
			List <CuType> iter_parrents = new ArrayList<CuType>();
			for (CuType t : type.parentType) {
				//System.out.println(t.id);	
				if (!t.isTop()) iter_parrents.add(new Iter(t));
			}
			super.changeParents(iter_parrents);
		}
		
		if (c.isInterface()) return CuType.top;
		return this;
	}
	/* instantiate this class */
	@Override public Map<String, CuType> plugIn(Map<String, CuType> ts) {
		//TODO: make sure calculateType is called before
		if(map.size()==0) return this.map;
		Map<String, CuType> t = flattenMap(ts);
		for(Entry<String, CuType> p : map.entrySet()) {
			String k = p.getKey();
			CuType val = p.getValue();
			if (t.containsKey(val.id)) {
				map.put(k, t.get(val.id));
			}
			p.getValue().plugIn(t);
		}
		//Helper.P(String.format("PLUGIN VClass map ori=%s, t=%s", this.map,t));
		return this.map;
	}
	public Map<String, CuType> flattenMap(Map<String, CuType> t) {
		Map<String, CuType> flatten = new HashMap<String, CuType>();
		flatten.putAll(t);
		for (CuType v: t.values()) {
			flatten.putAll(flattenMap(v.map));
		}
		return flatten;
	}

	/**	@Override public Map<CuType, CuType> plugIn(List<CuType> t) {
		if(map.size()==0) return this.map;
		if(map.size() != t.size()) {throw new NoSuchTypeException();}
		int i = 0;
		for (Entry<CuType, CuType> k : map.entrySet()) {
			//commented out by Yinglei, can be TypeParam
			//if(t.get(i).isTypePara()) {
			//	throw new NoSuchTypeException();
			//}
			k.setValue(t.get(i));
			i++;
		}
		return this.map;
	}
	@Override public Map<CuType, CuType> plugIn(Map<CuType, CuType> t) {
		if(map.size()==0) return this.map;
		for (Entry<CuType, CuType> p : t.entrySet()) {
			CuType k = p.getKey();
			if (map.containsKey(k)) {
				map.put(k, p.getValue());// only plugin valid keys
			}
		}
		return this.map;
	}
*/
	@Override public boolean isClassOrInterface() {return true;}
	@Override public boolean isSubtypeOf(CuType that) {
		// TODO: make sure calculateType is called already
		//System.out.println("in vclass is subtype of " + this.toString() + " that is " + that.toString() + " that type is " + that.type.toString());
		//System.out.println("map is " + this.map.toString());
		if (that.isTop()) return true;
		if (this.equals(that)) return true;
		if (this.isBottom()) return true;
		for (CuType p : this.parentType) {
			p.plugIn(this.map);
			//System.out.println("p is " + p.toString());
			if (p.isSubtypeOf(that) || p.equals(that)) {
Helper.P(String.format("%s isSubTypeOf %s", this, that));
				return true;
			}	
		}
		return false;
	}
	
	@Override public boolean equals(CuType that) {
		if(that.isClassOrInterface()) {
			VClass t = (VClass) that;
			Collection<CuType> tp1 = this.map.values();
			Collection<CuType> tp2 = t.map.values();
			return super.id.equals(t.id) && Helper.equals(tp1, tp2);
		}
		return false;
	}
	private boolean equalsInstance(CuType t) {
		return equals(t) && map.equals(((VClass)t).map); // for generic plug in
	}
	//added by Yinglei
	@Override public boolean isIterable() {return (super.id.equals("Iterable") || super.id.equals("String"));}
	@Override public boolean isString() {return super.id.equals("String");}
	@Override public boolean isCharacter() {return super.id.equals("Character");}
	@Override public boolean isInteger() {return super.id.equals("Integer");}
	@Override public boolean isBoolean() {return super.id.equals("Boolean");}
	@Override public String toString() {return String.format("%s\"%s\"", text,map);}
}


class VTypeInter extends CuType {
	public VTypeInter(CuType t1){
		super.parentType = new ArrayList<CuType>();
		parentType.add(t1);
		super.text=t1.toString();
	}
	public VTypeInter() {
		
	}
	@Override public void add(CuType t) {
		parentType.add(t);
		super.text += " \u222A "+t.toString();
	}
	//added by Yinglei
	//copy Constructor, added by Yinglei
	@Override CuType getcopy() {
		CuType re = new VTypeInter();
		re.parentType = new ArrayList<CuType>(this.parentType);
		re.iniArgs = new ArrayList<CuType> (this.iniArgs);
		re.id = new String (this.id);
		re.text = new String(this.text);
		re.map = new LinkedHashMap<String, CuType>(this.map);
		//change of reference should not change the value it is pointing to, ie re.type = Bottom doesn't make the 
		//this.type to be bottom
		re.type = this.type;
		return re;
	}
	@Override public CuType calculateType(CuContext context) throws NoSuchTypeException {
Helper.P("VTypeInter calc : "+text);
		/* type checking */
		HashSet<CuType> pAll = new HashSet<CuType>(); 
		HashSet<String> vAll = new HashSet<String>(); 
		for(int i = 0; i < parentType.size(); i++) {
			CuType t = parentType.get(i);
			//System.out.println( String.valueOf(i)+ " &&& " + t.id + " " + t.toString());
			if (!t.isTop() && (!t.isClassOrInterface())) {
				//System.out.println(t.id + " can not be extended");
				throw new NoSuchTypeException();
			}
			CuType tt = t.calculateType(context);
	    	// A & B & C..., only the first could be a class
			if ((i > 0) && !tt.isTop()) throw new NoSuchTypeException();
			// all parents are distinct except top
			List<CuType> temp = t.parentType;
			temp.remove(CuType.top);
            if (temp.size() > 0) {
				if(!pAll.addAll(temp)) throw new NoSuchTypeException();
            }
			// all method names are distinct
			Set<String> temp2 = context.mClasses.get(t.id).mFunctions.keySet();
            if (temp2.size() > 0 ) {
				if(!vAll.addAll(temp2)) throw new NoSuchTypeException();
            }
		}
		//System.out.println("itersection type check succeeded");
		return parentType.get(0).calculateType(context);
	}
	@Override public boolean isIntersect() {return true;}
/*	@Override public void changeParents(List<CuType> t) {
		super.parentType = t;
	}
*/
	@Override public boolean equals(CuType that) {
		if (that.isIntersect()) {
			VTypeInter t = (VTypeInter) that;
			return parentType.containsAll(t.parentType) && t.parentType.containsAll(parentType);
		}
		return false;
	}
}


class VTypePara extends CuType {
	public VTypePara(String s){
		//System.out.println( "in VTypePara, id is " + s);
		super.id = s;
		super.text = s;
	}
	@Override public boolean isTypePara() {return true;}
	@Override public boolean equals(CuType that) {
		return that.isTypePara(); // id is not important since it is generic type
	}
	//added by Yinglei
	//copy Constructor, added by Yinglei
	@Override CuType getcopy() {
		CuType re = new VTypePara(super.id);
		re.parentType = new ArrayList<CuType>(this.parentType);
		re.iniArgs = new ArrayList<CuType> (this.iniArgs);
		re.id = new String (this.id);
		re.text = new String(this.text);
		re.map = new LinkedHashMap<String, CuType>(this.map);
		//change of reference should not change the value it is pointing to, ie re.type = Bottom doesn't make the 
		//this.type to be bottom
		re.type = this.type;
		return re;
	}
	public CuType calculateType(CuContext context) throws NoSuchTypeException {
		if (!context.hasVTypePara(super.id)){
			throw new NoSuchTypeException();
		}
		return null;
	}
}

class Iter extends VClass {
	CuType iniArg;// is not null
	public Iter(CuType args) {
		super(CuVvc.ITERABLE, new ArrayList<CuType> ()); // id is "Iterable"
		Helper.P("inside Iter, arg type is " + args.toString());
		if (args == null) throw new NoSuchTypeException();
		iniArg = args;
		init();
	}
	public Iter(List<CuType> args) {
		super(CuVvc.ITERABLE, args); // id is "Iterable"
		iniArg = (args.isEmpty()) ? CuType.bottom : args.get(0);
		init();
	}
	
	private void init() {
		super.text=super.id+ " <" + iniArg.toString()+">";
		// set its parent types
		List<CuType> parents = new ArrayList<CuType>();
		if (!(iniArg.parentType.size() == 1 && iniArg.parentType.get(0) == null))
		{
			for (CuType t : iniArg.parentType) {
				// System.out.println(t.id);
				if (!t.isTop())
					parents.add(new Iter(t));
			}
		}
		if (!parents.isEmpty()) super.changeParents(parents);
		type = this.iniArg;
		//System.out.println("in iter end");
	}
	//added by Yinglei
	//copy Constructor, added by Yinglei
	@Override CuType getcopy() {
		CuType re = new Iter(this.iniArg);
		re.parentType = new ArrayList<CuType>(this.parentType);
		re.iniArgs = new ArrayList<CuType> (this.iniArgs);
		re.id = new String (this.id);
		re.text = new String(this.text);
		re.map = new LinkedHashMap<String, CuType>(this.map);
		//change of reference should not change the value it is pointing to, ie re.type = Bottom doesn't make the 
		//this.type to be bottom
		re.type = this.type;
		return re;
	}
	@Override public boolean isIterable() {return true;}
	@Override public boolean equals(CuType that) {
//Helper.P("inside Iter equals function, this is " + this.toString() + " that is " + that.toString());
		if (!that.isIterable()) return false;
		//added by Yinglei to fix PA3
		if (this.isString() && !that.isString()) return false;
		//added by Yinglei to fix PA3
		if (that.isString() && !this.isString()) return false;
//Helper.P("inside Iter equals function, this type is " + this.type.toString() + " that type is " + that.type.toString());
		if (this.type.isTypePara() && that.type.isTypePara()) return true;
		return this.type.equals(((VClass)that).type);
	}
	@Override public CuType calculateType(CuContext context) {
		// check if class or interface
		CuClass c = context.mClasses.get(id);
		if (c == null) throw new NoSuchTypeException(); 
		// get the generic types when declared
		List<String> typePara = c.kindCtxt;
		if (typePara.size() != 1) throw new NoSuchTypeException();
Helper.P(String.format("typePara %s, initArg %s", typePara, iniArg));
		if(!iniArg.isTypePara()) iniArg.calculateType(context);
		map.put(typePara.get(0), iniArg);
Helper.P(String.format("map %s", map));
		return this;
	}
	@Override public boolean isSubtypeOf(CuType that) {
Helper.P("Interable subtyping begin: this type is " + this.type + " that type is" + that.type);
		//added by Yinglei to fix PA3, String is iterable, but only string is subtype of string
		if (that.isString() && !this.isString()) return false;
		// TODO: make sure calculateType is called already
		if (this.equals(that) || that.isTop()) return true;
		if (that.isIterable() && this.type.isSubtypeOf(that.type)) {
Helper.P("Interable subtyping return true 2: this type is " + this.type + " that type is" + that.type);
			return true;
		}
		return false;
	}
	@Override public Map<String, CuType> plugIn(Map<String, CuType> ts) {
		//TODO: make sure calculateType is called before
		if(map.size()==0) return this.map;
		String key = "Bottom";
		for (Entry<String, CuType> p : map.entrySet()) {
			key = p.getKey();
		}
		Map<String, CuType> t = flattenMap(ts);
		type.plugIn(t); // plug in recursively
		//System.out.println("type is " + type.toString());
		for (Entry<String, CuType> p : t.entrySet()) {
			String k = p.getKey();
			if (key.equals(k) || type.id.equals(k)) {
				map.put(key, p.getValue()); // only plug in valid keys
				type = p.getValue();
				//System.out.println("after plug in type is " + type.toString());
//Helper.P(String.format("PLUGIN Iter map ori=%s, t=%s", this.map,t));
				return this.map;
			}
		}
//Helper.P(String.format("PLUGIN Iter map ori=%s, t=%s", this.map,t));
		return this.map;
	}
}

/*
class Bool extends VClass {
	public Bool() {
		super(CuVvc.BOOLEAN, new ArrayList<CuType> (), false);
	}
	@Override public boolean isBoolean() {return true;}
}
class Int extends VClass {
	public Int() {
		super(CuVvc.INTEGER, new ArrayList<CuType> (), false);
	}
	@Override public boolean isInteger() {return true;}
}
class Char extends VClass {
	public Char() {
		super(CuVvc.CHARACTER, new ArrayList<CuType> (), false);
	}
	@Override public boolean isCharacter() {return true;}
}
class Str extends VClass {
	public Str() {
		super(CuVvc.STRING, new ArrayList<CuType> (), false);
	}
	@Override public boolean isString() {return true;}
}
*/


class Top extends CuType{
	Top() {
		super.id = CuVvc.TOP;
		super.text = "Thing";
		super.type =  new Bottom();
	}
	@Override public CuType calculateType(CuContext context) { return this;}
	@Override public boolean isTop() {return true;}
	@Override public boolean equals(CuType that) { return that.isTop();}
	@Override public boolean isSubtypeOf(CuType t) { 
		if (t.isTop()) {
			return true;
		}
		else {
			return false;
		}
	}
}
class Bottom extends CuType {
	public Bottom(){
		super.id = CuVvc.BOTTOM;
		super.text= "Nothing";
	}
	@Override public CuType calculateType(CuContext context) { return this;}
	@Override public boolean isBottom() {return true;}
	@Override public boolean isSubtypeOf(CuType t) { 
		//System.out.println("in class Bottom"); 
		return true;}
	@Override public boolean equals(CuType that) { return that.isBottom();}
}

