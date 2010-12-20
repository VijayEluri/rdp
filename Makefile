OUTPUT = bin
SOURCES := $(shell find src src1.4 -type f -name \*.java)
LIBS = lib/java-getopt-1.0.13.jar:lib/log4j-java1.1.jar
FLAGS = -target 1.5 -classpath $(LIBS) -d $(OUTPUT)
KEYSTORE_ALIAS = "steam"

compile:
	@(javac $(FLAGS) $(SOURCES))
	@(mkdir -p bin/keymaps)
	@(find keymaps -type f -not -regex ".*svn.*" -exec cp {} bin/{} \;)

jar: compile
	@(jar cmf manifest.mf rdp.jar -C bin/ .)

sign: jar
	@(jarsigner -signedjar rdps.jar rdp.jar $(KEYSTORE_ALIAS))
	@(mv rdps.jar rdp.jar)

all: jar





