#!/usr/bin/env bash
set -euo pipefail

# ----- CONFIG -----
MAIN_CLASS="capstone.core.CapstoneCore"   # change if your main class differs
JAR_NAME="SmartStudentPlatform.jar"
SRC_GLOB="*.java"
PKG_DIR="capstone"
JAVA_OPTS=""   # add JVM options here if needed, e.g. -Xmx512m

# ----- helpers -----
check_cmd() {
  command -v "$1" >/dev/null 2>&1 || { echo "Required command '$1' not found. Install the JDK (javac/java/jar)." >&2; exit 1; }
}

compile() {
  echo "Compiling Java sources..."
  check_cmd javac
  # remove old package dir so javac -d starts fresh
  rm -rf "$PKG_DIR"
  javac -d . $SRC_GLOB
  echo "Compiled → $PKG_DIR/"
}

make_jar() {
  echo "Creating JAR: $JAR_NAME"
  check_cmd jar
  # use -C . PACKAGE_DIR so package structure is preserved
  jar cfe "$JAR_NAME" "$MAIN_CLASS" -C . "$PKG_DIR"
  echo "Created $JAR_NAME"
}

run_app() {
  check_cmd java
  # if DISPLAY is present run normally; otherwise try xvfb-run if installed
  if [ -n "${DISPLAY-:-}" ]; then
    echo "DISPLAY found ($DISPLAY) — running app..."
    java $JAVA_OPTS "$MAIN_CLASS" "$@"
  else
    if command -v xvfb-run >/dev/null 2>&1; then
      echo "No DISPLAY — attempting xvfb-run (may still fail if environment lacks headful libs)..."
      xvfb-run java $JAVA_OPTS "$MAIN_CLASS" "$@"
    else
      echo "No DISPLAY set and xvfb-run not installed. Either run on a desktop/X11-forwarded SSH session or install xvfb." >&2
      exit 1
    fi
  fi
}

clean() {
  echo "Cleaning compiled artifacts..."
  rm -rf "$PKG_DIR" *.class "$JAR_NAME"
  echo "Clean complete."
}

# ----- CLI -----
case "${1:-}" in
  build|compile)
    compile
    ;;
  jar)
    make_jar
    ;;
  run)
    shift || true
    run_app "$@"
    ;;
  all)
    # compile + jar + run (pass remaining args to java)
    compile
    make_jar
    shift || true
    run_app "$@"
    ;;
  clean)
    clean
    ;;
  *)
    cat <<EOF
Usage: $0 {build|jar|run|all|clean} [args...]
  build      Compile .java -> package directory (capstone/...)
  jar        Create $JAR_NAME from compiled package
  run        Run the application (pass arguments after 'run')
  all        build, jar, then run (pass args after 'all')
  clean      Remove compiled files and jar

Examples:
  $0 build
  $0 run
  $0 run 12345           # passes "12345" as program argument
  $0 all arg1 arg2       # compile, jar, then run with args
EOF
    exit 1
    ;;
esac
