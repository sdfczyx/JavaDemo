## JVM整体结构
- 类加载子系统
- 执行引擎
  - 解释器
  - JIT编译器 : 把热点代码编译成机器码, 缓存起来, 提高执行速度.
  - 垃圾回收器
- 运行时数据区
  - 本地方法栈
  - Java方法栈
  - 方法区
  - 程序计数器 : 记录程序下条指令地址
  - 堆

## 类加载子系统步骤
- 加载
- 链接
  - 验证 : 验证待加载的class文件是否正确, 比如验证文件格式
  - 准备 : 为static变量分配内存, 并赋零值
  - 解析 : 将符号引用解析为直接引用
- 初始化 : 


## 类加载器的分类
- 引导类加载器(BootStrapClassLoader)(JDK) 负责加载 jre/lib
- 自定义类加载器(继承ClassLoader)
  - ExtClassLoader(JDK) 负责加载 jre/lib/ext
  - AppClassLoader(JDK) 负责加载 应用的classpath的类
  - WebAppClassLoader

## 双亲委派 (避免类的重复加载, 防止核心API被覆盖)
   用 AppClassLoader 加载类的时候, 如果还没有加载过
   如果parent不为空, 使用parent的loadclass来加载 (App的parent是Ext, Ext的parent是null)
   否则查找 BootStrap
   
## Tomcat为什么自定义类加载器
为了进行类的隔离，如果Tomcat直接使用AppClassLoader类加载类，那就会出现如下情况：
1. 应用A中有一个com.zhouyu.Hello.class
2. 应用B中也有一个com.zhouyu.Hello.class
3. 虽然都叫做Hello，但是具体的方法、属性可能不一样
4. 如果AppClassLoader先加载了应用A中的Hello. class
5. 那么应用B中的Hello. class就不可能再被加载了，因为名字是一样
6. 如果就需要针对应用A和应用B设置各自单独的类加载器，也就是WebappClassLoader
7. 这样两个应用中的Hello. class都能被各自的类加载器所加载，不会冲突
8. 这就是Tomcat为什么用自定义类加载器的核心原因，为了实现类加载的隔离
9. JVM中判断一个类是不是已经被加载的逻辑是：类名＋对应的类加载器实例




## 程序计数器
PC Register，程序计数寄存器，简称为程序计数器：
1. 是物理寄存器的抽象实现
2. 用来记录待执行的下一条指令的地址
3. 它是程序控制流的指示器，循环、if else、异常处理、线程恢复等都依赖它来完成
4. 解释器工作时就是通过它来获取下一条需要执行的字节码指令的
5. 它是唯一一个在JVM规范中没有规定任何OutOfMemoryError情况的区域


## 虚拟机栈
每个线程在创建时都会创建一个虚拟机栈，栈内会保存一个个的栈帧，每个栈帧对应一个方法。
1. 虚拟机栈是线程私有的
2. 一个方法开始执行栈帧入栈、方法执行完对应的栈帧就出栈，所以虚拟机栈不需要进行垃圾回收
3. 虚拟机栈存在OutOfMemoryError、以及StackOverflowError
4. 线程太多，就可能会出现OutOfMemoryError，线程创建时没有足够的内存去创建虚拟机栈了
5. 方法调用层次太多，就可能会出现StackOverflowError
6. 可以通过—Xss来设置虚拟机栈的大小，官网