OUTPUT = bin
SOURCES := $(shell find src src1.4 -type f -name \*.java)
LIBS = lib/java-getopt-1.0.13.jar:lib/log4j-java1.1.jar
FLAGS = -target 1.5 -classpath $(LIBS) -d $(OUTPUT)

compile:
	@(javac $(FLAGS) $(SOURCES))
	@(find keymaps -type f -not -regex ".*svn.*" -exec cp {} bin/{} \;)

jar: compile
	@(jar cvmf manifest.mf rdp.jar -C bin/ .)

all: jar

# signlib:
# 	@(jarsigner -signedjar libs/signedvnc.jar libs/vnc.jar steam)
# 	@(jarsigner -signedjar libs/signedjsch.jar libs/jsch-0.1.44.jar steam)

# sign: jar
# 	@(jarsigner -signedjar signedsteam.jar steam.jar steam)
# 	@(rm steam.jar)

# clean:
# 	@(rm bin/*.class)

# deploy: sign 
# 	@(mv signedsteam.jar examples/applet/steam.jar)
# 	@(cp libs/signedvnc.jar examples/applet/vnc.jar)
# 	@(cp libs/signedjsch.jar examples/applet/jsch-0.1.44.jar)




