javac -d compiled_classes -classpath Pokemon.jar src/*.java src/*/*.java
jar -cfm lab2.jar META-INF/MANIFEST.MF -C compiled_classes .

