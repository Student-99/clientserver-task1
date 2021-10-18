import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyCallable implements Callable<List<Long>> {
    private long n;

    public MyCallable(String n) {
        this.n = Long.parseLong(n);
    }

    @Override
    public List<Long> call() {
        return Stream.iterate(new Long[]{0L, 1L}, arr -> new Long[]{arr[1], arr[0] + arr[1]})
                .limit(n)
                .map(y -> y[0])
                .collect(Collectors.toList());
    }
}
