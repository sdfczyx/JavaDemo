package com.zyx.suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class TenSorts {
    public static void main(String[] args) throws Exception {
        int arr[] = {44,3,38,5,47,15,36,26,27,2,46,4,19,50,48};
        // int[] arr1 = bubbleSort(arr);
        // printArr(arr1);
        // arr1 = selectSort(arr);
        // printArr(arr1);
        // arr1 = insertSort(arr);
        // printArr(arr1);
        // arr1 = shellSort(arr);
        // printArr(arr1);
        // arr1 = headSort(arr);
        // printArr(arr1);
        // arr1 = mergeSort(arr);
        // printArr(arr1);
        // arr1 = quickSort(arr);
        // printArr(arr1);
        // arr1 = countingSort(arr);
        // printArr(arr1);
        int[] arr2 = radixSort(arr);
        printArr(arr2);
        
    }

    private static void printArr(int[] array){
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println(" ");
    }

    /**
	 * 交换两个下标所对应的元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(int[] arr, int i, int j) {
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
	}

    // 冒泡排序 每一次循环找出最大的放到最后面
    public static int[] bubbleSort(int[] arr) {

        if (arr == null || arr.length < 2)
            return arr;

        int n = arr.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - 1 - i; j++)
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j, j + 1);
                }
        return arr;
    }

    // 优化冒泡排序  某次循环不发生交换,可以提前结束
    public static int[] bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    flag = false;
                    swap(arr, j, j + 1);
                }
            }
            // 一趟下来是否发生位置交换
            if (flag)
                break;
        }
        return arr;
    }
    
    // 02 选择排序
    // 每次循环都会找出当前循环中最小的元素，然后和此次循环中的队首元素进行交换。
    public static int[] selectSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
    
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex > i) {
                swap(array, i, minIndex);
            }
        }
        return array;
    }
    
    // 03 插入排序
    // 每次都会在先前排好序的子集合中插入下一个待排序的元素
    public static int[] insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }
    
    //04 希尔排序
    // 插入排序的改进版本 按照初始增量来将数组分成多个组，每个组内部使用插入排序。然后缩小增量来重新分组，组内再次使用插入排序...
    // 重复以上步骤，直到增量变为1的时候，这个时候整个数组就是一个分组，进行最后一次完整的插入排序即可结束。
    
    public static int[] shellSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        int gap = array.length >>> 1;
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > temp) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }
                array[j + gap] = temp;
            }
            gap >>>= 1;
        }
        return array;
    }
    //05 堆排序
    // 堆排序的过程是首先构建一个大顶堆，大顶堆首先是一棵完全二叉树，其次它保证堆中某个节点的值总是不大于其父节点的值
    public static int[] headSort(int[] arr) {
        int n = arr.length;
        //构建大顶堆
        for (int i = (n - 2) / 2; i >= 0; i--) {
            downAdjust(arr, i, n - 1);
        }
        //进行堆排序
        for (int i = n - 1; i >= 1; i--) {
            // 把堆顶元素与最后一个元素交换
            TenSorts.swap(arr, i, 0);
            // 把打乱的堆进行调整，恢复堆的特性
            downAdjust(arr, 0, i - 1);
        }
        return arr;
    }

    //下沉操作
    private static void downAdjust(int[] arr, int parent, int n) {
        //临时保存要下沉的元素
        int temp = arr[parent];
        //定位左孩子节点的位置
        int child = 2 * parent + 1;
        //开始下沉
        while (child <= n) {
            // 如果右孩子节点比左孩子大，则定位到右孩子
            if(child + 1 <= n && arr[child] < arr[child + 1])
                child++;
            // 如果孩子节点小于或等于父节点，则下沉结束
            if (arr[child] <= temp ) break;
            // 父节点进行下沉
            arr[parent] = arr[child];
            parent = child;
            child = 2 * parent + 1;
        }
        arr[parent] = temp;
    }
    
    //06 归并排序
    /*
     *  归并排序使用的是分治的思想，首先将数组不断拆分，
     *  直到最后拆分成两个元素的子数组，将这两个元素进行排序合并，
     *  再向上递归。不断重复这个拆分和合并的递归过程，最后得到的就是排好序的结果。
     */
    public static int[] mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
    
        return mSort(array, 0, array.length - 1);
    }
    
    private static int[] mSort(int[] array, int left, int right) {
        if (left < right) {
            //这里没有选择“(left + right) / 2”的方式，是为了防止数据溢出
            int mid = left + ((right - left) >>> 1);
            // 拆分子数组
            mSort(array, left, mid);
            mSort(array, mid + 1, right);
            // 对子数组进行合并
            merge(array, left, mid, right);
        }
        return array;
    }
    
    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        // p1和p2为需要对比的两个数组的指针，k为存放temp数组的指针
        int p1 = left, p2 = mid + 1, k = 0;
        while (p1 <= mid && p2 <= right) {
            if (array[p1] <= array[p2]) {
                temp[k++] = array[p1++];
            } else {
                temp[k++] = array[p2++];
            }
        }
        // 把剩余的数组直接放到temp数组中
        while (p1 <= mid) {
            temp[k++] = array[p1++];
        }
        while (p2 <= right) {
            temp[k++] = array[p2++];
        }
        // 复制回原数组
        for (int i = 0; i < temp.length; i++) {
            array[i + left] = temp[i];
        }
    }
    
    //07 快速排序
    // 快速排序的核心是要有一个基准数据temp，一般取数组的第一个位置元素
    // 两个指针left和right，分别指向数组的第一个和最后一个元素。
    // 第一次排序完成后, 左半部分比基数小, 右半部分比基数大
    // 然后递归排序左半部分和右半部分
    public static int[] quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
    
        return quickSort(array, 0, array.length - 1);
    }
    
    private static int[] quickSort(int[] array, int start, int end) {
    
        // left和right指针分别指向array的第一个和最后一个元素
        int left = start, right = end;
    
        /*
        取最左边、最右边和最中间的三个元素的中间值作为基准数据，以此来尽量避免每次都取第一个值作为基准数据、
        时间复杂度可能退化为O(n^2)的情况出现
        */
        int middleOf3Indexs = middleOf3Indexs(array, start, end);
        if (middleOf3Indexs != start) {
            swap(array, middleOf3Indexs, start);
        }
    
        // temp存放的是array中需要比较的基准数据
        int temp = array[start];
    
        while (left < right) {
            // 首先从right指针开始比较，如果right指针位置处数据大于temp，则将right指针左移
            while (left < right && array[right] >= temp) {
                right--;
            }
            // 如果找到一个right指针位置处数据小于temp，则将right指针处数据赋给left指针处
            if (left < right) {
                array[left++] = array[right];
            }
            // 然后从left指针开始比较，如果left指针位置处数据小于temp，则将left指针右移
            while (left < right && array[left] <= temp) {
                left++;
            }
            // 如果找到一个left指针位置处数据大于temp，则将left指针处数据赋给right指针处
            if (left < right) {
                array[right--] = array[left];
            }
        }
        // 当left和right指针相等时，此时循环跳出，将之前存放的基准数据赋给当前两个指针共同指向的数据处
        array[left] = temp;
        // 一次替换后，递归交换基准数据左边的数据
        if (start < left - 1) {
            array = quickSort(array, start, left - 1);
        }
        // 之后递归交换基准数据右边的数据
        if (right + 1 < end) {
            array = quickSort(array, right + 1, end);
        }
        return array;
    }
    
    private static int middleOf3Indexs(int[] array, int start, int end) {
        int mid = start + ((end - start) >>> 1);
        if (array[start] < array[mid]) {
            if (array[mid] < array[end]) {
                return mid;
            } else {
                return array[start] < array[end] ? end : start;
            }
        } else {
            if (array[mid] > array[end]) {
                return mid;
            } else {
                return array[start] < array[end] ? start : end;
            }
        }
    }
    
    //08 计数排序
    // 计数排序会创建一个临时的数组，里面存放每个数出现的次数
    public static int[] countingSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
    
        //记录待排序数组中的最大值
        int max = array[0];
        //记录待排序数组中的最小值
        int min = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int[] temp = new int[max - min + 1];
        //记录每个数出现的次数
        for (int i : array) {
            temp[i - min]++;
        }
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            //当输出一个数之后，当前位置的计数就减一，直到减到0为止
            while (temp[i]-- > 0) {
                array[index++] = i + min;
            }
        }
        return array;
    }
    
    // 改进的记数排序, 保持原来的顺序
    public static int[] stableCountingSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
    
        //记录待排序数组中的最大值
        int max = array[0];
        //记录待排序数组中的最小值
        int min = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int[] temp = new int[max - min + 1];
        //记录每个数出现的次数
        for (int i : array) {
            temp[i - min]++;
        }
        //将temp数组进行转换，记录每个数在最后排好序的数组中应该要存放的位置+1（如果有重复的就记录最后一个）
        for (int j = 1; j < temp.length; j++) {
            temp[j] += temp[j - 1];
        }
        int[] sortedArray = new int[array.length];
        //这里必须是从后往前遍历，以此来保证稳定性
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[temp[array[i] - min] - 1] = array[i];
            temp[array[i] - min]--;
        }
        return sortedArray;
    }
    //09 桶排序
    // 桶排序类似于哈希表，通过一定的映射规则将数组中的元素映射到不同的桶中，每个桶内进行内部排序，最后将每个桶按顺序输出就行了
    // 比如说第一个桶中存1-30的数字，第二个桶中存31-60的数字，第三个桶中存61-90的数字...以此类推
    public static int[] bucketSort(int[] arr) {
        if(arr == null || arr.length < 2) return arr;

        int n = arr.length;
        int max = arr[0];
        int min = arr[0];
        // 寻找数组的最大值与最小值
        for (int i = 1; i < n; i++) {
            if(min > arr[i])
                min = arr[i];
            if(max < arr[i])
                max = arr[i];
        }
        System.out.println("min: " + min + " max: " + max);
        //和优化版本的计数排序一样，弄一个大小为 min 的偏移值
        int d = max - min;
        //创建 d / 5 + 1 个桶，第 i 桶存放  5*i ~ 5*i+5-1范围的数
        int bucketNum = d / 5 + 1;
        System.out.println("bucketNum: " + bucketNum);
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);
        //初始化桶
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        //遍历原数组，将每个元素放入桶中
        for (int i = 0; i < n; i++) {
            bucketList.get((arr[i]-min)/5).add(arr[i] - min);
        }
        System.out.println(bucketList);
        //对桶内的元素进行排序，我这里采用系统自带的排序工具
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        //把每个桶排序好的数据进行合并汇总放回原数组
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Integer t : bucketList.get(i)) {
                arr[k++] = t + min;
            }
        }
        return arr;
    }
    //10 基数排序
    // 基数排序一般是对所有非负整数进行排序的
    // 第一轮排序，我拿到待排序数组中所有数个位上的数字来进行排序
    // ...
    // 第三轮排序我拿到待排序数组中所有数百位上的数字来进行排序
    // ...
    // 基数排序每一轮的内部排序会使用到计数排序来实现，因为每一位上的数字无非就是0-9，是一个小范围的数，所以使用计数排序很合适。
    public static int[] radixSort(int[] arr) throws Exception {
        int mod = 10;
        int dev = 1;
        int maxDigit = getMaxDigit(arr);

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }
    /**
     * 获取最高位数
     */
    private static int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    private static int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


}


