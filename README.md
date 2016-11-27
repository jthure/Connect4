# Connect4

## Build
To build a signed apk for release that can be used on android devices you can follow the steps
below. Before building, make sure you have the JAVA_HOME variable specified to a JDK installation,ie. C:\java\jdk1.8.0_066.
A JDK can be downloaded at http://www.oracle.com/technetwork/java/javase/overview/index.html.
Also, make sure you have a keystore containing a private key that you want to use for signing the apk.

### Steps
1. Clone the repository and change directory to the root of the repository in a terminal
2. Edit the values of four entries in the file keystore.properties to match your keystore
3. On Windows: Run gradlew.bat assembleRelease | On Linux: Run gradlew assembleRelease
4. The apk is located at Connect4\app\build\outputs\apk\app-release.apk. Transfer it to an Android device and install it.

