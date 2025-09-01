#!/bin/bash

# Compile the Java files
javac --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.media,javafx.graphics -cp src/main/java src/main/java/com/slotmachine/*.java

# Run the application
java --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.media,javafx.graphics -cp "src/main/java:src/main/resources" com.slotmachine.SlotsMachineApp
