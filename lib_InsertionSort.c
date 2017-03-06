

/* insertion sort ascending order */
// code for insertion sort from: http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
 
#include <stdio.h>
#include <jni.h>
#include "InsertionSort.h"

 JNIEXPORT jintArray JNICALL Java_InsertionSort_insertsort
(JNIEnv *env, jobject object, jintArray buf, jint length) {

  jint *array;
  int hazard = 0;
  int d, c, t;

  array = (jint *)(*env)->GetIntArrayElements(env, buf, 0);

  for (c = 1 ; c <= length - 1; c++) {
    d = c;

    while ( d > 0 && array[d] < array[d-1]) {
      t          = array[d];
      array[d]   = array[d-1];
      array[d-1] = t;
 
      d--;
    }
  }

  // array[length] = hazard;

  return (jintArray)array;

}