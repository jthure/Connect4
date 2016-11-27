# Connect4

## Build
You can either build an apk for debug or a signed apk for release, the latter is needed to be able to install and run app on a device.

### Build signed apk for release
1. Make sure you have the JAVA_HOME variable specified to a JDK installation, ie. C:\java\jdk1.8.0_066
2. Make sure you have a keystore containing a private key that you want to use for signing the apk
3. Clone the repository and change directory to the root of the repository in a terminal
4. Edit the values of four entries in the file keystore.properties to match your keystore
5. On Windows: Run gradlew.bat assembleRelease | On Linux: Run gradlew assembleRelease
6. The apk is located at Connect4\app\build\outputs\apk\app-release.apk. Transfer it to an Android device and install it.

### Build apk for debug
1. Make sure you have the JAVA_HOME variable specified to a JDK installation, ie. C:\java\jdk1.8.0_066
3. Clone the repository and change directory to the root of the repository in a terminal
3. On Windows: Run gradlew.bat assembleDebug | On Linux: Run gradlew assembleDebug
4. The apk is located at Connect4\app\build\outputs\apk\app-debug.apk.
