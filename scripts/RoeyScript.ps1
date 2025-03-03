Clear-Host

Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

choco install github-desktop obsidian -y

$openjdk =
@{
    version = "17.0.2";
    zip_url = "https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip";
    sha_url = "https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip.sha256"
}

$java_folder = "$env:ProgramFiles\Java"

$jdk_folder = "$java_folder\jdk\jdk-" + $openjdk.version
if ($jdk_folder.EndsWith(".0.0"))
{
    $jdk_folder = $jdk_folder.Substring(0, $jdk_folder.Length - 4)
}

$bin_folder = "$jdk_folder\bin"

New-Item -ItemType directory -Path $java_folder -Force | Out-Null
Set-Location $java_folder

$jdk_zip_file = 'jdk.zip'
$jdk_sha_file = 'sha.sha256'

Invoke-WebRequest -Uri $openjdk.zip_url -OutFile $jdk_zip_file
Invoke-WebRequest -Uri $openjdk.sha_url -OutFile $jdk_sha_file

$computed_hash = (Get-FileHash -Algorithm SHA256 -Path $jdk_zip_file).Hash
$existing_hash = Get-Content -Path $jdk_sha_file

if ($computed_hash -ne $existing_hash) {
  Remove-Item -Path $jdk_zip_file
  Remove-Item -Path $jdk_sha_file

  Write-Error -Message 'The checksum of the downloaded JDK is incorrect. The file may be corrupt.'
  Write-Error -Message 'This might be resolved by re-running this script.'
  $host.EnterNestedPrompt()
  return
}

Expand-Archive -Path $jdk_zip_file
Remove-Item -Path $jdk_zip_file
Remove-Item -Path $jdk_sha_file

$path = [Environment]::GetEnvironmentVariable('Path', 'Machine')
[Environment]::SetEnvironmentVariable('Path', $path + ';' + $bin_folder, 'Machine')

[Environment]::SetEnvironmentVariable('JAVA_HOME', $jdk_folder, 'Machine')
[Environment]::SetEnvironmentVariable('JDK_HOME', '%JAVA_HOME%', 'Machine')
[Environment]::SetEnvironmentVariable('JRE_HOME', '%JAVA_HOME%', 'Machine')

Clear-Host
Write-Host "Script Finished!"

pause
