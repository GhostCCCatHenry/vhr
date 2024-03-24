package org.javaboy.mailserver.develops;

import java.util.*;
import java.util.concurrent.*;

/**
 * 2000次每分钟; 随时可变因此不可缓存
 * 给定一个批量查询接口queryByIdList（不需要实现，可用空函数代替），一次最多可传入100个id进行批量查询，id为随机内容的字符串，
 * 但只有120次每分钟的次数限制（超出则始终返回失败），每次查询需要花费1秒，返回的结果随时可变因此不可缓存；
 * 要求在这个基础上提供queryById的接口，针对单个id进行查询，该接口能提供至少2000次每分钟的服务能力，
 * 该接口每次查询的耗时不能超过5秒。
 * 提示：1、请保证该接口在只调用一次的情况下，依然能在要求的耗时内返回结果；
 * 2、请考虑2000个请求在一秒内同时进来时该如何处理；
 * 3、1秒内多个调用重复查询同一个id时需要正确返回结果。
 */
public class Test1 {
    private static List<String> cache = new CopyOnWriteArrayList<>();
    private Map<String, QueryTask> map = new ConcurrentHashMap<>();
    private FutureTask<String> future = new FutureTask<>(() -> "");
    private LinkedBlockingQueue<QueryTask> queue = new LinkedBlockingQueue<>();
    private final int MAX_BATCH_NUMS = 100;

    public Test1() {
        Thread thread = new Thread(this::processBatch);
    }

    private void processBatch() {
        while (true) {
            List<QueryTask> tempList = new ArrayList<>();
            queue.drainTo(tempList, MAX_BATCH_NUMS);
            if (!tempList.isEmpty()) {
                List<String> ids = new ArrayList<>();
                for (QueryTask queryTask : tempList) {
                    ids.add(queryTask.id);
                }
                List<Object> results = queryByIdList(ids);
                for (int i = 0; i < tempList.size(); i++) {
                    tempList.get(i).getFuture().complete(results.get(i));
                }
            }
            future.run();
        }
    }

    public static List<Object> queryByIdList(List<String> idList) {
        return Collections.emptyList();
    }

    public Object queryById(String id) throws InterruptedException, ExecutionException {
        QueryTask task = new QueryTask(id);
        queue.offer(task);
        return task.getFuture().get();
    }

    private static class QueryTask {
        private final String id;
        private final CompletableFuture<Object> future = new CompletableFuture<>();

        private QueryTask(String id) {
            this.id = id;
        }

        public CompletableFuture<Object> getFuture() {
            return future;
        }
    }

    public static void main(String[] args) {

        int[][] arr = new int[0][1];
        Arrays.sort(arr, (arr1, arr2) ->
            arr1[0] - arr2[0]
        );
    }
}
