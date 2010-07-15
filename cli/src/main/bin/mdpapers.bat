
@echo off

set LIB = "%~dp0..\lib"
set JARS = "%LIB%\commons-cli-1.2.jar;%LIB%\markdownpapers-core-0.1.0-SNAPSHOT.jar;%LIB%\markdownpapers-cli-0.1.0-SNAPSHOT.jar"

java -classpath "%JARS%" org.tautua.markdownpapers.cli.Main "$@"
