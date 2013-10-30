all:
	export PATH="/Users/apple/Documents/eclipseWorkspace/CompilersPS3-2/src":"${PATH}"
	export CLASSPATH=".:/usr/local/lib/antlr-4.1-complete.jar:${CLASSPATH}"
	alias antlr4="java -jar /usr/local/lib/antlr-4.1-complete.jar"
	alias grun="java org.antlr.v4.runtime.misc.TestRig"
	[[ -s ~/.bashrc ]] && source ~/.bashrc
	java -jar /usr/local/lib/antlr-4.1-complete.jar src/*.g4 -no-listener
	javac src/*.java
	jar cvfm CGen.jar src/MANIFEST.MF src/*.class src/*.java src/*.g4

test:
	chmod +x TypeCheck.jar
	bash TestCases TypeCheck.jar

clean:
	rm bin/*.class
	rm src/*.class
	rm *.class
	rm *.jar
	rm *.tokens

old_test:
	java -jar TypeCheck.jar tests/tc_test1.in
	java -jar TypeCheck.jar tests/tc_test2.in
