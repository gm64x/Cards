#!/usr/bin/env bash
# rename_portuguese.sh
# Best-effort renaming of common Portuguese identifiers to English.

set -euo pipefail
DRY_RUN=1
RENAME_FILES=0
ROOT="."

while [[ $# -gt 0 ]]; do
  case "$1" in
    --apply) DRY_RUN=0; shift;;
    --rename-files) RENAME_FILES=1; shift;;
    --root) ROOT="$2"; shift 2;;
    -h|--help) echo "Usage: $0 [--apply] [--rename-files] [--root PATH]" ; exit 0;;
    *) echo "Unknown arg: $1"; exit 1;;
  esac
done

# Ordered replacements
REPL_OLD=("cartasShuffle" "cartasEasy" "CartasShuffle" "CartasEasy" "cartas" "Cartas" "pergunta" "Pergunta" "resposta" "Resposta" "dificuldade" "Dificuldade" "Carta" "carta")
REPL_NEW=("cardsShuffle" "cardsEasy" "CardsShuffle" "CardsEasy" "cards" "Cards" "question" "Question" "answer" "Answer" "difficulty" "Difficulty" "Card" "card")

FILES=$(find "$ROOT" -type f \( -name '*.java' -o -name '*.md' -o -name '*.fxml' -o -name '*.xml' -o -name '*.properties' -o -name '*.txt' -o -name 'pom.xml' -o -name '*.yml' -o -name '*.yaml' -o -name '*.gradle' \) -not -path '*/.git/*' -not -path '*/target/*')

declare -A summary

for f in $FILES; do
  content=$(cat "$f")
  new="$content"
  total=0
  for i in "${!REPL_OLD[@]}"; do
    old=${REPL_OLD[i]}
    newval=${REPL_NEW[i]}
    cnt=$(grep -o "$old" "$f" | wc -l || true)
    if [ "$cnt" -gt 0 ]; then
      new=$(printf "%s" "$new" | sed "s/${old}/${newval}/g")
      total=$((total + cnt))
    fi
  done
  if [ "$total" -gt 0 ]; then
    summary["$f"]=$total
    if [ "$DRY_RUN" -eq 0 ]; then
      cp "$f" "$f.bak"
      printf "%s" "$new" > "$f"
    fi
  fi
done

if [ ${#summary[@]} -eq 0 ]; then
  echo "No occurrences found."
  exit 0
fi

for k in "${!summary[@]}"; do
  echo "$k : ${summary[$k]} replacements"
done

if [ "$DRY_RUN" -eq 1 ]; then
  echo "Dry-run complete. Run with --apply to modify files (backs up files with .bak)."
else
  echo "Applied replacements. backups in .bak files."
fi

if [ "$RENAME_FILES" -eq 1 ]; then
  echo "Renaming files not implemented in shell script; use PowerShell script for renames or implement cautiously."
fi

exit 0
