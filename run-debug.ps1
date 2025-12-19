param(
    [Parameter(Mandatory = $false)]
    [string]$AvdName
)

Write-Host "=== Android Run Debug Script ==="

# -----------------------------
# SDK Paths
# -----------------------------
$SDK = $env:ANDROID_HOME
if (-not $SDK) {
    $SDK = "C:\tools\AndroidSDK"
}

$emulator = "$SDK\emulator\emulator.exe"
$adb      = "$SDK\platform-tools\adb.exe"
$gradlew  = ".\gradlew.bat"

if (!(Test-Path $emulator)) { Write-Error "Emulator not found"; exit 1 }
if (!(Test-Path $adb))      { Write-Error "ADB not found"; exit 1 }


# emulator name default
$AvdName = "Medium_Phone"


# -----------------------------
# Start emulator if needed
# -----------------------------
$device = & $adb devices | Select-String "emulator-"

if (-not $device) {
    Write-Host "Starting emulator: $AvdName"
    Start-Process $emulator -ArgumentList "-avd `"$AvdName`"" -NoNewWindow
    & $adb wait-for-device
} else {
    Write-Host "Emulator already running"
}

# -----------------------------
# Wait for boot
# -----------------------------
Write-Host "Waiting for emulator to boot..."
do {
    Start-Sleep 2
    $booted = & $adb shell getprop sys.boot_completed 2>$null
} while ($booted -ne "1")

Write-Host "Emulator booted"

# -----------------------------
# Build APK
# -----------------------------
Write-Host "Building debug APK..."
& $gradlew assembleDebug
if ($LASTEXITCODE -ne 0) {
    Write-Error "Gradle build failed"
    exit 1
}

# -----------------------------
# Locate APK
# -----------------------------
$apk = Get-ChildItem -Recurse -Filter "*debug*.apk" app\build\outputs\apk |
       Select-Object -First 1

if (-not $apk) {
    Write-Error "APK not found"
    exit 1
}

Write-Host "APK found: $($apk.FullName)"

# -----------------------------
# Install APK
# -----------------------------
Write-Host "Installing APK..."
& $adb install -r $apk.FullName
if ($LASTEXITCODE -ne 0) {
    Write-Error "APK install failed"
    exit 1
}

# -----------------------------
# Detect applicationId
# -----------------------------
function Get-ApplicationId {
    foreach ($line in Get-Content "app\build.gradle.kts") {
        if ($line -match 'applicationId\s*=\s*"([^"]+)"') {
            return $matches[1]
        }
    }
    Write-Error "applicationId not found"
    exit 1
}

$PACKAGE = Get-ApplicationId
Write-Host "Detected package: $PACKAGE"

# -----------------------------
# Launch app
# -----------------------------
Write-Host "Launching app..."
& $adb shell am start -n "$PACKAGE/.MainActivity"

Write-Host "`n✅ App launched successfully"
