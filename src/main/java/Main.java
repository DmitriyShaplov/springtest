import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); //кол-во объектов
        int k = scan.nextInt(); //кол-во групп

        List<TaskObject> input = new ArrayList<>();

        //сформировали инпут
        for (int i = 0; i < n; i++) {
            int id = scan.nextInt();
            int m = scan.nextInt();
            Set<Integer> params = new HashSet<>();
            for (int j = 0; j < m; j++) {
                params.add(scan.nextInt());
            }
            input.add(new TaskObject(id, params));
        }
        //получаем множество всех возможных характеристик, чтобы разбить на k групп.
        Set<Integer> manyParams = new HashSet<>();
        input.forEach(obj -> manyParams.addAll(obj.getParams()));


        List<List<List<Integer>>> variants = helper(new ArrayList<>(manyParams), k);

        Variant result = null;
        double kRes = 0;
        for (List<List<Integer>> variant : variants) {

            Variant varAgr = new Variant();
            varAgr.setVariant(variant);

            for (TaskObject taskObject : input) {
                Set<Integer> params = taskObject.getParams();

                double kBig = 0;
                Aggregator aggregator = new Aggregator();
                aggregator.setObjectId(taskObject.getId());

                for (List<Integer> integers : variant) {
                    Set<Integer> paramsTemp = new HashSet<>(params);
                    Set<Integer> integersTemp = new HashSet<>(integers);
                    paramsTemp.retainAll(integersTemp);
                    integersTemp.addAll(params);
                    int i = paramsTemp.size();
                    int u = integersTemp.size();
                    double kBigTemp = i == 0 ? 0 : (double) i / u;
                    if (kBigTemp > kBig) {
                        kBig = kBigTemp;
                        aggregator.setGroup(integers);
                        aggregator.setkBig(kBig);
                    }
                }
                varAgr.getAggregators().add(aggregator);
                varAgr.setkSum(varAgr.getkSum() + kBig);
            }
            if (varAgr.getkSum() > kRes) {
                kRes = varAgr.getkSum();
                result = varAgr;
            }
        }

        result.getAggregators().forEach(
                aggregator -> {
                    StringBuilder stringBuilder = new StringBuilder(String.valueOf(aggregator.getObjectId()));
                    stringBuilder.append(" ");
                    String collect = aggregator.getGroup().stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" "));
                    stringBuilder.append(collect);
                    System.out.println(stringBuilder);
                }
        );
    }

    public static class Variant {
        private List<List<Integer>> variant;
        private List<Aggregator> aggregators = new ArrayList<>();
        private double kSum = 0;

        public void setkSum(double kSum) {
            this.kSum = kSum;
        }

        public double getkSum() {
            return kSum;
        }

        public List<List<Integer>> getVariant() {
            return variant;
        }

        public List<Aggregator> getAggregators() {
            return aggregators;
        }

        public void setVariant(List<List<Integer>> variant) {
            this.variant = variant;
        }

        public void setAggregators(List<Aggregator> aggregators) {
            this.aggregators = aggregators;
        }
    }

    public static class Aggregator {
        private int objectId;
        private List<Integer> group;
        private double kBig;

        public int getObjectId() {
            return objectId;
        }

        public List<Integer> getGroup() {
            return group;
        }

        public double getkBig() {
            return kBig;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public void setGroup(List<Integer> group) {
            this.group = group;
        }

        public void setkBig(double kBig) {
            this.kBig = kBig;
        }
    }

    public static class TaskObject {
        private int id;
        private Set<Integer> params;

        public TaskObject() {
        }

        public TaskObject(int id, Set<Integer> params) {
            this.id = id;
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public Set<Integer> getParams() {
            return params;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setParams(Set<Integer> params) {
            this.params = params;
        }
    }

    private static List<List<List<Integer>>> helper(List<Integer> ori, int m) {
        List<List<List<Integer>>> ret = new ArrayList<>();
        if(ori.size() < m || m < 1) return ret;

        if(m == 1) {
            List<List<Integer>> partition = new ArrayList<>();
            partition.add(new ArrayList<>(ori));
            ret.add(partition);
            return ret;
        }

        // f(n-1, m)
        List<List<List<Integer>>> prev1 = helper(ori.subList(0, ori.size() - 1), m);
        for (List<List<Integer>> lists : prev1) {
            for (int j = 0; j < lists.size(); j++) {
                // Deep copy from prev1.get(i) to l
                List<List<Integer>> l = new ArrayList<>();
                for (List<Integer> inner : lists) {
                    l.add(new ArrayList<>(inner));
                }

                l.get(j).add(ori.get(ori.size() - 1));
                ret.add(l);
            }
        }

        List<Integer> set = new ArrayList<>();
        set.add(ori.get(ori.size() - 1));
        // f(n-1, m-1)
        List<List<List<Integer>>> prev2 = helper(ori.subList(0, ori.size() - 1), m - 1);
        for (List<List<Integer>> lists : prev2) {
            List<List<Integer>> l = new ArrayList<>(lists);
            l.add(set);
            ret.add(l);
        }

        return ret;
    }
}