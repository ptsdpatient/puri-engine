param (
    [Parameter(Mandatory = $true)]
    [string]$NewPackage
)

$projectRoot = Get-Location
$srcRoot = "$projectRoot/app/src/main/java"
$gradleFile = "$projectRoot/app/build.gradle.kts"

Write-Host "Changing package to: $NewPackage"

# --------------------------------
# 1. Update build.gradle.kts
# --------------------------------
(Get-Content $gradleFile) `
    -replace 'applicationId\s*=\s*".*?"', "applicationId = `"$NewPackage`"" `
    -replace 'namespace\s*=\s*".*?"', "namespace = `"$NewPackage`"" |
Set-Content $gradleFile

# --------------------------------
# 2. Find MainActivity.java
# --------------------------------
$mainActivity = Get-ChildItem $srcRoot -Recurse -Filter MainActivity.java | Select-Object -First 1
if (-not $mainActivity) {
    Write-Error "MainActivity.java not found"
    exit 1
}

$oldDir = $mainActivity.Directory.FullName

# --------------------------------
# 3. Read old package name
# --------------------------------
$packageLine = Select-String -Path $mainActivity.FullName -Pattern '^package '
if (-not $packageLine) {
    Write-Error "Package declaration not found"
    exit 1
}

$oldPackage = ($packageLine.Line -replace 'package\s+|;', '').Trim()
Write-Host "Old package: $oldPackage"

# --------------------------------
# 4. Update package declaration in ALL Java files
# --------------------------------
Get-ChildItem $oldDir -Filter *.java | ForEach-Object {
    (Get-Content $_.FullName) `
        -replace "package\s+$oldPackage;", "package $NewPackage;" |
    Set-Content $_.FullName
}

# --------------------------------
# 5. Create new folder structure
# --------------------------------
$newDir = Join-Path $srcRoot ($NewPackage -replace '\.', '\')

if (!(Test-Path $newDir)) {
    New-Item -ItemType Directory -Path $newDir -Force | Out-Null
}

# --------------------------------
# 6. Move Java files
# --------------------------------
Get-ChildItem $oldDir -Filter *.java | Move-Item -Destination $newDir -Force

# --------------------------------
# 7. Clean up old empty folders
# --------------------------------
if ((Get-ChildItem $oldDir -Recurse | Measure-Object).Count -eq 0) {
    Remove-Item $oldDir -Recurse -Force
}

Write-Host "Package updated successfully"
Write-Host "New source folder: $newDir"
