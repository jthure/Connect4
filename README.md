# Connect4

## Build
You can either build an apk for debug or a signed apk for release, the latter is needed to be able to install and run app on a device.

### Build signed apk for release
1. Make sure you have a keystore containing a private key that you want to use for signing the apk
2. Clone the repository and change directory to the root of the repository in a terminal
3. Edit the values of four entries in the file keystore.properties to match your keystore
4. On Windows: Run gradlew.bat assembleRelease | On Linux: Run gradlew assembleRelease
5. The apk is located at Connect4\app\build\outputs\apk\app-release.apk. Transfer it to an Android device and install it.

### Build apk for debug
1. Clone the repository and change directory to the root of the repository in a terminal
2. On Windows: Run gradlew.bat assembleDebug | On Linux: Run gradlew assembleDebug
3. The apk is located at Connect4\app\build\outputs\apk\app-debug.apk.
