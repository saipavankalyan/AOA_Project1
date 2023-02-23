JAVAC = javac
JAVA = java
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

.PHONY: run1 run2 run3 run4 clean 

all: $(classes)

$(classes): %.class: %.java
	$(JAVAC) $<

run1: EarliestStartTime.class
	$(JAVA) EarliestStartTime

run2: LatestStartTime.class
	$(JAVA) LatestStartTime

run3: MinimumTime.class 
	$(JAVA) MinimumTime

run4: EarliestEndTime.class
	$(JAVA) EarliestEndTime

clean:
	rm -f *.class
