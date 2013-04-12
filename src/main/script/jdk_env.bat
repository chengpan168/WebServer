echo set jdk envirenment
set JAVA_HOME=%cd%
echo JAVA_HOME IS %cd%
set path=%JAVA_HOME%\bin;%path%
set CLASSPATH=.;%JAVA_HOME%\jre\lib
