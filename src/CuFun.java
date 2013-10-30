import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//struct for functions
public abstract class CuFun {
	public String v;
	public CuTypeScheme ts;
	public CuStat funBody = null; 

	protected StringBuilder sb= new StringBuilder();
	protected StringBuilder inputs=new StringBuilder();
	protected String ctext="";
	//new Stats(new ArrayList<CuStat>());
	//public void add(CuVvc v, CuTypeScheme ts) {}
	//public void add(CuVvc v, CuTypeScheme ts, CuStat s) {}
	//public void add(CuStat s){}
	public abstract CuType calculateType(String v, CuTypeScheme ts, CuStat s);
	public abstract String toC();
}

class Function extends CuFun {
	public Function (String v_input, CuTypeScheme ts_input, CuStat s_input){
		super.v = v_input;
		super.ts = ts_input;
		super.funBody=s_input;
		
		//TODO: please let statement call this instead
		Helper.cFunType.put(v, ts.data_t.id);
		ArrayList<String> local=new ArrayList<String>();
		
		sb.append("void* "+v.toString()+"(");
		String delim = "";
		for (Entry<String, CuType> e : ts.data_tc.entrySet()){
			inputs.append(delim).append(e.getKey() +"* "+e.getValue().toString());
			delim=" , ";
			Helper.cVarType.put(e.getKey(), e.getValue().id);
		}
		sb.append(inputs);
		sb.append(") {\n");
		sb.append(funBody.toC(local));
		sb.append("}");
	}

	public String toC(){
		return sb.toString();
	}
	
	//Figure 7: Type checking Returns
	@Override public CuType calculateType(String v, CuTypeScheme ts, CuStat s){
		return null;
	}
	
	
}