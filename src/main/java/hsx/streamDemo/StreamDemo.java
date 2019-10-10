package hsx.streamDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StreamDemo {

    public List<Car> getCarInfo() {
        List<Car> cars = new ArrayList<>(16);
        Car car = new Car();
        for (int i = 0; i < 16; i++) {
            car.setColor("red" + i);
            car.setHeight(i + "M");
            car.setWidth(i + "M");
            car.setPrice(0 + i);
            cars.add(car);
        }

        //1. stream() 最常用到的方法，将集合变成流
        //cars.stream();

        //2.filter(T -> boolean),例子：保留价格大于10的car
        cars = cars.stream().filter(carInfo -> carInfo.getPrice() > 10).collect(toList());

        //3.distinct() 返回由该流的不同元素组成的流
        List<String> list = Arrays.asList("A", "B", "C", "B", "C", "A", "A");
        //获取非重复元素的个数
        Long count = list.stream().distinct().count();
        //输出非重复元素
        String outPut = list.stream().distinct().collect(Collectors.joining(","));

        //如果我们要对一个对象列表去重的话，则需要从写该对象的hashCode和equals方法
        //在上面的cars列表中添加几个重复对象
        cars.add(new Car("red5", "5M", "5M", 5.00));
        cars.add(new Car("red8", "8M", "8M", 8.00));

        //获取非重复对象个数
        Long countObj = cars.stream().distinct().count();
        //输出非重复对象
        cars.stream().distinct().forEach(t -> System.out.println(t.color));

        //4.sorted() / sorted((T, T) -> double)
        //根据汽车价格大小来比较
        cars = cars.stream().sorted(Comparator.comparingDouble(Car::getPrice)).collect(toList());


        //5. limit(long n) 返回前n个元素
        cars = cars.stream().limit(5).collect(toList());


        //6. skip(long n) 去除前n个元素
        cars = cars.stream().skip(5).collect(toList());

        //7. map(Car -> String)  newlist 里面的元素为 list 中每一个 Car 对象的 color 变量
        List<String> newList = cars.stream().map(Car::getColor).collect(toList());

        //8. flatMap(Car -> Stream<String>)
        //将流中的每一个元素 Car 映射为一个流，再把每一个流连接成为一个流
        //流的扁平化
        //例子：将单词列表 ["Hello","World"],去重后,你想返回列表["H","e","l","o","W","r","d"]
        String[] words = new String[]{"Hello", "World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);


        //9. anyMatch(Car -> boolean)
        //流中是否有一个元素匹配给定的 Car -> boolean 条件
        //例子：是否存在一个 Car 对象的 color 等于 red5：true
        boolean b = cars.stream().anyMatch(c -> c.getColor().equals("red5"));

        //10. allMatch(Car -> boolean)
        //流中是否所有元素都匹配给定的 Car -> boolean 条件
        //例子：判断cars列表中所有的car对象的color等于red5：false
        boolean d = cars.stream().allMatch(c -> c.getColor().equals("red5"));

        //11. noneMatch(Car -> boolean)
        //流中是否没有元素匹配给定的 Car -> boolean 条件
        //例子：判断cars列表中所有的car对象的colord都不等于red5：false
        boolean e = cars.stream().noneMatch(c -> c.getColor().equals("red5"));

        //12.findFirst和findAny
        // 通过名字，就可以看到，对这个集合的流，做一系列的中间操作后，
        // 可以调用findFirst，返回集合的第一个对象，
        // findAny返回这个集合中，取到的任何一个对象；
        // 通过这样的描述，我们也可以知道，在串行的流中，findAny和findFirst返回的，都是第一个对象；
        // 而在并行的流中，findAny返回的是最快处理完的那个线程的数据，
        // 所以说，在并行操作中，对数据没有顺序上的要求，那么findAny的效率会比findFirst要快的
        /**值得注意的是，这两个方法返回的是一个 Optional<T> 对象，它是一个容器类，能代表一个值存在或不存在，跳转地址**/
        //串行流中
        Optional<Car> optionalCar = cars.stream().filter(s -> s.getPrice() > 10).findFirst();
        Optional<Car> optionalCar2 = cars.stream().filter(s -> s.getPrice() > 10).findAny();

        //并行流中
        Optional<Car> optionalCar3 = cars.parallelStream().filter(s -> s.getPrice() > 10).findFirst();
        Optional<Car> optionalCar4 = cars.parallelStream().filter(s -> s.getPrice() > 10).findAny();


        //13. reduce((T, T) -> T) 和 reduce(T, (T, T) -> T)
        //用于组合流中的元素，如求和，求积，求最大值等
        //例子：求cars中所有Car对象的价格之和
        double sum = cars.stream().map(Car::getPrice).reduce(0.00, (a1, b1) -> a1 + b1);
        //or
        double sum2 = cars.stream().map(Car::getPrice).reduce(0.00, Double::sum);

        //计算乘积
        double sum3 = cars.stream().map(Car::getPrice).reduce(1.00, (a1, b1) -> a1 * b1);


        //我们使用 reduce 方法计算流中元素的总和，它有一个暗含的装箱成本。每个 Integer 都必须拆箱成一个原始类型，再进行求和。
        //在这里，Stream API还提供了原始类型流特化，专门支持处理数值流的方法。
        // IntStream, DoubleStream, LongStream,这种流中的元素都是原始数据类型，分别是 int，double，long
        //所以上面代码就可以改成下面所示：
        double sum4 = cars.stream().mapToDouble(Car::getPrice).sum();
        //这里， mapToDouble 会从每个Car对象中提取价格（用一个 Double 表示），并返回一个 DoubleStream（而不是一个 Stream ）。
        //然后你就可以调用 DoubleStream 接口中定义的 sum 方法，对价格求和了！
        //请注意，如果流是空的，sum 默认返回 0 。
        //DoubleStream 还支持其他的方便方法，如max、min、average 等。

        /*数值范围
        IntStream 与 LongStream 拥有 range 和 rangeClosed 方法用于数值范围处理

        IntStream ： rangeClosed(int, int) / range(int, int)
        LongStream ： rangeClosed(long, long) / range(long, long)
        这两个方法的区别在于一个是闭区间，一个是半开半闭区间：

        rangeClosed(1, 100) ：[1, 100]
        range(1, 100) ：[1, 100)
        我们可以利用 IntStream.rangeClosed(1, 100) 生成 1 到 100 的数值流*/

        //求 1 到 10 的数值总和：
        IntStream intStream = IntStream.rangeClosed(1, 10);
        int sum5 = intStream.sum();


        //14. count()
        //返回流中元素个数，结果为 long 类型

        //14. collect()
        ///收集方法，我们很常用的是 collect(toList())，当然还有 collect(toSet()) 等，参数是一个收集器接口，这个后面会另外讲

        //15. forEach()
        //例子：
        list.stream().forEach(System.out::println);
        //循环调用Mapper中的add方法
        //cars.stream().forEach(CarMapper::add);


        //构建流

        //详解collect

        //并行

        //效率


        return cars;
    }
}
