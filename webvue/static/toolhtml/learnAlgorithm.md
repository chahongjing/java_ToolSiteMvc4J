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
![选择排序](https://img-blog.csdn.net/2018033115051430?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2RldmVsb3BlcjEwMjQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
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
![归并排序](https://img-blog.csdn.net/20180331152305162?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2RldmVsb3BlcjEwMjQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
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
![希尔排序](https://img-blog.csdn.net/20180331112407628?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2RldmVsb3BlcjEwMjQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
# 堆排序
~~~
/**
    * 
    * @description 本方法只有一个参数，那就是待排序的array
    * @author yuzhao.yang
    * @param
    * @return
    * @time 2018年3月9日 下午2:24:45
    */
public static void sort(int[] array) {
    // 按照完全二叉树的特点，从最后一个非叶子节点开始，对于整棵树进行大根堆的调整
    // 也就是说，是按照自下而上，每一层都是自右向左来进行调整的
    // 注意，这里元素的索引是从0开始的
    // 另一件需要注意的事情，这里的建堆，是用堆调整的方式来做的
    // 堆调整的逻辑在建堆和后续排序过程中复用的
    for (int i = array.length / 2 - 1; i >= 0; i--) {
        adjustHeap(array, i, array.length);
    }

    // 上述逻辑，建堆结束
    // 下面，开始排序逻辑
    for (int j = array.length - 1; j > 0; j--) {
        // 元素交换
        // 说是交换，其实质就是把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
        swap(array, 0, j);
        // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
        // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
        // 而这里，实质上是自上而下，自左向右进行调整的
        adjustHeap(array, 0, j);
    }
}

/**
    * 
    * @description 这里，是整个堆排序最关键的地方，正是因为把这个方法抽取出来，才更好理解了堆排序的精髓，会尽可能仔细讲解
    * @author yuzhao.yang
    * @param
    * @return
    * @time 2018年3月9日 下午2:54:38
    */
public static void adjustHeap(int[] array, int i, int length) {
    // 先把当前元素取出来，因为当前元素可能要一直移动
    int temp = array[i];
    // 可以参照sort中的调用逻辑，在堆建成，且完成第一次交换之后，实质上i=0；也就是说，是从根所在的最小子树开始调整的
    // 接下来的讲解，都是按照i的初始值为0来讲述的
    // 这一段很好理解，如果i=0；则k=1；k+1=2
    // 实质上，就是根节点和其左右子节点记性比较，让k指向这个不超过三个节点的子树中最大的值
    // 这里，必须要说下为什么k值是跳跃性的。
    // 首先，举个例子，如果a[0] > a[1]&&a[0]>a[2],说明0,1,2这棵树不需要调整，那么，下一步该到哪个节点了呢？肯定是a[1]所在的子树了，
    // 也就是说，是以本节点的左子节点为根的那棵小的子树
    // 而如果a[0}<a[2]呢，那就调整a[0]和a[2]的位置，然后继续调整以a[2]为根节点的那棵子树，而且肯定是从左子树开始调整的
    // 所以，这里面的用意就在于，自上而下，自左向右一点点调整整棵树的部分，直到每一颗小子树都满足大根堆的规律为止
    for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
        // 让k先指向子节点中最大的节点
        if (k + 1 < length && array[k] < array[k + 1]) {
            k++;
        }

        // 如果发现子节点更大，则进行值的交换
        if (array[k] > temp) {
            swap(array, i, k);
            // 下面就是非常关键的一步了
            // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
            // 所以，循环对子节点所在的树继续进行判断
            i = k;
            // 如果不用交换，那么，就直接终止循环了
        } else {
            break;
        }
    }
}

/**
    * 交换元素
    * 
    * @param arr
    * @param a
    *            元素的下标
    * @param b
    *            元素的下标
    */
public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
~~~
# 计数排序
~~~
public static int[] countSort1(int[] arr){
    if (arr == null || arr.length == 0) {
        return null;
    }
    
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    
    //找出数组中的最大最小值
    for(int i = 0; i < arr.length; i++){
        max = Math.max(max, arr[i]);
        min = Math.min(min, arr[i]);
    }
    
    int help[] = new int[max - min + 1];
    
    //找出每个数字出现的次数
    for(int i = 0; i < arr.length; i++){
        int mapPos = arr[i] - min;
        help[mapPos]++;
    }
    
    int index = 0;
    for(int i = 0; i < help.length; i++){
        while(help[i]-- > 0){
            arr[index++] = i+min;
        }
    }
    
    return arr;
}
~~~
# 桶排序
#### 主要看桶的数量，如果桶数大于等于最大值-最小值，则表示很个值都可以只放到一个桶中即每个桶只存储一个数字，有可能有重复，可以对每个桶进行计数。然后再输出。
~~~
public static void bucketSort(int[] arr){
    
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < arr.length; i++){
        max = Math.max(max, arr[i]);
        min = Math.min(min, arr[i]);
    }
    
    //桶数
    int bucketNum = (max - min) / arr.length + 1;
    ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
    for(int i = 0; i < bucketNum; i++){
        bucketArr.add(new ArrayList<Integer>());
    }
    
    //将每个元素放入桶
    for(int i = 0; i < arr.length; i++){
        int num = (arr[i] - min) / (arr.length);
        bucketArr.get(num).add(arr[i]);
    }
    
    //对每个桶进行排序
    for(int i = 0; i < bucketArr.size(); i++){
        Collections.sort(bucketArr.get(i));
    }
    
    System.out.println(bucketArr.toString());
    
}
~~~
![桶排序](https://img-blog.csdn.net/2018033115584192?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2RldmVsb3BlcjEwMjQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
# 基数排序
~~~
private static void radixSort(int[] array,int d)
{
    int n=1;//代表位数对应的数：1,10,100...
    int k=0;//保存每一位排序后的结果用于下一位的排序输入
    int length=array.length;
    int[][] bucket=new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
    int[] order=new int[length];//用于保存每个桶里有多少个数字
    while(n<d)
    {
        for(int num:array) //将数组array里的每个数字放在相应的桶里
        {
            int digit=(num/n)%10;
            bucket[digit][order[digit]]=num;
            order[digit]++;
        }
        for(int i=0;i<length;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
        {
            if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
            {
                for(int j=0;j<order[i];j++)
                {
                    array[k]=bucket[i][j];
                    k++;
                }
            }
            order[i]=0;//将桶里计数器置0，用于下一次位排序
        }
        n*=10;
        k=0;//将k置0，用于下一轮保存位排序结果
    }
    
}
~~~
# 其它算法
- 找到链表中环的入口：使用hashset，如果重复则是入口，方法二：一个慢指针一个快指针
- 给一个100w的长度的数字，查找其中最小的100个，使用小顶堆
