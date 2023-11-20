<#
rename_portuguese.ps1

Best-effort script to rename common Portuguese identifiers to English across the repository.

Usage:
  .\rename_portuguese.ps1            # dry-run (shows summary)
  .\rename_portuguese.ps1 -Apply    # apply changes (creates .bak backups)
  .\rename_portuguese.ps1 -Apply -RenameFiles  # also rename filenames containing matches

Notes:
- Dry-run is the default; review output before applying.
- The script does plain-text replacements and may break build or runtime behavior. Create a git branch and backups before applying.
#>

param(
    [switch]$Apply,
    [switch]$RenameFiles,
    [string]$Root = "."
)

# Ordered replacements (longer keys first)
$replacements = @(
    @{old='cartasShuffle'; new='cardsShuffle'},
    @{old='cartasEasy'; new='cardsEasy'},
    @{old='CartasShuffle'; new='CardsShuffle'},
    @{old='CartasEasy'; new='CardsEasy'},
    @{old='cartas'; new='cards'},
    @{old='Cartas'; new='Cards'},
    @{old='pergunta'; new='question'},
    @{old='Pergunta'; new='Question'},
    @{old='resposta'; new='answer'},
    @{old='Resposta'; new='Answer'},
    @{old='dificuldade'; new='difficulty'},
    @{old='Dificuldade'; new='Difficulty'},
    @{old='Carta'; new='Card'},
    @{old='carta'; new='card'}
)

$include = @('*.java','*.md','*.fxml','*.xml','*.properties','*.txt','pom.xml','*.yml','*.yaml','*.gradle','README.md')

Write-Host "Scanning files under: $Root"

$files = Get-ChildItem -Path $Root -Recurse -File -Include $include -ErrorAction SilentlyContinue | Where-Object { $_.FullName -notmatch '\\.(git|svn)\\' -and $_.FullName -notmatch '\\btarget\\b' }

if (-not $files) { Write-Host "No files matched search patterns."; exit 0 }

$summary = @{}

foreach ($file in $files) {
    try {
        $content = Get-Content -Raw -LiteralPath $file.FullName -ErrorAction Stop
    } catch {
        continue
    }
    $new = $content
    $totalRepl = 0
    foreach ($r in $replacements) {
        $cnt = ([regex]::Matches($new, [regex]::Escape($r.old))).Count
        if ($cnt -gt 0) {
            $new = $new -replace [regex]::Escape($r.old), $r.new
            $totalRepl += $cnt
        }
    }
    if ($totalRepl -gt 0) {
        $summary[$file.FullName] = $totalRepl
        if ($Apply) {
            Copy-Item -LiteralPath $file.FullName -Destination ($file.FullName + '.bak') -Force -ErrorAction SilentlyContinue
            Set-Content -LiteralPath $file.FullName -Value $new -Encoding UTF8
        }
    }
}

if ($summary.Count -eq 0) { Write-Host "No configured Portuguese identifiers found."; exit 0 }

Write-Host "Files with matches:"
foreach ($k in $summary.Keys) { Write-Host ("{0} : {1} replacements" -f $k, $summary[$k]) }

if (-not $Apply) {
    Write-Host "`nDry-run complete. To apply changes run with -Apply. Backups (.bak) will be created when applying.";
} else {
    Write-Host "`nApplied replacements. Backups saved with .bak extension.";
}

if ($RenameFiles) {
    Write-Host "`nRenaming filenames (RenameFiles flag set)..."
    $allFiles = Get-ChildItem -Path $Root -Recurse -File -ErrorAction SilentlyContinue | Where-Object { $_.FullName -notmatch '\\.(git|svn)\\' -and $_.FullName -notmatch '\\btarget\\b' }
    $renamed = 0
    foreach ($f in $allFiles) {
        $newName = $f.Name
        foreach ($r in $replacements) {
            if ($newName -like "*${($r.old)}*") {
                $newName = $newName -replace [regex]::Escape($r.old), $r.new
            }
        }
        if ($newName -ne $f.Name) {
            $dest = Join-Path $f.DirectoryName $newName
            Write-Host ("Rename: {0} -> {1}" -f $f.FullName, $dest)
            if ($Apply) {
                if (-not (Test-Path $dest)) {
                    Rename-Item -LiteralPath $f.FullName -NewName $newName
                    $renamed++
                } else {
                    Write-Warning ("Destination exists, skipping rename: {0}" -f $dest)
                }
            }
        }
    }
    Write-Host ("Renames performed: {0}" -f $renamed)
    if (-not $Apply) { Write-Host "To actually rename files add -Apply." }
}

Write-Host "Done." 
