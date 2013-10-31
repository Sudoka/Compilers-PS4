// Generated from CubexParser2.g4 by ANTLR 4.1

   import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CubexParser2 extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		APPEND=42, CLASS=11, STAR=31, LRTHR=46, THR=43, WHILE=4, LONW=48, CLSINTF=17, 
		COMMENTS_POND=55, ONW=47, LANGLE=36, GTE=50, LBRACE=29, THING=13, FOR=5, 
		SPACE=56, LTE=49, LPAREN=25, IF=2, LBRACKET=21, RPAREN=26, LTHR=44, SLASH=32, 
		IN=6, COMMENTS=54, COMMA=27, EQUAL=24, RETURN=7, NOTHING=14, PLUS=34, 
		PIPE=40, VAR=19, SUPER=12, RBRACKET=22, RANGLE=37, DOT=41, RTHR=45, INTEGER=20, 
		INEQUAL=52, RBRACE=30, PERCENT=33, DASH=35, ELSE=3, AMPERSAND=39, SEMICOLON=28, 
		BANG=38, ERROR=57, TRUE=15, COLON=23, EQEQUAL=51, ASSIGN=53, INTERFACE=8, 
		TPARA=18, FUN=10, FALSE=16, EXTENDS=9, STRING=1;
	public static final String[] tokenNames = {
		"<INVALID>", "STRING", "'if'", "'else'", "'while'", "'for'", "'in'", "'return'", 
		"'interface'", "'extends'", "'fun'", "'class'", "'super'", "'Thing'", 
		"'Nothing'", "'true'", "'false'", "CLSINTF", "TPARA", "VAR", "INTEGER", 
		"'['", "']'", "':'", "'='", "'('", "')'", "','", "';'", "'{'", "'}'", 
		"'*'", "'/'", "'%'", "'+'", "'-'", "'<'", "'>'", "'!'", "'&'", "'|'", 
		"'.'", "'++'", "'..'", "'<.'", "'.<'", "'<<'", "'...'", "'<..'", "'<='", 
		"'>='", "'=='", "'!='", "':='", "COMMENTS", "COMMENTS_POND", "SPACE", 
		"ERROR"
	};
	public static final int
		RULE_vc = 0, RULE_vv = 1, RULE_kindcontext = 2, RULE_typecontext = 3, 
		RULE_paratype = 4, RULE_type_prime = 5, RULE_type = 6, RULE_typescheme = 7, 
		RULE_expr = 8, RULE_exprs = 9, RULE_stat = 10, RULE_stats = 11, RULE_funBody = 12, 
		RULE_intf = 13, RULE_cls = 14, RULE_program = 15, RULE_progr = 16;
	public static final String[] ruleNames = {
		"vc", "vv", "kindcontext", "typecontext", "paratype", "type_prime", "type", 
		"typescheme", "expr", "exprs", "stat", "stats", "funBody", "intf", "cls", 
		"program", "progr"
	};

	@Override
	public String getGrammarFileName() { return "CubexParser2.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }




	public CubexParser2(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class VcContext extends ParserRuleContext {
		public CuVvc v;
		public Token vvv;
		public TerminalNode CLSINTF() { return getToken(CubexParser2.CLSINTF, 0); }
		public VcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vc; }
	}

	public final VcContext vc() throws RecognitionException {
		VcContext _localctx = new VcContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_vc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); ((VcContext)_localctx).vvv = match(CLSINTF);
			((VcContext)_localctx).v =  new Vc((((VcContext)_localctx).vvv!=null?((VcContext)_localctx).vvv.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VvContext extends ParserRuleContext {
		public CuVvc v;
		public Token vvv;
		public TerminalNode VAR() { return getToken(CubexParser2.VAR, 0); }
		public VvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vv; }
	}

	public final VvContext vv() throws RecognitionException {
		VvContext _localctx = new VvContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_vv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); ((VvContext)_localctx).vvv = match(VAR);
			((VvContext)_localctx).v =  new Vv((((VvContext)_localctx).vvv!=null?((VvContext)_localctx).vvv.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KindcontextContext extends ParserRuleContext {
		public List<String> kc;
		public Token TPARA;
		public List<TerminalNode> COMMA() { return getTokens(CubexParser2.COMMA); }
		public List<TerminalNode> TPARA() { return getTokens(CubexParser2.TPARA); }
		public TerminalNode LANGLE() { return getToken(CubexParser2.LANGLE, 0); }
		public TerminalNode TPARA(int i) {
			return getToken(CubexParser2.TPARA, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(CubexParser2.COMMA, i);
		}
		public TerminalNode RANGLE() { return getToken(CubexParser2.RANGLE, 0); }
		public KindcontextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kindcontext; }
	}

	public final KindcontextContext kindcontext() throws RecognitionException {
		KindcontextContext _localctx = new KindcontextContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_kindcontext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((KindcontextContext)_localctx).kc =  new ArrayList<String>();
			setState(55);
			_la = _input.LA(1);
			if (_la==LANGLE) {
				{
				setState(41); match(LANGLE);
				setState(52);
				_la = _input.LA(1);
				if (_la==TPARA) {
					{
					setState(42); ((KindcontextContext)_localctx).TPARA = match(TPARA);
					 _localctx.kc.add((((KindcontextContext)_localctx).TPARA!=null?((KindcontextContext)_localctx).TPARA.getText():null)); 
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(44); match(COMMA);
						setState(45); ((KindcontextContext)_localctx).TPARA = match(TPARA);
						 _localctx.kc.add((((KindcontextContext)_localctx).TPARA!=null?((KindcontextContext)_localctx).TPARA.getText():null)); 
						}
						}
						setState(51);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(54); match(RANGLE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypecontextContext extends ParserRuleContext {
		public Map<String,CuType> tc;
		public Token VAR;
		public TypeContext t;
		public TerminalNode VAR(int i) {
			return getToken(CubexParser2.VAR, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CubexParser2.COMMA); }
		public List<TerminalNode> VAR() { return getTokens(CubexParser2.VAR); }
		public TerminalNode RPAREN() { return getToken(CubexParser2.RPAREN, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode COLON(int i) {
			return getToken(CubexParser2.COLON, i);
		}
		public TerminalNode LPAREN() { return getToken(CubexParser2.LPAREN, 0); }
		public List<TerminalNode> COLON() { return getTokens(CubexParser2.COLON); }
		public TerminalNode COMMA(int i) {
			return getToken(CubexParser2.COMMA, i);
		}
		public TypecontextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typecontext; }
	}

	public final TypecontextContext typecontext() throws RecognitionException {
		TypecontextContext _localctx = new TypecontextContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typecontext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((TypecontextContext)_localctx).tc =  new LinkedHashMap<String,CuType>(); 
			setState(58); match(LPAREN);
			setState(74);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(59); ((TypecontextContext)_localctx).VAR = match(VAR);
				setState(60); match(COLON);
				setState(61); ((TypecontextContext)_localctx).t = type();
				 _localctx.tc.put((((TypecontextContext)_localctx).VAR!=null?((TypecontextContext)_localctx).VAR.getText():null), ((TypecontextContext)_localctx).t.t); 
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(63); match(COMMA);
					setState(64); ((TypecontextContext)_localctx).VAR = match(VAR);
					setState(65); match(COLON);
					setState(66); ((TypecontextContext)_localctx).t = type();
					 _localctx.tc.put((((TypecontextContext)_localctx).VAR!=null?((TypecontextContext)_localctx).VAR.getText():null), ((TypecontextContext)_localctx).t.t); 
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(76); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParatypeContext extends ParserRuleContext {
		public List<CuType> pt;
		public TypeContext t;
		public List<TerminalNode> COMMA() { return getTokens(CubexParser2.COMMA); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode LANGLE() { return getToken(CubexParser2.LANGLE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(CubexParser2.COMMA, i);
		}
		public TerminalNode RANGLE() { return getToken(CubexParser2.RANGLE, 0); }
		public ParatypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paratype; }
	}

	public final ParatypeContext paratype() throws RecognitionException {
		ParatypeContext _localctx = new ParatypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paratype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ParatypeContext)_localctx).pt =  new ArrayList<CuType>(); 
			setState(94);
			_la = _input.LA(1);
			if (_la==LANGLE) {
				{
				setState(79); match(LANGLE);
				setState(91);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THING) | (1L << NOTHING) | (1L << CLSINTF) | (1L << TPARA))) != 0)) {
					{
					setState(80); ((ParatypeContext)_localctx).t = type();
					_localctx.pt.add(((ParatypeContext)_localctx).t.t);
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(82); match(COMMA);
						setState(83); ((ParatypeContext)_localctx).t = type();
						_localctx.pt.add(((ParatypeContext)_localctx).t.t);
						}
						}
						setState(90);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(93); match(RANGLE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_primeContext extends ParserRuleContext {
		public CuType t;
		public Token v;
		public Token CLSINTF;
		public ParatypeContext p;
		public TerminalNode TPARA() { return getToken(CubexParser2.TPARA, 0); }
		public TerminalNode CLSINTF() { return getToken(CubexParser2.CLSINTF, 0); }
		public TerminalNode THING() { return getToken(CubexParser2.THING, 0); }
		public ParatypeContext paratype() {
			return getRuleContext(ParatypeContext.class,0);
		}
		public TerminalNode NOTHING() { return getToken(CubexParser2.NOTHING, 0); }
		public Type_primeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_prime; }
	}

	public final Type_primeContext type_prime() throws RecognitionException {
		Type_primeContext _localctx = new Type_primeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_prime);
		try {
			setState(106);
			switch (_input.LA(1)) {
			case TPARA:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); ((Type_primeContext)_localctx).v = match(TPARA);
				((Type_primeContext)_localctx).t =  new VTypePara((((Type_primeContext)_localctx).v!=null?((Type_primeContext)_localctx).v.getText():null));
				}
				break;
			case THING:
				enterOuterAlt(_localctx, 2);
				{
				setState(98); match(THING);
				((Type_primeContext)_localctx).t =  new Top();
				}
				break;
			case NOTHING:
				enterOuterAlt(_localctx, 3);
				{
				setState(100); match(NOTHING);
				((Type_primeContext)_localctx).t =  new Bottom();
				}
				break;
			case CLSINTF:
				enterOuterAlt(_localctx, 4);
				{
				setState(102); ((Type_primeContext)_localctx).CLSINTF = match(CLSINTF);
				setState(103); ((Type_primeContext)_localctx).p = paratype();
				((Type_primeContext)_localctx).t =  ((((Type_primeContext)_localctx).CLSINTF!=null?((Type_primeContext)_localctx).CLSINTF.getText():null).equals("Iterable"))? new Iter(((Type_primeContext)_localctx).p.pt) : new VClass((((Type_primeContext)_localctx).CLSINTF!=null?((Type_primeContext)_localctx).CLSINTF.getText():null), ((Type_primeContext)_localctx).p.pt);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public CuType t;
		public Type_primeContext v;
		public Type_primeContext l;
		public Type_primeContext r1;
		public List<TerminalNode> AMPERSAND() { return getTokens(CubexParser2.AMPERSAND); }
		public Type_primeContext type_prime(int i) {
			return getRuleContext(Type_primeContext.class,i);
		}
		public TerminalNode AMPERSAND(int i) {
			return getToken(CubexParser2.AMPERSAND, i);
		}
		public List<Type_primeContext> type_prime() {
			return getRuleContexts(Type_primeContext.class);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		int _la;
		try {
			setState(121);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); ((TypeContext)_localctx).v = type_prime();
				((TypeContext)_localctx).t =  ((TypeContext)_localctx).v.t;
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111); ((TypeContext)_localctx).l = type_prime();
				((TypeContext)_localctx).t =  new VTypeInter(((TypeContext)_localctx).l.t);
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(113); match(AMPERSAND);
					setState(114); ((TypeContext)_localctx).r1 = type_prime();
					_localctx.t.add(((TypeContext)_localctx).r1.t);
					}
					}
					setState(119); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AMPERSAND );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeschemeContext extends ParserRuleContext {
		public CuTypeScheme ts;
		public KindcontextContext kc;
		public TypecontextContext tc;
		public TypeContext t;
		public TypecontextContext typecontext() {
			return getRuleContext(TypecontextContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public KindcontextContext kindcontext() {
			return getRuleContext(KindcontextContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CubexParser2.COLON, 0); }
		public TypeschemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typescheme; }
	}

	public final TypeschemeContext typescheme() throws RecognitionException {
		TypeschemeContext _localctx = new TypeschemeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typescheme);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); ((TypeschemeContext)_localctx).kc = kindcontext();
			setState(124); ((TypeschemeContext)_localctx).tc = typecontext();
			setState(125); match(COLON);
			setState(126); ((TypeschemeContext)_localctx).t = type();
			((TypeschemeContext)_localctx).ts =  new TypeScheme(((TypeschemeContext)_localctx).kc.kc, ((TypeschemeContext)_localctx).tc.tc, ((TypeschemeContext)_localctx).t.t);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public CuExpr e;
		public ExprContext ex;
		public ExprContext l;
		public Token op;
		public Token VAR;
		public ParatypeContext pt;
		public ExprsContext es;
		public Token CLSINTF;
		public Token INTEGER;
		public Token STRING;
		public ExprContext r;
		public TerminalNode APPEND() { return getToken(CubexParser2.APPEND, 0); }
		public TerminalNode TRUE() { return getToken(CubexParser2.TRUE, 0); }
		public TerminalNode CLSINTF() { return getToken(CubexParser2.CLSINTF, 0); }
		public TerminalNode LANGLE() { return getToken(CubexParser2.LANGLE, 0); }
		public TerminalNode STAR() { return getToken(CubexParser2.STAR, 0); }
		public TerminalNode RBRACKET() { return getToken(CubexParser2.RBRACKET, 0); }
		public TerminalNode AMPERSAND() { return getToken(CubexParser2.AMPERSAND, 0); }
		public TerminalNode RTHR() { return getToken(CubexParser2.RTHR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode VAR() { return getToken(CubexParser2.VAR, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CubexParser2.PLUS, 0); }
		public TerminalNode LRTHR() { return getToken(CubexParser2.LRTHR, 0); }
		public TerminalNode ONW() { return getToken(CubexParser2.ONW, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public TerminalNode PIPE() { return getToken(CubexParser2.PIPE, 0); }
		public TerminalNode GTE() { return getToken(CubexParser2.GTE, 0); }
		public TerminalNode PERCENT() { return getToken(CubexParser2.PERCENT, 0); }
		public TerminalNode LTE() { return getToken(CubexParser2.LTE, 0); }
		public TerminalNode BANG() { return getToken(CubexParser2.BANG, 0); }
		public TerminalNode DASH() { return getToken(CubexParser2.DASH, 0); }
		public TerminalNode LPAREN() { return getToken(CubexParser2.LPAREN, 0); }
		public TerminalNode LBRACKET() { return getToken(CubexParser2.LBRACKET, 0); }
		public TerminalNode INEQUAL() { return getToken(CubexParser2.INEQUAL, 0); }
		public TerminalNode EQEQUAL() { return getToken(CubexParser2.EQEQUAL, 0); }
		public TerminalNode RANGLE() { return getToken(CubexParser2.RANGLE, 0); }
		public TerminalNode DOT() { return getToken(CubexParser2.DOT, 0); }
		public TerminalNode LONW() { return getToken(CubexParser2.LONW, 0); }
		public TerminalNode RPAREN() { return getToken(CubexParser2.RPAREN, 0); }
		public TerminalNode LTHR() { return getToken(CubexParser2.LTHR, 0); }
		public ParatypeContext paratype() {
			return getRuleContext(ParatypeContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(CubexParser2.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(CubexParser2.STRING, 0); }
		public TerminalNode SLASH() { return getToken(CubexParser2.SLASH, 0); }
		public TerminalNode FALSE() { return getToken(CubexParser2.FALSE, 0); }
		public TerminalNode THR() { return getToken(CubexParser2.THR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			switch (_input.LA(1)) {
			case DASH:
			case BANG:
				{
				setState(130);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==DASH || _la==BANG) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(131); ((ExprContext)_localctx).ex = expr(10);
				 ((ExprContext)_localctx).e =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == DASH ? new NegativeExpr(((ExprContext)_localctx).ex.e) : new NegateExpr(((ExprContext)_localctx).ex.e); 
				}
				break;
			case LPAREN:
				{
				setState(134); match(LPAREN);
				setState(135); ((ExprContext)_localctx).ex = expr(0);
				setState(136); match(RPAREN);
				((ExprContext)_localctx).e =  ((ExprContext)_localctx).ex.e;
				}
				break;
			case VAR:
				{
				setState(139); ((ExprContext)_localctx).VAR = match(VAR);
				((ExprContext)_localctx).e =  new VvExp((((ExprContext)_localctx).VAR!=null?((ExprContext)_localctx).VAR.getText():null));
				setState(147);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(141); ((ExprContext)_localctx).pt = paratype();
					setState(142); match(LPAREN);
					setState(143); ((ExprContext)_localctx).es = exprs();
					setState(144); match(RPAREN);
					_localctx.e.add(((ExprContext)_localctx).pt.pt, ((ExprContext)_localctx).es.cu);
					}
					break;
				}
				}
				break;
			case CLSINTF:
				{
				setState(149); ((ExprContext)_localctx).CLSINTF = match(CLSINTF);
				setState(150); ((ExprContext)_localctx).pt = paratype();
				setState(151); match(LPAREN);
				setState(152); ((ExprContext)_localctx).es = exprs();
				setState(153); match(RPAREN);
				((ExprContext)_localctx).e =  new VcExp((((ExprContext)_localctx).CLSINTF!=null?((ExprContext)_localctx).CLSINTF.getText():null), ((ExprContext)_localctx).pt.pt, ((ExprContext)_localctx).es.cu);
				}
				break;
			case TRUE:
				{
				setState(156); match(TRUE);
				((ExprContext)_localctx).e =  new CBoolean(true);
				}
				break;
			case FALSE:
				{
				setState(158); match(FALSE);
				((ExprContext)_localctx).e =  new CBoolean(false);
				}
				break;
			case INTEGER:
				{
				setState(160); ((ExprContext)_localctx).INTEGER = match(INTEGER);
				((ExprContext)_localctx).e =  new CInteger((((ExprContext)_localctx).INTEGER!=null?Integer.valueOf(((ExprContext)_localctx).INTEGER.getText()):0));
				}
				break;
			case STRING:
				{
				setState(162); ((ExprContext)_localctx).STRING = match(STRING);
				((ExprContext)_localctx).e =  new CString((((ExprContext)_localctx).STRING!=null?((ExprContext)_localctx).STRING.getText():null));
				}
				break;
			case LBRACKET:
				{
				setState(164); match(LBRACKET);
				setState(165); ((ExprContext)_localctx).es = exprs();
				setState(166); match(RBRACKET);
				((ExprContext)_localctx).e =  new BrkExpr(((ExprContext)_localctx).es.cu);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(223);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(171);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						setState(172);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << SLASH) | (1L << PERCENT))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(173); ((ExprContext)_localctx).r = expr(10);
						 ((ExprContext)_localctx).e =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == STAR ? new TimesExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e)
						                                    : (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == SLASH ? new DivideExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e)
						                                                        : new ModuloExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e); 
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
						setState(177);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==DASH) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(178); ((ExprContext)_localctx).r = expr(9);
						 ((ExprContext)_localctx).e =  ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == PLUS)
						                       ? new PlusExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e)
						                       : new MinusExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e); 
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(181);
						if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
						setState(182);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THR) | (1L << LTHR) | (1L << RTHR) | (1L << LRTHR))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(183); ((ExprContext)_localctx).r = expr(8);
						((ExprContext)_localctx).e =  ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == THR) ? new ThroughExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, true, true) 
						                                      : ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == LTHR) 
						                                            ? new ThroughExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, false, true) 
						                                            : ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == RTHR) 
						                                                ? new ThroughExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, true, false)
						                                                : new ThroughExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, false, false);
						}
						break;

					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(186);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(187);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LANGLE) | (1L << RANGLE) | (1L << LTE) | (1L << GTE))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(188); ((ExprContext)_localctx).r = expr(6);
						((ExprContext)_localctx).e =  ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == LANGLE) 
						                  ? new LessThanExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, true) 
						                  : ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == LTE) 
						                      ? new LessThanExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, false) 
						                      : ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == RANGLE) 
						                          ? new GreaterThanExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, true)
						                          : new GreaterThanExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, false);
						}
						break;

					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(191);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(192);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQEQUAL || _la==INEQUAL) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(193); ((ExprContext)_localctx).r = expr(5);
						((ExprContext)_localctx).e =  ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == EQEQUAL) 
						                 ? new EqualExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, true) 
						                 : new EqualExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e, false);
						}
						break;

					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(196);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(197); match(AMPERSAND);
						setState(198); ((ExprContext)_localctx).r = expr(4);
						 ((ExprContext)_localctx).e =  new AndExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e); 
						}
						break;

					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(201);
						if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
						setState(202); match(PIPE);
						setState(203); ((ExprContext)_localctx).r = expr(3);
						 ((ExprContext)_localctx).e =  new OrExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e); 
						}
						break;

					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "1 >= $_p");
						setState(207); match(APPEND);
						setState(208); ((ExprContext)_localctx).r = expr(2);
						((ExprContext)_localctx).e =  new AppExpr(((ExprContext)_localctx).l.e, ((ExprContext)_localctx).r.e); 
						}
						break;

					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.ex = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(211);
						if (!(16 >= _localctx._p)) throw new FailedPredicateException(this, "16 >= $_p");
						setState(212); match(DOT);
						setState(213); ((ExprContext)_localctx).VAR = match(VAR);
						setState(214); ((ExprContext)_localctx).pt = paratype();
						setState(215); match(LPAREN);
						setState(216); ((ExprContext)_localctx).es = exprs();
						setState(217); match(RPAREN);
						((ExprContext)_localctx).e =  new VarExpr(((ExprContext)_localctx).ex.e, (((ExprContext)_localctx).VAR!=null?((ExprContext)_localctx).VAR.getText():null), ((ExprContext)_localctx).pt.pt, ((ExprContext)_localctx).es.cu);
						}
						break;

					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.ex = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(220);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(221);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ONW || _la==LONW) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						((ExprContext)_localctx).e =  ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0) == ONW) ? new OnwardsExpr(((ExprContext)_localctx).ex.e, true) : new OnwardsExpr(((ExprContext)_localctx).ex.e, false);
						}
						break;
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprsContext extends ParserRuleContext {
		public List<CuExpr> cu;
		public ExprContext e;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(CubexParser2.COMMA); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(CubexParser2.COMMA, i);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ExprsContext)_localctx).cu =  new ArrayList<CuExpr>();
			setState(240);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << TRUE) | (1L << FALSE) | (1L << CLSINTF) | (1L << VAR) | (1L << INTEGER) | (1L << LBRACKET) | (1L << LPAREN) | (1L << DASH) | (1L << BANG))) != 0)) {
				{
				setState(229); ((ExprsContext)_localctx).e = expr(0);
				_localctx.cu.add(((ExprsContext)_localctx).e.e);
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(231); match(COMMA);
					setState(232); ((ExprsContext)_localctx).e = expr(0);
					_localctx.cu.add(((ExprsContext)_localctx).e.e);
					}
					}
					setState(239);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public CuStat s;
		public StatsContext ss;
		public VvContext v;
		public ExprContext e;
		public StatContext l;
		public StatContext r;
		public StatContext st;
		public TerminalNode LBRACE() { return getToken(CubexParser2.LBRACE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CubexParser2.SEMICOLON, 0); }
		public VvContext vv() {
			return getRuleContext(VvContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(CubexParser2.EQUAL, 0); }
		public TerminalNode RBRACE() { return getToken(CubexParser2.RBRACE, 0); }
		public TerminalNode LPAREN() { return getToken(CubexParser2.LPAREN, 0); }
		public TerminalNode IN() { return getToken(CubexParser2.IN, 0); }
		public TerminalNode WHILE() { return getToken(CubexParser2.WHILE, 0); }
		public TerminalNode IF() { return getToken(CubexParser2.IF, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode FOR() { return getToken(CubexParser2.FOR, 0); }
		public TerminalNode ELSE() { return getToken(CubexParser2.ELSE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CubexParser2.ASSIGN, 0); }
		public TerminalNode RPAREN() { return getToken(CubexParser2.RPAREN, 0); }
		public TerminalNode RETURN() { return getToken(CubexParser2.RETURN, 0); }
		public StatsContext stats() {
			return getRuleContext(StatsContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_stat);
		int _la;
		try {
			setState(286);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(242); match(LBRACE);
				setState(243); ((StatContext)_localctx).ss = stats();
				setState(244); match(RBRACE);
				((StatContext)_localctx).s =  new Stats(((StatContext)_localctx).ss.cu);
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(247); ((StatContext)_localctx).v = vv();
				setState(248); match(ASSIGN);
				setState(249); ((StatContext)_localctx).e = expr(0);
				setState(250); match(SEMICOLON);
				((StatContext)_localctx).s =  new AssignStat(((StatContext)_localctx).v.v, ((StatContext)_localctx).e.e);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(253); match(IF);
				setState(254); match(LPAREN);
				setState(255); ((StatContext)_localctx).e = expr(0);
				setState(256); match(RPAREN);
				setState(257); ((StatContext)_localctx).l = stat();
				((StatContext)_localctx).s =  new IfStat(((StatContext)_localctx).e.e, ((StatContext)_localctx).l.s);
				setState(263);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(259); match(ELSE);
					setState(260); ((StatContext)_localctx).r = stat();
					_localctx.s.add(((StatContext)_localctx).r.s);
					}
					break;
				}
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(265); match(WHILE);
				setState(266); match(LPAREN);
				setState(267); ((StatContext)_localctx).e = expr(0);
				setState(268); match(RPAREN);
				setState(269); ((StatContext)_localctx).st = stat();
				((StatContext)_localctx).s =  new WhileStat(((StatContext)_localctx).e.e, ((StatContext)_localctx).st.s);
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(272); match(FOR);
				setState(273); match(LPAREN);
				setState(274); ((StatContext)_localctx).v = vv();
				setState(275); match(IN);
				setState(276); ((StatContext)_localctx).e = expr(0);
				setState(277); match(RPAREN);
				setState(278); ((StatContext)_localctx).st = stat();
				((StatContext)_localctx).s =  new ForStat(((StatContext)_localctx).v.v, ((StatContext)_localctx).e.e, ((StatContext)_localctx).st.s);
				}
				break;
			case RETURN:
			case EQUAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(281);
				_la = _input.LA(1);
				if ( !(_la==RETURN || _la==EQUAL) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(282); ((StatContext)_localctx).e = expr(0);
				setState(283); match(SEMICOLON);
				((StatContext)_localctx).s =  new ReturnStat(((StatContext)_localctx).e.e);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatsContext extends ParserRuleContext {
		public List<CuStat> cu;
		public StatContext s;
		public StatContext ss;
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stats; }
	}

	public final StatsContext stats() throws RecognitionException {
		StatsContext _localctx = new StatsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stats);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((StatsContext)_localctx).cu =  new ArrayList<CuStat>();
			setState(299);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << VAR) | (1L << EQUAL) | (1L << LBRACE))) != 0)) {
				{
				setState(289); ((StatsContext)_localctx).s = stat();
				_localctx.cu.add(((StatsContext)_localctx).s.s);
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << VAR) | (1L << EQUAL) | (1L << LBRACE))) != 0)) {
					{
					{
					setState(291); ((StatsContext)_localctx).ss = stat();
					_localctx.cu.add(((StatsContext)_localctx).ss.s);
					}
					}
					setState(298);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunBodyContext extends ParserRuleContext {
		public CuStat body;
		public StatContext s;
		public TerminalNode SEMICOLON() { return getToken(CubexParser2.SEMICOLON, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public FunBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funBody; }
	}

	public final FunBodyContext funBody() throws RecognitionException {
		FunBodyContext _localctx = new FunBodyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funBody);
		try {
			setState(306);
			switch (_input.LA(1)) {
			case SEMICOLON:
				enterOuterAlt(_localctx, 1);
				{
				setState(301); match(SEMICOLON);
				((FunBodyContext)_localctx).body = new EmptyBody();
				}
				break;
			case IF:
			case WHILE:
			case FOR:
			case RETURN:
			case VAR:
			case EQUAL:
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(303); ((FunBodyContext)_localctx).s = stat();
				((FunBodyContext)_localctx).body = ((FunBodyContext)_localctx).s.s;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntfContext extends ParserRuleContext {
		public CuClass c;
		public Token CLSINTF;
		public KindcontextContext p;
		public TypeContext t;
		public Token VAR;
		public TypeschemeContext ts;
		public FunBodyContext f;
		public TerminalNode VAR(int i) {
			return getToken(CubexParser2.VAR, i);
		}
		public TerminalNode LBRACE() { return getToken(CubexParser2.LBRACE, 0); }
		public List<TerminalNode> FUN() { return getTokens(CubexParser2.FUN); }
		public TerminalNode CLSINTF() { return getToken(CubexParser2.CLSINTF, 0); }
		public KindcontextContext kindcontext() {
			return getRuleContext(KindcontextContext.class,0);
		}
		public List<TypeschemeContext> typescheme() {
			return getRuleContexts(TypeschemeContext.class);
		}
		public TerminalNode RBRACE() { return getToken(CubexParser2.RBRACE, 0); }
		public List<FunBodyContext> funBody() {
			return getRuleContexts(FunBodyContext.class);
		}
		public FunBodyContext funBody(int i) {
			return getRuleContext(FunBodyContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(CubexParser2.VAR); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeschemeContext typescheme(int i) {
			return getRuleContext(TypeschemeContext.class,i);
		}
		public TerminalNode INTERFACE() { return getToken(CubexParser2.INTERFACE, 0); }
		public TerminalNode FUN(int i) {
			return getToken(CubexParser2.FUN, i);
		}
		public TerminalNode EXTENDS() { return getToken(CubexParser2.EXTENDS, 0); }
		public IntfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intf; }
	}

	public final IntfContext intf() throws RecognitionException {
		IntfContext _localctx = new IntfContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_intf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); match(INTERFACE);
			setState(309); ((IntfContext)_localctx).CLSINTF = match(CLSINTF);
			setState(310); ((IntfContext)_localctx).p = kindcontext();
			((IntfContext)_localctx).c =  new Intf((((IntfContext)_localctx).CLSINTF!=null?((IntfContext)_localctx).CLSINTF.getText():null), ((IntfContext)_localctx).p.kc); 
			setState(316);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(312); match(EXTENDS);
				setState(313); ((IntfContext)_localctx).t = type();
				_localctx.c.addSuper(((IntfContext)_localctx).t.t);
				}
			}

			setState(318); match(LBRACE);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				{
				setState(319); match(FUN);
				setState(320); ((IntfContext)_localctx).VAR = match(VAR);
				setState(321); ((IntfContext)_localctx).ts = typescheme();
				setState(322); ((IntfContext)_localctx).f = funBody();
				_localctx.c.add((((IntfContext)_localctx).VAR!=null?((IntfContext)_localctx).VAR.getText():null), ((IntfContext)_localctx).ts.ts, ((IntfContext)_localctx).f.body); 
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(330); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClsContext extends ParserRuleContext {
		public CuClass c;
		public Token CLSINTF;
		public KindcontextContext pk;
		public TypecontextContext pt;
		public TypeContext t;
		public StatContext s;
		public ExprsContext es;
		public Token VAR;
		public TypeschemeContext ts;
		public FunBodyContext f;
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public TerminalNode VAR(int i) {
			return getToken(CubexParser2.VAR, i);
		}
		public TerminalNode LBRACE() { return getToken(CubexParser2.LBRACE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CubexParser2.SEMICOLON, 0); }
		public TerminalNode SUPER() { return getToken(CubexParser2.SUPER, 0); }
		public List<TerminalNode> FUN() { return getTokens(CubexParser2.FUN); }
		public TerminalNode CLSINTF() { return getToken(CubexParser2.CLSINTF, 0); }
		public KindcontextContext kindcontext() {
			return getRuleContext(KindcontextContext.class,0);
		}
		public List<TypeschemeContext> typescheme() {
			return getRuleContexts(TypeschemeContext.class);
		}
		public TerminalNode RBRACE() { return getToken(CubexParser2.RBRACE, 0); }
		public TerminalNode LPAREN() { return getToken(CubexParser2.LPAREN, 0); }
		public List<FunBodyContext> funBody() {
			return getRuleContexts(FunBodyContext.class);
		}
		public TerminalNode CLASS() { return getToken(CubexParser2.CLASS, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public FunBodyContext funBody(int i) {
			return getRuleContext(FunBodyContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(CubexParser2.VAR); }
		public TypecontextContext typecontext() {
			return getRuleContext(TypecontextContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CubexParser2.RPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeschemeContext typescheme(int i) {
			return getRuleContext(TypeschemeContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode FUN(int i) {
			return getToken(CubexParser2.FUN, i);
		}
		public TerminalNode EXTENDS() { return getToken(CubexParser2.EXTENDS, 0); }
		public ClsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cls; }
	}

	public final ClsContext cls() throws RecognitionException {
		ClsContext _localctx = new ClsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_cls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332); match(CLASS);
			setState(333); ((ClsContext)_localctx).CLSINTF = match(CLSINTF);
			setState(334); ((ClsContext)_localctx).pk = kindcontext();
			setState(335); ((ClsContext)_localctx).pt = typecontext();
			((ClsContext)_localctx).c =  new Cls((((ClsContext)_localctx).CLSINTF!=null?((ClsContext)_localctx).CLSINTF.getText():null), ((ClsContext)_localctx).pk.kc, (LinkedHashMap)(((ClsContext)_localctx).pt.tc)); 
			setState(341);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(337); match(EXTENDS);
				setState(338); ((ClsContext)_localctx).t = type();
				_localctx.c.addSuper(((ClsContext)_localctx).t.t);
				}
			}

			setState(343); match(LBRACE);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << VAR) | (1L << EQUAL) | (1L << LBRACE))) != 0)) {
				{
				{
				setState(344); ((ClsContext)_localctx).s = stat();
				_localctx.c.add(((ClsContext)_localctx).s.s);
				}
				}
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(359);
			_la = _input.LA(1);
			if (_la==SUPER) {
				{
				setState(352); match(SUPER);
				setState(353); match(LPAREN);
				setState(354); ((ClsContext)_localctx).es = exprs();
				setState(355); match(RPAREN);
				setState(356); match(SEMICOLON);
				_localctx.c.add(((ClsContext)_localctx).es.cu); 
				}
			}

			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				{
				setState(361); match(FUN);
				setState(362); ((ClsContext)_localctx).VAR = match(VAR);
				setState(363); ((ClsContext)_localctx).ts = typescheme();
				setState(364); ((ClsContext)_localctx).f = funBody();
				_localctx.c.add((((ClsContext)_localctx).VAR!=null?((ClsContext)_localctx).VAR.getText():null), ((ClsContext)_localctx).ts.ts, ((ClsContext)_localctx).f.body);  
				}
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(372); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public CuProgr p;
		public ProgrContext pr;
		public StatContext s;
		public ProgrContext progr(int i) {
			return getRuleContext(ProgrContext.class,i);
		}
		public List<ProgrContext> progr() {
			return getRuleContexts(ProgrContext.class);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			((ProgramContext)_localctx).p =  new FullPrg(); 
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(375); ((ProgramContext)_localctx).pr = progr();
					_localctx.p.add_prg(((ProgramContext)_localctx).pr.p);
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(383); ((ProgramContext)_localctx).s = stat();
			_localctx.p.add_lastStat(((ProgramContext)_localctx).s.s);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgrContext extends ParserRuleContext {
		public CuProgr p;
		public StatContext s;
		public VvContext v;
		public TypeschemeContext ts;
		public IntfContext i;
		public ClsContext c;
		public IntfContext intf() {
			return getRuleContext(IntfContext.class,0);
		}
		public ClsContext cls() {
			return getRuleContext(ClsContext.class,0);
		}
		public VvContext vv() {
			return getRuleContext(VvContext.class,0);
		}
		public TerminalNode FUN() { return getToken(CubexParser2.FUN, 0); }
		public TypeschemeContext typescheme() {
			return getRuleContext(TypeschemeContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public ProgrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_progr; }
	}

	public final ProgrContext progr() throws RecognitionException {
		ProgrContext _localctx = new ProgrContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_progr);
		try {
			setState(401);
			switch (_input.LA(1)) {
			case IF:
			case WHILE:
			case FOR:
			case RETURN:
			case VAR:
			case EQUAL:
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(386); ((ProgrContext)_localctx).s = stat();
				((ProgrContext)_localctx).p =  new StatPrg(((ProgrContext)_localctx).s.s); 
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 2);
				{
				setState(389); match(FUN);
				setState(390); ((ProgrContext)_localctx).v = vv();
				setState(391); ((ProgrContext)_localctx).ts = typescheme();
				setState(392); ((ProgrContext)_localctx).s = stat();
				 ((ProgrContext)_localctx).p =  new FunPrg(((ProgrContext)_localctx).v.v, ((ProgrContext)_localctx).ts.ts, ((ProgrContext)_localctx).s.s); 
				}
				break;
			case INTERFACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(395); ((ProgrContext)_localctx).i = intf();
				((ProgrContext)_localctx).p =  new ClassPrg(((ProgrContext)_localctx).i.c);
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 4);
				{
				setState(398); ((ProgrContext)_localctx).c = cls();
				((ProgrContext)_localctx).p =  new ClassPrg(((ProgrContext)_localctx).c.c); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 9 >= _localctx._p;

		case 1: return 8 >= _localctx._p;

		case 2: return 7 >= _localctx._p;

		case 3: return 5 >= _localctx._p;

		case 4: return 4 >= _localctx._p;

		case 5: return 3 >= _localctx._p;

		case 6: return 2 >= _localctx._p;

		case 7: return 1 >= _localctx._p;

		case 8: return 16 >= _localctx._p;

		case 9: return 6 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3;\u0196\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\62\n\4\f\4\16"+
		"\4\65\13\4\5\4\67\n\4\3\4\5\4:\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\7\5H\n\5\f\5\16\5K\13\5\5\5M\n\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6Y\n\6\f\6\16\6\\\13\6\5\6^\n\6\3\6\5\6a\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7m\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\6\bx\n\b\r\b\16\by\5\b|\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0096"+
		"\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\5\n\u00ac\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00e2\n\n\f\n\16\n\u00e5\13\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00ee\n\13\f\13\16\13\u00f1\13\13\5\13"+
		"\u00f3\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010a\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0121\n\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\7\r\u0129\n\r\f\r\16\r\u012c\13\r\5\r\u012e\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u0135\n\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u013f\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0148"+
		"\n\17\f\17\16\17\u014b\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\5\20\u0158\n\20\3\20\3\20\3\20\3\20\7\20\u015e\n\20\f\20"+
		"\16\20\u0161\13\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u016a\n\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0172\n\20\f\20\16\20\u0175\13\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u017d\n\21\f\21\16\21\u0180\13\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\5\22\u0194\n\22\3\22\2\23\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"\2\n\4\2%%((\3\2!#\3\2$%\3\2-\60\4\2&\'\63\64\3\2\65"+
		"\66\3\2\61\62\4\2\t\t\32\32\u01b9\2$\3\2\2\2\4\'\3\2\2\2\6*\3\2\2\2\b"+
		";\3\2\2\2\nP\3\2\2\2\fl\3\2\2\2\16{\3\2\2\2\20}\3\2\2\2\22\u00ab\3\2\2"+
		"\2\24\u00e6\3\2\2\2\26\u0120\3\2\2\2\30\u0122\3\2\2\2\32\u0134\3\2\2\2"+
		"\34\u0136\3\2\2\2\36\u014e\3\2\2\2 \u0178\3\2\2\2\"\u0193\3\2\2\2$%\7"+
		"\23\2\2%&\b\2\1\2&\3\3\2\2\2\'(\7\25\2\2()\b\3\1\2)\5\3\2\2\2*9\b\4\1"+
		"\2+\66\7&\2\2,-\7\24\2\2-\63\b\4\1\2./\7\35\2\2/\60\7\24\2\2\60\62\b\4"+
		"\1\2\61.\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2"+
		"\2\65\63\3\2\2\2\66,\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28:\7\'\2\29+\3\2"+
		"\2\29:\3\2\2\2:\7\3\2\2\2;<\b\5\1\2<L\7\33\2\2=>\7\25\2\2>?\7\31\2\2?"+
		"@\5\16\b\2@I\b\5\1\2AB\7\35\2\2BC\7\25\2\2CD\7\31\2\2DE\5\16\b\2EF\b\5"+
		"\1\2FH\3\2\2\2GA\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JM\3\2\2\2KI\3\2"+
		"\2\2L=\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\7\34\2\2O\t\3\2\2\2P`\b\6\1\2Q]\7"+
		"&\2\2RS\5\16\b\2SZ\b\6\1\2TU\7\35\2\2UV\5\16\b\2VW\b\6\1\2WY\3\2\2\2X"+
		"T\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2]R\3\2\2"+
		"\2]^\3\2\2\2^_\3\2\2\2_a\7\'\2\2`Q\3\2\2\2`a\3\2\2\2a\13\3\2\2\2bc\7\24"+
		"\2\2cm\b\7\1\2de\7\17\2\2em\b\7\1\2fg\7\20\2\2gm\b\7\1\2hi\7\23\2\2ij"+
		"\5\n\6\2jk\b\7\1\2km\3\2\2\2lb\3\2\2\2ld\3\2\2\2lf\3\2\2\2lh\3\2\2\2m"+
		"\r\3\2\2\2no\5\f\7\2op\b\b\1\2p|\3\2\2\2qr\5\f\7\2rw\b\b\1\2st\7)\2\2"+
		"tu\5\f\7\2uv\b\b\1\2vx\3\2\2\2ws\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2"+
		"z|\3\2\2\2{n\3\2\2\2{q\3\2\2\2|\17\3\2\2\2}~\5\6\4\2~\177\5\b\5\2\177"+
		"\u0080\7\31\2\2\u0080\u0081\5\16\b\2\u0081\u0082\b\t\1\2\u0082\21\3\2"+
		"\2\2\u0083\u0084\b\n\1\2\u0084\u0085\t\2\2\2\u0085\u0086\5\22\n\2\u0086"+
		"\u0087\b\n\1\2\u0087\u00ac\3\2\2\2\u0088\u0089\7\33\2\2\u0089\u008a\5"+
		"\22\n\2\u008a\u008b\7\34\2\2\u008b\u008c\b\n\1\2\u008c\u00ac\3\2\2\2\u008d"+
		"\u008e\7\25\2\2\u008e\u0095\b\n\1\2\u008f\u0090\5\n\6\2\u0090\u0091\7"+
		"\33\2\2\u0091\u0092\5\24\13\2\u0092\u0093\7\34\2\2\u0093\u0094\b\n\1\2"+
		"\u0094\u0096\3\2\2\2\u0095\u008f\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u00ac"+
		"\3\2\2\2\u0097\u0098\7\23\2\2\u0098\u0099\5\n\6\2\u0099\u009a\7\33\2\2"+
		"\u009a\u009b\5\24\13\2\u009b\u009c\7\34\2\2\u009c\u009d\b\n\1\2\u009d"+
		"\u00ac\3\2\2\2\u009e\u009f\7\21\2\2\u009f\u00ac\b\n\1\2\u00a0\u00a1\7"+
		"\22\2\2\u00a1\u00ac\b\n\1\2\u00a2\u00a3\7\26\2\2\u00a3\u00ac\b\n\1\2\u00a4"+
		"\u00a5\7\3\2\2\u00a5\u00ac\b\n\1\2\u00a6\u00a7\7\27\2\2\u00a7\u00a8\5"+
		"\24\13\2\u00a8\u00a9\7\30\2\2\u00a9\u00aa\b\n\1\2\u00aa\u00ac\3\2\2\2"+
		"\u00ab\u0083\3\2\2\2\u00ab\u0088\3\2\2\2\u00ab\u008d\3\2\2\2\u00ab\u0097"+
		"\3\2\2\2\u00ab\u009e\3\2\2\2\u00ab\u00a0\3\2\2\2\u00ab\u00a2\3\2\2\2\u00ab"+
		"\u00a4\3\2\2\2\u00ab\u00a6\3\2\2\2\u00ac\u00e3\3\2\2\2\u00ad\u00ae\6\n"+
		"\2\3\u00ae\u00af\t\3\2\2\u00af\u00b0\5\22\n\2\u00b0\u00b1\b\n\1\2\u00b1"+
		"\u00e2\3\2\2\2\u00b2\u00b3\6\n\3\3\u00b3\u00b4\t\4\2\2\u00b4\u00b5\5\22"+
		"\n\2\u00b5\u00b6\b\n\1\2\u00b6\u00e2\3\2\2\2\u00b7\u00b8\6\n\4\3\u00b8"+
		"\u00b9\t\5\2\2\u00b9\u00ba\5\22\n\2\u00ba\u00bb\b\n\1\2\u00bb\u00e2\3"+
		"\2\2\2\u00bc\u00bd\6\n\5\3\u00bd\u00be\t\6\2\2\u00be\u00bf\5\22\n\2\u00bf"+
		"\u00c0\b\n\1\2\u00c0\u00e2\3\2\2\2\u00c1\u00c2\6\n\6\3\u00c2\u00c3\t\7"+
		"\2\2\u00c3\u00c4\5\22\n\2\u00c4\u00c5\b\n\1\2\u00c5\u00e2\3\2\2\2\u00c6"+
		"\u00c7\6\n\7\3\u00c7\u00c8\7)\2\2\u00c8\u00c9\5\22\n\2\u00c9\u00ca\b\n"+
		"\1\2\u00ca\u00e2\3\2\2\2\u00cb\u00cc\6\n\b\3\u00cc\u00cd\7*\2\2\u00cd"+
		"\u00ce\5\22\n\2\u00ce\u00cf\b\n\1\2\u00cf\u00e2\3\2\2\2\u00d0\u00d1\6"+
		"\n\t\3\u00d1\u00d2\7,\2\2\u00d2\u00d3\5\22\n\2\u00d3\u00d4\b\n\1\2\u00d4"+
		"\u00e2\3\2\2\2\u00d5\u00d6\6\n\n\3\u00d6\u00d7\7+\2\2\u00d7\u00d8\7\25"+
		"\2\2\u00d8\u00d9\5\n\6\2\u00d9\u00da\7\33\2\2\u00da\u00db\5\24\13\2\u00db"+
		"\u00dc\7\34\2\2\u00dc\u00dd\b\n\1\2\u00dd\u00e2\3\2\2\2\u00de\u00df\6"+
		"\n\13\3\u00df\u00e0\t\b\2\2\u00e0\u00e2\b\n\1\2\u00e1\u00ad\3\2\2\2\u00e1"+
		"\u00b2\3\2\2\2\u00e1\u00b7\3\2\2\2\u00e1\u00bc\3\2\2\2\u00e1\u00c1\3\2"+
		"\2\2\u00e1\u00c6\3\2\2\2\u00e1\u00cb\3\2\2\2\u00e1\u00d0\3\2\2\2\u00e1"+
		"\u00d5\3\2\2\2\u00e1\u00de\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2"+
		"\2\2\u00e3\u00e4\3\2\2\2\u00e4\23\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00f2"+
		"\b\13\1\2\u00e7\u00e8\5\22\n\2\u00e8\u00ef\b\13\1\2\u00e9\u00ea\7\35\2"+
		"\2\u00ea\u00eb\5\22\n\2\u00eb\u00ec\b\13\1\2\u00ec\u00ee\3\2\2\2\u00ed"+
		"\u00e9\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00e7\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\25\3\2\2\2\u00f4\u00f5\7\37\2\2\u00f5\u00f6\5\30"+
		"\r\2\u00f6\u00f7\7 \2\2\u00f7\u00f8\b\f\1\2\u00f8\u0121\3\2\2\2\u00f9"+
		"\u00fa\5\4\3\2\u00fa\u00fb\7\67\2\2\u00fb\u00fc\5\22\n\2\u00fc\u00fd\7"+
		"\36\2\2\u00fd\u00fe\b\f\1\2\u00fe\u0121\3\2\2\2\u00ff\u0100\7\4\2\2\u0100"+
		"\u0101\7\33\2\2\u0101\u0102\5\22\n\2\u0102\u0103\7\34\2\2\u0103\u0104"+
		"\5\26\f\2\u0104\u0109\b\f\1\2\u0105\u0106\7\5\2\2\u0106\u0107\5\26\f\2"+
		"\u0107\u0108\b\f\1\2\u0108\u010a\3\2\2\2\u0109\u0105\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\u0121\3\2\2\2\u010b\u010c\7\6\2\2\u010c\u010d\7\33\2\2"+
		"\u010d\u010e\5\22\n\2\u010e\u010f\7\34\2\2\u010f\u0110\5\26\f\2\u0110"+
		"\u0111\b\f\1\2\u0111\u0121\3\2\2\2\u0112\u0113\7\7\2\2\u0113\u0114\7\33"+
		"\2\2\u0114\u0115\5\4\3\2\u0115\u0116\7\b\2\2\u0116\u0117\5\22\n\2\u0117"+
		"\u0118\7\34\2\2\u0118\u0119\5\26\f\2\u0119\u011a\b\f\1\2\u011a\u0121\3"+
		"\2\2\2\u011b\u011c\t\t\2\2\u011c\u011d\5\22\n\2\u011d\u011e\7\36\2\2\u011e"+
		"\u011f\b\f\1\2\u011f\u0121\3\2\2\2\u0120\u00f4\3\2\2\2\u0120\u00f9\3\2"+
		"\2\2\u0120\u00ff\3\2\2\2\u0120\u010b\3\2\2\2\u0120\u0112\3\2\2\2\u0120"+
		"\u011b\3\2\2\2\u0121\27\3\2\2\2\u0122\u012d\b\r\1\2\u0123\u0124\5\26\f"+
		"\2\u0124\u012a\b\r\1\2\u0125\u0126\5\26\f\2\u0126\u0127\b\r\1\2\u0127"+
		"\u0129\3\2\2\2\u0128\u0125\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012d"+
		"\u0123\3\2\2\2\u012d\u012e\3\2\2\2\u012e\31\3\2\2\2\u012f\u0130\7\36\2"+
		"\2\u0130\u0135\b\16\1\2\u0131\u0132\5\26\f\2\u0132\u0133\b\16\1\2\u0133"+
		"\u0135\3\2\2\2\u0134\u012f\3\2\2\2\u0134\u0131\3\2\2\2\u0135\33\3\2\2"+
		"\2\u0136\u0137\7\n\2\2\u0137\u0138\7\23\2\2\u0138\u0139\5\6\4\2\u0139"+
		"\u013e\b\17\1\2\u013a\u013b\7\13\2\2\u013b\u013c\5\16\b\2\u013c\u013d"+
		"\b\17\1\2\u013d\u013f\3\2\2\2\u013e\u013a\3\2\2\2\u013e\u013f\3\2\2\2"+
		"\u013f\u0140\3\2\2\2\u0140\u0149\7\37\2\2\u0141\u0142\7\f\2\2\u0142\u0143"+
		"\7\25\2\2\u0143\u0144\5\20\t\2\u0144\u0145\5\32\16\2\u0145\u0146\b\17"+
		"\1\2\u0146\u0148\3\2\2\2\u0147\u0141\3\2\2\2\u0148\u014b\3\2\2\2\u0149"+
		"\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2"+
		"\2\2\u014c\u014d\7 \2\2\u014d\35\3\2\2\2\u014e\u014f\7\r\2\2\u014f\u0150"+
		"\7\23\2\2\u0150\u0151\5\6\4\2\u0151\u0152\5\b\5\2\u0152\u0157\b\20\1\2"+
		"\u0153\u0154\7\13\2\2\u0154\u0155\5\16\b\2\u0155\u0156\b\20\1\2\u0156"+
		"\u0158\3\2\2\2\u0157\u0153\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2"+
		"\2\2\u0159\u015f\7\37\2\2\u015a\u015b\5\26\f\2\u015b\u015c\b\20\1\2\u015c"+
		"\u015e\3\2\2\2\u015d\u015a\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2"+
		"\2\2\u015f\u0160\3\2\2\2\u0160\u0169\3\2\2\2\u0161\u015f\3\2\2\2\u0162"+
		"\u0163\7\16\2\2\u0163\u0164\7\33\2\2\u0164\u0165\5\24\13\2\u0165\u0166"+
		"\7\34\2\2\u0166\u0167\7\36\2\2\u0167\u0168\b\20\1\2\u0168\u016a\3\2\2"+
		"\2\u0169\u0162\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u0173\3\2\2\2\u016b\u016c"+
		"\7\f\2\2\u016c\u016d\7\25\2\2\u016d\u016e\5\20\t\2\u016e\u016f\5\32\16"+
		"\2\u016f\u0170\b\20\1\2\u0170\u0172\3\2\2\2\u0171\u016b\3\2\2\2\u0172"+
		"\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2"+
		"\2\2\u0175\u0173\3\2\2\2\u0176\u0177\7 \2\2\u0177\37\3\2\2\2\u0178\u017e"+
		"\b\21\1\2\u0179\u017a\5\"\22\2\u017a\u017b\b\21\1\2\u017b\u017d\3\2\2"+
		"\2\u017c\u0179\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f"+
		"\3\2\2\2\u017f\u0181\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0182\5\26\f\2"+
		"\u0182\u0183\b\21\1\2\u0183!\3\2\2\2\u0184\u0185\5\26\f\2\u0185\u0186"+
		"\b\22\1\2\u0186\u0194\3\2\2\2\u0187\u0188\7\f\2\2\u0188\u0189\5\4\3\2"+
		"\u0189\u018a\5\20\t\2\u018a\u018b\5\26\f\2\u018b\u018c\b\22\1\2\u018c"+
		"\u0194\3\2\2\2\u018d\u018e\5\34\17\2\u018e\u018f\b\22\1\2\u018f\u0194"+
		"\3\2\2\2\u0190\u0191\5\36\20\2\u0191\u0192\b\22\1\2\u0192\u0194\3\2\2"+
		"\2\u0193\u0184\3\2\2\2\u0193\u0187\3\2\2\2\u0193\u018d\3\2\2\2\u0193\u0190"+
		"\3\2\2\2\u0194#\3\2\2\2 \63\669ILZ]`ly{\u0095\u00ab\u00e1\u00e3\u00ef"+
		"\u00f2\u0109\u0120\u012a\u012d\u0134\u013e\u0149\u0157\u015f\u0169\u0173"+
		"\u017e\u0193";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}