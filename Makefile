b: buildw
buildw:
	./gradlew clean build test jacocoTestReport -i
	gradle
install-jacococli:
	wget https://search.maven.org/remotecontent\?filepath\=org/jacoco/jacoco/0.8.7/jacoco-0.8.7.zip
	unzip remotecontent\?filepath=org%2Fjacoco%2Fjacoco%2F0.8.7%2Fjacoco-0.8.7.zip
unpack-reports:
	mkdir -p jacoco
	java -jar lib/jacococli.jar report catcher-app-android/build/jacoco/testReleaseUnitTest.exec --classfiles catcher-app-android/build/.transforms/*/transformed/out/jars/classes.jar --xml jacoco/jacocoRelease.xml
	java -jar lib/jacococli.jar report catcher-app-android/build/jacoco/testDebugUnitTest.exec --classfiles catcher-app-android/build/.transforms/*/transformed/out/jars/classes.jar --xml jacoco/jacocoDebug.xml
upgrade:
	gradle wrapper --gradle-version 8.0.1
coverage:
	./gradlew clean build test jacocoTestReport
	./gradlew -i
