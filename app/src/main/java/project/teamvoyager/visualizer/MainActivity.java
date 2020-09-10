package project.teamvoyager.visualizer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    int globalWidth;
    Button bb;
    int arr[];
    int tt;
    int cc = 1;
    int i = 0, j = 0;
    int noOfItems;
    int count = 0;
    Queue<Integer> q1= new LinkedList<>();
    Queue<Integer> q2= new LinkedList<>();
    Queue<Integer> h1= new LinkedList<>();
    Queue<Integer> h2= new LinkedList<>();
    int c=0;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        globalWidth = width;

        noOfItems = 60;
        arr = new int[noOfItems];
        createLayoutDynamically(noOfItems);

//        BubbleSort();
        cc=1;
//        Msort(arr,0,noOfItems-1);
//        SelectionSort();
        InsertionSort();

//        for (int i=0;i<noOfItems;i++){
//            root.removeView(i);
//        }

    }


    void InsertionSort()
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
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
                        params1.width = h1.peek();
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
                    params1.width = h1.peek();
                    mButton1.setLayoutParams(params1);
                    q1.remove();
                    h1.remove();

                }
            }, 10*cc);
            cc++;

        }
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
            //swapping starts here




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
                    params1.width = h1.peek();
                    mButton1.setLayoutParams(params1);


                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                    params2.width = h2.peek();
                    mButton2.setLayoutParams(params2);
                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
                    q1.remove();
                    q2.remove();
                    h1.remove();
                    h2.remove();

                }
            }, 100*cc);
            cc++;




        }
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
                        params1.width = h1.peek();
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
                        params1.width = h1.peek();
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
                    params1.width = h1.peek();
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
                    params1.width = h1.peek();
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
                                    params1.width = h1.peek();
                                    mButton1.setLayoutParams(params1);


                                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mButton2.getLayoutParams();
                                    params2.width = h2.peek();
                                    mButton2.setLayoutParams(params2);
                                    Log.d(TAG, h1.peek()+" "+h2.peek()+" "+q1.peek()+" "+q2.peek());
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

    }



    private void createLayoutDynamically(int n) {

        int minH=globalWidth / 5;
        int progressive=(globalWidth-minH)/n;

        for (int i = 0; i < n; i++) {
            Button myButton = new Button(this);
            myButton.setId(i);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            layout.addView(myButton);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    myButton.getLayoutParams();
            params.weight = 1.0f;
            int randomNum = ThreadLocalRandom.current().nextInt(globalWidth / 5, globalWidth - (globalWidth / 6));
            arr[i]=randomNum;
            params.width = arr[i];
            myButton.setLayoutParams(params);
            myButton.setBackgroundColor(Color.parseColor("#000000"));

        }

    }


}



