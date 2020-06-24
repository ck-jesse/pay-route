package com.coy.pay.route.util.security;

/**
 * 元组类型
 */
public class Tuple {
    /**
     * 二个元素的元组
     *
     * @param <T1> 元组第一个素类型
     * @param <T2> 元组第二个素类型
     */
    public static class Tuple2<T1, T2> {
        private T1 t1;
        private T2 t2;

        public Tuple2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 _1() {
            return t1;
        }

        public T2 _2() {
            return t2;
        }
    }
}
