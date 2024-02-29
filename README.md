# Lesson 15 #
## Задание 1 ##

    По JIT:
Сделать цикл на 100000 итераций, в цикле в предварительно созданную Map<Integer, String> 
сложить ключ - индекс, значение - "value" + индекс

Запустить с опцией -XX:+PrintCompilation, проанализировать информацию в консоли. <br/>
Запустить с опцией -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining , 
проанализировать информацию в консоли

    Реализация:
java -XX:+PrintCompilation  Task1.java <br/>

`135   74       3       java.util.HashMap::putVal (300 bytes)`<br/>
`135   82       3       java.util.HashMap$HashIterator::nextNode (100 bytes)`<br/>
`135   73       3       java.util.HashMap$HashIterator::hasNext (13 bytes)`<br/>
`137   88       4       java.lang.String::hashCode (60 bytes)` <br/>
`***`<br/>
`146  108       4       java.util.HashMap::putVal (300 bytes)` <br/>
`***` <br/>
`155   74       3       java.util.HashMap::putVal (300 bytes)   made not entrant` <br/>

Метод putVal был скомпилирован на уровне 3, затем на уровне уровня 4 он был дополнительно оптимизирован 
и был сделан не входящим для 3-го уровня. Это означает, что он будет заменен кодом с 4-го уровня.


java -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining Task1.java <br />
`@ 23   java.util.HashMap::hash (20 bytes)   inline` <br/>
`@ 25   java.lang.SecurityManager::checkPermission (5 bytes)   no static binding`<br/>
`@ 9   java.util.HashMap::putVal (300 bytes)   callee is too large`<br/>
Показывает встроенные методы.
Сообщение вызываемый объект слишком велик означает, что вызываемый метод слишком велик, чтобы его можно 
было встроить в вызывающий объект.

## Задание 2 ## 
    По GC:
Из %JAVA_HOME%\bin запустить jvisualvm, установить через пункт меню Tools\Plugins\Available Plugis 
плагин: Visual GC. <br/>
Запустить приложение создающее много объектов с разными GC, посмотреть в jvisualvm как заполняются 
объекты в разных областях памяти(heap).

    Реализация:
java -XX:+UseSerialGC Task2.java <br />
serial = 216 collections, 10.5s<br />
![img.png](src/main/resources/img.png)

java -XX:+IgnoreUnrecognizedVMOptions -XX:+UseConcMarkSweepGC Task2.java <br />
ConcMarkSweep = 31 collections, 713.2ms <br />
![img_1.png](src/main/resources/img_1.png)

java -XX:+UseG1GC Task2.java <br />
G1 = 30 collections, 657.9ms <br />
![img_2.png](src/main/resources/img_2.png)

java -XX:+IgnoreUnrecognizedVMOptions -XX:+UseParallelOldGC Task2.java <br />
ParallelOldGC = , 31 collections, 687.1ms <br />
![img_3.png](src/main/resources/img_3.png)

java -XX:+UseParallelGC Task2.java<br />
ParallelGC = 21 collections, 1.253s <br />
![img_4.png](src/main/resources/img_4.png)


