# 冒泡排序
~~~
void Sort(int arr[], int sz)
{
    int i;
    int j;
    for(i = 0; i < sz; i++)
    {
        for(j = 0; j < sz - i - 1; j++)
        {
            if(arr[j] > arr[j + 1])
            {
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
    }
}
~~~
![冒泡排序](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015223238449-2146169197.gif)
# 选择排序
~~~
function selectionSort(arr) {
    var len = arr.length;
    var minIndex, temp;
    for (var i = 0; i < len - 1; i++) {
        minIndex = i;
        for (var j = i + 1; j < len; j++) {
            if (arr[j] < arr[minIndex]) {     // 寻找最小的数
                minIndex = j;                 // 将最小数的索引保存
            }
        }
        temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
    return arr;
}
~~~
![选择排序](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015224719590-1433219824.gif)
# 插入排序
~~~
function insertionSort(arr) {
    var len = arr.length;
    var preIndex, current;
    for (var i = 1; i < len; i++) {
        preIndex = i - 1;
        current = arr[i];
        while (preIndex >= 0 && arr[preIndex] > current) {
            arr[preIndex + 1] = arr[preIndex];
            preIndex--;
        }
        arr[preIndex + 1] = current;
    }
    return arr;
}
~~~
![插入排序](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015225645277-1151100000.gif)
# 归并排序
~~~
function mergeSort(arr) {  // 采用自上而下的递归方法
    var len = arr.length;
    if (len < 2) {
        return arr;
    }
    var middle = Math.floor(len / 2),
        left = arr.slice(0, middle),
        right = arr.slice(middle);
    return merge(mergeSort(left), mergeSort(right));
}
    
function merge(left, right) {
    var result = [];
    
    while (left.length > 0 && right.length > 0) {
        if (left[0] <= right[0]) {
            result.push(left.shift());
        } else {
            result.push(right.shift());
        }
    }
    
    while (left.length)
        result.push(left.shift());
    
    while (right.length)
        result.push(right.shift());
    
    return result;
}
~~~
![归并排序](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015230557043-37375010.gif)
# 快速排序
~~~
void QuickSort(int arr[], int left, int right)
{
    int i = left;
    int j = right;
    int tmp = arr[i];//设定中间数
    if(i < j)//判断是否进入递归
    {
        while(i < j)//判断是否完成一次快速排序
        {
            while((arr[j] > tmp)&&(i < j))
            {
                j--;
            }
            arr[i] = arr[j];
            while((arr[i] < tmp)&&(i < j))
            {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = tmp;//赋值中间数
        QuickSort(arr, left, i-1);//递归中间数左边的元素
        QuickSort(arr, j+1, right);//递归中间数右边的元素
    }
    else
    {
        return;
    }
}
~~~
![快速排序](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015230936371-1413523412.gif)
# 希尔排序
~~~
void shell_sort(const int start, const int end) {
    int increment = end - start + 1;    //初始化划分增量
    int temp{ 0 };
    do {    //每次减小增量，直到increment = 1
        increment = increment / 3 + 1;
        for (int i = start + increment; i <= end; ++i) {    //对每个划分进行直接插入排序
            if (numbers[i - increment] > numbers[i]) {
                temp = numbers[i];
                int j = i - increment;
                do {    //移动元素并寻找位置
                    numbers[j + increment] = numbers[j];
                    j -= increment;
                } while (j >= start && numbers[j] > temp);
                numbers[j + increment] = temp;  //插入元素
            }
        }
    } while (increment > 1);
}
~~~
![希尔排序](https://images2018.cnblogs.com/blog/849589/201803/849589-20180331170017421-364506073.gif)
# 其它算法
- 找到链表中环的入口：使用hashset，如果重复则是入口，方法二：一个慢指针一个快指针
- 给一个100w的长度的数字，查找其中最小的100个，使用小顶堆
