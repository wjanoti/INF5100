all: clean
	javac Tick.java
	javac -classpath .:../esper-4.9.0/esper-4.9.0.jar INF5100.java


run:
	java -classpath .:../esper-4.9.0/esper-4.9.0.jar:../esper-4.9.0/esper/lib/* INF5100

clean:
	rm -f *.class

