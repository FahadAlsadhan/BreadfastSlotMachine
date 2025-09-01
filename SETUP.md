# Slot Machine Project Setup

## Prerequisites
- Java 18 or higher
- Git

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/FahadAlsadhan/BreadfastSlotMachine.git
cd BreadfastSlotMachine
```

### 2. Download Required Resources

#### Download JavaFX SDK
```bash
curl -L -o javafx-sdk.zip "https://download2.gluonhq.com/openjfx/18.0.2/openjfx-18.0.2_osx-x64_bin-sdk.zip"
unzip javafx-sdk.zip
```

#### Download Game Resources
You need to download the image and audio files separately due to size limitations.

### 3. Run the Application

#### From Command Line
```bash
./run.sh
```

#### From IntelliJ IDEA
1. Open the project in IntelliJ IDEA
2. Configure the run configuration with VM options:
   ```
   --module-path "javafx-sdk-18.0.2/lib" --add-modules javafx.controls,javafx.media,javafx.graphics,javafx.base
   ```
3. Run the `com.slotmachine.SlotsMachineApp` class

## Game Controls
- Press **SPACE** to trigger the slot machine lever
- Click on the lever to pull it manually

## Troubleshooting
- If you get "JavaFX runtime components are missing" error, make sure the JavaFX SDK is downloaded and the VM options are set correctly
- If images don't load, ensure all image files are in the `src/main/resources/images/` directory
- If audio doesn't work, ensure all audio files are in the `src/main/resources/audio/` directory
