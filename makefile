JAVA_HOME = /usr/lib/jvm/java-8-openjdk-amd64

all: 
	javac javah gcc path

javac:
	javac *.java
javah: 
	javah InsertionSort
gcc: 
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o libinsertsort.so InsertionSort.c
path: 
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.