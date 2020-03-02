# 实现一个简单的Future

 - Future -> get获取执行结果
 - FutureTask -> 传入的执行任务
 - FutureService -> 桥接Future和FutureTask
 
FutureService中创建一个工作线程来执行FutureTask中传入的任务。并返回一个Future
任务没有执行完成，则Future的get一直阻塞，直到拿到result之后，才会返回结果