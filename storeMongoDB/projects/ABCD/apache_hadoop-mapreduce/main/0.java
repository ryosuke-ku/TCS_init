  public static <T, E extends Callable<T>> void execute(int nThreads, List<E> callables
      ) throws InterruptedException, ExecutionException {
    final ExecutorService executor = Executors.newFixedThreadPool(nThreads); 
    final List<Future<T>> futures = executor.invokeAll(callables);
    for(Future<T> f : futures)
      f.get();
  }
