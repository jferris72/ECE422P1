JAVA_HOME = /usr/lib/jvm/java-8-openjdk-amd64

make:
	javac *.java
	javah InsertionSort
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o libinsertsort.so lib_InsertionSort.c
# path: 
# 	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.