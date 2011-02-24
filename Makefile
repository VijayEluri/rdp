OUTPUT = bin
SOURCES := $(shell find src src1.4 -type f -name \*.java)
LIBS = lib/java-getopt-1.0.13.jar:lib/log4j-java1.1.jar
FLAGS = -target 1.5 -classpath $(LIBS) -d $(OUTPUT)
KEYSTORE_ALIAS = "dev"
KEYSTORE_PASS = "123456"

all: jar

build:
	@(mkdir -p bin/keymaps)
	@(javac $(FLAGS) $(SOURCES))
	@(find keymaps -type f -not -regex ".*svn.*" -exec cp {} bin/{} \;)

unjarlibs:
	cd bin; find ../lib -type f -not -regex ".*svn.*" -name \*.jar -exec jar xfv {} \;
	rm -rf bin/META-INF

jar: build unjarlibs
	@(jar cmf manifest.mf rdp.jar -C bin/ .)

sign: jar
	@(jarsigner -storepass $(KEYSTORE_PASS) -signedjar rdps.jar rdp.jar $(KEYSTORE_ALIAS))
	@(mv rdps.jar rdp.jar)

# make ARGS=localhost:3389 run
run: 
	java -jar rdp.jar $(ARGS)

runserver: deploy 
	@(python ./examples/dev_appserver.py)

deploy: sign 
	@(mkdir -p examples/applet)
	@(cp rdp.jar examples/applet/; cp -r lib examples/applet/)

