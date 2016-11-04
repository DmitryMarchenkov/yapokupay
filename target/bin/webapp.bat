@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\springframework\spring-jdbc\4.0.3.RELEASE\spring-jdbc-4.0.3.RELEASE.jar;"%REPO%"\org\springframework\spring-core\4.0.3.RELEASE\spring-core-4.0.3.RELEASE.jar;"%REPO%"\commons-logging\commons-logging\1.1.3\commons-logging-1.1.3.jar;"%REPO%"\mysql\mysql-connector-java\5.1.9\mysql-connector-java-5.1.9.jar;"%REPO%"\org\springframework\spring-webmvc\4.0.3.RELEASE\spring-webmvc-4.0.3.RELEASE.jar;"%REPO%"\org\springframework\spring-context\4.0.3.RELEASE\spring-context-4.0.3.RELEASE.jar;"%REPO%"\org\springframework\spring-aop\4.0.3.RELEASE\spring-aop-4.0.3.RELEASE.jar;"%REPO%"\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;"%REPO%"\org\springframework\spring-expression\4.0.3.RELEASE\spring-expression-4.0.3.RELEASE.jar;"%REPO%"\org\springframework\spring-web\4.0.3.RELEASE\spring-web-4.0.3.RELEASE.jar;"%REPO%"\org\springframework\spring-tx\4.0.3.RELEASE\spring-tx-4.0.3.RELEASE.jar;"%REPO%"\org\hibernate\hibernate-core\4.3.5.Final\hibernate-core-4.3.5.Final.jar;"%REPO%"\org\jboss\logging\jboss-logging\3.1.3.GA\jboss-logging-3.1.3.GA.jar;"%REPO%"\org\jboss\logging\jboss-logging-annotations\1.2.0.Beta1\jboss-logging-annotations-1.2.0.Beta1.jar;"%REPO%"\org\jboss\spec\javax\transaction\jboss-transaction-api_1.2_spec\1.0.0.Final\jboss-transaction-api_1.2_spec-1.0.0.Final.jar;"%REPO%"\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;"%REPO%"\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;"%REPO%"\org\hibernate\common\hibernate-commons-annotations\4.0.4.Final\hibernate-commons-annotations-4.0.4.Final.jar;"%REPO%"\org\hibernate\javax\persistence\hibernate-jpa-2.1-api\1.0.0.Final\hibernate-jpa-2.1-api-1.0.0.Final.jar;"%REPO%"\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;"%REPO%"\antlr\antlr\2.7.7\antlr-2.7.7.jar;"%REPO%"\org\jboss\jandex\1.1.0.Final\jandex-1.1.0.Final.jar;"%REPO%"\org\hibernate\hibernate-entitymanager\4.3.5.Final\hibernate-entitymanager-4.3.5.Final.jar;"%REPO%"\commons-dbcp\commons-dbcp\1.4\commons-dbcp-1.4.jar;"%REPO%"\commons-pool\commons-pool\1.5.4\commons-pool-1.5.4.jar;"%REPO%"\org\springframework\spring-orm\4.0.3.RELEASE\spring-orm-4.0.3.RELEASE.jar;"%REPO%"\org\aspectj\aspectjrt\1.7.4\aspectjrt-1.7.4.jar;"%REPO%"\javax\inject\javax.inject\1\javax.inject-1.jar;"%REPO%"\javax\servlet\jstl\1.2\jstl-1.2.jar;"%REPO%"\org\codehaus\jackson\jackson-mapper-asl\1.7.1\jackson-mapper-asl-1.7.1.jar;"%REPO%"\org\codehaus\jackson\jackson-core-asl\1.7.1\jackson-core-asl-1.7.1.jar;"%REPO%"\postgresql\postgresql\9.1-901.jdbc4\postgresql-9.1-901.jdbc4.jar;"%REPO%"\org\springframework\spring-beans\4.0.3.RELEASE\spring-beans-4.0.3.RELEASE.jar;"%REPO%"\com\ya\pokupay\YaPokupay\1.0.0-BUILD-SNAPSHOT\YaPokupay-1.0.0-BUILD-SNAPSHOT.war
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" com/ya/pokupay/MainController %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
