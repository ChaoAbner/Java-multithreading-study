# The study of Java.util.concurrent  package

## CountDownLatch

### 介绍

CountDownLatch是一种同步帮助，允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。 

实现了一个计数器的功能，可以指定一个计数值，只有任意线程调用了CountDown方法才能使这个计数值-1，知道这个计数值为零的时候，返回await()方法，类似于Thread类中的join方法，不过CountDownLatch的使用更加灵活。

他的await()方法可以使用在多个线程中，直到count=0的时候才返回。

### 示例

举一个例子，多线程爬虫获取结果，然后再汇总处理。

[example](https://github.com/ChaoAbner/Java-multithreading-study/tree/master/src/main/java/com/fosuchao/multithreading/concurrent/util/countdownlatch)
### 适用场景

前面的任务可以使用并发处理提高效率，然后又使用串行方式的场景。

处理一种有层次关系的事情时，用join较难处理，这时可以使用CountDownLatch

![](http://img.fosuchao.com/20200226150757.png)

## CyclicBarrier

### 介绍

​		CyclicBarrier允许一组线程相互等待，每次有一个线程进入await()方法，会唤醒其它正在await中的线程，当这时的count=0，其它线程会被返回，不再等待。如果传递Runnable参数，则会回调传入的Runnable的run方法。并开始下一代的循环（完成当前周期的所有任务）

![](http://img.fosuchao.com/20200226161348.png)

![](http://img.fosuchao.com/20200226161410.png)

### 示例
[example](https://github.com/ChaoAbner/Java-multithreading-study/tree/master/src/main/java/com/fosuchao/multithreading/concurrent/util/cyclibarrier)


### vs CountDownLatch

#### 线程之间

CyclicBarrier线程之间相互等待，新的线程会尝试唤醒其它线程，如果count=0，则成功唤醒并回调，count不等于0则所有线程继续await。

CountDownLatch线程之间互不相关。只关注count是否=0。

#### 使用

CyclicBarrier可以循环使用，可以主动调用reset方法，或者任务全部结束后会重新开启下一代。正如其名。

CountDownLatch不可以循环使用。

## Exchanger

### 介绍

​		线程可以对的同步点和对中的元素交换元素的同步点。每一个线程进入 `exchange`方法提出了一些目标，与合作伙伴的线匹配，并接收其伴侣的对象返回。交换机可以被看作是一个双向的 `SynchronousQueue`形式。交换器等应用遗传算法和管道的设计是有用的。  

### 示例

**简单交换**
[example-1](https://github.com/ChaoAbner/Java-multithreading-study/blob/master/src/main/java/com/fosuchao/multithreading/concurrent/util/exchanger/ExchangerSimple.java)

**循环交换信息**
[example-2](https://github.com/ChaoAbner/Java-multithreading-study/blob/master/src/main/java/com/fosuchao/multithreading/concurrent/util/exchanger/ExchangerCycle.java)


### 注意事项

1. 一般只适用于两个线程之间交换数据，如果有多个线程，会导致数据交换的不确定性，**至少要求线程成对出现。**
2. 要注意超时的问题，一旦一个线程超时没有交换数据，会导致另一个线程一直等待。

## Semaphore

### 介绍

计数信号量。从概念上讲，一个信号量维护一组允许。每个线程可以通过acquire获取一定数量的信号量，当剩余信号量为空时，其它线程等待。

信号量通常是用来限制线程的数量比可以访问一些（物理或逻辑）资源

### 示例

#### 锁的作用
[example-1](https://github.com/ChaoAbner/Java-multithreading-study/blob/master/src/main/java/com/fosuchao/multithreading/concurrent/util/semaphore/SemaphoreLock.java)


#### 简单使用
[example-2](https://github.com/ChaoAbner/Java-multithreading-study/blob/master/src/main/java/com/fosuchao/multithreading/concurrent/util/semaphore/SemaphoreSimple.java)
