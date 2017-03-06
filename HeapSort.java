    /*
     *
     * Java Program to Implement Heap Sort
     * Code taken from website:
     * http://www.sanfoundry.com/java-program-implement-heap-sort/
     */

     

    import java.util.Scanner;

     

    /* Class HeapSort */

    public class HeapSort 

    {    

        private static int N;
        private int hazard;

        public HeapSort() {
            this.hazard = 0;
        }

        /* Sort Function */

        public void sort(int arr[])

        {       

            heapify(arr);        

            for (int i = N; i > 0; i--)

            {

                swap(arr,0, i);

                N = N-1;

                hazard++;

                maxheap(arr, 0);

            }

        }     

        /* Function to build a heap */   

        public void heapify(int arr[])

        {

            N = arr.length-1;

            hazard++;

            for (int i = N/2; i >= 0; i--) maxheap(arr, i);

            hazard++;        

        }

        /* Function to swap largest element in heap */        

        public void maxheap(int arr[], int i)

        { 

            int left = 2*i ;

            hazard++;

            int right = 2*i + 1;

            hazard++;

            int max = i;

            hazard++;

            if (left <= N && arr[left] > arr[i]) max = left;

            hazard++;

            if (right <= N && arr[right] > arr[max]) max = right;

            hazard++;


     

            if (max != i)

            {

                swap(arr, i, max);

                maxheap(arr, max);

            }


        }    

        /* Function to swap two numbers in an array */

        public void swap(int arr[], int i, int j)

        {

            int tmp = arr[i];

            hazard++;

            arr[i] = arr[j];

            hazard++;

            arr[j] = tmp; 

            hazard++;

        }      

        public int getHazard() {
            return this.hazard;
        }
    }