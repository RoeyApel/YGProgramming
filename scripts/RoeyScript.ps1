Clear-Host

[String[]] $EXTENSIONLIST =
    "vscjava.vscode-java-pack",
    "vscode-icons-team.vscode-icons"

ForEach ( $EXTENSION In $EXTENSIONLIST )
{
    code --install-extension $EXTENSION
}

Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

choco install obsidian github-desktop -y

Clear-Host
Write-Host "Script Finished!"

pause