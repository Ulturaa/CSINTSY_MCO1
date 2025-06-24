set -xeu

src_dir="./src/"
classes=("file_man" "graphrel")

for i in "${classes[@]}"; do
  javac $src_dir"$i/"*.java
done

java src.graphrel.Main
