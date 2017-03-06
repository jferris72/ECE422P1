

/* insertion sort ascending order */
// code for insertion sort from: http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
 
#include <stdio.h>
#include <jni.h>
#include "InsertionSort.h"

 JNIEXPORT jint JNICALL Java_InsertionSort_insertsort
(JNIEnv *env, jobject object, jintArray buf, jint length) {

  jintArray array[length+1];
  int hazard = 0;

  array = (*env)->GetIntArrayElements(env, buf, is_copy);

  for (c = 1 ; c <= length - 1; c++) {
    d = c;

    while ( d > 0 && array[d] < array[d-1]) {
      t          = array[d];
      array[d]   = array[d-1];
      array[d-1] = t;
 
      d--;
    }
  }

  array[length+1] = hazard;

  return array;

}