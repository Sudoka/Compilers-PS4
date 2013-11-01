import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.*;

public class Cubex {
	public static void main (String args[]) throws IOException {
		getParser(args[0]);
	}
	
	protected static void getParser(String fn) throws IOException {
		CubexLexer2 lexer = new CubexLexer2(new ANTLRFileStream(fn));
		
		//bound inputs to a variable input, put in context
		CuContext context=new CuContext();
		context.init();
		
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CubexParser2 parser = new CubexParser2(tokens);
		// altering anltr error messages
		parser.removeErrorListeners();
		parser.addErrorListener(new ParserErrorListener(false)); //prevent printing debugging messages
		
		CuProgr ourProgram = null;
		try {
			ourProgram = parser.program().p;
			ourProgram.calculateType(context);
			Helper.ToDo("comment out accept when we submit");
			System.out.println("accept");
		} catch (Exception e) {
			System.out.println("reject");
			//even if rejected, we still generate a 
			//System.exit(-2);
		}
		
		ArrayList<String> localVars = new ArrayList<String>();
		PrintWriter writer = new PrintWriter("out.c", "UTF-8");
		writer.println(ourProgram.toC(localVars));
		writer.close();
	}
}
