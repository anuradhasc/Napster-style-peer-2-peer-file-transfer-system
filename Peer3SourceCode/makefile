JFLAGS = -g
JC = javac
RM = rm -f

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Peer3Server.java\
	PeerClient.java\
	Peer3Client.java\
	PeerClient2.java\
	MainClient.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
