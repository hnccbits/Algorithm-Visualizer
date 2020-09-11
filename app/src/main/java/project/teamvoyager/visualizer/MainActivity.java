package project.teamvoyager.visualizer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    private static final String TAG = "MyTag";
    String[] algo = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort",
            "Cocktail Shaker Sort","Radix Sort","Heap Sort"};
    int globalWidth;
    int globalHeight;
    Button bb;
    int arr[];
    int tt;
    int cc = 1;
    int i = 0, j = 0;
    int noOfItems;
    Queue<Integer> q1= new LinkedList<>();
    Queue<Integer> q2= new LinkedList<>();
    Queue<Integer> h1= new LinkedList<>();
    Queue<Integer> h2= new LinkedList<>();
    int c=0;
    LinearLayout root;
    int itemSelectedSpinner=0;
    Button start;
    boolean flag=false;
    int inProcess=0;
    Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        globalWidth = width;
        globalHeight=height;

        Log.d(TAG, "Height = "+globalHeight+"   Width = "+globalWidth);

        noOfItems = 60;
        arr = new int[noOfItems];
        createLayoutDynamically(noOfItems);

        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, algo);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        start=findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=true;
                Handler handler = new Handler();
                switch (itemSelectedSpinner){
                    case 0://bubble
                        inProcess=1;

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "Start Bubble Sort");

                            inProcess=1;
                                c=1;
                                cc=1;
                            BubbleSort();


                            }
                        }, 500);

                        Log.d(TAG, "run: Bubble Sort ended");

                        break;
                    case 1://selection
                        inProcess=2;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "Start Selection Sort");
                                inProcess=2;
                                c=1;
                                cc=1;

                                SelectionSort();

                            }
                        }, 500);
                        break;
                    case 2://insertion
                        inProcess=3;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                inProcess=3;
                                Log.d(TAG, "Start Insertion Sort");
                                c=1;
                                cc=1;

                                InsertionSort();

                            }
                        }, 500);
                        break;
                    case 3://merge
                        inProcess=4;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                inProcess=4;
                                Log.d(TAG, "Start Merge Sort");
                                c=1;
                                cc=1;

                                Msort(arr,0,noOfItems-1);

                            }
                        }, 500);
                        break;
                    case 4://Cocktail
                        inProcess=5;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                inProcess=5;
                                Log.d(TAG, "Start Cocktail Shaker Sort");
                                c=1;
                                cc=1;

                                cocktailSort(arr);

                            }
                        }, 500);
                        break;
                    case 5://Radix Sort
                        inProcess=6;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                inProcess=6;
                                Log.d(TAG, "Start Radix Sort");
                                c=1;
                                cc=1;

                                radixsort(arr,noOfItems);

                            }
                        }, 500);
                        break;
                    case 6://Heap Sort
                        inProcess=7;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                inProcess=7;
                                Log.d(TAG, "Start Radix Sort");
                                c=1;
                                cc=1;

                                HeapSort(arr);

                            }
                        }, 500);
                        break;
                }
            }
        });

        cc=1;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelectedSpinner=i;


        if (inProcess>0){
            Toast.makeText(this, "Wait for Algorithm to finish", Toast.LENGTH_SHORT).show();
            spin.setSelection(inProcess-1);
        }
        else{
            arrangeRandomly(noOfItems);
            q1.clear();
            q2.clear();
            h1.clear();
            h2.clear();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        itemSelectedSpinner=0;
    }
    private void createLayoutDynamically(int n) {

        int minH=globalHeight / 5;
        int progressive=(globalHeight-minH)/n;

        for (int i = 0; i < n; i++) {
            Button myButton = new Button(this);
            myButton.setId(i);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            layout.addView(myButton);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    myButton.getLayoutParams();
            params.weight = 1.0f;
            int randomNum = ThreadLocalRandom.current().nextInt(globalHeight / 5, globalHeight - (globalHeight / 6));
            arr[i]=randomNum;
            params.height = arr[i];
            myButton.setLayoutParams(params);
            myButton.setBackgroundColor(Color.parseColor("#000000"));

        }

    }
    private void arrangeRandomly(int n) {
        Log.d(TAG, "arrangeRandomly: EXECUTED");

        int minH=globalHeight / 5;
        int progressive=(globalHeight-minH)/n;

        for (int i = 0; i < n; i++) {

            @SuppressLint("ResourceType") Button mButton = (Button) findViewById(i);
            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton.getLayoutParams();
            int randomNum = ThreadLocalRandom.current().nextInt(globalHeight / 5, globalHeight - (globalHeight / 6));
            arr[i]=randomNum;
            params1.height = arr[i];
            mButton.setLayoutParams(params1);


        }

    }


    void InsertionSort()
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];

                q1.add(j+1);
                h1.add(arr[j+1]);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                        params1.height = h1.peek();
                        mButton1.setLayoutParams(params1);
                        q1.remove();
                        h1.remove();
                    }
                }, 10*cc);
                cc++;





                j = j - 1;
            }
            arr[j + 1] = key;
            q1.add(j+1);
            h1.add(arr[j+1]);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    q1.remove();
                    h1.remove();

                }
            }, 10*cc);
            cc++;

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 10*cc);
        cc++;
    }

    void SelectionSort()
    {

        int n = arr.length;

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            q1.add(i);
            q2.add(min_idx);
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

            h1.add(arr[i]);
            h2.add(arr[min_idx]);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());

                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);


                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                    params2.height = h2.peek();
                    mButton2.setLayoutParams(params2);
//                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                    q1.remove();
                    q2.remove();
                    h1.remove();
                    h2.remove();

                }
            }, 60*cc);
            cc++;




        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 60*cc);
        cc++;
    }

    void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                q1.add(k);
                h1.add(arr[k]);
                i++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                        params1.height = h1.peek();
                        mButton1.setLayoutParams(params1);
                        q1.remove();
                        h1.remove();

                    }
                }, 10*cc);
                cc++;
            }
            else {
                arr[k] = R[j];
                q1.add(k);
                h1.add(arr[k]);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                        params1.height = h1.peek();
                        mButton1.setLayoutParams(params1);
                        q1.remove();
                        h1.remove();

                    }
                }, 10*cc);
                cc++;
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            q1.add(k);
            h1.add(arr[k]);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    q1.remove();
                    h1.remove();

                }
            }, 10*cc);
            cc++;
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            q1.add(k);
            h1.add(arr[k]);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    q1.remove();
                    h1.remove();

                }
            }, 10*cc);
            cc++;
            j++;
            k++;
        }
    }

    void Msort(int arr[], int l, int r)
    {
        if (l < r) {
            int m = (l + r) / 2;
            Msort(arr, l, m);
            Msort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
        if (l==0 && r==noOfItems-1){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    inProcess=0;
                    Log.d(TAG, "run: Merger Sort Ended");

                }
            }, 10*cc);
            cc++;
        }
    }



    public void BubbleSort() {

        int n = noOfItems;
                for (i = 0; i < n - 1; i++) {
                    for (j = 0; j < n - i - 1; j++) {
                        if (arr[j] > arr[j + 1]) {

                            q1.add(j);
                            q2.add(j+1);

                            int temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;

                            h1.add(arr[j]);
                            h2.add(arr[j+1]);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                                    @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());
                                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                                    params1.height = h1.peek();
                                    mButton1.setLayoutParams(params1);
                                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                                    params2.height = h2.peek();
                                    mButton2.setLayoutParams(params2);
//                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                                    q1.remove();
                                    q2.remove();
                                    h1.remove();
                                    h2.remove();

                                }
                            }, 10*cc);
                            cc++;

                        }
                    }
                }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 10*cc);
        cc++;

    }

    void cocktailSort(int a[])
    {
        boolean swapped = true;
        int start = 0;
        int end = a.length;

        while (swapped == true) {

            swapped = false;

            for (int i = start; i < end - 1; ++i) {
                if (a[i] > a[i + 1]) {

                    q1.add(i);
                    q2.add(i+1);

                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;

                    h1.add(arr[i]);
                    h2.add(arr[i+1]);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                            @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());
                            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                            params1.height = h1.peek();
                            mButton1.setLayoutParams(params1);
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                            params2.height = h2.peek();
                            mButton2.setLayoutParams(params2);
//                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                            q1.remove();
                            q2.remove();
                            h1.remove();
                            h2.remove();

                        }
                    }, 10*cc);
                    cc++;


                    swapped = true;
                }
            }

            if (swapped == false)
                break;

            swapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (a[i] > a[i + 1]) {

                    q1.add(i);
                    q2.add(i+1);

                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;

                    h1.add(arr[i]);
                    h2.add(arr[i+1]);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                            @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());
                            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                            params1.height = h1.peek();
                            mButton1.setLayoutParams(params1);
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                            params2.height = h2.peek();
                            mButton2.setLayoutParams(params2);
//                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                            q1.remove();
                            q2.remove();
                            h1.remove();
                            h2.remove();

                        }
                    }, 10*cc);
                    cc++;

                    swapped = true;
                }
            }

            start = start + 1;
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 10*cc);
        cc++;
    }


    //Radix Sort Starts here
    //Reference to code be found at GeeksforGeek

    int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        for (i = 0; i < n; i++){

            arr[i] = output[i];
            q1.add(i);
            h1.add(arr[i]);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    q1.remove();
                    h1.remove();

                }
            }, 50*cc);
            cc++;

        }

    }

    void radixsort(int arr[], int n)
    {
        int m = getMax(arr, n);

        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 50*cc);
        cc++;
    }
    //Heap Sort Starts here below

    public void HeapSort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            q1.add(0);
            q2.add(i);

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            h1.add(arr[0]);
            h2.add(arr[i]);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                    params2.height = h2.peek();
                    mButton2.setLayoutParams(params2);
//                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                    q1.remove();
                    q2.remove();
                    h1.remove();
                    h2.remove();

                }
            }, 30*cc);
            cc++;



            heapify(arr, i, 0);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                inProcess=0;

            }
        }, 30*cc);
        cc++;
    }

    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            q1.add(i);
            q2.add(largest);

            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            h1.add(arr[i]);
            h2.add(arr[largest]);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    @SuppressLint("ResourceType") Button mButton1 = (Button) findViewById(q1.peek());
                    @SuppressLint("ResourceType") Button mButton2 = (Button) findViewById(q2.peek());
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) mButton1.getLayoutParams();
                    params1.height = h1.peek();
                    mButton1.setLayoutParams(params1);
                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                    params2.height = h2.peek();
                    mButton2.setLayoutParams(params2);
//                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                    q1.remove();
                    q2.remove();
                    h1.remove();
                    h2.remove();

                }
            }, 30*cc);
            cc++;

            heapify(arr, n, largest);
        }
    }

    //Bitonic Sort below
    void compAndSwap(int a[], int i, int j, int dir)
    {
        if ( (a[i] > a[j] && dir == 1) ||
                (a[i] < a[j] && dir == 0))
        {
            // Swapping elements
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    void bitonicMerge(int a[], int low, int cnt, int dir)
    {
        if (cnt>1)
        {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }


    void bitonicSort(int a[], int low, int cnt, int dir)
    {
        if (cnt>1)
        {
            int k = cnt/2;

            bitonicSort(a, low, k, 1);

            bitonicSort(a,low+k, k, 0);

            bitonicMerge(a, low, cnt, dir);
        }
    }


    void StartBitonicSort(int a[], int N, int up)
    {
        bitonicSort(a, 0, N, up);
    }




}



