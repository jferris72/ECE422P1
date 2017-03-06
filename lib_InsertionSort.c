

/* insertion sort ascending order */
// code for insertion sort from: http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
 
#include <stdio.h>
#include <jni.h>
#include "InsertionSort.h"

 JNIEXPORT jintArray JNICALL Java_InsertionSort_insertsort
(JNIEnv *env, jobject object, jintArray buf, jint length) {

  jint *array;
  jint *hazard = 0;
  jint d, c, t;

  jsize len = (*env)->GetArrayLength(env, buf);
  array = (jint *)(*env)->GetIntArrayElements(env, buf, 0);

  for (c = 1 ; c <= length - 1; c++) {
    d = c;
    hazard+= 3;

    while ( d > 0 && array[d] < array[d-1]) {
      t          = array[d];
      array[d]   = array[d-1];
      array[d-1] = t;
 
      d--;
      hazard += 9;
    }
  }

  // array[length] = hazard;
  printf("hazard: %d\n", hazard);
  jintArray tempArray;
  tempArray = (*env)->NewIntArray(env, len+1);
  (*env)->SetIntArrayRegion(env, tempArray, 0, len, array);

  (*env)->SetIntArrayRegion(env, tempArray, len, 1, hazard);

  return tempArray;

}