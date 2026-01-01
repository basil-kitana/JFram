$libPath = ".\lib\mysql-connector-j-8.4.0.jar"
if (-not (Test-Path $libPath)) {
    $found = Get-ChildItem -Path ".\lib" -Filter "mysql-connector*.jar" -Recurse | Select-Object -First 1
    if ($found) {
        $libPath = $found.FullName
    } else {
        Write-Host "Usage: Please place the 'mysql-connector-j-X.X.X.jar' file inside the 'lib' folder." -ForegroundColor Red
        Write-Host "Download it from: https://dev.mysql.com/downloads/connector/j/"
        exit 1
    }
}

Write-Host "Compiling..."
javac -cp ".;$libPath" *.java
if ($LASTEXITCODE -eq 0) {
    Write-Host "Running..."
    java -cp ".;$libPath" Frame1
} else {
    Write-Host "Compilation failed." -ForegroundColor Red
}
