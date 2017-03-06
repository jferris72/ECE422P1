

/* insertion sort ascending order */
// code for insertion sort from: http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
 
#include <stdio.h>
#include <jni.h>
#include "InsertionSort.h"

 JNIEXPORT jintArray JNICALL Java_InsertionSort_insertsort
(JNIEnv *env, jobject object, jintArray buf, jint length) {

  jint *array;
  int hazard = 0;
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

  jintArray tempArray;
  tempArray = (*env)->NewIntArray(env, len+1);
  (*env)->SetIntArrayRegion(env, tempArray, 0, len, array);

  FILE *fp = fopen("hazard", "w");

  fprintf(fp, "%d\n", hazard);

  fclose(fp);

  return tempArray;

}